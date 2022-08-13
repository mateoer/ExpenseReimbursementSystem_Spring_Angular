import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserId } from '../interfaces/user-id';
import { Observable } from 'rxjs';

let user: UserId = {
  userId: 1
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  API_URL = `http://localhost:9050`;
  USER_ROLE = `${this.API_URL}/typeofuser`;

  constructor(private http: HttpClient) { }

  public findUserRole(): Observable<string>{
    return this.http.post<string>(`${this.USER_ROLE}`, user);
  }
}
