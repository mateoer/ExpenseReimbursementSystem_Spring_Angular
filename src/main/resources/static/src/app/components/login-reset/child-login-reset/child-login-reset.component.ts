import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { PasswordResetService } from 'src/app/services/password-reset.service';
import { LoginResetComponent } from '../login-reset.component';

@Component({
  selector: 'app-child-login-reset',
  templateUrl: './child-login-reset.component.html',
  styleUrls: ['./child-login-reset.component.css']
})
export class ChildLoginResetComponent extends LoginResetComponent implements OnInit, OnChanges {

  constructor(resetService: PasswordResetService) {
    super(resetService);
  }

  @Input() override re_email!: string;
  @Input() override email!: string;
  @Input() override username!: string;
  @Input() override message!: any;

  changelog: string[] = [];
  
  override ngOnInit(): void { }
  ngOnChanges(changes: SimpleChanges): void {
    // console.log('OnChanges');
    //     console.log(JSON.stringify(changes));
 
        // tslint:disable-next-line:forin
        for (const propName in changes) {
             const change = changes[propName];
             const to  = JSON.stringify(change.currentValue);
             const from = JSON.stringify(change.previousValue);
             const changeLog = `${propName}: changed from ${from} to ${to} `;
             this.changelog.push(changeLog);
        }
  }


}
