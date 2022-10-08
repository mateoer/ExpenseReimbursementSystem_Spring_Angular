import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { RegisterComponent } from '../register.component';
import { UserCredentials } from '../../../interfaces/user-credentials';
import { RegisterService } from 'src/app/services/register-service.service';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login-service.service';
import { LocationStrategy } from '@angular/common';

@Component({
  selector: 'app-child-password-check',
  templateUrl: './child-password-check.component.html',
  styleUrls: ['./child-password-check.component.css']
})
export class ChildPasswordCheckComponent extends RegisterComponent implements OnInit, OnChanges {

  constructor(registerService: RegisterService, loginService: LoginService, 
    _route: Router,location: LocationStrategy) {
   super(registerService,loginService,_route, location);
 }
  @Input() override passwordMessage!: string;
  @Input() override registrationForm!: UserCredentials;
  @Input() override re_password!: string;


  changelog: string[] = [];
  ngOnChanges(changes: SimpleChanges): void {
    for (const propName in changes) {
      const change = changes[propName];
      const to  = JSON.stringify(change.currentValue);
      const from = JSON.stringify(change.previousValue);
      const changeLog = `${propName}: changed from ${from} to ${to} `;
      this.changelog.push(changeLog);
    }
  }

  override ngOnInit(): void {
  }

}
