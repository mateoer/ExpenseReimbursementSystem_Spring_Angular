package root.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import root.model.PDFResponse;
import root.model.User;
import root.service.pdf.PDFGenerator;

@RestController
@CrossOrigin
public class PDFController {

	private PDFGenerator pdfGenerator;

	@Autowired
	public PDFController(PDFGenerator pdfGenerator) {
		this.pdfGenerator = pdfGenerator;
	}

	@PostMapping(value = "/generatePdf", produces = "application/pdf")
	public ResponseEntity<ByteArrayResource> generateEmployeePDF(@RequestBody User user, HttpServletResponse response) 
			throws DocumentException, IOException {
		if (user != null) {

			PDFResponse pdfResponse = pdfGenerator.generatePDFReport(user);
			
			if(pdfResponse.getDocument() == null) return null;
			
//			Document docResponse = pdfResponse.getDocument();
			String filename = pdfResponse.getFilename();
			String stringResponse = pdfResponse.getResponseText();
			
//			MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
			MediaType mediaType = MediaType.APPLICATION_JSON;
	        System.out.println("fileName: " + filename);
	        System.out.println("mediaType: " + mediaType);

	        Path path = Paths.get(filename);
	        byte[] data = Files.readAllBytes(path);
	        ByteArrayResource resource = new ByteArrayResource(data);
	        
	        
	        File myFile = new File(filename);
	        myFile.delete();
	        
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
	                .contentType(mediaType) 
	                .contentLength(data.length) 
	                //This line allows Angular to retrieve filename from content disposition
	                //Postman does not have a problem with this tho
	                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
	                .header("Response-Message", stringResponse)
	                .body(resource);      

		}
		return null;
	}	

}
