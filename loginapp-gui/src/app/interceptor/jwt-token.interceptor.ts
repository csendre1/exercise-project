import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { AuthService } from '../auth/services/auth.service';
import { MessageService } from 'primeng/api';
import { GlobalMessageService } from '../utils/service/global-message.service';

const BEARER = 'Bearer ';
@Injectable()
export class JwtTokenInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService,
    private messageService: GlobalMessageService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.authService.isLoggedIn()) {
      request = request.clone(
        {
          setHeaders: { Authorization: `${BEARER}${this.authService.getToken()}` }
        }
      )
    }
    return next.handle(request).pipe(catchError(err => {
      this.handleError(err)
      throw err
    }));
  }

  private handleError(err: any) {
    let msg = "An error occurred."
    console.error(err)
    if (err.status === 0) {
      msg = "Failed to access the server."
    } else if (err.error) {
      msg = err.error.errorMessage
    }
    this.messageService.error(msg)
  }
}
