import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { PasswordResetService } from 'src/app/services/password-reset.service';
import { ProfilePictureService } from 'src/app/services/profile-picture.service';
import { FinalizepasswordresetComponent } from '../finalizepasswordreset.component';

@Component({
  selector: 'app-child-finalize-reset',
  templateUrl: './child-finalize-reset.component.html',
  styleUrls: ['./child-finalize-reset.component.css']
})
export class ChildFinalizeResetComponent extends FinalizepasswordresetComponent implements OnInit, OnChanges {

  constructor( _route: Router,  pfp: ProfilePictureService, resetService: PasswordResetService) {
    super(_route,pfp,resetService);
  }

  @Input() override message!: string;
  @Input() override reset_token!: string;
  @Input() override new_password!: string;
  @Input() override re_new_password!: string;
  changelog: string[] = []; 
  ngOnChanges(changes: SimpleChanges): void { 
        // tslint:disable-next-line:forin
        for (const propName in changes) {
             const change = changes[propName];
             const to  = JSON.stringify(change.currentValue);
             const from = JSON.stringify(change.previousValue);
             const changeLog = `${propName}: changed from ${from} to ${to} `;
             this.changelog.push(changeLog);
        }
  }

  override ngOnInit(): void { }

}
