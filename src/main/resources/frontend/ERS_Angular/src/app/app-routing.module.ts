import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeComponent } from './employee/employee.component';
import { LoginComponent } from './login/login.component';
import { ManagerComponent } from './manager/manager.component';
import { ProfilePictureComponent } from './profile-picture/profile-picture.component';
import { PreventLoggedInAccessService } from './services/prevent-logged-in-access.service';

const routes: Routes = [
  {
    path: '', redirectTo: '/login', pathMatch: 'full'
    
  },
  {
    path: 'login', component: LoginComponent
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
