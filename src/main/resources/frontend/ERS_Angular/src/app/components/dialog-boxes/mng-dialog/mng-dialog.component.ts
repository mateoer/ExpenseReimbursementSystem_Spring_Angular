import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EmpReimbursements } from 'src/app/interfaces/emp-reimbursements';
import { ManagerService } from 'src/app/services/manager-service.service';

@Component({
  selector: 'app-mng-dialog',
  templateUrl: './mng-dialog.component.html',
  styleUrls: ['./mng-dialog.component.css']
})
export class MngDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<MngDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: EmpReimbursements,
    public mngService : ManagerService) 
    {
      
    }
  ngOnInit(): void {  }

  
  responseMessage: any = '';  
  approveReimbursement(){
    this.mngService.approveReimbursement(this.data.reiId)
      .subscribe((e: any) => this.responseMessage = e); 
  }

  denyReimbursement(){
    this.mngService.denyReimbursement(this.data.reiId)
      .subscribe((e: any) => this.responseMessage = e);
  }
  

  onNoClick(): void {
    this.dialogRef.close();
  }

}
