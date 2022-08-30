import { LocationStrategy } from '@angular/common';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { UserCredentials } from 'src/app/interfaces/user-credentials';
import { LoginService } from 'src/app/services/login-service.service';
import { RegisterService } from 'src/app/services/register-service.service';
import { RegisterComponent } from '../register.component';

@Component({
  selector: 'app-child-email-check',
  templateUrl: './child-email-check.component.html',
  styleUrls: ['./child-email-check.component.css']
})
export class ChildEmailCheckComponent extends RegisterComponent implements OnInit, OnChanges {

  constructor(registerService: RegisterService, loginService: LoginService, 
     _route: Router, location: LocationStrategy) {
    super(registerService,loginService,_route, location);
  }

  @Input() override emailMessage!: string;
  @Input() override registrationForm!: UserCredentials;
  @Input() override re_email!: string;

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

  override ngOnInit(): void {  }

}
