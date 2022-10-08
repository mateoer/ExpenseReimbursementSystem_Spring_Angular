import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GeneralRouteService } from './general-route.service';

@Injectable({
  providedIn: 'root'
})
export class PasswordResetService {

  constructor(private http: HttpClient, private urlService: GeneralRouteService) {} 
  

  validateFromLogin(username: string, email: string){
    return this.http.post(`${this.urlService.FROM_LOGIN}`, 
    {
      "username": username,
      "email": email
    }, 
    { responseType: 'text' });
  }

  validateFromHome(oldpassword: string, newpassword: string){
    return this.http.post(`${this.urlService.FROM_HOME}`,
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
    return this.http.post(`${this.urlService.RESET_WITH_TOKEN}`,
    {
      "user":{
        "passwordResetToken": resetToken
      },
      "newPassword": new_password
    },
    { responseType: 'text' });
  }

  
}
