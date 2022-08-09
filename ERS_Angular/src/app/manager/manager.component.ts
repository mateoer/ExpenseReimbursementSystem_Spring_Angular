import { Component, OnInit } from '@angular/core';
import { ListEmployeesReimb } from '../interfaces/list-employees-reimb';
import { UserName } from '../interfaces/user-name';
import { ManagerService } from '../services/manager-service.service';
import { from, Observable, take } from 'rxjs';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  filterRei : any[] = [' ','APPROVED','PENDING','DENIED'];
  selected: string = ' ';

  // reimbsCopyArray: ListEmployeesReimb[] = []; 
  // reiArray: ListEmployeesReimb[] = [];
  reimbsCopyArray!: ListEmployeesReimb; 
  reiArray!: ListEmployeesReimb;
  
  fNameLName: UserName = {firstName : '', lastName: ''};
  managerName: string = ' ';

  reimbIdNumberApp!: number;
  reimbIdNumberDeny!: number;

  constructor(private mngService : ManagerService) { }

  

  ngOnInit(): void {
    this.onSelect();
    this.mngService.getUserName().subscribe(fNameLName => this.fNameLName = fNameLName);
    this.mngService.viewAllReimbursements().subscribe(reimbsCopyArray => this.reimbsCopyArray = reimbsCopyArray);    
    this.mngService.viewAllReimbursements().subscribe(reiArray => this.reiArray = reiArray);    
  }

  public getUser(){
    this.managerName = this.fNameLName.firstName+" "+this.fNameLName.lastName;
    return this.managerName;
  }

  public approveReimb(){
    this.mngService.approveReimbursement(this.reimbIdNumberApp).subscribe();
  }

  public denyReimb(){
    this.mngService.denyReimbursement(this.reimbIdNumberDeny).subscribe();
  }

  public onSelect(){
    if (this.selected == ' ') {
      // console.log(this.reimbsCopyArray);
      return this.reiArray = this.reimbsCopyArray; 
    } 
      // return this.reiArray = this.reimbsCopyArray
      //   .filter(e => e.User.reimbursements
      //     .filter(b => b.reiStatus == this.selected));
      return null;
  }
  
  public removeFilter(){
    this.selected = ' ';
    this.reiArray = this.reimbsCopyArray; 
  }

  public reloadCurrentPage(){
    window.location.reload();
  }


}
