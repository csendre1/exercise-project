import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { GlobalMessageService } from 'src/app/utils/service/global-message.service';

@Component({
  selector: 'app-dashboard-page',
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.scss']
})
export class DashboardPageComponent implements OnInit {

  constructor(private authService: AuthService,
    private messageService: GlobalMessageService) { }

  ngOnInit(): void {
  }

}
