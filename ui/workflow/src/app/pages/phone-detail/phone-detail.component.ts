import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, NgZone, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';

import { Subject, finalize, forkJoin, takeUntil, of } from 'rxjs';
import {
  CallCorrespondentStatsDTO,
  CorrespondentStatsResponseDTO,
  PhoneLogsService,
  PhoneUserDTO,
  PhoneUserMonthlyCaHmDTO
} from '../../api';

@Component({
  selector: 'app-phone-user-detail',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatProgressBarModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule,
  ],
  templateUrl: './phone-detail.component.html',
  styleUrl: './phone-detail.component.scss',
})
export class PhoneDetailComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();

  isLoading = false;
  user: PhoneUserDTO | null = null;

  // ===== Stats (CA/HM mensuel) =====
  statsLoading = false;
  statsCurrent: PhoneUserMonthlyCaHmDTO | null = null;
  statsSeries: PhoneUserMonthlyCaHmDTO[] = []; // 6 derniers mois
  statsError: string | null = null;

  /**
   * ✅ Cumul CA/HM sur toute la période sélectionnée.
   * (On ne touche pas à statsCurrent : il reste le "dernier mois" pour les infos de contexte.)
   */
  get statsTotalCaHm(): number | null {
    if (!this.statsSeries?.length) return null;

    return this.statsSeries.reduce((acc, s) => {
      const n = Number((s as any)?.caHm);
      return acc + (Number.isFinite(n) ? n : 0);
    }, 0);
  }

  rangeStart: Date | null = null;
  rangeEnd: Date | null = null;

  private picking: 'start' | 'end' = 'start';

  readonly months = [
    { value: 1, label: 'Jan' },
    { value: 2, label: 'Fév' },
    { value: 3, label: 'Mar' },
    { value: 4, label: 'Avr' },
    { value: 5, label: 'Mai' },
    { value: 6, label: 'Juin' },
    { value: 7, label: 'Juil' },
    { value: 8, label: 'Août' },
    { value: 9, label: 'Sep' },
    { value: 10, label: 'Oct' },
    { value: 11, label: 'Nov' },
    { value: 12, label: 'Déc' },
  ];

  // ✅ seul ID utilisé partout
  userId: string | null = null;

  // ===== Correspondants (commerciaux SAAMP) =====
  correspondentLoading = false;
  correspondentError: string | null = null;
  correspondentCallSaamp: CallCorrespondentStatsDTO[] = [];

  // ===== Autres users avec le même mysaampIdClient =====
  sameClientLoading = false;
  sameClientError: string | null = null;
  sameClientUsers: PhoneUserDTO[] = [];
  sameClientTotal: number | null = null;
  private sameClientLoadedFor: number | null = null;

  private avatarPalettes = [
    'bg-slate-50 text-slate-700 ring-slate-200',
    'bg-indigo-50 text-indigo-700 ring-indigo-200',
    'bg-emerald-50 text-emerald-700 ring-emerald-200',
    'bg-amber-50 text-amber-800 ring-amber-200',
    'bg-rose-50 text-rose-700 ring-rose-200',
    'bg-cyan-50 text-cyan-700 ring-cyan-200',
    'bg-violet-50 text-violet-700 ring-violet-200',
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private phoneLogsService: PhoneLogsService,
    private snackBar: MatSnackBar,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone
  ) {
    const now = new Date();
    const end = new Date(now.getFullYear(), now.getMonth(), 1);
    const start = new Date(end.getFullYear(), end.getMonth() - 5, 1);

    this.rangeStart = start;
    this.rangeEnd = end;
  }

  ngOnInit(): void {
    this.route.paramMap.pipe(takeUntil(this.destroy$)).subscribe((pm) => {
      this.userId = pm.get('id');
      this.reload();
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  goBack(): void {
    this.router.navigate(['/phone-user']);
  }

  reload(): void {
    if (!this.userId) {
      this.user = null;

      this.statsCurrent = null;
      this.statsSeries = [];
      this.statsError = null;

      this.correspondentCallSaamp = [];
      this.correspondentError = null;

      this.sameClientUsers = [];
      this.sameClientError = null;
      this.sameClientTotal = null;
      this.sameClientLoadedFor = null;

      return;
    }

    this.isLoading = true;
    this.user = null;
    this.cdr.markForCheck();

    // ✅ TON appel existant
    const svc: any = this.phoneLogsService as any;
    const req = svc.getPhoneUsersDetail?.(this.userId);

    if (!req?.pipe) {
      this.isLoading = false;
      this.snackBar.open(`getPhoneUsersDetail(${this.userId}) introuvable côté front.`, 'OK', { duration: 5000 });
      return;
    }

    req
      .pipe(
        finalize(() => {
          this.isLoading = false;
          this.cdr.markForCheck();
        }),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (u: PhoneUserDTO) => {
          this.user = u ?? null;
          this.scheduleUiUpdate();
          this.loadStatsAndCorrespondents();
        },
        error: (err: any) => {
          console.error(err);
          this.user = null;
          this.statsCurrent = null;
          this.statsSeries = [];
          this.statsError = null;
          this.correspondentCallSaamp = [];
          this.correspondentError = null;

          this.sameClientUsers = [];
          this.sameClientError = null;
          this.sameClientTotal = null;
          this.sameClientLoadedFor = null;
        },
      });
  }

  onStatsPeriodChange(): void {
    this.loadStatsAndCorrespondents();
  }

  // ====== CA/HM + Correspondants pour la période sélectionnée ======
  private loadStatsAndCorrespondents(): void {
    if (!this.userId) return;

    this.loadMonthlyCaHm();       // 6 mois
    this.loadCorrespondentStats(); // période mois sélectionné
  }

  // ====== CA/HM (6 mois) ======
  private loadMonthlyCaHm(): void {
    if (!this.userId || !this.rangeStart || !this.rangeEnd) return;

    this.statsLoading = true;
    this.statsError = null;
    this.statsCurrent = null;
    this.statsSeries = [];
    this.cdr.markForCheck();

    const svc: any = this.phoneLogsService as any;

    if (!svc.getPhoneUserStats) {
      this.statsLoading = false;
      this.statsError = 'getPhoneUserStats(phoneUserId, annee, mois) introuvable côté front.';
      this.cdr.markForCheck();
      return;
    }

    const periods = this.monthsInRange(this.rangeStart, this.rangeEnd);

    const calls = periods.map(({ year, month }) => {
      const obs = svc.getPhoneUserStats(this.userId, year, month);
      return obs?.pipe ? obs : of(null);
    });

    forkJoin(calls)
      .pipe(
        finalize(() => {
          this.statsLoading = false;
          this.cdr.markForCheck();
        }),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (arr: Array<PhoneUserMonthlyCaHmDTO | null>) => {
          const cleaned = (arr ?? []).filter((x): x is PhoneUserMonthlyCaHmDTO => !!x);
          this.statsSeries = cleaned;
          this.statsCurrent = cleaned[cleaned.length - 1] ?? null;

          // ✅ charge la liste des autres clients ayant le même mysaampIdClient
          const mysaampIdClient = (this.statsCurrent as any)?.mysaampIdClient as number | null;
          if (typeof mysaampIdClient === 'number') {
            this.loadSameClientUsers(mysaampIdClient);
          } else {
            this.sameClientUsers = [];
            this.sameClientTotal = null;
            this.sameClientError = null;
            this.sameClientLoadedFor = null;
          }

          this.scheduleUiUpdate();
        },
        error: (err: any) => {
          console.error(err);
          this.statsError = 'Impossible de charger les stats.';
        },
      });
  }

  // ====== Autres PhoneUsers par mysaampIdClient ======
  private loadSameClientUsers(mysaampIdClient: number): void {
    // évite de recharger en boucle si rien ne change
    if (this.sameClientLoadedFor === mysaampIdClient) return;
    this.sameClientLoadedFor = mysaampIdClient;

    this.sameClientLoading = true;
    this.sameClientError = null;
    this.sameClientUsers = [];
    this.sameClientTotal = null;
    this.cdr.markForCheck();

    const svc: any = this.phoneLogsService as any;
    const req = svc.getPhoneUsersPageWithClIdent?.(mysaampIdClient, 0, 200, null);

    if (!req?.pipe) {
      this.sameClientLoading = false;
      this.sameClientError = 'getPhoneUsersPageWithClIdent(mysaampIdClient, page, size, q) introuvable côté front.';
      this.cdr.markForCheck();
      return;
    }

    req
      .pipe(
        finalize(() => {
          this.sameClientLoading = false;
          this.cdr.markForCheck();
        }),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (pageDto: any) => {
          const items: PhoneUserDTO[] = (pageDto?.items ?? []) as PhoneUserDTO[];
          const total = pageDto?.meta?.total ?? pageDto?.meta?.totalElements ?? null;

          // filtre l'user courant si présent

          this.sameClientUsers = items;
          this.sameClientTotal = typeof total === 'number' ? total : (items?.length ?? 0);
          this.scheduleUiUpdate();
        },
        error: (err: any) => {
          console.error(err);
          this.sameClientError = 'Impossible de charger les autres users du client.';
          this.sameClientUsers = [];
          this.sameClientTotal = null;
        },
      });
  }

  openPhoneUser(u: PhoneUserDTO): void {
    const id = String((u as any)?.id ?? '').trim();
    if (!id) return;
    this.router.navigate(['/phone-user', id]);
  }

  // ====== Correspondants (mois sélectionné) ======
  private loadCorrespondentStats(): void {
    if (!this.userId || !this.rangeStart || !this.rangeEnd) return;

    this.correspondentLoading = true;
    this.correspondentError = null;
    this.correspondentCallSaamp = [];
    this.cdr.markForCheck();

    const startIso = this.monthStartUtc(this.rangeStart).toISOString();
    const endIso = this.monthEndUtc(this.rangeEnd).toISOString();

    const stats$ = (this.phoneLogsService as any).getCorrespondentStats(
      this.userId,
      startIso,
      endIso
    ) as any;

    if (!stats$?.pipe) {
      this.correspondentLoading = false;
      this.correspondentError = 'getCorrespondentStats(phoneUserId, startIso, endIso) introuvable côté front.';
      this.cdr.markForCheck();
      return;
    }

    stats$
      .pipe(
        finalize(() => {
          this.correspondentLoading = false;
          this.cdr.markForCheck();
        }),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (res: CorrespondentStatsResponseDTO) => {
          const callStats = res?.callStats ?? [];

          // ✅ seulement SAAMP
          const saamp = callStats.filter((c) => this.isSaampCompany(c.company));
          saamp.sort((a, b) => (b.callCount ?? 0) - (a.callCount ?? 0));

          this.correspondentCallSaamp = saamp;
          this.scheduleUiUpdate();
        },
        error: (err: any) => {
          console.error(err);
          this.correspondentError = 'Impossible de charger les correspondants.';
        },
      });
  }

  // ===== Helpers correspondants =====
  private isSaampCompany(company?: string | null): boolean {
    const c = (company ?? '').trim().toLowerCase();
    return c === 'saamp' || c.startsWith('saamp');
  }

  correspondentLabel(c: CallCorrespondentStatsDTO): string {
    const dn = (c.displayName ?? '').trim();
    if (dn) return dn;

    const full = `${c.firstname ?? ''} ${c.lastname ?? ''}`.trim();
    if (full) return full;

    return c.number;
  }

  formatDuration(totalSec?: number | null): string {
    const s = Math.max(0, Math.floor(totalSec ?? 0));
    const h = Math.floor(s / 3600);
    const m = Math.floor((s % 3600) / 60);
    const r = s % 60;

    if (h > 0) return `${h}h ${m}m ${r}s`;
    if (m > 0) return `${m}m ${r}s`;
    return `${r}s`;
  }

  formatMoneyEUR(v?: number | null): string {
    if (v == null || Number.isNaN(v)) return '—';
    return new Intl.NumberFormat('fr-FR', { style: 'currency', currency: 'EUR' }).format(v);
  }

  // ===== Helpers dates/stats =====
  formatCaHm(v?: number | null): string {
    if (v == null || Number.isNaN(v)) return '—';
    return new Intl.NumberFormat('fr-FR', { maximumFractionDigits: 2 }).format(v);
  }

  get sparklinePoints(): string {
    const values = this.statsSeries.map((s) => s.caHm ?? 0);
    if (!values.length) return '';

    const min = Math.min(...values);
    const max = Math.max(...values);
    const range = max - min || 1;

    const w = 100;
    const h = 32;
    const pad = 2;
    const innerW = w - pad * 2;
    const innerH = h - pad * 2;

    return values
      .map((v, i) => {
        const x = pad + (innerW * i) / Math.max(1, values.length - 1);
        const y = pad + innerH - ((v - min) / range) * innerH;
        return `${x.toFixed(2)},${y.toFixed(2)}`;
      })
      .join(' ');
  }

  // ===== UI helpers user =====
  displayName(u: PhoneUserDTO): string {
    const full = `${(u as any).firstname ?? ''} ${(u as any).lastname ?? ''}`.trim();
    return full.length ? full : '—';
  }

  initials(u: PhoneUserDTO): string {
    const f = String((u as any).firstname ?? '').trim();
    const l = String((u as any).lastname ?? '').trim();
    const a = f ? f[0] : '';
    const b = l ? l[0] : '';
    const res = `${a}${b}`.toUpperCase().trim();
    if (res) return res;
    const dn = this.displayName(u);
    return dn !== '—' ? dn.slice(0, 1).toUpperCase() : '?';
  }

  avatarClass(u: PhoneUserDTO): string {
    const seed =
      String((u as any)?.id ?? '') + '|' + ((u as any).firstname ?? '') + '|' + ((u as any).lastname ?? '');
    const idx = this.hashString(seed) % this.avatarPalettes.length;
    return this.avatarPalettes[idx];
  }

  phones(u: PhoneUserDTO): { label: string; value: string }[] {
    const res: { label: string; value: string }[] = [];
    const seen = new Set<string>();

    const add = (label: string, raw: unknown) => {
      const value = this.extractPhoneValue(raw);
      if (!value) return;

      const key = this.normalizePhone(value);
      if (seen.has(key)) return;

      seen.add(key);
      res.push({ label, value });
    };

    add('Pro', u.phonePro?.phoneNumber);
    add('Perso', u.phonePerso?.phoneNumber);
    add('Telavox', u.phoneTelavox?.phoneNumber);
    add('Other', u.phoneOther?.phoneNumber);

    return res;
  }

  async copyPhone(v: string): Promise<void> {
    const value = (v ?? '').trim();
    if (!value) return;

    try {
      if (navigator?.clipboard?.writeText) {
        await navigator.clipboard.writeText(value);
      } else {
        const ta = document.createElement('textarea');
        ta.value = value;
        ta.style.position = 'fixed';
        ta.style.opacity = '0';
        document.body.appendChild(ta);
        ta.focus();
        ta.select();
        document.execCommand('copy');
        document.body.removeChild(ta);
      }
      this.snackBar.open('Numéro copié', 'OK', { duration: 1500 });
    } catch {
      this.snackBar.open('Impossible de copier le numéro', 'OK', { duration: 2500 });
    }
  }

  async copyAsJson(): Promise<void> {
    if (!this.user) return;
    try {
      const json = JSON.stringify(this.user, null, 2);
      if (navigator?.clipboard?.writeText) {
        await navigator.clipboard.writeText(json);
      }
      this.snackBar.open('JSON copié', 'OK', { duration: 1500 });
    } catch {
      this.snackBar.open('Impossible de copier le JSON', 'OK', { duration: 2500 });
    }
  }

  private extractPhoneValue(raw: unknown): string | null {
    if (raw == null) return null;
    if (typeof raw === 'string') return raw.trim() || null;
    if (typeof raw === 'number') return String(raw);
    return null;
  }

  private normalizePhone(v: string): string {
    const trimmed = (v ?? '').trim();
    if (!trimmed) return '';
    const keep = trimmed.replace(/[^\d+]/g, '');
    return keep || trimmed;
  }

  private hashString(s: string): number {
    let h = 0;
    for (let i = 0; i < s.length; i++) {
      h = (h << 5) - h + s.charCodeAt(i);
      h |= 0;
    }
    return Math.abs(h);
  }

  private scheduleUiUpdate(): void {
    this.ngZone.run(() => {
      this.cdr.detectChanges();
    });
  }

  get rangeLabel(): string {
    if (!this.rangeStart && !this.rangeEnd) return '';
    if (this.rangeStart && !this.rangeEnd) return `${this.monthLabel(this.rangeStart)} → …`;
    return `${this.monthLabel(this.rangeStart!)} → ${this.monthLabel(this.rangeEnd!)}`;
  }

  monthLabel(d: Date): string {
    return new Intl.DateTimeFormat('fr-FR', { month: '2-digit', year: 'numeric' }).format(d);
  }

  private monthStartUtc(d: Date): Date {
    return new Date(Date.UTC(d.getFullYear(), d.getMonth(), 1, 0, 0, 0, 0));
  }

  private monthEndUtc(d: Date): Date {
    const next = new Date(Date.UTC(d.getFullYear(), d.getMonth() + 1, 1, 0, 0, 0, 0));
    return new Date(next.getTime() - 1);
  }

  pickRangeMonth(chosenMonth: Date, picker: any): void {
    const picked = new Date(chosenMonth.getFullYear(), chosenMonth.getMonth(), 1);

    if (this.picking === 'start') {
      this.rangeStart = picked;
      this.rangeEnd = null;
      this.picking = 'end';

      // on ferme + ré-ouvre pour forcer le “2e choix” sans jours
      picker?.close?.();
      setTimeout(() => picker?.open?.(), 0);

      this.cdr.markForCheck();
      return;
    }

    // picking === 'end'
    this.rangeEnd = picked;

    // si end < start => swap
    if (this.rangeStart && this.rangeEnd.getTime() < this.rangeStart.getTime()) {
      const tmp = this.rangeStart;
      this.rangeStart = this.rangeEnd;
      this.rangeEnd = tmp;
    }

    this.picking = 'start';
    picker?.close?.();

    // reload uniquement si plage complète
    if (this.rangeStart && this.rangeEnd) {
      this.loadStatsAndCorrespondents();
    }

    this.cdr.markForCheck();
  }

  private monthsInRange(start: Date, end: Date): Array<{ year: number; month: number }> {
    const res: Array<{ year: number; month: number }> = [];
    const s = new Date(start.getFullYear(), start.getMonth(), 1);
    const e = new Date(end.getFullYear(), end.getMonth(), 1);

    let y = s.getFullYear();
    let m = s.getMonth(); // 0..11
    const endKey = e.getFullYear() * 12 + e.getMonth();

    while (y * 12 + m <= endKey) {
      res.push({ year: y, month: m + 1 }); // 1..12
      m++;
      if (m > 11) {
        m = 0;
        y++;
      }
    }
    return res;
  }
}
