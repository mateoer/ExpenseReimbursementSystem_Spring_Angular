import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login-service.service';
import { RegisterService } from 'src/app/services/register-service.service';
import { UserCredentials } from '../../interfaces/user-credentials';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  constructor(public registerService: RegisterService, public loginService: LoginService, 
    public _route: Router, public location: LocationStrategy) {
      history.pushState(null, "null", window.location.href);  
      this.location.onPopState(() => {
      history.pushState(null, "null", window.location.href);
    });
  }

  ngOnInit(): void {  }

  userRole : any[] = ['','MANAGER','EMPLOYEE'];
  selectedRole: string = ' ';

  registrationForm: UserCredentials ={
    user: {
      userId: NaN,
      username: '',
      password: '',
      firstName: '',
      lastName: '',
      email: '',
      userRole: '',
      profilePicName: ''
    },
    found: false
  }
  
  re_email: string = '';
  re_password: string = '';

  usernameMessage: any = '';
  readyUsername: boolean = false;

  emailAvailableMessage: any = '';
  emailMessage: string = '';
  readyEmail: boolean = false;

  passwordMessage: string = '';
  
  newlyCreatedUser: UserCredentials = {
    user: {
      userId: 0,
      username: '',
      password: '',
      firstName: '',
      lastName: '',
      email: '',
      userRole: '',
      profilePicName: ''
    },
    found: false
  };

  errorCreatingUser: any = '';

  register(){
    
    let FName = this.registrationForm.user.firstName != '';
    let LName = this.registrationForm.user.lastName != '';
    let uName = this.readyUsername == true;
    let mail =  (this.readyEmail == true) && (this.registrationForm.user.email == this.re_email) && (this.re_email != '' || ' ');
    let uRole = this.registrationForm.user.userRole != '';
    let PasW = (this.registrationForm.user.password == this.re_password) && (this.re_password != '' || ' ');

    if (FName && LName && uRole && uName && mail && PasW) {  
      

      this.registerService.registerNewUser(this.registrationForm)
        .subscribe(e => 
          {
            if (e.found == true) { 
              this.registrationForm = e;        
              this.newlyCreatedUser = e;
              this.sessionSave();

              console.log(this.registrationForm);

              const moveTo = this.registrationForm.user.userRole.toLowerCase();
              this._route.navigate([`/${moveTo}`]);
            }
          });          
    }else {
      this.errorCreatingUser = "Something went wrong";
    }
  }

  public sessionSave(){
    sessionStorage.setItem('userId', JSON.stringify(this.newlyCreatedUser.user.userId));
    sessionStorage.setItem('username', this.newlyCreatedUser.user.username);
    sessionStorage.setItem('password', this.newlyCreatedUser.user.password);
    sessionStorage.setItem('firstName', this.newlyCreatedUser.user.firstName);
    sessionStorage.setItem('lastName', this.newlyCreatedUser.user.lastName);
    sessionStorage.setItem('email', this.newlyCreatedUser.user.lastName);
    sessionStorage.setItem('userRole', this.newlyCreatedUser.user.userRole);
    sessionStorage.setItem('profilePicture', this.newlyCreatedUser.user.profilePicName);
    sessionStorage.setItem('found', JSON.stringify(this.newlyCreatedUser.found));
  }    

  onSelectRole(){
    if (this.selectedRole != '') {
      this.registrationForm.user.userRole = this.selectedRole;
    }
  }

  onUsernameFilled(a: any, input: any = null){
    this.registrationForm.user.username = a;
    if(this.registrationForm.user.username !== ' '){
      this.registerService.checkUsername(this.registrationForm.user.username)
      .subscribe(e => {
        this.usernameMessage = e;
        if (this.usernameMessage == "Username is available") {
          this.readyUsername = true;
        }
      });
    }
  }

  onEmailFilled(a: any, input: any = null){
    this.registrationForm.user.email = a;
    if (this.registrationForm.user.email !== ' ') {
      this.registerService.checkEmail(this.registrationForm.user.email)
          .subscribe(e => {
            this.emailAvailableMessage = e;
            if (this.emailAvailableMessage == '') {
              this.readyEmail = true;
            }
          });
    }
  }

}
