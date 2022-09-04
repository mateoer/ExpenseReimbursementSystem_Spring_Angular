package root.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import root.model.User;
import root.model.UserRole;


@DataJpaTest
@Sql(scripts = "/create-data.sql")
class UserRepositoryH2Test {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void saveEntitiesToRepository () {
		User myUser = userRepository.findByUsername("suechan");
		
		assertNotNull(myUser.getUsername());
		System.out.println("Username: "+ myUser.getUsername());
		System.out.println("Username: "+ myUser.getUserRole());
	}
	
	@Test
	void setManagerEmployeeTest() {				
		
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
