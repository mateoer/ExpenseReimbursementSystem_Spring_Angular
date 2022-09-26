import { Component, Inject, Injectable, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ReiListMap } from 'src/app/interfaces/rei-list-map';
import { ManagerService } from 'src/app/services/manager-service.service';
import { ManagerComponent } from '../../manager/manager.component';

@Injectable({
  providedIn: 'root'
})
@Component({
  selector: 'app-mng-dialog',
  templateUrl: './mng-dialog.component.html',
  styleUrls: ['./mng-dialog.component.css']
})
export class MngDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<MngDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ReiListMap,
    public mngService : ManagerService,
    public mngComponent: ManagerComponent) 
    {
      
    }
  ngOnInit(): void {  }

 
  
  responseMessage: any = '';  
  approveReimbursement(){
    this.mngService.approveReimbursement(this.data.reiId)
      .subscribe((e: any) =>
      {
        this.responseMessage = e;
      });
      
  }

  denyReimbursement(){
    this.mngService.denyReimbursement(this.data.reiId)
      .subscribe((e: any) =>
      {
        this.responseMessage = e;        
      }); 
      
  }

  reimbursement : ReiListMap = {
    userId: this.data.userId,
    firstName: this.data.firstName,
    lastName: this.data.lastName,
    username: this.data.username,    
    userRole: this.data.userRole,

    reiId: this.data.reiId,
    rei_amount: this.data.rei_amount,
    rei_description: this.data.rei_description,
    reiStatus: this.data.reiStatus,
    reiType: this.data.reiType,
    reiAuthor: this.data.reiAuthor,
    rei_resolver: this.data.rei_resolver,
    rei_submitteDate: this.data.rei_submitteDate,
    rei_resolvedDate: this.data.rei_resolvedDate,
    receiptPicName: this.data.receiptPicName
  }

  viewReceipt(){
    let receiptLocation: any;
    this.mngService.viewReceipt(this.reimbursement.username, this.reimbursement.receiptPicName).subscribe(
      e => {
        receiptLocation = e;
        
        // window.location.href = `${receiptLocation}`;
        window.open(`${receiptLocation}`);
       
      }
    );
  }
  

  onNoClick(): void {
    this.dialogRef.close();
  }

}
