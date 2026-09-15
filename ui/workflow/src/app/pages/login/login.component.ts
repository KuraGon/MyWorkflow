import {Component, OnInit, signal} from '@angular/core';
import {ReactiveFormsModule, FormBuilder, Validators, FormGroup} from '@angular/forms';
import {Router, RouterLink} from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule
],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent{
  hide = signal(true);
  loading = signal(false);
  form!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      remember: [false],
    });
  }


  get f() { return this.form?.controls; }

  submit(): void {
    if (this.form?.invalid || this.loading()) return;
    this.loading.set(true);
    // TODO: call auth service
    setTimeout(() => this.router.navigate(['/home']), 1200);
  }
}
