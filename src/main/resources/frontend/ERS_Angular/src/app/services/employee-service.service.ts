import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmpReimbursements } from '../interfaces/emp-reimbursements';
import { Reimbursement } from '../interfaces/reimbursement';
import { UserId } from '../interfaces/user-id';
import { UserName } from '../interfaces/user-name';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  constructor(private http: HttpClient) { }

  

  API_URL = `http://localhost:9050`;
  

  GET_REI = `${this.API_URL}/getreimbursements` 
  GET_USER = `${this.API_URL}/getusername`;
  NEW_REI = `${this.API_URL}/addreimbursement`;
  
  public getEmpReimbursements() : Observable<EmpReimbursements[]> {
    let myUserId = JSON.parse(sessionStorage.getItem('userId')!);
      const user: UserId = {
        userId: myUserId
      };
    return this.http.post<EmpReimbursements[]>(`${this.GET_REI}`, user);
  }

  public getUserName(): Observable<UserName> {
    let myUserId = JSON.parse(sessionStorage.getItem('userId')!);
      const user: UserId = {
        userId: myUserId
      };
    return this.http.post<UserName>(`${this.GET_USER}`, user);
  }  

  public newReimbursement(NRO: Reimbursement): Observable<any> | any{    
      const headers = { 'content-type': 'application/json'}; 
      const reimbursement = NRO;
      let myUserId = JSON.parse(sessionStorage.getItem('userId')!);
      const user: UserId = {
        userId: myUserId
      };  
      return this.http.post<any>(`${this.NEW_REI}`,  
      {
        reimbursement,
        user    
      }
      , {'headers':headers} );    
  } 
  
}
