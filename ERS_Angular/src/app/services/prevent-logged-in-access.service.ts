import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';

@Injectable({
  providedIn: 'root'
})
export class PreventLoggedInAccessService implements CanActivate {

  constructor(private login: LoginComponent, public _route: Router) { }

  canActivate(): boolean {
    if (!this.login.userLoggedIn()) {
      this._route.navigate([`/login`]);
    }
    return this.login.userLoggedIn();      
  }
}
