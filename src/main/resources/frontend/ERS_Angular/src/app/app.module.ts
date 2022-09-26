import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule, } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { ManagerComponent } from './components/manager/manager.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { PreventLoggedInAccessService } from './services/prevent-logged-in-access.service';
import { ProfilePictureComponent } from './components/profile-picture/profile-picture.component';
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
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table' ;
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReiDialogComponent } from './components/dialog-boxes/rei-dialog/rei-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input'; 
import { MatSelectModule } from '@angular/material/select';
import { MatGridList, MatGridListModule } from '@angular/material/grid-list';
import { MngDialogComponent } from './components/dialog-boxes/mng-dialog/mng-dialog.component';
import { PdfReportComponent } from './components/pdf-report/pdf-report.component';

@NgModule({
  declarations: [
    AppComponent,    
    LoginComponent, ManagerComponent, EmployeeComponent, 
    ProfilePictureComponent, LoginResetComponent, HomeResetComponent, 
    ChildResetComponent, ChildLoginResetComponent, NavbarComponent, 
    FinalizepasswordresetComponent, ChildFinalizeResetComponent, 
    RegisterComponent, ChildEmailCheckComponent, ChildPasswordCheckComponent, ReiDialogComponent, MngDialogComponent, PdfReportComponent,
    
    
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,    
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatDialogModule,
    MatButtonModule,
    MatTableModule,
    NgbModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatSelectModule,
    MatGridListModule
    
    
  ],
  providers: [AppComponent, PreventLoggedInAccessService],
  bootstrap: [AppComponent]
})
export class AppModule { }
