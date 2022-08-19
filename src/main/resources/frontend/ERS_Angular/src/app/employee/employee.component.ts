import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmpReimbursements } from '../interfaces/emp-reimbursements';
import { Reimbursement, ReiType, Status } from '../interfaces/reimbursement';
import { LoginComponent } from '../login/login.component';
import { EmployeeService } from '../services/employee-service.service';


@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit, OnDestroy {

  filterRei : any[] = [' ','APPROVED','PENDING','DENIED'];
  selected: string = ' ';

  reimbsCopyArray: EmpReimbursements[] = []; 
  reiArray: EmpReimbursements[] = [];

    
  typesOfReimbursement : any[] = ['LODGING','GAS','FOOD','OTHER'];
  
  newReimbursementObj: Reimbursement = {
    rei_amount: NaN,
    rei_description: '',
    reiType: ReiType.OTHER,
    reiStatus: Status.PENDING,
    reiId: 0
  };  

  constructor(public empService : EmployeeService, 
              public loginComponent: LoginComponent,
              public _route: Router) { } 
  
  ngOnDestroy(): void {
    sessionStorage.clear();
    localStorage.clear();
    this.empService.getEmpReimbursements()
      .subscribe(reimbsCopyArray => this.reimbsCopyArray = reimbsCopyArray)
      .unsubscribe();

    this.empService.getEmpReimbursements()
      .subscribe(reiArray => this.reiArray = reiArray)
      .unsubscribe();  
  }  

  ngOnInit() {    
    this.onSelect();
    if (JSON.parse(sessionStorage.getItem('found')!) == false) {
      this._route.navigate(["/login"]);
    }
    this.empService.getEmpReimbursements().subscribe(reimbsCopyArray => this.reimbsCopyArray = reimbsCopyArray);    
    this.empService.getEmpReimbursements().subscribe(reiArray => this.reiArray = reiArray);
  }  
  
   
  public createNewReimbursement() { 
    this.empService.newReimbursement(this.newReimbursementObj).subscribe();
    this.newReimbursementObj.rei_description = '';
    this.newReimbursementObj.rei_amount = NaN;
    this.newReimbursementObj.reiType = ReiType.OTHER;
  }

  public getUser(){
    if (JSON.parse(sessionStorage.getItem('found')!) == false) {
      this._route.navigate(["/login"]);
    }
    return sessionStorage.getItem('firstName')+" "+ sessionStorage.getItem('lastName');
  }

  public onSelect(){
    if (this.selected == ' ') {
      return this.reiArray = this.reimbsCopyArray; 
    } 
      return this.reiArray = this.reimbsCopyArray.filter(e => e.reiStatus == this.selected);    
  }
  
  public removeFilter(){
    this.selected = ' ';
    this.reiArray = this.reimbsCopyArray; 
  }

  public refreshTable(){
    // window.location.reload();
    this.removeFilter();
    this.ngOnInit();
  }

}