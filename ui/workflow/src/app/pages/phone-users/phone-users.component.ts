import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, NgZone, OnDestroy, OnInit } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

import { Subject, debounceTime, distinctUntilChanged, finalize, takeUntil } from 'rxjs';
import { PhoneLogsService, PhoneUserDTO, PhoneUserPageDTO } from '../../api';

@Component({
  selector: 'app-phone-users',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatButtonModule,
    MatSnackBarModule,
  ],
  templateUrl: './phone-users.component.html',
  styleUrl: './phone-users.component.scss',
})
export class PhoneUsersComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();

  // Recherche
  searchCtrl = new FormControl<string>('', { nonNullable: true });

  // Data
  users: PhoneUserDTO[] = [];

  // Table
  displayedColumns = ['identity', 'denomination', 'company', 'phones', 'actions'];

  // Pagination
  pageSize = 25;
  pageIndex = 0; // 0-based
  totalElements = 0;

  // Loading
  isLoading = false;

  // Petites palettes Tailwind pour avatars (déterministe par user)
  private avatarPalettes = [
    'bg-amber-50 text-amber-800 ring-amber-200',
  ];

  constructor(
    private phoneLogsService: PhoneLogsService,
    private snackBar: MatSnackBar,
    private cdr: ChangeDetectorRef,
    private ngZone: NgZone,
    private router: Router
  ) {}

  ngOnInit(): void {
    // load initial
    this.loadPage(true);

    // search debounce
    this.searchCtrl.valueChanges
      .pipe(debounceTime(300), distinctUntilChanged(), takeUntil(this.destroy$))
      .subscribe(() => {
        this.pageIndex = 0;
        this.loadPage(true);
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  onPage(e: PageEvent): void {
    this.pageIndex = e.pageIndex;
    this.pageSize = e.pageSize;
    this.loadPage(false);
  }

  clearSearch(): void {
    this.searchCtrl.setValue('');
  }

  goToDetail(u: PhoneUserDTO): void {
    this.router.navigate(['/phone-user', u.id]);
  }

  displayName(u: PhoneUserDTO): string {
    const full = `${u.firstname ?? ''} ${u.lastname ?? ''}`.trim();
    return full.length ? full : '—';
  }

  initials(u: PhoneUserDTO): string {
    const f = (u.firstname ?? '').trim();
    const l = (u.lastname ?? '').trim();

    const a = f ? f[0] : '';
    const b = l ? l[0] : '';

    const res = `${a}${b}`.toUpperCase().trim();
    if (res) return res;

    const dn = this.displayName(u);
    return dn !== '—' ? dn.slice(0, 1).toUpperCase() : '?';
  }

  avatarClass(u: PhoneUserDTO): string {
    const seed = String((u as any)?.id ?? '') + '|' + (u.firstname ?? '') + '|' + (u.lastname ?? '');
    const idx = this.hashString(seed) % this.avatarPalettes.length;
    return this.avatarPalettes[idx];
  }

  async copyPhone(v: string): Promise<void> {
    const value = (v ?? '').trim();
    if (!value) return;

    try {
      if (navigator?.clipboard?.writeText) {
        await navigator.clipboard.writeText(value);
      } else {
        // fallback (anciennes permissions / vieux navigateurs)
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

  /**
   * Affiche tous les numéros dispo sans présumer de la forme exacte du DTO.
   * - Supporte phonePro/phonePerso/phoneTelavox/phoneOther
   * - Supporte des tableaux: phones / phoneContacts / contacts / phoneNumbers
   * - Déduplique les numéros (normalisation digits)
   */
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

    const anyU = u as any;

    // Champs "classiques"
    add('Pro', anyU.phonePro);
    add('Perso', anyU.phonePerso);
    add('Telavox', anyU.phoneTelavox);

    // Souvent présent en DB / modèle
    add('Autre', anyU.phoneOther);

    // Variantes possibles
    add('Pro', anyU.proPhone);
    add('Perso', anyU.persoPhone);
    add('Telavox', anyU.telavoxPhone);

    // Tableaux possibles
    const arrays: Array<{ key: string; defaultLabel: string }> = [
      { key: 'phones', defaultLabel: 'Tel' },
      { key: 'phoneContacts', defaultLabel: 'Tel' },
      { key: 'contacts', defaultLabel: 'Tel' },
      { key: 'phoneNumbers', defaultLabel: 'Tel' },
    ];

    for (const a of arrays) {
      const arr = anyU[a.key];
      if (!Array.isArray(arr)) continue;

      for (const item of arr) {
        const label =
          (typeof item === 'object' && item
            ? (item.label ?? item.type ?? item.kind ?? item.source ?? a.defaultLabel)
            : a.defaultLabel) ?? a.defaultLabel;

        add(String(label), item);
      }
    }

    return res;
  }

  loadPage(resetList: boolean): void {
    const q = this.trimOrNull(this.searchCtrl.value);

    this.isLoading = true;
    this.cdr.markForCheck();

    const req$ = (this.phoneLogsService as any).getPhoneUsersPage({
      page: this.pageIndex,
      size: this.pageSize,
      q,
    }) as any;

    req$
      .pipe(
        finalize(() => {
          this.isLoading = false;
          this.cdr.markForCheck();
        }),
        takeUntil(this.destroy$)
      )
      .subscribe({
        next: (page: PhoneUserPageDTO) => {
          this.users = page?.items ?? [];
          this.totalElements = page?.meta.totalElements ?? 0;

          this.pageIndex = (page as any)?.number ?? this.pageIndex;
          this.pageSize = (page as any)?.size ?? this.pageSize;

          this.scheduleUiUpdate();
        },
        error: (err: any) => {
          console.error(err);
        },
      });
  }

  private trimOrNull(v: string): string | null {
    const t = (v ?? '').trim();
    return t.length ? t : null;
  }

  private scheduleUiUpdate(): void {
    this.ngZone.run(() => {
      this.cdr.detectChanges();
    });
  }

  private extractPhoneValue(raw: unknown): string | null {
    if (raw == null) return null;

    // string direct
    if (typeof raw === 'string') return raw.trim() || null;

    // number direct
    if (typeof raw === 'number') return String(raw);

    // objet type PhoneContact
    if (typeof raw === 'object') {
      const anyRaw = raw as any;
      const v =
        anyRaw.phoneNumber ??
        anyRaw.number ??
        anyRaw.value ??
        anyRaw.phone ??
        anyRaw.msisdn ??
        anyRaw.e164;

      if (typeof v === 'string') return v.trim() || null;
      if (typeof v === 'number') return String(v);
    }

    return null;
  }

  private normalizePhone(v: string): string {
    // normalisation simple pour dédupliquer : garde chiffres + +
    const trimmed = (v ?? '').trim();
    if (!trimmed) return '';
    const keep = trimmed.replace(/[^\d+]/g, '');
    return keep || trimmed;
  }

  private hashString(s: string): number {
    // hash simple (stable) -> int positif
    let h = 0;
    for (let i = 0; i < s.length; i++) {
      h = (h << 5) - h + s.charCodeAt(i);
      h |= 0; // 32-bit
    }
    return Math.abs(h);
  }
}
