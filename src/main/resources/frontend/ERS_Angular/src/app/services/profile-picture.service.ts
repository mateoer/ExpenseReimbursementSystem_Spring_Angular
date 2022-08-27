import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCredentials } from '../interfaces/user-credentials';

@Injectable({
  providedIn: 'root'
})
export class ProfilePictureService {

  constructor(private http: HttpClient) { }

  API_URL = `http://localhost:9050`;
  UPLOAD_PFP = `${this.API_URL}/uploadPfp`;
  GET_PFP = `${this.API_URL}/getPfp`;

  getProfilePicture(): Observable<string>{    
      let myUser: UserCredentials = {
        user: {
          userId: JSON.parse(sessionStorage.getItem('userId')!),
          username: sessionStorage.getItem('username')!,
          password: sessionStorage.getItem('password')!,
          firstName: sessionStorage.getItem('firstName')!,
          lastName: sessionStorage.getItem('lastName')!,
          email: sessionStorage.getItem('email')!,
          userRole: sessionStorage.getItem('userRole')!,
          profilePicName: sessionStorage.getItem('profilePicture')!
        },
        found: JSON.parse(sessionStorage.getItem('found')!)
      }     
    return this.http.post<string>(`${this.GET_PFP}`, myUser, { responseType: 'text' as 'json' });    
  }

  uploadProfilePicture(formDataToSend: FormData){    
    return this.http.post(`${this.UPLOAD_PFP}`, formDataToSend, { responseType: 'text' as 'json' });
  }

 
}
