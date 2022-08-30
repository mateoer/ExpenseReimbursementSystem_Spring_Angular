import { HttpClient } from '@angular/common/http';
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
    let result = this.http.post<UserCredentials>(
      `${this.urlService.USER_CREDENTIALS}`,
      usernamePassword
    );
    return result;
  }
}
