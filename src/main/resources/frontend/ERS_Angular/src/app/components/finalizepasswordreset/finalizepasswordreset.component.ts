import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PasswordResetService } from 'src/app/services/password-reset.service';
import { ProfilePictureService } from 'src/app/services/profile-picture.service';

@Component({
  selector: 'app-finalizepasswordreset',
  templateUrl: './finalizepasswordreset.component.html',
  styleUrls: ['./finalizepasswordreset.component.css']
})
export class FinalizepasswordresetComponent implements OnInit {

  constructor(public _route: Router, 
              public pfp: ProfilePictureService,
              public resetService: PasswordResetService,
              public actRoute: ActivatedRoute) { }

  resetToken : any;  

  message = '';  
  new_password='';
  re_new_password='';
  userImage:any = null;
  ngOnInit(): void {
    if (sessionStorage.getItem('profilePicture') != 'null') {
      this.pfp.getProfilePicture().subscribe(userImage => this.userImage = userImage );      
    }else{
      this.userImage = "//ssl.gstatic.com/accounts/ui/avatar_2x.png";
    }

    this.resetToken = this.actRoute.snapshot.paramMap.get('resetToken');

  }

  back(){
    const moveTo = sessionStorage.getItem('userRole')!.toLowerCase();
    this._route.navigate([`/${moveTo}`]);
  }
  passwordReset(){
    if ((this.new_password != '' && this.resetToken != '') && (this.new_password === this.re_new_password)){
      this.resetService.validateResetToken(this.resetToken, this.new_password)
        .subscribe(e => {
          this.message = e;
          this.resetToken='';
          this.new_password='';
          this.re_new_password='';
      });
    }
  }

}
