import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from '@angular/core';
import { PasswordResetService } from 'src/app/services/password-reset.service';

@Component({
  selector: 'app-login-reset',
  templateUrl: './login-reset.component.html',
  styleUrls: ['./login-reset.component.css'],
})
export class LoginResetComponent implements OnInit {
  constructor(public resetService: PasswordResetService) {}
  re_email = '';
  username = '';
  email = '';
  message: any = '';
  ngOnInit(): void {}

  passwordReset(){
    if ((this.re_email != '' && this.email != '') && (this.re_email === this.email)){
      this.resetService.validateFromLogin(this.username, this.email)
        .subscribe(e => {
          this.message = e;
          this.re_email='';
          this.email='';
          this.username='';
        });
    }
  }
}
