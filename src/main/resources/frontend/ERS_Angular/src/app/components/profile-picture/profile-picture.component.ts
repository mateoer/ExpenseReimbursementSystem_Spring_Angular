import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { UserCredentials } from '../../interfaces/user-credentials';
import { UsernamePassword } from '../../interfaces/username-password';
import { LoginService } from '../../services/login-service.service';
import { ProfilePictureService } from '../../services/profile-picture.service';

@Component({
  selector: 'app-profile-picture',
  templateUrl: './profile-picture.component.html',
  styleUrls: ['./profile-picture.component.css']
})
export class ProfilePictureComponent implements OnInit {
  
  src: any = null;  
  size = 200;   
  placeholderPfp = "https://via.placeholder.com/200.png?text=Avitar-Picture";

  constructor(private profileService: ProfilePictureService, public loginService: LoginService) { }

  ngOnInit(): void {
    if (sessionStorage.getItem('profilePicture') != 'null' || '') {
      this.profileService.getProfilePicture().subscribe(src => this.src = src );      
    } 
  }  

  selectedFile!: File;
  onFileSelected(event: any){
    this.selectedFile = <File>event.target.files[0];    
    console.log(event);
    this.uploadPfp();
  }

  uploadPfp(){
    const myPfp = new FormData();
    let userId = sessionStorage.getItem('userId')!;
    myPfp.append('file', this.selectedFile);    
    myPfp.append('userId', userId);    
    this.profileService.uploadProfilePicture(myPfp).subscribe(src => this.src = src);    
  }  

}
