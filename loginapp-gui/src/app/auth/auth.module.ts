import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { DesignModule } from '../design/design.module';
import { NavigationModule } from '../navigation/navigation.module';
import { TranslateModule } from '@ngx-translate/core';


@NgModule({
  declarations: [
    LoginPageComponent,
    RegistrationComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AuthRoutingModule,
    DesignModule,
    NavigationModule,
    TranslateModule
  ],
})
export class AuthModule { }
