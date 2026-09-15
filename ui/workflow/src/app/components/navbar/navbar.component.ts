import {Component, computed, signal} from '@angular/core';
import {FormBuilder, FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router, RouterLink} from '@angular/router';
import {NgClass} from '@angular/common';

// Material (les mêmes imports que tu m’as donnés)
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatBadgeModule} from '@angular/material/badge';
import {MatDividerModule} from '@angular/material/divider';
import {MatRippleModule} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {AuthService} from '../../services/auth.service';


type Locale = { code: string; label: string; flag?: string };

@Component({
  selector: 'app-navbar',
  standalone: true,
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatTooltipModule,
    MatBadgeModule,
    MatDividerModule,
    MatRippleModule,
    FormsModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    RouterLink
  ],
})
export class NavbarComponent {
  // Recherche
  search: FormControl<string>;
  // Langues
  locales: Locale[] = [
    {code: 'fr', label: 'Français', flag: '🇫🇷'},
    {code: 'en', label: 'English', flag: '🇬🇧'},
    {code: 'it', label: 'Italiano', flag: '🇮🇹'},
  ];
  private _currentLocale = this.locales[0];
  currentLocale = () => this._currentLocale;

  changeLocale(l: Locale) {
    this._currentLocale = l;
  }

  // User (mock)
  userName = 'John Doe';
  userSite = 'SAAMP Aubagne';

  // Quotes (affichées dans la piste)
  quotes = signal<any[]>([{
    code: 'AU',
    fix: 'FIX PM 07-11',
    price: '110,898',
    unit: '€/kg',
    spot: 'Spot 9:13: 113,271–113,379 €/kg'
  }, {code: 'AG', fix: 'FIX 07-11', price: '1,355', unit: '€/kg', spot: 'Spot 9:13: 1,383–1,387 €/kg'}, {
    code: 'PT',
    fix: 'FIX PM 07-11',
    price: '43,183',
    unit: '€/kg',
    spot: 'Spot 9:13: 43,812–44,050 €/kg'
  }, {code: 'PD', fix: 'FIX PM 07-11', price: '38,792', unit: '€/kg', spot: 'Spot 9:13: 38,933–39,221 €/kg'},]);
  quotesView = computed(() => this.quotes());
  // classes pour q.code (utilisé dans [ngClass]="chipBy[q.code]")

  chipBy: Record<string, string> = {
    AU: 'quote-chip ring-1 ring-[#d4b157]/50 bg-gradient-to-br from-[#f3e7c2] to-[#e3c879] text-slate-800 dark:from-[#c2a250]/40 dark:to-[#8a7b55]/30 dark:text-[#f7e7b2]',
    AG: 'quote-chip ring-1 ring-slate-300/60 bg-gradient-to-br from-slate-100 to-slate-200 text-slate-700 dark:from-slate-700/50 dark:to-slate-800/50 dark:text-slate-200',
    PT: 'quote-chip ring-1 ring-slate-400/40 bg-gradient-to-br from-[#d7d9dd] to-[#c1c4ca] text-slate-800 dark:from-[#9ca3af]/40 dark:to-[#6b7280]/40 dark:text-slate-100',
  };


  constructor(
    fb: FormBuilder,
    private router: Router,
    protected authService: AuthService
  ) {
    this.userName = this.authService.fullName() ?? " ";
    this.userSite = this.authService.user()?.company ?? " ";
    this.search = fb.nonNullable.control('');
  }

  // Submit / clear recherche
  submitSearch(): void {
    const q = (this.search.value || '').trim();
    if (q) this.router.navigate(['/search'], {queryParams: {q}});
  }

  clearSearch(): void {
    this.search.setValue('');
  }


  // (facultatif) trackBy si tu l’utilises
  trackByCode = (_: number, item: { code: string }) => item.code;
}
