import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatProgressSpinner} from '@angular/material/progress-spinner';
import {MatIcon} from '@angular/material/icon';
import {MatError, MatFormField, MatInput, MatLabel, MatSuffix} from '@angular/material/input';
import {MatButton, MatIconButton} from '@angular/material/button';
import {RouterLink} from '@angular/router';
import {AuthLayoutComponent} from '../auth-layout.component';
import {AuthService} from '../../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  imports: [
    MatProgressSpinner,
    MatIcon,
    MatError,
    MatIconButton,
    MatSuffix,
    MatInput,
    MatLabel,
    MatFormField,
    ReactiveFormsModule,
    MatButton,
    RouterLink,
    AuthLayoutComponent
  ]
})
export class RegisterComponent {
  loading = false;
  error?: string;
  hidePassword = true;

  form : FormGroup<any>;

  constructor(private fb: FormBuilder, protected auth: AuthService) {
    this.form = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.error = undefined;

    // TODO: appel API d’inscription
    console.log(this.form.value);

    setTimeout(() => {
      this.loading = false;
    }, 1000);
  }
}
