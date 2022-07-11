package root.model;

import java.util.List;

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

import lombok.Data;

@Data
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorValue("user_role")
//@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Table(name = "user_table")
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
	
	@OneToMany(mappedBy = "rei_author", fetch = FetchType.EAGER)
//	@JoinColumn(name = "rei_author", referencedColumnName = "user_id")
	private List<Reimbursement> reimbursements;
	
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
	
	
	
}






