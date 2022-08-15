import { Component, OnDestroy, OnInit } from '@angular/core';
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
    rei_amount: 0,
    rei_description: '',
    reiType: ReiType.OTHER,
    reiStatus: Status.PENDING,
    reiId: 0
  };  

  constructor(public empService : EmployeeService, public loginComponent: LoginComponent) { } 
  
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
    this.empService.getEmpReimbursements().subscribe(reimbsCopyArray => this.reimbsCopyArray = reimbsCopyArray);    
    this.empService.getEmpReimbursements().subscribe(reiArray => this.reiArray = reiArray);
  }  
  
   
  public createNewReimbursement() { 
    return this.empService.newReimbursement(this.newReimbursementObj).subscribe();        
  }

  public getUser(){
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

  public reloadCurrentPage(){
    window.location.reload();
  }

}