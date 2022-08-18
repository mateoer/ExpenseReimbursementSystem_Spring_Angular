import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmpReimbursements } from '../interfaces/emp-reimbursements';
import { LoginComponent } from '../login/login.component';
import { ManagerService } from '../services/manager-service.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit, OnDestroy {

  constructor(private mngService : ManagerService, 
              public loginComponent: LoginComponent,
              public _route: Router) { }
  
  ngOnDestroy(): void {
    sessionStorage.clear();
    localStorage.clear();
    this.mngService.viewListOfAllReimbursements()
      .subscribe(copyOfReiList => this.copyOfReiList = copyOfReiList)
      .unsubscribe();

    this.mngService.viewListOfAllReimbursements()
      .subscribe(reiList => this.reiList = reiList)
      .unsubscribe();  
  }
  
  filterRei : any[] = [' ','APPROVED','PENDING','DENIED'];
  selected: string = ' ';   
  
  // reiMapList = new Map<UserList, ListReis[]> ();
  reiList: EmpReimbursements[] = [];
  copyOfReiList: EmpReimbursements[] = [];  

  //These are required to approve/deny reimburseents
  reimbIdNumberApp!: number;
  reimbIdNumberDeny!: number; 


  ngOnInit() {
    this.onSelect();
    if (JSON.parse(sessionStorage.getItem('found')!) == false) {
      this._route.navigate(["/login"]);
    }
    this.mngService.viewListOfAllReimbursements().subscribe(copyOfReiList => this.copyOfReiList = copyOfReiList);
    this.mngService.viewListOfAllReimbursements().subscribe(reiList => this.reiList = reiList);
  }
  
  public getUser(){
    if (JSON.parse(sessionStorage.getItem('found')!) == false) {
      this._route.navigate(["/login"]);
    }
    return sessionStorage.getItem('firstName')+" "+ sessionStorage.getItem('lastName');
  }

  public approveReimb(){
    this.mngService.approveReimbursement(this.reimbIdNumberApp).subscribe();
  }

  public denyReimb(){
    this.mngService.denyReimbursement(this.reimbIdNumberDeny).subscribe();
  }

  public onSelect(){
    if (this.selected == ' ') {
      return this.reiList = this.copyOfReiList; 
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


