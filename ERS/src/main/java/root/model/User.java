package root.model;

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
@Table (name = "user_table")

public class User {
	
	@Id
	@Column (name = "user_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name = "username", unique = true, nullable = false, length = 20)
	private String username;
	
	@Column(name = "user_password", unique = false, nullable = false)
	private String password;
	
	@Column(name = "first_name", unique = false, nullable = false, length = 20)
	private String firstName;
	
	@Column(name = "last_name", unique = false, nullable = false, length = 20)
	private String lastName;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;	
	
	
	public enum UserRole {	
		MANAGER ,
		EMPLOYEE 	
	}
	
	
	
	@Enumerated
	private UserRole userRole;	
	
	
}






