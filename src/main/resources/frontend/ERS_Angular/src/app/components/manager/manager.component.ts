import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { EmpReimbursements } from '../../interfaces/emp-reimbursements';
import { LoginComponent } from '../login/login.component';
import { ManagerService } from '../../services/manager-service.service';
import { MaterialTableProps } from 'material-table';
import { NoopScrollStrategy } from '@angular/cdk/overlay';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ReiDialogComponent } from '../dialog-boxes/rei-dialog/rei-dialog.component';
import { MngDialogComponent } from '../dialog-boxes/mng-dialog/mng-dialog.component';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  reimbAppStatusMessage:any ='';
  reimbDeniedStatusMessage:any ='';

  constructor(private mngService : ManagerService, 
              public loginComponent: LoginComponent,
              public _route: Router, public dialog: MatDialog) { }

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

    ///////new table function
    this.loadReimbursements();
  }
  
  ////////////NEW TABLE STUFF START HERE////////////////////////////////////////////////
  
  displayedColumns: string[] = ['ID','Amount','Description','Status','Type','Submitted','Resolved'];
  dataSource!: MatTableDataSource<EmpReimbursements>;
  dataLoading: boolean = true;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  public handleReimbursements(reimbursements : EmpReimbursements[]){
    this.dataLoading = false;
    this.dataSource = new MatTableDataSource(reimbursements);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;    
  }

  public loadReimbursements(){
    this.mngService.viewListOfAllReimbursements().subscribe(
      (reimbursements : EmpReimbursements[]) => 
      {
        reimbursements.sort((a,b)=> b.reiId - a.reiId);
        this.handleReimbursements(reimbursements);  
      }    
    );
  }
 
  //////DIALOG BOX CONFIGURATION////////////////////////////
  public displayReimbursement(reimbursement: EmpReimbursements){
    
    let dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.data = reimbursement;
    // dialogConfig.width = '700px';
    // dialogConfig.height = '450px';
    dialogConfig.scrollStrategy =  new NoopScrollStrategy();

    this.dialog.open(MngDialogComponent, dialogConfig).afterClosed()
      .subscribe( );
      
  }
  ////////////NEW TABLE STUFF END HERE////////////////////////////////////////////////  


  /////////////////OLD STUFF FROM HERE DOWN///////////////
  
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
  
  
  public getUser(){
    if (JSON.parse(sessionStorage.getItem('found')!) == false) {
      this._route.navigate(["/login"]);
    }
    return sessionStorage.getItem('firstName')+" "+ sessionStorage.getItem('lastName');
  }

  public approveReimb(){
    this.mngService.approveReimbursement(this.reimbIdNumberApp)
      .subscribe((e:any)=>
      {      
        this.reimbAppStatusMessage = e;       
      });      
      this.reimbIdNumberApp = NaN;
      setTimeout(() => {
        this.reimbAppStatusMessage = ''
      }, 2000);
  }

  
  public denyReimb(){
    this.mngService.denyReimbursement(this.reimbIdNumberDeny).subscribe((e:any)=> this.reimbDeniedStatusMessage = e);
    this.reimbIdNumberDeny = NaN;
    setTimeout(() => {
      this.reimbDeniedStatusMessage = ''
    }, 2000);
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


