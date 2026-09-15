import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {
  FormBuilder,
  ReactiveFormsModule,
  Validators,
  AbstractControl,
  ValidationErrors,
  FormGroup
} from '@angular/forms';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';

import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {AuthLayoutComponent} from '../auth-layout.component';
import {AuthService} from '../../../services/auth.service';

function passwordMatchValidator(ctrl: AbstractControl): ValidationErrors | null {
  const password = ctrl.get('password')?.value;
  const confirm = ctrl.get('confirmPassword')?.value;
  if (!password || !confirm) return null;
  return password === confirm ? null : {passwordMismatch: true};
}

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    AuthLayoutComponent,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
  ],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.scss',
})
export class ResetPasswordComponent {
  loading = false;
  done = false;
  error: string | null = null;

  hidePassword = true;
  hideConfirm = true;

  // Token depuis l’URL: /reset-password?token=...
  readonly token: string;

  form: FormGroup<any>;

  constructor(
    private fb: FormBuilder,
    protected auth: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.token = this.route.snapshot.queryParamMap.get('token') ?? '';
    this.form = this.fb.nonNullable.group(
      {
        password: ['', [Validators.required, Validators.minLength(8)]],
        confirmPassword: ['', [Validators.required]],
      },
      {validators: [passwordMatchValidator]}
    );
  }

  async submit(): Promise<void> {
    if (!this.token) {
      this.error = 'Lien invalide ou expiré.';
      return;
    }

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.error = null;

    try {
      const {password} = this.form.getRawValue();

      // TODO: branche ton endpoint
      // await this.auth.resetPassword({ token: this.token, password });

      console.log('RESET PASSWORD', {token: this.token, password});
      this.done = true;
    } catch (e: any) {
      this.error = "Impossible de réinitialiser le mot de passe.";
    } finally {
      this.loading = false;
    }
  }

  goLogin(): void {
    this.router.navigateByUrl('/login');
  }
}
