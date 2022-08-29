import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PasswordResetService } from 'src/app/services/password-reset.service';
import { ProfilePictureService } from 'src/app/services/profile-picture.service';

@Component({
  selector: 'app-finalizepasswordreset',
  templateUrl: './finalizepasswordreset.component.html',
  styleUrls: ['./finalizepasswordreset.component.css']
})
export class FinalizepasswordresetComponent implements OnInit {

  constructor(public _route: Router, public pfp: ProfilePictureService,public resetService: PasswordResetService) { }

  message = '';
  reset_token= '';
  new_password='';
  re_new_password='';
  userImage:any = null;
  ngOnInit(): void {
    if (sessionStorage.getItem('profilePicture') != 'null') {
      this.pfp.getProfilePicture().subscribe(userImage => this.userImage = userImage );      
    }else{
      this.userImage = "../../../assets/avatar_2x.png";
    }
  }

  back(){
    const moveTo = sessionStorage.getItem('userRole')!.toLowerCase();
    this._route.navigate([`/${moveTo}`]);
  }
  passwordReset(){
    if ((this.new_password != '' && this.reset_token != '') && (this.new_password === this.re_new_password)){
      this.resetService.validateResetToken(this.reset_token, this.new_password)
        .subscribe(e => {
          this.message = e;
          this.reset_token='';
          this.new_password='';
          this.re_new_password='';
      });
    }
  }

}
