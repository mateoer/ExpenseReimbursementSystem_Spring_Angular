import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCredentials } from '../interfaces/user-credentials';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  API_URL = `http://localhost:9050`;
  CHECK_USERNAME = `${this.API_URL}/checkUsername/`;
  CHECK_EMAIL = `${this.API_URL}/checkEmail/`;
  NEW_USER = `${this.API_URL}/registerNewUser`;

  checkUsername(username: string){
    return this.http.get(`${this.CHECK_USERNAME}${username}`, { responseType: 'text' as 'json' });
  }
  
  checkEmail(email: string){
    return this.http.get(`${this.CHECK_EMAIL}${email}`,{ responseType: 'text' });
  }

  registerNewUser(newUser: UserCredentials): Observable<UserCredentials>{
    return this.http.post<UserCredentials>(`${this.NEW_USER}`, newUser);
  }
}
