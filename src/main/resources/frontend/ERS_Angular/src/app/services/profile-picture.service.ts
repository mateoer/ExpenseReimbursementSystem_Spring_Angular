import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserCredentials } from '../interfaces/user-credentials';
import { GeneralRouteService } from './general-route.service';

@Injectable({
  providedIn: 'root'
})
export class ProfilePictureService {

  constructor(private http: HttpClient, private urlService: GeneralRouteService) { }  

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
    return this.http.post<string>(`${this.urlService.GET_PFP}`, myUser,
     { responseType: 'text' as 'json' });    
  }

  uploadProfilePicture(formDataToSend: FormData){    
    return this.http.post(`${this.urlService.UPLOAD_PFP}`, formDataToSend, { responseType: 'text' as 'json' });     
  }

 
}
