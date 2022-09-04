import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FinalizepasswordresetComponent } from './components/finalizepasswordreset/finalizepasswordreset.component';
import { HomeResetComponent } from './components/home-reset/home-reset.component';
import { LoginResetComponent } from './components/login-reset/login-reset.component';
import { RegisterComponent } from './components/register/register.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { LoginComponent } from './components/login/login.component';
import { ManagerComponent } from './components/manager/manager.component';
import { PreventLoggedInAccessService } from './services/prevent-logged-in-access.service';

const routes: Routes = [
  {
    path: '', redirectTo: '/login', pathMatch: 'full'
    
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'finalizepasswordreset', component: FinalizepasswordresetComponent
  },
  {
    path: 'loginreset', component: LoginResetComponent
    
  },
  {
    path: 'register', component: RegisterComponent
    
  },
  {
    path: 'homereset', component: HomeResetComponent
     , canActivate: [PreventLoggedInAccessService]
  },
  {
    path: 'manager', component: ManagerComponent 
     , canActivate: [PreventLoggedInAccessService]
  },
  {
    path: 'employee', component: EmployeeComponent
    , canActivate: [PreventLoggedInAccessService]
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
