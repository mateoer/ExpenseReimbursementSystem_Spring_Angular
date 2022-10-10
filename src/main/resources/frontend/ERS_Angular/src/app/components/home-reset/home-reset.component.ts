import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { ProfilePictureComponent } from 'src/app/components/profile-picture/profile-picture.component';
import { PasswordResetService } from 'src/app/services/password-reset.service';
import { ProfilePictureService } from 'src/app/services/profile-picture.service';

@Component({
  selector: 'app-home-reset',
  templateUrl: './home-reset.component.html',
  styleUrls: ['./home-reset.component.css']
})
export class HomeResetComponent implements OnInit {
  
  constructor(public _route: Router, public pfp: ProfilePictureService,public resetService: PasswordResetService) { }

  message = '';
  password= '';
  new_password='';
  re_new_password='';
  userImage: string = "../../../assets/avatar_2x.png";
  ngOnInit() {
    
    if (sessionStorage.getItem('profilePicture') != 'null' || '' || null || undefined) {
      this.pfp.getProfilePicture().subscribe(userImage => this.userImage = userImage );      
    }
    
  }

  back(){
    const moveTo = sessionStorage.getItem('userRole')!.toLowerCase();
    this._route.navigate([`/${moveTo}`]);
  }
  passwordReset(){
    if ((this.new_password != '' && this.password != '') && (this.new_password === this.re_new_password)){
      this.resetService.validateFromHome(this.password, this.new_password)
        .subscribe(e => {
          this.message = e;
          this.password='';
          this.new_password='';
          this.re_new_password='';
      });
    }
  }
  
}


