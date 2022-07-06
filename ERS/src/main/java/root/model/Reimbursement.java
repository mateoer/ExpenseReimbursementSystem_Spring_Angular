package root.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "reimbursements_table")
public class Reimbursement {
	
	
	@Id
	@Column (name = "rei_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int reiId;
	
	@Column (name = "rei_amount", nullable = false)
	private int rei_amount;
	
	@Column (name = "rei_submitted_date")
	private Date rei_submitteDate;
	
	@Column (name = "rei_resolved_date")
	private Date rei_resolvedDate;
	
	@Column (name = "rei_description")
	private String rei_description;
	
	@Column (name = "rei_author")
	private int rei_author;
	
	@Column (name = "rei_resolver")
	private int rei_resolver;
	
	
	public enum ReiStatus {
		PENDING,
		DENIED,
		APPROVED
	}
	
	@Enumerated 
	private ReiStatus reiStatus;
	
	public enum ReiType {
		FOOD,
		LODGING,
		GAS,
		OTHER		
	}
	
	@Enumerated
	private ReiType reiType;
	
	
}
