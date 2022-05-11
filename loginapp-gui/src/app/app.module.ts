import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JwtTokenInterceptor } from './interceptor/jwt-token.interceptor';
import { NavigationModule } from './navigation/navigation.module';
import { MessageService } from 'primeng/api';
import { DesignModule } from './design/design.module';
import { ActivatedDirective } from './directive/activated.directive';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    NavigationModule,
    DesignModule
  ],
  providers: [
    MessageService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtTokenInterceptor, multi: true },

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
