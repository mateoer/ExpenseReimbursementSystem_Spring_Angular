import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EmpReimbursements } from 'src/app/interfaces/emp-reimbursements';
import { EmployeeService } from 'src/app/services/employee-service.service';
import { MatFormFieldControl, MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-rei-dialog',
  templateUrl: './rei-dialog.component.html',
  styleUrls: ['./rei-dialog.component.css']
})
export class ReiDialogComponent implements OnInit {
 
  constructor(
    public dialogRef: MatDialogRef<ReiDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: EmpReimbursements,
    public empService : EmployeeService) 
    {
      
    }
  ngOnInit(): void {  }

  // editOn: boolean = false;
  editOn: boolean = true;

  
  responseMessage: any = '';
  cancelReimbursement(){    
    this.empService.cancelReimbursement(this.data.reiId)
      .subscribe((e: any) => this.responseMessage = e);    
  }

  editReimbursement(){
    this.responseMessage = "Reimbursement editing is not available at the moment";
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
