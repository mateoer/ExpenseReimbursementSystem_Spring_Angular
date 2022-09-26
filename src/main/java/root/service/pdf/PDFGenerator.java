package root.service.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import root.dao.ReimbursementRepository;
import root.dao.UserRepository;
import root.model.PDFResponse;
import root.model.Reimbursement;
import root.model.User;
import root.model.UserRole;

@Service
public class PDFGenerator {
	
//	private String pdfDir = "src/main/resources/pdf_report/";
	private String reportFileName = "Reimbursements Report";
	private String reportFileNameDateFormat = "dd_MMMM_yyyy";
	private String localDateFormat = "dd MMMM yyyy HH:mm:ss";
	private String logoImagePath = "src/main/resources/logo/cedar-tree-logo-template.jpg";
	private Float [] logoImageScale = {50F,50F};
	private String currencySymbol = "$";	
	private int noOfColumns = 6;	
	private List<String> columnNames = new ArrayList<>();
	// Amount  Description Status Type Submitted On  Resolved On Resolver
	
	@Autowired
	private ReimbursementRepository reiRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    public PDFGenerator(ReimbursementRepository reiRepo, UserRepository userRepo) {
        this.reiRepo = reiRepo;
        this.userRepo = userRepo;
    }
	
	private static Font COURIER = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
	private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
	private static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
	
	public PDFResponse generatePDFReport(User employee) {
		PDFResponse pdfResponse = new PDFResponse();
		// Amount  Description Status Type Submitted On  Resolved On Resolver
		columnNames.add("Amount");
		columnNames.add("Description");
		columnNames.add("Status");
		columnNames.add("Type");
		columnNames.add("Submitted On");
		columnNames.add("Resolved On");
		
		User myemp = userRepo.findByUserId(employee.getUserId());
		if(myemp.getUserRole() != UserRole.EMPLOYEE) {
			pdfResponse.setResponseText("Invalid user type");
			return pdfResponse;
		}
		
		Document document = new Document();
		try {
			String filename = getPdfNameWithDate();
			FileOutputStream fileOutputStream = new FileOutputStream(filename);
			PdfWriter.getInstance(document, fileOutputStream);
			document.open();
			addLogo(document);
			addDocTitle(document, myemp);
			createTable(document,noOfColumns, employee);
			addFooter(document);
			document.close();
			System.out.println("--------------Your PDF Report is ready!-----------------");
			
			pdfResponse.setDocument(document);
			pdfResponse.setFilename(filename);

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			pdfResponse.setResponseText("Error");
			return pdfResponse;
		}		
		
		return pdfResponse;
	}
	
	private void addLogo(Document document) {
		try {	
			Image img = Image.getInstance(logoImagePath);
			img.scalePercent(logoImageScale[0], logoImageScale[1]);
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addDocTitle(Document document, User employee) throws DocumentException {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
		Paragraph p1 = new Paragraph();
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph(reportFileName, COURIER));
		p1.setAlignment(Element.ALIGN_CENTER);
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph("Report generated on " + localDateString, COURIER_SMALL));
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph("Employee: "+employee.getFirstName()+" "+employee.getLastName()));
		
		document.add(p1);
	}
	
	private void createTable(Document document, int noOfColumns, User employee) throws DocumentException {
		
		
		
		Paragraph paragraph = new Paragraph();
		leaveEmptyLine(paragraph, 3);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(noOfColumns);
		
		for(int i=0; i<noOfColumns; i++) {
			PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.CYAN);
			table.addCell(cell);
		}

		table.setHeaderRows(1);
		getDbData(table, employee);
		document.add(table);
	}
	
	private void getDbData(PdfPTable table, User employee) {
		
		List<Reimbursement> reiList = reiRepo.findByReiAuthor(employee.getUserId());
		for (Reimbursement rei : reiList) {
			
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			table.addCell(currencySymbol + rei.getRei_amount());
			table.addCell(rei.getRei_description());
			table.addCell(rei.getReiStatus().toString());
			table.addCell(rei.getReiType().toString());
			table.addCell(rei.getRei_submitteDate().toString());
			
			if(rei.getRei_resolvedDate() == null) {
				table.addCell("---");
			}else {
				table.addCell(rei.getRei_resolvedDate().toString());				
			}
			
			
			
		}
		
	}
	
	private void addFooter(Document document) throws DocumentException {
		Paragraph p2 = new Paragraph();
		leaveEmptyLine(p2, 3);
		p2.setAlignment(Element.ALIGN_MIDDLE);
		p2.add(new Paragraph(
				"----------------------End Of " +reportFileName+"--------------------", 
				COURIER_SMALL_FOOTER));
		
		document.add(p2);
	}
	
	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	private String getPdfNameWithDate() {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(reportFileNameDateFormat));
		return "Report-"+localDateString+"-"+UUID.randomUUID()+".pdf";
	}
	
}
