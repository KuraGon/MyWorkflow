import { Inject, Injectable, PLATFORM_ID, signal, computed } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { Router } from '@angular/router';
import { UserDTO } from '../api';

type TtlEnvelope<T> = { v: T; exp: number }; // exp = epoch ms

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly AUTH_KEY = 'basic_auth_header';
  private readonly USER_KEY = 'current_user';
  private readonly isBrowser: boolean;

  /**
   * ✅ TTL pour le mode "remember = false" (multi-onglets mais temporaire)
   */
  private readonly TEMP_SESSION_TTL_MS = 8 * 60 * 60 * 1000; // 8 heures

  private readonly _authHeader = signal<string | null>(null);
  private readonly _user = signal<UserDTO | null>(null);

  readonly isLoggedIn = computed(() => !!this._authHeader());
  readonly user = computed(() => this._user());
  readonly fullName = computed(() => {
    const u = this._user();
    if (!u) return null;
    return `${u.firstName ?? ''} ${u.lastName ?? ''}`.trim() || u.username;
  });

  constructor(
    private http: HttpClient,
    private router: Router,
    @Inject(PLATFORM_ID) platformId: object
  ) {
    this.isBrowser = isPlatformBrowser(platformId);

    if (this.isBrowser) {
      this.restoreFromStorage();

      // sync multi-onglets (localStorage uniquement)
      window.addEventListener('storage', (e) => {
        if (e.key === this.AUTH_KEY) {
          const header = this.readAuthHeaderFromLocalStorage();
          this._authHeader.set(header);
        }
        if (e.key === this.USER_KEY) {
          const user = this.readUserFromLocalStorage();
          this._user.set(user);
        }
      });
    }
  }

  getAuthHeader(): string | null {
    return this._authHeader();
  }

  getUser(): UserDTO | null {
    return this._user();
  }

  private basicAuthHeader(username: string, password: string): string {
    return `Basic ${btoa(`${username}:${password}`)}`.trim();
  }

  // ---------------------------
  // Storage helpers (TTL + legacy safe)
  // ---------------------------

  private isTtlEnvelope<T>(obj: any): obj is TtlEnvelope<T> {
    return obj && typeof obj === 'object' && 'v' in obj && 'exp' in obj && typeof obj.exp === 'number';
  }

  private safeJsonParse<T>(raw: string | null): T | null {
    if (!raw) return null;
    try {
      return JSON.parse(raw) as T;
    } catch {
      return null;
    }
  }

  private readAuthHeaderFromLocalStorage(): string | null {
    // 1) essayer TTL envelope
    const parsed = this.safeJsonParse<TtlEnvelope<string>>(localStorage.getItem(this.AUTH_KEY));
    if (parsed && this.isTtlEnvelope<string>(parsed)) {
      if (Date.now() > parsed.exp) {
        localStorage.removeItem(this.AUTH_KEY);
        return null;
      }
      return parsed.v?.trim() || null;
    }

    // 2) legacy: string direct
    const raw = localStorage.getItem(this.AUTH_KEY);
    return raw?.trim() || null;
  }

  private readUserFromLocalStorage(): UserDTO | null {
    const parsed = this.safeJsonParse<TtlEnvelope<UserDTO>>(localStorage.getItem(this.USER_KEY));
    if (parsed && this.isTtlEnvelope<UserDTO>(parsed)) {
      if (Date.now() > parsed.exp) {
        localStorage.removeItem(this.USER_KEY);
        return null;
      }
      return (parsed.v as UserDTO) ?? null;
    }

    // legacy: user JSON direct
    const raw = localStorage.getItem(this.USER_KEY);
    const legacy = this.safeJsonParse<UserDTO>(raw);
    return legacy ?? null;
  }

  private readAuthHeaderFromSessionStorage(): string | null {
    const raw = sessionStorage.getItem(this.AUTH_KEY);
    return raw?.trim() || null;
  }

  private readUserFromSessionStorage(): UserDTO | null {
    const raw = sessionStorage.getItem(this.USER_KEY);
    const parsed = this.safeJsonParse<UserDTO>(raw);
    return parsed ?? null;
  }

  private restoreFromStorage(): void {
    // Priorité:
    // - localStorage (remember=true ou TTL temporary multi-onglets)
    // - sinon sessionStorage (legacy / fallback)
    const header = this.readAuthHeaderFromLocalStorage() ?? this.readAuthHeaderFromSessionStorage();
    const user = this.readUserFromLocalStorage() ?? this.readUserFromSessionStorage();

    // Si TTL expiré d’un côté et pas l’autre, on laisse le restore faire son job.
    // On nettoie aussi les incohérences (header sans user, etc.) si tu veux être strict.
    this._authHeader.set(header);
    this._user.set(user);
  }

  private writeRemembered(header: string, user: UserDTO): void {
    // remember = true => persistant "normal"
    localStorage.setItem(this.AUTH_KEY, header);
    localStorage.setItem(this.USER_KEY, JSON.stringify(user));

    // on nettoie l’autre storage
    sessionStorage.removeItem(this.AUTH_KEY);
    sessionStorage.removeItem(this.USER_KEY);
  }

  private writeTemporaryMultiTab(header: string, user: UserDTO): void {
    // remember = false => multi-onglets mais avec TTL (stocké dans localStorage)
    const exp = Date.now() + this.TEMP_SESSION_TTL_MS;

    const authEnvelope: TtlEnvelope<string> = { v: header, exp };
    const userEnvelope: TtlEnvelope<UserDTO> = { v: user, exp };

    localStorage.setItem(this.AUTH_KEY, JSON.stringify(authEnvelope));
    localStorage.setItem(this.USER_KEY, JSON.stringify(userEnvelope));

    // on peut aussi garder une copie sessionStorage pour l’onglet courant (optionnel)
    // mais pas nécessaire. On nettoie quand même pour éviter des incohérences :
    sessionStorage.removeItem(this.AUTH_KEY);
    sessionStorage.removeItem(this.USER_KEY);
  }

  async login(username: string, password: string, remember: boolean): Promise<UserDTO> {
    const header = this.basicAuthHeader(username, password);

    const me = await firstValueFrom(
      this.http.get<UserDTO>('http://localhost:8080/me', {
        headers: new HttpHeaders({ Authorization: header }),
      })
    );

    if (this.isBrowser) {
      if (remember) {
        this.writeRemembered(header, me);
      } else {
        this.writeTemporaryMultiTab(header, me);
      }
    }

    this._authHeader.set(header);
    this._user.set(me);

    return me;
  }

  logout(): void {
    if (this.isBrowser) {
      localStorage.removeItem(this.AUTH_KEY);
      sessionStorage.removeItem(this.AUTH_KEY);
      localStorage.removeItem(this.USER_KEY);
      sessionStorage.removeItem(this.USER_KEY);
    }

    this._authHeader.set(null);
    this._user.set(null);
    this.router.navigate(['/login']);
  }
}
