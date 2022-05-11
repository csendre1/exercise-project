import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuard } from '../guards/login.guard';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { RegistrationComponent } from './components/registration/registration.component';

const routes: Routes = [
  {
    path: '',
    component: LoginPageComponent,
    canActivate: [LoginGuard],

  },
  {
    path: 'register',
    component: RegistrationComponent,
    canActivate: [LoginGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
