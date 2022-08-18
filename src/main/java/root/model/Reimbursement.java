package root.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

@Setter
@Getter
@AllArgsConstructor
@ToString

@Entity
@Table(name = "reimbursements_table")
@JsonIgnoreProperties(value = {"reimbursementAuthor"}, allowSetters = true, allowGetters = true)
public class Reimbursement {
	
	
	public Reimbursement(double rei_amount, String rei_description) {
		super();
		this.rei_amount = rei_amount;
		this.rei_description = rei_description;
	}

	@Id
	@Column (name = "rei_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int reiId;
	
	@Column (name = "rei_amount")
	private double rei_amount;
	
	@Column (name = "rei_submitted_date")
	private LocalDateTime rei_submitteDate;
	
	@Column (name = "rei_resolved_date")
	private LocalDateTime rei_resolvedDate;
	
	@Column (name = "rei_description")
	private String rei_description;
	
	@Column(name = "rei_author"/* , insertable = false ,updatable = false */)
	private Integer reiAuthor;
	
	@Column (name = "rei_resolver")
	private Integer rei_resolver;
	
	
	
	@Enumerated (EnumType.STRING)
	private ReiStatus reiStatus;
	
		
	@Enumerated (EnumType.STRING)
	private ReiType reiType;
	
	@Transient
	@ManyToOne(fetch = FetchType.LAZY)
//	@JsonBackReference
//	@JoinColumn(name = "rei_author", referencedColumnName = "user_id")
	private User reimbursementAuthor;

	public Reimbursement() {
		
	}
	
	public Reimbursement(double rei_amount, String rei_description, ReiStatus reiStatus,
			ReiType reiType) {
		super();
		this.rei_amount = rei_amount;
		this.rei_description = rei_description;
		this.reiStatus = reiStatus;
		this.reiType = reiType;
	}

	public Reimbursement(double rei_amount, String rei_description, int reiAuthor) {
		super();
		this.rei_amount = rei_amount;
		this.rei_description = rei_description;
		this.reiAuthor = reiAuthor;
	}

	
	
	
	
	
	
}
