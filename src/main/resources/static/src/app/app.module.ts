import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule, } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ManagerComponent } from './manager/manager.component';
import { EmployeeComponent } from './employee/employee.component';
import { PreventLoggedInAccessService } from './services/prevent-logged-in-access.service';
import { ProfilePictureComponent } from './profile-picture/profile-picture.component';
import { LoginResetComponent } from './components/login-reset/login-reset.component';
import { HomeResetComponent } from './components/home-reset/home-reset.component';
import { ChildResetComponent } from './components/home-reset/child-reset/child-reset.component';
import { ChildLoginResetComponent } from './components/login-reset/child-login-reset/child-login-reset.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FinalizepasswordresetComponent } from './components/finalizepasswordreset/finalizepasswordreset.component';
import { ChildFinalizeResetComponent } from './components/finalizepasswordreset/child-finalize-reset/child-finalize-reset.component';
import { RegisterComponent } from './components/register/register.component';
import { ChildEmailCheckComponent } from './components/register/child-email-check/child-email-check.component';
import { ChildPasswordCheckComponent } from './components/register/child-password-check/child-password-check.component';

@NgModule({
  declarations: [
    AppComponent,    
    LoginComponent, ManagerComponent, EmployeeComponent, 
    ProfilePictureComponent, LoginResetComponent, HomeResetComponent, ChildResetComponent, ChildLoginResetComponent, NavbarComponent, FinalizepasswordresetComponent, ChildFinalizeResetComponent, RegisterComponent, ChildEmailCheckComponent, ChildPasswordCheckComponent
    
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
    
  ],
  providers: [AppComponent, PreventLoggedInAccessService],
  bootstrap: [AppComponent]
})
export class AppModule { }
