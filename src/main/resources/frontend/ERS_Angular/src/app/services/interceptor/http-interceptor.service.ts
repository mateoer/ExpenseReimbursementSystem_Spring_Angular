import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GeneralRouteService } from '../general-route.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private urlService: GeneralRouteService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       
    if (req.url != this.urlService.USER_CREDENTIALS) {
      req = req.clone({
        withCredentials: true
      });
    } 
    
    return next.handle(req);
  }
}
