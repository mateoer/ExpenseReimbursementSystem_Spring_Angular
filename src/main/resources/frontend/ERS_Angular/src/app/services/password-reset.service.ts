import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PasswordResetService {

  constructor(private http: HttpClient) {}

  API_URL = `http://localhost:9050`;
  FROM_LOGIN = `${this.API_URL}/validateUserEmail`;
  FROM_HOME = `${this.API_URL}/validateUserPassword`;
  RESET_WITH_TOKEN = `${this.API_URL}/validateResetToken`;

  validateFromLogin(username: string, email: string){
    return this.http.post(`${this.FROM_LOGIN}`, 
    {
      "username": username,
      "email": email
    }, 
    { responseType: 'text' });
  }

  validateFromHome(oldpassword: string, newpassword: string){
    return this.http.post(`${this.FROM_HOME}`,
    {
      "user":{
        "username": sessionStorage.getItem('username')!,
        "password": oldpassword
      },
      "newPassword": newpassword
    },
    { responseType: 'text' });
  }

  validateResetToken(resetToken: string, new_password: string){
    return this.http.post(`${this.RESET_WITH_TOKEN}`,
    {
      "user":{
        "passwordResetToken": resetToken
      },
      "newPassword": new_password
    },
    { responseType: 'text' });
  }

  
}
