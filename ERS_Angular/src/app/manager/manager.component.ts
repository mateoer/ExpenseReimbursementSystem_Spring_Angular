import { Component, OnInit } from '@angular/core';
import { EmpReimbursements } from '../interfaces/emp-reimbursements';
import { UserName } from '../interfaces/user-name';
import { ManagerService } from '../services/manager-service.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  filterRei : any[] = [' ','APPROVED','PENDING','DENIED'];
  selected: string = ' '; 
  
  
  // reiMapList = new Map<UserList, ListReis[]> ();
  reiList: EmpReimbursements[] = [];
  copyOfReiList: EmpReimbursements[] = [];
  
  //Manager's name for welcome message
  fNameLName: UserName = {firstName : '', lastName: ''};
  managerName: string = ' ';

  //These are required to approve/deny reimburseents
  reimbIdNumberApp!: number;
  reimbIdNumberDeny!: number; 

  

  constructor(private mngService : ManagerService) { }

  

  ngOnInit(): void {
    this.onSelect();
    this.mngService.getUserName().subscribe(fNameLName => this.fNameLName = fNameLName);
    this.mngService.viewListOfAllReimbursements().subscribe(reiList => this.reiList = reiList);
    this.mngService.viewListOfAllReimbursements().subscribe(copyOfReiList => this.copyOfReiList = copyOfReiList);
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
      return this.reiList; 
    } 
      return this.reiList = this.copyOfReiList.filter(e => e.reiStatus == this.selected);
  }
  
  public removeFilter(){
    this.selected = ' ';
    this.reiList = this.copyOfReiList; 
  }

  public reloadCurrentPage(){
    window.location.reload();
  }


}


