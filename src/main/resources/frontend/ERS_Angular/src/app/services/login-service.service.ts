import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
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
    return this.http.get(`${this.urlService.GREETINGS}`, { responseType: 'text', withCredentials: true});
  }

    
  public findUserLogin(usernamePassword: UsernamePassword): Observable<HttpResponse<any>> {
    const userCredentials = new FormData();
    // var access_control_CORS = new HttpHeaders();
    //     access_control_CORS.set('Access-Control-Allow-Origin', `${this.urlService.API_URL}/`);
        userCredentials.append("username", usernamePassword.username);
        userCredentials.append("password", usernamePassword.password);
        let result = this.http.post<any>(
          `${this.urlService.USER_CREDENTIALS}`,
          userCredentials
          // , { headers: access_control_CORS, observe: 'response'}
          , { observe: 'response'}
    );
    return result;
  }
  public re_login_user(usernamePassword: UsernamePassword, authorizationData: string ): Observable<UserCredentials> {
    const userCredentials = new FormData();
    
    
    // var access_control_CORS = new HttpHeaders();
    //     access_control_CORS.append('Access-Control-Allow-Origin', 'http://localhost:9050/');
    //     access_control_CORS.append('Authorization', authorizationData);

        userCredentials.append("username", usernamePassword.username);
        userCredentials.append("password", usernamePassword.password);
        let result = this.http.post<UserCredentials>(
          `${this.urlService.USER_CREDENTIALS}`,
          userCredentials
          // , { headers : access_control_CORS }
    );
    return result;
  }
}
