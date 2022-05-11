import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { AuthService } from '../auth/services/auth.service';
import { MessageService } from 'primeng/api';

const BEARER = 'Bearer ';
@Injectable()
export class JwtTokenInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService,
    private messageService: MessageService) { }

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
    if (err.status === 0) {
      this.messageService.add({ severity: 'error', summary: "Error", detail: "Failed to access the server." })
    } else if (err.error.errorMessage) {
      this.messageService.add({ severity: 'error', summary: "Error", detail: err.error.errorMessage })
    }
    else {
      console.log(err)
    }
  }

}
