import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { UserCredentials } from '../interfaces/user-credentials';
import { UsernamePassword } from '../interfaces/username-password';


@Injectable({
  providedIn: 'root',
})
export class LoginService {

  constructor(private http: HttpClient) {}

  API_URL = `http://localhost:9050`;
  USER_CREDENTIALS = `${this.API_URL}/getcredentials`;

  public greetings(){
    return this.http.get(`${this.API_URL}/greetings`, { responseType: 'text'});
  }

    
  public findUserLogin(usernamePassword: UsernamePassword): Observable<UserCredentials> {
    let result = this.http.post<UserCredentials>(
      `${this.USER_CREDENTIALS}`,
      usernamePassword
    );
    return result;
  }
}
