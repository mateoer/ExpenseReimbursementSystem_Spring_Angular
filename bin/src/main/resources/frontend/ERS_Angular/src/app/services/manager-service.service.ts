import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmpReimbursements } from '../interfaces/emp-reimbursements';
import { Reimbursement, ReiType, Status } from '../interfaces/reimbursement';
import { UserId } from '../interfaces/user-id';
import { UserName } from '../interfaces/user-name';
import { GeneralRouteService } from './general-route.service';


let reimbursement: Reimbursement = {
  reiId: 0,
  rei_amount: 0,
  rei_description: '',
  reiType: ReiType.LODGING,
  reiStatus: Status.PENDING
}

@Injectable({
  providedIn: 'root'
})
export class ManagerService {  
  

  constructor(private http: HttpClient, private urlService: GeneralRouteService) { }

  public viewListOfAllReimbursements(): Observable<EmpReimbursements[]>{
    return this.http.get<EmpReimbursements[]>(`${this.urlService.LIST_REI}`);
  }
  
  public getUserName(): Observable<UserName> {
    let myUserId = JSON.parse(sessionStorage.getItem('userId')!);
      const user: UserId = {
        userId: myUserId
      };  
    return this.http.post<UserName>(`${this.urlService.VIEW_USER}`, user);
  } 

  public approveReimbursement(reimbId: number): Observable<any> | any{
    return this.http.post<any>(`${this.urlService.APP_REI}`, 
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

  public denyReimbursement(reimbId: number): Observable<any> | any{      
      return this.http.post<any>(`${this.urlService.DEN_REI}`,  
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
