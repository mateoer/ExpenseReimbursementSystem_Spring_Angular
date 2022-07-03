package root.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "username", unique = true, nullable = false, length = 20, table = "user_table")
	private String username;
	
	@Column(name = "user_password", unique = false, nullable = false, table = "user_table")
	private String password;
	
	@Column(name = "first_name", unique = false, nullable = false, length = 20, table = "user_table")
	private String firstName;
	
	@Column(name = "last_name", unique = false, nullable = false, length = 20, table = "user_table")
	private String lastName;
	
	@Column(name = "email", unique = true, nullable = false, table = "user_table")
	private String email;	
	
	@Column(name = "user_role_number", table = "user_table")
	private int userRoleNumber;
	
	
	
}





