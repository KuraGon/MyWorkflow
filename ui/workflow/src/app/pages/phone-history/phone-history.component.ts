import { CommonModule } from '@angular/common';
import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  NgZone,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTabsModule } from '@angular/material/tabs';
import {
  MatDatepickerModule,
  MatDateRangeInput,
  MatDateRangePicker,
  MatStartDate,
  MatEndDate,
} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

import { Observable, Subject, Subscription, forkJoin, filter } from 'rxjs';
import { debounceTime, finalize, takeUntil } from 'rxjs/operators';

import { Router } from '@angular/router';
import { UUID } from 'node:crypto';
import {
  CallCorrespondentStatsDTO,
  CorrespondentStatsResponseDTO,
  PhoneLogDTO,
  PhoneLogPageDTO,
  PhoneLogsService,
  PhoneUserDTO,
  PhoneUserLiteDTO,
  SalesRepHourBucketDTO,
  SalesRepStatsDTO,
  SmsCorrespondentStatsDTO,
} from '../../api';

type Sparkline = { w: number; h: number; points: string; min: number; max: number };
type PieSlice = { key: 'client' | 'internal' | 'unknown'; label: string; pct: number; path: string };

@Component({
  selector: 'app-phone-history',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,

    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatTableModule,
    MatIconModule,
    MatSnackBarModule,
    MatButtonModule,
    MatTooltipModule,
    MatTabsModule,

    MatDatepickerModule,
    MatNativeDateModule,
    MatStartDate,
    MatEndDate,
  ],
  templateUrl: './phone-history.component.html',
  styleUrls: ['./phone-history.component.scss'],
})
export class PhoneHistoryComponent implements OnInit, AfterViewInit, OnDestroy {
  private readonly destroy$ = new Subject<void>();
  private refreshSub: Subscription | null = null;
  private refreshToken = 0;
  private destroyed = false;

  forfaitContacts: PhoneUserDTO[] = [];
  selectedUserId: string | null = null;

  // date range
  dateRange = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  startDate: Date | null = null;
  endDate: Date | null = null;

  @ViewChild('rangePicker') rangePicker!: MatDateRangePicker<Date>;
  @ViewChild('rangeInput') rangeInput!: MatDateRangeInput<Date>;

  // data
  paginatedLogs: PhoneLogDTO[] = [];
  callStats: CallCorrespondentStatsDTO[] = [];
  smsStats: SmsCorrespondentStatsDTO[] = [];
  salesRepStats: SalesRepStatsDTO | null = null;

  // pagination
  pageSize = 15;
  pageIndex = 0;
  totalPages = 1;
  totalElements = 0;
  hasNext = false;
  hasPrev = false;

  // loading
  isLoadingForfaits = false;
  isLoadingLogs = false;
  isLoadingStats = false;
  isLoadingSalesRep = false;

  displayedColumns: string[] = ['date', 'direction', 'caller', 'destination', 'duration', 'cost'];

  constructor(
    private phoneLogsService: PhoneLogsService,
    private snackBar: MatSnackBar,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadForfaitContacts();

    // refresh auto sur changement de période (debounce)
    this.dateRange.valueChanges
      .pipe(
        debounceTime(200),
        filter(({ start, end }) => !!start && !!end),
        takeUntil(this.destroy$)
      )
      .subscribe(({ start, end }) => {
        const nextStart = start!;
        const nextEnd = end!;

        if (!this.selectedUserId) {
          this.startDate = nextStart;
          this.endDate = nextEnd;
          return;
        }

        this.scheduleUiUpdate(() => {
          this.startDate = nextStart;
          this.endDate = nextEnd;
          this.refreshAll(true);
        });
      });
  }

  ngAfterViewInit(): void {
    this.cdr.detectChanges();
  }

  ngOnDestroy(): void {
    this.destroyed = true;
    this.refreshSub?.unsubscribe();
    this.destroy$.next();
    this.destroy$.complete();
  }

  private scheduleUiUpdate(fn: () => void): void {
    setTimeout(() => {
      if (this.destroyed) return;
      this.ngZone.run(() => {
        fn();
        this.cdr.detectChanges();
      });
    }, 0);
  }

  // ---------- UI helpers ----------

  formatForfaitLabel(contact: PhoneUserDTO): string {
    const firstname = (contact as any).firstname ?? '';
    const lastname = (contact as any).lastname ?? '';
    const denomination = contact.denomination ?? '';
    if (lastname && firstname) return `${lastname} ${firstname}`;
    if (denomination) return `${denomination}`;
    return (
      contact.phonePro?.phoneNumber ||
      contact.phonePerso?.phoneNumber ||
      contact.phoneTelavox?.phoneNumber ||
      'Ligne inconnue'
    );
  }

  formatForfaitLabelLite(contact: PhoneUserLiteDTO): string {
    const firstname = (contact as any)?.firstname ?? '';
    const lastname = (contact as any)?.lastname ?? '';
    const denomination = contact?.denomination ?? '';
    if (lastname && firstname) return `${lastname} ${firstname}`;
    if (denomination) return `${denomination}`;
    return contact?.phone?.phoneNumber || 'Ligne inconnue';
  }

  formatDuration(seconds?: number | null): string {
    if (seconds == null) return '—';
    const s = Number(seconds);
    const m = Math.floor(s / 60);
    const r = s % 60;
    return m === 0 ? `${r}s` : `${m}m ${r}s`;
  }

  formatCost(cost?: number | null): string {
    if (cost == null) return '—';
    return `${Number(cost).toFixed(2)} €`;
  }

  // ---------- SalesRep activity (tri) ----------

  get salesRepActivityByHourSorted(): Array<{ hour: number; calls: number; sms: number; total: number }> {
    const buckets: SalesRepHourBucketDTO[] = (this.salesRepStats?.activityByHour ?? []) as SalesRepHourBucketDTO[];

    return (buckets ?? [])
      .map((b: any) => {
        const hour = Number(b.hour ?? 0);
        const calls = Number(b.callCount ?? 0);
        const sms = Number(b.smsCount ?? 0);

        const totalFromDto = b.total != null ? Number(b.total) : null;
        const totalFromCount = b.count != null ? Number(b.count) : null;
        const total = totalFromDto ?? totalFromCount ?? calls + sms;

        return { hour, calls, sms, total };
      })
      .sort((a, b) => a.hour - b.hour);
  }

  /**
   * Normalize to always 24 hours (0..23).
   */
  private get salesRepActivityByHour24h(): Array<{ hour: number; calls: number; sms: number; total: number }> {
    const byHour = new Map<number, { hour: number; calls: number; sms: number; total: number }>();
    for (const b of this.salesRepActivityByHourSorted) byHour.set(b.hour, b);

    const result: Array<{ hour: number; calls: number; sms: number; total: number }> = [];
    for (let h = 0; h < 24; h++) result.push(byHour.get(h) ?? { hour: h, calls: 0, sms: 0, total: 0 });
    return result;
  }

  // ---------- Activity chart (SVG) ----------

  readonly activityChartViewBox = { w: 640, h: 240 };
  readonly activityChartPadding = { l: 44, r: 16, t: 16, b: 34 };

  get activityChartMax(): number {
    const data = this.salesRepActivityByHour24h;
    const max = Math.max(
      1,
      ...data.map((d) => d.calls),
      ...data.map((d) => d.sms),
      ...data.map((d) => d.total)
    );
    return max;
  }

  get activityChartPoints(): Array<{
    hour: number;
    calls: number;
    sms: number;
    total: number;
    x: number;
    yCalls: number;
    ySms: number;
  }> {
    const vb = this.activityChartViewBox;
    const pad = this.activityChartPadding;
    const data = this.salesRepActivityByHour24h;
    const max = this.activityChartMax;

    const innerW = vb.w - pad.l - pad.r;
    const innerH = vb.h - pad.t - pad.b;
    const stepX = innerW / (data.length - 1);

    const y = (value: number) => pad.t + innerH * (1 - value / max);

    return data.map((d, idx) => {
      const x = pad.l + stepX * idx;
      return {
        hour: d.hour,
        calls: d.calls,
        sms: d.sms,
        total: d.total,
        x,
        yCalls: y(d.calls),
        ySms: y(d.sms),
      };
    });
  }

  get activityChartCallsPolyline(): string {
    return this.activityChartPoints.map((p) => `${p.x},${p.yCalls}`).join(' ');
  }
  get activityChartSmsPolyline(): string {
    return this.activityChartPoints.map((p) => `${p.x},${p.ySms}`).join(' ');
  }

  private buildAreaPath(points: Array<{ x: number; y: number }>): string {
    const vb = this.activityChartViewBox;
    const pad = this.activityChartPadding;
    const baselineY = vb.h - pad.b;
    if (points.length === 0) return '';

    const start = points[0];
    const end = points[points.length - 1];
    const line = points.map((p, i) => `${i === 0 ? 'M' : 'L'} ${p.x} ${p.y}`).join(' ');
    return `${line} L ${end.x} ${baselineY} L ${start.x} ${baselineY} Z`;
  }

  get activityChartCallsAreaPath(): string {
    return this.buildAreaPath(this.activityChartPoints.map((p) => ({ x: p.x, y: p.yCalls })));
  }
  get activityChartSmsAreaPath(): string {
    return this.buildAreaPath(this.activityChartPoints.map((p) => ({ x: p.x, y: p.ySms })));
  }

  get activityChartXTicks(): Array<{ x: number; label: string }> {
    const points = this.activityChartPoints;
    return points.filter((p) => p.hour % 3 === 0).map((p) => ({ x: p.x, label: `${p.hour}h` }));
  }

  get activityChartYTicks(): Array<{ y: number; label: string }> {
    const vb = this.activityChartViewBox;
    const pad = this.activityChartPadding;
    const max = this.activityChartMax;
    const innerH = vb.h - pad.t - pad.b;

    const steps = 3;
    const ticks: Array<{ y: number; label: string }> = [];
    for (let i = 0; i <= steps; i++) {
      const value = Math.round((max * i) / steps);
      const y = pad.t + innerH * (1 - i / steps);
      ticks.push({ y, label: String(value) });
    }
    return ticks;
  }

  // ---------- KPI Sparklines (micro graph) ----------

  private buildSparkline(values: number[], w = 64, h = 20, pad = 2): Sparkline {
    const safe = values.length ? values : [0];
    const min = Math.min(...safe);
    const max = Math.max(...safe, 1);

    const innerW = w - pad * 2;
    const innerH = h - pad * 2;
    const stepX = safe.length > 1 ? innerW / (safe.length - 1) : 0;

    const y = (v: number) => pad + innerH * (1 - (v - min) / (max - min || 1));
    const pts = safe
      .map((v, i) => `${pad + i * stepX},${y(v)}`)
      .join(' ');

    return { w, h, points: pts, min, max };
  }

  /**
   * Trend calls/hour (sparkline) used for "Nombre d'appels"
   */
  get kpiSparkCalls(): Sparkline {
    const values = this.salesRepActivityByHour24h.map((d) => d.calls);
    return this.buildSparkline(values, 72, 22, 2);
  }

  /**
   * Trend total/hour (sparkline) used as a proxy for "Temps d’appel cumulé"
   * (we don't have duration/hour in DTO; total activity is the best proxy without changing backend)
   */
  get kpiSparkTotal(): Sparkline {
    const values = this.salesRepActivityByHour24h.map((d) => d.total);
    return this.buildSparkline(values, 72, 22, 2);
  }

  // ---------- Pie chart (ratio) ----------

  get hasRatioData(): boolean {
    const r: any = (this.salesRepStats as any)?.kpi?.ratio;
    if (!r) return false;
    const a = Number(r.clientPct ?? 0);
    const b = Number(r.internalPct ?? 0);
    const c = Number(r.unknownPct ?? 0);
    return a + b + c > 0;
  }

  private polar(cx: number, cy: number, r: number, angleDeg: number): { x: number; y: number } {
    const a = ((angleDeg - 90) * Math.PI) / 180;
    return { x: cx + r * Math.cos(a), y: cy + r * Math.sin(a) };
  }

  private arcPath(cx: number, cy: number, r: number, startAngle: number, endAngle: number): string {
    const start = this.polar(cx, cy, r, endAngle);
    const end = this.polar(cx, cy, r, startAngle);
    const largeArc = endAngle - startAngle <= 180 ? '0' : '1';
    return `M ${cx} ${cy} L ${start.x} ${start.y} A ${r} ${r} 0 ${largeArc} 0 ${end.x} ${end.y} Z`;
  }

  get ratioSlices(): PieSlice[] {
    const r: any = (this.salesRepStats as any)?.kpi?.ratio ?? {};
    // normalize to 100-ish (some backends round)
    const client = Math.max(0, Number(r.clientPct ?? 0));
    const internal = Math.max(0, Number(r.internalPct ?? 0));
    const unknown = Math.max(0, Number(r.unknownPct ?? 0));
    const sum = client + internal + unknown || 1;

    const cx = 60;
    const cy = 60;
    const radius = 48;

    const parts: Array<{ key: PieSlice['key']; label: string; pct: number }> = [
      { key: 'client', label: 'Clients', pct: (client / sum) * 100 },
      { key: 'internal', label: 'Interne', pct: (internal / sum) * 100 },
      { key: 'unknown', label: 'Inconnu', pct: (unknown / sum) * 100 },
    ];

    let cursor = 0; // degrees 0..360
    return parts
      .filter((p) => p.pct > 0.01)
      .map((p) => {
        const start = cursor;
        const sweep = (p.pct / 100) * 360;
        const end = cursor + sweep;
        cursor = end;
        return {
          key: p.key,
          label: p.label,
          pct: Math.round(p.pct),
          path: this.arcPath(cx, cy, radius, start, end),
        };
      });
  }

  // ---------- actions ----------

  onForfaitChange(userId: string | null): void {
    this.selectedUserId = userId;
    this.clearData();
    if (!userId) return;
    this.refreshAll(true);
  }

  onResetDateRange(): void {
    this.dateRange.reset({ start: null, end: null }, { emitEvent: false });
    this.startDate = null;
    this.endDate = null;

    this.rangePicker?.close();

    if (!this.selectedUserId) return;

    this.clearData();
    this.refreshAll(true);
  }

  changePage(delta: number): void {
    if (!this.selectedUserId) return;

    const lastIndex = Math.max(0, this.totalPages - 1);
    const next = this.pageIndex + delta;
    if (next < 0 || next > lastIndex) return;

    this.fetchLogsPage(next);
  }

  get canGoPrev(): boolean {
    return this.hasPrev;
  }
  get canGoNext(): boolean {
    return this.hasNext;
  }

  // ---------- data load ----------

  private loadForfaitContacts(): void {
    this.isLoadingForfaits = true;

    this.phoneLogsService
      .getPhoneContacts()
      .pipe(finalize(() => (this.isLoadingForfaits = false)))
      .subscribe({
        next: (contacts: PhoneUserDTO[]) => {
          this.scheduleUiUpdate(() => {
            this.forfaitContacts = contacts ?? [];
          });
        },
        error: () => {
          this.scheduleUiUpdate(() => {
            this.forfaitContacts = [];
          });
        },
      });
  }

  private refreshAll(resetToFirstPage: boolean): void {
    if (!this.selectedUserId) return;
    this.clearData();
    const token = ++this.refreshToken;

    this.refreshSub?.unsubscribe();
    this.refreshSub = null;

    if (resetToFirstPage) this.pageIndex = 0;

    const { startIso, endIso } = this.buildDateParams();

    this.isLoadingLogs = true;
    this.isLoadingStats = true;
    this.isLoadingSalesRep = true;

    const logs$ = (this.phoneLogsService as any).getPhoneLogsPage(
      this.selectedUserId,
      startIso,
      endIso,
      this.pageIndex,
      this.pageSize
    ) as Observable<PhoneLogPageDTO>;

    const stats$ = (this.phoneLogsService as any).getCorrespondentStats(
      this.selectedUserId,
      startIso,
      endIso
    ) as Observable<CorrespondentStatsResponseDTO>;

    const salesRep$ = (this.phoneLogsService as any).getSalesRepStats(
      this.selectedUserId,
      startIso,
      endIso
    ) as Observable<SalesRepStatsDTO>;

    const combined$ = forkJoin({ logs: logs$, stats: stats$, salesRep: salesRep$ }) as Observable<{
      logs: PhoneLogPageDTO;
      stats: CorrespondentStatsResponseDTO;
      salesRep: SalesRepStatsDTO;
    }>;

    this.refreshSub = combined$
      .pipe(
        finalize(() => {
          if (token === this.refreshToken) {
            this.scheduleUiUpdate(() => {
              this.isLoadingLogs = false;
              this.isLoadingStats = false;
              this.isLoadingSalesRep = false;
            });
          }
        })
      )
      .subscribe({
        next: ({ logs, stats, salesRep }) => {
          if (token !== this.refreshToken) return;
          this.scheduleUiUpdate(() => {
            this.applyPage(logs);
            this.callStats = (stats?.callStats ?? []) as CallCorrespondentStatsDTO[];
            this.smsStats = (stats?.smsStats ?? []) as SmsCorrespondentStatsDTO[];
            this.salesRepStats = salesRep ?? null;
          });
        },
        error: () => {
          if (token !== this.refreshToken) return;

          this.scheduleUiUpdate(() => {
            this.clearData();
          });

          this.snackBar.open('Erreur lors du chargement (logs/stats/commercial)', 'Fermer', {
            duration: 4000,
          });
        },
      });
  }

  private fetchLogsPage(page: number): void {
    if (!this.selectedUserId) return;

    const token = ++this.refreshToken;
    const { startIso, endIso } = this.buildDateParams();

    this.isLoadingLogs = true;

    const logs$ = (this.phoneLogsService as any).getPhoneLogsPage(
      this.selectedUserId,
      startIso,
      endIso,
      page,
      this.pageSize
    ) as Observable<PhoneLogPageDTO>;

    logs$
      .pipe(
        finalize(() => {
          if (token === this.refreshToken) {
            this.scheduleUiUpdate(() => {
              this.isLoadingLogs = false;
            });
          }
        })
      )
      .subscribe({
        next: (res: PhoneLogPageDTO) => {
          if (token !== this.refreshToken) return;
          this.scheduleUiUpdate(() => {
            this.applyPage(res);
          });
        },
        error: () => {
          if (token !== this.refreshToken) return;

          this.scheduleUiUpdate(() => {
            this.isLoadingLogs = false;
          });

          this.snackBar.open('Erreur lors du chargement de la page', 'Fermer', { duration: 4000 });
        },
      });
  }

  private buildDateParams(): { startIso?: string; endIso?: string } {
    return {
      startIso: this.startDate ? this.startDate.toISOString() : undefined,
      endIso: this.endDate ? this.endDate.toISOString() : undefined,
    };
  }

  private applyPage(res: PhoneLogPageDTO): void {
    const meta: any = (res as any).meta;
    const items: any = (res as any).items;

    const rawPageIndex = Number(meta?.page ?? 0);
    const rawPageSize = Number(meta?.size ?? this.pageSize);
    const rawTotalElements = Number(meta?.totalElements ?? 0);
    const rawTotalPages = Number(meta?.totalPages ?? 1);

    this.pageSize = Number.isFinite(rawPageSize) && rawPageSize > 0 ? rawPageSize : this.pageSize;
    this.totalElements = Number.isFinite(rawTotalElements) && rawTotalElements > 0 ? rawTotalElements : 0;
    this.totalPages = Number.isFinite(rawTotalPages) && rawTotalPages > 0 ? rawTotalPages : 1;

    const lastIndex = Math.max(0, this.totalPages - 1);
    this.pageIndex = Number.isFinite(rawPageIndex) ? Math.min(Math.max(rawPageIndex, 0), lastIndex) : 0;

    this.hasPrev = this.pageIndex > 0;
    this.hasNext = this.pageIndex < lastIndex;

    this.paginatedLogs = (items ?? []) as PhoneLogDTO[];
  }

  private clearData(): void {
    this.paginatedLogs = [];

    this.pageIndex = 0;
    this.totalPages = 1;
    this.totalElements = 0;
    this.hasNext = false;
    this.hasPrev = false;

    this.callStats = [];
    this.smsStats = [];
    this.salesRepStats = null;
  }

  protected toDetail(row: PhoneUserLiteDTO) {
    this.router.navigate(['phone-user', row.id]);
  }
}
