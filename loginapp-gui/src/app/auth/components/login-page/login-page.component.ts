import { Component, OnInit } from '@angular/core';
import { AuthUser } from 'src/app/models/authuser';
import { AuthService } from '../../services/auth.service';
import { MessageService } from 'primeng/api'
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { IExport } from 'src/app/utils/export';
import { GlobalMessageService } from 'src/app/utils/service/global-message.service';

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
    private router: Router,
    private messageService: GlobalMessageService) { }

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
      this.successfulLogin(resp)
    }, (err: HttpErrorResponse) => {
      console.error(err)
      this.loggingIn = false
    });
  }

  private successfulLogin(response: any) {
    this.authService.setCredentials(this.user.username, response.access_token);
    this.loggingIn = false
    this.authService.loggedIn.emit(true)
    this.messageService.success("User logged in.")
    this.router.navigate(['/dashboard']);
  }
}
