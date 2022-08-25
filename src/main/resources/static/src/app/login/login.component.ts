import { LocationStrategy } from '@angular/common';
import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserCredentials } from '../interfaces/user-credentials';
import { UsernamePassword } from '../interfaces/username-password';
import { LoginService } from '../services/login-service.service';

@Injectable({
  providedIn: 'root',
})
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit{  
  
  loginForm: UsernamePassword = {
    username: '',
    password: ''
  };  
  
  userCredentials : UserCredentials = {
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

  constructor(public loginService: LoginService, 
              public _route: Router,
              private location: LocationStrategy) { 
                
    history.pushState(null, "null", window.location.href);  
    this.location.onPopState(() => {
      history.pushState(null, "null", window.location.href);
    });  
  }  
  
  greetingMessage!: any;
  noUserFoundMessage: string = '';

  ngOnInit() {
    this.loginService.greetings().subscribe(e => this.greetingMessage = e);
    sessionStorage.clear();
    localStorage.clear();
  }

  public sessionSave(){
    sessionStorage.setItem('userId', JSON.stringify(this.userCredentials.user.userId));
    sessionStorage.setItem('username', this.userCredentials.user.username);
    sessionStorage.setItem('password', this.userCredentials.user.password);
    sessionStorage.setItem('firstName', this.userCredentials.user.firstName);
    sessionStorage.setItem('lastName', this.userCredentials.user.lastName);
    sessionStorage.setItem('email', this.userCredentials.user.lastName);
    sessionStorage.setItem('userRole', this.userCredentials.user.userRole);
    sessionStorage.setItem('profilePicture', this.userCredentials.user.profilePicName);
    sessionStorage.setItem('found', JSON.stringify(this.userCredentials.found));
  }  

  userLoggedIn(){
    let myUser = JSON.parse(sessionStorage.getItem('found')!);
    return myUser;  
  }
  
    
  loginUser(){
    this.loginService.findUserLogin(this.loginForm).subscribe();
    this.loginService.findUserLogin(this.loginForm).subscribe(e =>{
      if (e.found == true) { 
        this.userCredentials = e;        
        this.sessionSave();
        const moveTo = this.userCredentials.user.userRole.toLowerCase();
        this._route.navigate([`/${moveTo}`]);
      }else{
        this.noUserFoundMessage = "User not found";
      }
    });  
  } 
  
  

}


