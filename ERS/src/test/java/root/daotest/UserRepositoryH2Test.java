package root.daotest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import root.dao.UserRepository;
import root.model.User;
import root.model.UserRole;


@DataJpaTest
//@Sql(scripts = "/user-schema.sql")
@Sql(scripts = "/create-data.sql")
//@Sql(scripts = "/cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class UserRepositoryH2Test {

	@Autowired
	private UserRepository userRepository;
	
	@Test
//	@Modifying
//	@Query(value = "INSERT INTO user_table (first_name, last_name, user_password, username, email, type) VALUES ('Eric', 'Mateo', 'helloWorld', 'mateoer','eric234@revature.net', 'E');")
	void saveEntitiesToRepository () {
		User myUser = userRepository.findByUsername("mateoer");
		
		assertNotNull(myUser.getUsername());
		System.out.println("Username: "+ myUser.getUsername());
		System.out.println("Username: "+ myUser.getUserRole());
	}
	
	@Test
	void setManagerEmployeeTest() {		
		
//		User myUser = userRepository.findByUsername("mateoer");		
		
		User myUser = new User();
		myUser.setUsername("sue731");
		
		userRepository.save(myUser);
		
		
		assertThat(userRepository.findById(myUser.getUserId())).isNotNull();
		System.out.println("User ID: "+myUser.getUserId());
		
		myUser.setUserRole(UserRole.MANAGER);
		assertEquals(UserRole.MANAGER, myUser.getUserRole());
		System.out.println("User role: "+myUser.getUserRole());
		
		myUser.setUserRole(UserRole.EMPLOYEE);
		assertEquals(UserRole.EMPLOYEE, myUser.getUserRole());
		System.out.println("User role: "+myUser.getUserRole());
		
		
		
	}

}
