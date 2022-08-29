import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmpReimbursements } from '../interfaces/emp-reimbursements';
import { Reimbursement } from '../interfaces/reimbursement';
import { UserId } from '../interfaces/user-id';
import { UserName } from '../interfaces/user-name';
import { GeneralRouteService } from './general-route.service';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  constructor(private http: HttpClient, private urlService: GeneralRouteService) { }
   
  
  public getEmpReimbursements() : Observable<EmpReimbursements[]> {
    let myUserId = JSON.parse(sessionStorage.getItem('userId')!);
      const user: UserId = {
        userId: myUserId
      };
    return this.http.post<EmpReimbursements[]>(`${this.urlService.GET_REI}`, user);
  }

  public getUserName(): Observable<UserName> {
    let myUserId = JSON.parse(sessionStorage.getItem('userId')!);
      const user: UserId = {
        userId: myUserId
      };
    return this.http.post<UserName>(`${this.urlService.GET_USER}`, user);
  }  

  public newReimbursement(NRO: Reimbursement): Observable<any> | any{    
      const headers = { 'content-type': 'application/json'}; 
      const reimbursement = NRO;
      let myUserId = JSON.parse(sessionStorage.getItem('userId')!);
      const user: UserId = {
        userId: myUserId
      };  
      return this.http.post<any>(`${this.urlService.NEW_REI}`,  
      {
        reimbursement,
        user    
      }
      , {'headers':headers} );    
  } 

  public cancelReimbursement(reimbId: number): Observable<any> | any{     
       
      return this.http.post<any>(`${this.urlService.DEL_REI}`,  
      {
        "user":{
          "userId": JSON.parse(sessionStorage.getItem('userId')!)
        },
        "reimbursement":{
          "reiId": reimbId
        }
      }
      , { responseType: 'text' as 'json' } );
  }
  
}
