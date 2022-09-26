package root.model;

import java.io.Serializable;

import com.itextpdf.text.Document;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PDFResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4398772908857067372L;
	private String responseText = "PDF report generated successfully!";
	private Document document;
	private String filename;
}
