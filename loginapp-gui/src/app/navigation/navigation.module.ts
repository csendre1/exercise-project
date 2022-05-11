import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HeaderComponent } from './components/header/header.component';
import { DesignModule } from '../design/design.module';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { RouterModule } from '@angular/router';
import { ActivatedDirective } from '../directive/activated.directive';
import { TranslateModule } from '@ngx-translate/core';



@NgModule({
  declarations: [
    HeaderComponent,
    SidebarComponent,
    ActivatedDirective
  ],
  imports: [
    CommonModule,
    DesignModule,
    RouterModule,
    TranslateModule
  ],
  exports: [
    HeaderComponent,
    SidebarComponent
  ]
})
export class NavigationModule { }
