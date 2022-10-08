import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCredentials } from '../interfaces/user-credentials';
import { GeneralRouteService } from './general-route.service';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient, private urlService: GeneralRouteService) { }
  

  checkUsername(username: string){
    return this.http.get(`${this.urlService.CHECK_USERNAME}${username}`, { responseType: 'text' as 'json' });
  }
  
  checkEmail(email: string){
    return this.http.get(`${this.urlService.CHECK_EMAIL}${email}`,{ responseType: 'text' });
  }

  registerNewUser(newUser: UserCredentials): Observable<UserCredentials>{
    const httpBasicOption = new HttpHeaders();
    let authorizationData = 'Basic ' + btoa(newUser.user.username + ':' + newUser.user.password);
    httpBasicOption.append('Authorization', authorizationData);
  
    return this.http.post<UserCredentials>(`${this.urlService.NEW_USER}`, newUser, { headers: httpBasicOption });
  }
}
