import { Component, OnInit } from '@angular/core';
import { EmpReimbursements } from '../interfaces/emp-reimbursements';
import { Reimbursement, ReiType, Status } from '../interfaces/reimbursement';
import { UserName } from '../interfaces/user-name';
import { EmployeeService } from '../services/employee-service.service';


@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  filterRei : any[] = [' ','APPROVED','PENDING','DENIED'];
  selected: string = ' ';

  reimbsCopyArray: EmpReimbursements[] = []; 
  reiArray: EmpReimbursements[] = [];

  fNameLName: UserName = {firstName : '', lastName: ''};
  employeeName: string = ' ';

    
  typesOfReimbursement : any[] = ['LODGING','GAS','FOOD','OTHER'];

  
  newReimbursementObj: Reimbursement = {
    rei_amount: 0,
    rei_description: '',
    reiType: ReiType.OTHER,
    reiStatus: Status.PENDING,
    reiId: 0
  };
    
  

  constructor(private empService : EmployeeService) { }  

  ngOnInit() {    
    this.onSelect();
    this.empService.getUserName().subscribe(fNameLName => this.fNameLName = fNameLName);    
    this.empService.getEmpReimbursements().subscribe(reimbsCopyArray => this.reimbsCopyArray = reimbsCopyArray);    
    this.empService.getEmpReimbursements().subscribe(reiArray => this.reiArray = reiArray);
  }
  

   
  public createNewReimbursement() { 
    return this.empService.newReimbursement(this.newReimbursementObj).subscribe();        
  }

  public getUser(){
    this.employeeName = this.fNameLName.firstName+" "+this.fNameLName.lastName;
    return this.employeeName;
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