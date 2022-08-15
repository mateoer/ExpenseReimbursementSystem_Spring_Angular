import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmpReimbursements } from '../interfaces/emp-reimbursements';
import { Reimbursement, ReiType, Status } from '../interfaces/reimbursement';
import { UserId } from '../interfaces/user-id';
import { UserName } from '../interfaces/user-name';


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

  API_URL = `http://localhost:9050`;
   
  VIEW_USER = `${this.API_URL}/getmngusername`;
  APP_REI = `${this.API_URL}/approvereimbursement`;
  DEN_REI = `${this.API_URL}/denyreimbursement`;
  LIST_REI = `${this.API_URL}/getlistofreimbursements`;

  constructor(private http: HttpClient) { }

  public viewListOfAllReimbursements(): Observable<EmpReimbursements[]>{
    return this.http.get<EmpReimbursements[]>(`${this.LIST_REI}`);
  }
  
  public getUserName(): Observable<UserName> {
    let myUserId = JSON.parse(sessionStorage.getItem('userId')!);
      const user: UserId = {
        userId: myUserId
      };  
    return this.http.post<UserName>(`${this.VIEW_USER}`, user);
  } 

  public approveReimbursement(reimbId: number): Observable<any>{
    const headers = { 'content-type': 'application/json'}; 
    reimbursement.reiId = reimbId;
    
      
      return this.http.post<any>(`${this.APP_REI}`,  
      reimbursement
      , {'headers':headers} );
  }

  public denyReimbursement(reimbId: number): Observable<any>{
    const headers = { 'content-type': 'application/json'}; 
    reimbursement.reiId = reimbId;
      
      return this.http.post<any>(`${this.DEN_REI}`,  
      reimbursement
      , {'headers':headers} );
  }

}
