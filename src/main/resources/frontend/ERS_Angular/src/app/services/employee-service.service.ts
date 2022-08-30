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

  public newReimbursement(newReimbursement: Reimbursement): Observable<any> | any{    
      
      return this.http.post<any>(`${this.urlService.NEW_REI}`,  
      {
        "user":{
          "userId": JSON.parse(sessionStorage.getItem('userId')!)
        },
        "reimbursement":{
          "rei_amount": newReimbursement.rei_amount,
          "rei_description": newReimbursement.rei_description,
          "reiType": newReimbursement.reiType
        }
      }
      , { responseType: 'text' as 'json' } );    
  } 

  public editReimbursement(editedReimbursement:EmpReimbursements): Observable<any> | any {
    return this.http.post<any>(`${this.urlService.EDIT_REI}`,
    {
      "user":{
        "userId": JSON.parse(sessionStorage.getItem('userId')!)
      },
      "reimbursement":{
        "reiId": editedReimbursement.reiId,
        "rei_amount": editedReimbursement.rei_amount,
        "rei_description": editedReimbursement.rei_description,
        "reiType": editedReimbursement.reiType
      }
    }
    , { responseType: 'text' as 'json' });
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
