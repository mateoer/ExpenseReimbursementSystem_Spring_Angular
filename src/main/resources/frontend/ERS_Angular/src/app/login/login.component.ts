import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { LoginService } from '../services/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  
  constructor(private loginService: LoginService) { }

  outputMessage: string = '';
  ngOnInit(): void {
    this.loginService.findUserRole().subscribe(outputMessage => this.outputMessage = outputMessage.toString());
    this.showMessage();
  }

  showMessage(){
    return this.outputMessage;
  }

  

}
