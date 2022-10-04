import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { UserCredentials } from '../interfaces/user-credentials';
import { UsernamePassword } from '../interfaces/username-password';
import { GeneralRouteService } from './general-route.service';


@Injectable({
  providedIn: 'root',
})
export class LoginService {

  constructor(private http: HttpClient, private urlService: GeneralRouteService) {}  

  public greetings(){
    return this.http.get(`${this.urlService.GREETINGS}`, { responseType: 'text'});
  }

    
  public findUserLogin(usernamePassword: UsernamePassword): Observable<UserCredentials> {
    const userCredentials = new FormData();
    // var access_control_CORS = new HttpHeaders();
    //     access_control_CORS.append('Access-Control-Allow-Origin', 'http://localhost:9050/');
    // , { headers : access_control_CORS}
    userCredentials.append("username", usernamePassword.username);
    userCredentials.append("password", usernamePassword.password);
    let result = this.http.post<UserCredentials>(
      `${this.urlService.USER_CREDENTIALS}`,
      userCredentials
    );
    return result;
  }
}
