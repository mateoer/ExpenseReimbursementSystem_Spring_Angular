import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { EmpReimbursements } from '../interfaces/emp-reimbursements';
import { Reimbursement, ReiType, Status } from '../interfaces/reimbursement';
import { UserId } from '../interfaces/user-id';
import { UserName } from '../interfaces/user-name';




let user: UserId = {
  userId: 1
}


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  
  API_URL = `http://localhost:9050`;
   
  GET_REI = `${this.API_URL}/getreimbursements` 
  GET_USER = `${this.API_URL}/getusername`;
  NEW_REI = `${this.API_URL}/addreimbursement`;

  private newReimbursementObj = new Subject<Reimbursement>();

  public getNewRei(): Observable<Reimbursement>{
    return this.newReimbursementObj.asObservable();
  }

  public setNewRei(reiToSetFrom: Reimbursement){
    this.newReimbursementObj.next(reiToSetFrom);
  }

  constructor(private http: HttpClient) { }
  
  public getEmpReimbursements() : Observable<EmpReimbursements[]> {
    return this.http.post<EmpReimbursements[]>(`${this.GET_REI}`, user);
  }

  public getUserName(): Observable<UserName> {
    return this.http.post<UserName>(`${this.GET_USER}`, user);
  }
  
  
  

  public newReimbursement(NRO: Reimbursement): Observable<any> | any{
      const headers = { 'content-type': 'application/json'}; 
      const reimbursement = NRO;
      
      console.log(this.getNewRei());
      return this.http.post<any>(`${this.API_URL}/addreimbursement`,  
      {
        reimbursement,
        user    
      }
      , {'headers':headers} );    
  } 
  
}
