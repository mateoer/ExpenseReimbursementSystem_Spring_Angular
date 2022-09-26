import { Component, Inject, Injectable, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { EmpReimbursements } from '../../interfaces/emp-reimbursements';
import { Reimbursement, ReiType, Status } from '../../interfaces/reimbursement';
import { LoginComponent } from '../login/login.component';
import { EmployeeService } from '../../services/employee-service.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ReiDialogComponent } from '../dialog-boxes/rei-dialog/rei-dialog.component';
import { BlockScrollStrategy, NoopScrollStrategy } from '@angular/cdk/overlay';
import { MatFormFieldControl, MatFormFieldModule } from '@angular/material/form-field';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  newReimbCreatedMessage: any = '';

  constructor(public empService : EmployeeService, 
              public loginComponent: LoginComponent,
              public _route: Router,
  
              public dialog: MatDialog
  ){ } 

  ngOnInit() {    
      this.onSelect();
      if (JSON.parse(sessionStorage.getItem('found')!) == false) {
        this._route.navigate(["/login"]);
      } 

        ///////new table function
        this.loadReimbursements();
        this.employeeGuard();
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
    this.empService.getEmpReimbursements().subscribe(
      (reimbursements : EmpReimbursements[]) => 
      {
        reimbursements.sort((a,b)=> b.reiId - a.reiId);
        this.reimbsCopyArray = reimbursements;
        this.reiArray = reimbursements;        
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

    this.dialog.open(ReiDialogComponent, dialogConfig).afterClosed()
      .subscribe( );
      
  }
  ////////////NEW TABLE STUFF END HERE////////////////////////////////////////////////  


  /////////////////OLD STUFF FROM HERE DOWN///////////////

  filterRei :     any[] = [' ','APPROVED','PENDING','DENIED'];
  filterReiType : any[] = [' ','LODGING','GAS','FOOD','OTHER'];
  selected: string = ' ';   
  selectedType: string = ' ';   

  reimbsCopyArray: EmpReimbursements[] = []; 
  reiArray: EmpReimbursements[] = [];

    
  typesOfReimbursement : any[] = ['LODGING','GAS','FOOD','OTHER'];
  
  newReimbursementObj: Reimbursement = {
    rei_amount: NaN,
    rei_description: '',
    reiType: ReiType.OTHER,
    reiStatus: Status.PENDING,
    reiId: NaN
  };  
   
  public createNewReimbursement() { 
    this.empService.newReimbursement(this.newReimbursementObj)
        .subscribe((e:any)=> 
        {
            this.newReimbCreatedMessage = e;
        });
    this.newReimbursementObj.rei_description = '';
    this.newReimbursementObj.rei_amount = NaN;
    this.newReimbursementObj.reiType = ReiType.OTHER;
    this.refreshTable();
    setTimeout(() => {
      this.newReimbCreatedMessage = ''
    }, 3000);
  }

  public getUser(){
    if (JSON.parse(sessionStorage.getItem('found')!) == false) {
      this._route.navigate(["/login"]);
    }
    return sessionStorage.getItem('firstName')+" "+ sessionStorage.getItem('lastName');
  }

  public onSelect(){
    if (this.selected == ' ') {
      return this.dataSource = new MatTableDataSource(this.reiArray);
    } 
      this.selectedType = ' ';
      return this.dataSource = new MatTableDataSource(this.reimbsCopyArray.filter(e => e.reiStatus == this.selected));      
  }

  public onSelectType(){
    if (this.selectedType == ' ') {       
      return this.dataSource = new MatTableDataSource(this.reiArray);
    } 
      this.selected = ' ';
      return this.dataSource = new MatTableDataSource(this.reimbsCopyArray.filter(e => e.reiType == this.selectedType));
  }
  
  public removeFilter(){
    this.selected = ' ';
    this.selectedType = ' ';
    this.dataSource = new MatTableDataSource(this.reimbsCopyArray);
  }

  public refreshTable(){    
    this.removeFilter();
    this.ngOnInit();
  }

  public employeeGuard(){
    if (!(sessionStorage.getItem('userRole') == 'EMPLOYEE')) {
      this._route.navigate(["/login"]);
    }
  }

}


