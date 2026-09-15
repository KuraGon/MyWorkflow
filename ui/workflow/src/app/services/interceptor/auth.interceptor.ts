import { Injectable } from '@angular/core';
import {
  HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService, private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authHeader = this.auth.getAuthHeader();

    const authReq = authHeader
      ? req.clone({ setHeaders: { Authorization: authHeader } })
      : req;

    // ✅ debug 10 sec : tu verras si le header est bien présent
    // console.log('REQ', authReq.url, 'AUTH?', !!authHeader);

    return next.handle(authReq).pipe(
      catchError((err: HttpErrorResponse) => {
        if (err.status === 401) {
          // 🔥 important : si on a déjà un header, OK -> logout
          // sinon, ça évite de te faire “logout” sur un call public ou un call parti trop tôt
          if (authHeader && this.router.url !== '/login') {
            this.auth.logout();
            this.router.navigateByUrl('/login');
          }
        }
        return throwError(() => err);
      })
    );
  }
}
