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
    // sessionStorage.clear();
    // localStorage.clear();
    // this.mngService.viewListOfAllReimbursements()
    //   .subscribe(copyOfReiList => this.copyOfReiList = copyOfReiList)
    //   .unsubscribe();

    // this.mngService.viewListOfAllReimbursements()
    //   .subscribe(reiList => this.reiList = reiList)
    //   .unsubscribe();  
  }
  
  filterRei :     any[] = [' ','APPROVED','PENDING','DENIED'];
  filterReiType : any[] = [' ','LODGING','GAS','FOOD','OTHER'];
  selected: string = ' ';   
  selectedType: string = ' ';   
  
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
    this.mngService.viewListOfAllReimbursements().subscribe(reiList => {
      this.reiList = reiList;
      this.reiList.sort((a,b)=> b.reiId - a.reiId);
    });
  }
  
  public getUser(){
    if (JSON.parse(sessionStorage.getItem('found')!) == false) {
      this._route.navigate(["/login"]);
    }
    return sessionStorage.getItem('firstName')+" "+ sessionStorage.getItem('lastName');
  }

  public approveReimb(){
    this.mngService.approveReimbursement(this.reimbIdNumberApp).subscribe();
    this.reimbIdNumberApp = NaN;
  }

  public denyReimb(){
    this.mngService.denyReimbursement(this.reimbIdNumberDeny).subscribe();
    this.reimbIdNumberDeny = NaN;
  }

  public onSelect(){
    if (this.selected == ' ') {
      this.reiList = this.copyOfReiList;       
      return this.reiList.sort((a,b)=> b.reiId - a.reiId);
    } 
      this.selectedType = ' ';
      return this.reiList = this.copyOfReiList
        .sort((a,b)=> b.reiId - a.reiId)
          .filter(e => e.reiStatus == this.selected);      
  }

  public onSelectType(){
    if (this.selectedType == ' ') {
      this.reiList = this.copyOfReiList; 
      return this.reiList.sort((a,b)=> b.reiId - a.reiId);
    } 
      this.selected = ' ';
      return this.reiList = this.copyOfReiList
        .sort((a,b)=> b.reiId - a.reiId)
          .filter(e => e.reiType == this.selectedType);
  }
  
  public removeFilter(){
    this.selected = ' ';
    this.selectedType = ' ';
    this.reiList = this.copyOfReiList; 
  }

  public refreshTable(){
    // window.location.reload();
    this.removeFilter();
    this.ngOnInit();
  }


}


