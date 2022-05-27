import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { AuthService } from './auth/services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [MessageService]

})
export class AppComponent implements OnInit {

  loggedIn: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.loggedIn = !!this.authService.getToken()
    this.authService.loggedIn.subscribe(value => {
      this.loggedIn = value;
    })
  }
}
