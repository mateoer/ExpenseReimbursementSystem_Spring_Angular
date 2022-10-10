import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GeneralRouteService } from './general-route.service';

@Injectable({
  providedIn: 'root'
})
export class PdfReportService {

  constructor(private http: HttpClient, private urlService: GeneralRouteService) { }

  get_employee_report(): Observable<HttpResponse<Blob>>{
    let headers = new HttpHeaders();
        headers = headers.set('Accept', 'application/pdf');
    return this.http.post<Blob>(`${this.urlService.EMP_REPORT}`,
    {
      "userId": JSON.parse(sessionStorage.getItem("userId")!)
    },
    { observe: 'response', responseType: 'blob' as 'json' });
  }
}
