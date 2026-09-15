import {Routes} from '@angular/router';
import {inject} from '@angular/core'; // On a besoin de la fonction inject ici uniquement
import {LoginComponent} from './pages/login/login.component';
import {PageComponent} from './pages/page/page.component';
import {UpdateHistoryComponent} from './pages/update-history/update-history.component';
import {PhoneHistoryComponent} from './pages/phone-history/phone-history.component';
import {PricingSettingsComponent} from './pages/pricing-settings/pricing-settings.component';
import {PhoneUsersComponent} from './pages/phone-users/phone-users.component';
import {AuthGuard} from './services/utils/auth.guard';
import {NoAuthGuard} from './services/utils/no-auth.guard';
import {ForgotPasswordComponent} from './components/auth/forgot-password/forgot-password.component';

export const routes: Routes = [
  {
    path: 'login',
    canActivate: [() => inject(NoAuthGuard).canActivate()],
    loadComponent: () =>
      import('./components/auth/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'register',
    canActivate: [() => inject(NoAuthGuard).canActivate()],
    loadComponent: () =>
      import('./components/auth/register/register.component').then(m => m.RegisterComponent),
  },
  {
    path: 'forgot',
    canActivate: [() => inject(NoAuthGuard).canActivate()],
    loadComponent: () =>
      import('./components/auth/forgot-password/forgot-password.component').then(m => m.ForgotPasswordComponent),
  },
  {
    path: 'reset-password',
    canActivate: [() => inject(NoAuthGuard).canActivate()],
    loadComponent: () =>
      import('./components/auth/reset-password/reset-password.component').then(m => m.ResetPasswordComponent),
  },
  {
    path: '',
    component: PageComponent,
    canActivate: [(route, state) => inject(AuthGuard).canActivate(route, state)],
    children: [
      {path: 'phone/history', component: PhoneHistoryComponent},
      {path: 'phone/settings/suivis', component: UpdateHistoryComponent},
      {path: 'phone-user', component: PhoneUsersComponent},
      {
        path: 'phone-user/:id',
        loadComponent: () =>
          import('./pages/phone-detail/phone-detail.component').then(
            (m) => m.PhoneDetailComponent
          ),
      },
      {path: 'pricing/settings', component: PricingSettingsComponent},
    ],
  },
  {path: '**', redirectTo: ''},
];
