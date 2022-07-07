package root.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

@Data
@Entity
@Table(name = "reimbursements_table")
public class Reimbursement {
	
	
	@Id
	@Column (name = "rei_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int reiId;
	
	@Column (name = "rei_amount")
	private int rei_amount;
	
	@Column (name = "rei_submitted_date")
	private Date rei_submitteDate;
	
	@Column (name = "rei_resolved_date")
	private Date rei_resolvedDate;
	
	@Column (name = "rei_description")
	private String rei_description;
	
	@Column (name = "rei_author", insertable = false ,updatable = false)
	private int rei_author;
	
	@Column (name = "rei_resolver")
	private int rei_resolver;
	
	
	
	@Enumerated 
	private ReiStatus reiStatus;
	
		
	@Enumerated
	private ReiType reiType;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rei_author", referencedColumnName = "user_id")
	private User reiAuthor;
	
	
	
	
}
