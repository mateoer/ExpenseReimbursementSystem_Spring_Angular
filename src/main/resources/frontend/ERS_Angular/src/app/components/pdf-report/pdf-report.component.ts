import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { PdfReportService } from 'src/app/services/pdf-report.service';

@Component({
  selector: 'app-pdf-report',
  templateUrl: './pdf-report.component.html',
  styleUrls: ['./pdf-report.component.css']
})
export class PdfReportComponent implements OnInit {

  constructor(public pdfReport: PdfReportService) { }

  ngOnInit(): void {
  }

  generate_pdf_report(){
    this.pdfReport.get_employee_report().subscribe((data: HttpResponse<Blob>) => {
      let filename = data.headers.get('content-disposition')!.split(';')[1].split('=')[1].replace(/\"/g, '')
      this.downloadFile(data.body, filename);
    });
  }

  downloadFile(data: any, filename: string) {
    const blob = new Blob([data], { type: 'application/pdf' }); 
    const file = new File([blob], filename)   
    const url= window.URL.createObjectURL(file);
    window.open(url);
  }

}
