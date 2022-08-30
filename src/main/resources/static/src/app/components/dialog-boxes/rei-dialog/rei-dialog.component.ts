import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EmpReimbursements } from 'src/app/interfaces/emp-reimbursements';
import { EmployeeService } from 'src/app/services/employee-service.service';
import { MatFormFieldControl, MatFormFieldModule } from '@angular/material/form-field';
import { EmployeeComponent } from '../../employee/employee.component';
import { Reimbursement, ReiType, Status } from 'src/app/interfaces/reimbursement';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-rei-dialog',
  templateUrl: './rei-dialog.component.html',
  styleUrls: ['./rei-dialog.component.css']
})
export class ReiDialogComponent implements OnInit {
 
  constructor(
    public dialogRef: MatDialogRef<ReiDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: EmpReimbursements,
    public empService : EmployeeService,
    public empComponent : EmployeeComponent) 
    {
      
    }
  ngOnInit(): void {  }

  editOn: boolean = false;
  typesOfReimbursement = this.empComponent.typesOfReimbursement;
  editedRei: EmpReimbursements = {
    reiId: this.data.reiId,
    rei_amount: this.data.rei_amount,
    rei_description: this.data.rei_description,
    reiStatus: this.data.reiStatus,
    reiType: this.data.reiType,
    reiAuthor: this.data.reiAuthor,
    rei_resolver: this.data.rei_resolver,
    rei_submitteDate: this.data.rei_submitteDate,
    rei_resolvedDate: this.data.rei_resolvedDate
  }

  @Input() edit_amount: number = 0.00;
  @Input() edit_description: string= '';
  @Input() edit_type: string ='';

  
  responseMessage: any = '';
  cancelReimbursement(){    
    this.empService.cancelReimbursement(this.data.reiId)
      .subscribe((e: any) => this.responseMessage = e);    
  }

  editReimbursement(){
    // this.responseMessage = "Reimbursement editing is not available at the moment";
    if (this.editOn == false) {
      this.editOn = true;
    } else {
      this.editOn = false;
    }
  }

  saveChanges(){
    // this.onNoClick();
    console.log(this.data);
    console.log(this.editedRei);
    if (this.editedRei.rei_amount == null) {
      this.responseMessage = "Invalid input amount. No changes were made."
      return;
    }
   

    let rType = this.editedRei.reiType != this.data.reiType;
    let rAmt = this.editedRei.rei_amount != this.data.rei_amount;
    let rDesc = this.editedRei.rei_description != this.data.rei_description;
    if (rType || rAmt || rDesc) {
      this.empService.editReimbursement(this.editedRei)
          .subscribe((e: any) =>
          {
            this.responseMessage = e;
          });
      setTimeout(() => {      
        this.onNoClick();      
      }, 4000);
    }else{
      this.onNoClick();
    }

  }

  onNoClick(): void {
    this.responseMessage = '';
    this.dialogRef.close();
  }

}
