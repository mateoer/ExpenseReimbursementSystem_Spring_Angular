import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable } from 'rxjs';
import { ListEmployeesReimb } from '../interfaces/list-employees-reimb';
import { ReiId } from '../interfaces/rei-id';
import { UserId } from '../interfaces/user-id';
import { UserName } from '../interfaces/user-name';
import { from, take } from 'rxjs';

let user: UserId = {
  userId: 1
}

let reimbursement: ReiId = {
  reiId: 0
}

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  API_URL = `http://localhost:9050`;
   
  VIEW_REI = `${this.API_URL}/viewreimbursements` 
  VIEW_USER = `${this.API_URL}/getmngusername`;
  APP_REI = `${this.API_URL}/approvereimbursement`;
  DEN_REI = `${this.API_URL}/denyreimbursement`;

  constructor(private http: HttpClient) { }

  public viewAllReimbursements(): Observable<ListEmployeesReimb>{
    return this.http.get<ListEmployeesReimb>(`${this.VIEW_REI}`);            
  }

  public getUserName(): Observable<UserName> {
    return this.http.post<UserName>(`${this.VIEW_USER}`, user);
  } 

  public approveReimbursement(reimbId: number): Observable<any>{
    const headers = { 'content-type': 'application/json'}; 
    reimbursement.reiId = reimbId;
    console.log(reimbursement);
      
      return this.http.post<any>(`${this.APP_REI}`,  
      reimbursement
      , {'headers':headers} );
  }

  public denyReimbursement(reimbId: number): Observable<any>{
    const headers = { 'content-type': 'application/json'}; 
    reimbursement.reiId = reimbId;
    console.log(reimbursement);
      
      return this.http.post<any>(`${this.DEN_REI}`,  
      reimbursement
      , {'headers':headers} );
  }

}
