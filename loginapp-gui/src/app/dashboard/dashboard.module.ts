import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardPageComponent } from './components/dashboard-page/dashboard-page.component';
import { NavigationModule } from '../navigation/navigation.module';
import { DesignModule } from '../design/design.module';
import { TranslateModule } from '@ngx-translate/core';


@NgModule({
  declarations: [
    DashboardPageComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    NavigationModule,
    DesignModule,
    TranslateModule
  ]
})
export class DashboardModule { }
