package root.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorValue("user_role")
//@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Table(name = "user_table")
@JsonIgnoreProperties(value = {"reimbursements"}, allowSetters = true, allowGetters = true)
public class User {
	
		
	@Id
	@Column (name = "user_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name = "username", unique = true,  length = 20)
	private String username;
	
	@Column(name = "user_password", unique = false)
	private String password;
	
	@Column(name = "first_name", unique = false, length = 20)
	private String firstName;
	
	@Column(name = "last_name", unique = false, length = 20)
	private String lastName;
	
	@Column(name = "email", unique = true)
	private String email;	
	
	
	@Column(name = "user_role")
	@Enumerated (EnumType.STRING)
	private UserRole userRole;	
	
	@Column(name = "password_reset_token")
	private String passwordResetToken;
	
	@Column(name = "profile_picture_name")
	private String profilePicName;
	
	@Transient
//	@JsonManagedReference
	@OneToMany(mappedBy = "reimbursementAuthor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	@JoinColumn(name = "rei_author", referencedColumnName = "user_id")
	private List<Reimbursement> reimbursements = new ArrayList<>();
	
	public void addReimbursements(Reimbursement rei) {
		reimbursements.add(rei);
		rei.setReimbursementAuthor(this);
	}
	public void removeReimbursements(Reimbursement rei) {
		reimbursements.remove(rei);
		rei.setReimbursementAuthor(null);
	}
	
	public User () {
		
	}
	public User (int userId) {
		this.userId = userId;
	}
	public User(String username, String password, String firstName, String lastName, String email, UserRole userRole) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}
	public User(String username) {
		super();
		this.username = username;
	}
	
	
	
}






