import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import {AuthService} from '../../../services/auth.service';
import {AuthLayoutComponent} from '../auth-layout.component';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,

    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    AuthLayoutComponent,
  ],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.scss',
})
export class ForgotPasswordComponent implements OnInit {
  readonly year = new Date().getFullYear();

  loading = false;
  sent = false;
  error: string | null = null;

  form: FormGroup<any>;

  constructor(
    private fb: FormBuilder,
    protected auth: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) {
    this.form = this.fb.nonNullable.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {
    if (this.auth.getAuthHeader()) {
      const redirect = this.route.snapshot.queryParamMap.get('redirect');
      this.router.navigate([redirect || '/phone-history']);
    }
  }

  async submit(): Promise<void> {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.error = null;
    this.cdr.markForCheck();

    const { email } = this.form.getRawValue();

    try {
      // TODO: branche ton endpoint
      // Exemple attendu côté service :
      // await this.auth.requestPasswordReset(email);

      console.log('FORGOT PASSWORD', { email });
      this.sent = true;
    } catch (e: any) {
      this.error = "Impossible d'envoyer l'email";
    } finally {
      this.loading = false;
      this.cdr.markForCheck();
    }
  }
}
