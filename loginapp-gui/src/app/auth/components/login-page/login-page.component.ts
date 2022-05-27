import { Component, OnInit } from '@angular/core';
import { AuthUser } from 'src/app/models/authuser';
import { AuthService } from '../../services/auth.service';
import { MessageService } from 'primeng/api'
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { IExport } from 'src/app/utils/export';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss'],
  providers: [MessageService]
})
export class LoginPageComponent implements OnInit {

  user: AuthUser = { username: '', password: '' }

  errorMessage: string = ''

  isAllCompleted: boolean = true;

  loggingIn: boolean = false


  constructor(private authService: AuthService,
    private router: Router) { }

  public ngOnInit(): void { }

  public login(): void {
    if (this.isCompletedInputs()) {
      this.performLogin();
    }
  }

  private isCompletedInputs(): boolean {
    return this.user.username != '' && this.user.password != '';
  }

  private performLogin(): void {
    this.loggingIn = true
    this.authService.login(this.user).subscribe(resp => {
      this.authService.setCredentials(this.user.username, resp.access_token);
      this.loggingIn = false
      this.router.navigate(['/dashboard']);
    }, (err: HttpErrorResponse) => {
      console.error(err)
      this.loggingIn = false
    });
  }
}
