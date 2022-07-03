package root.daotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import root.dao.UserDao;
import root.model.User;

@DataJpaTest
//@Sql(scripts = "/user-schema.sql")
@Sql(scripts = "/create-data.sql")
//@Sql(scripts = "/cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class UserRepositoryH2Test {

	@Autowired
	private UserDao userRepository;
	
//	@AfterAll
//	void tearDown() throws Exception{
//		userRepository.deleteAll();
//	}

	@Test
	void findByUsernameTest() {
		
		User user = userRepository.findByUsername("mateoer");
		assertNotNull(user.getUsername(), "username must be [mateoer]");
		assertEquals("mateoer", user.getUsername());
		
		System.out.println(user.getUserId()+" "+user.getUsername()+" "+user.getFirstName()+" "+user.getLastName()
							+" "+user.getEmail()+" "+user.getPassword()+" "+user.getUserRole());
		
	}
	@Test
	void findByEmailTest() {
		
		User user = userRepository.findByEmail("mateoer@kean.edu");
		assertNotNull(user.getEmail(), "email must be [mateoer@kean.edu]");
		assertEquals("mateoer@kean.edu", user.getEmail());
		
		System.out.println(user.getUserId()+" "+user.getUsername()+" "+user.getFirstName()+" "+user.getLastName()
		+" "+user.getEmail()+" "+user.getPassword()+" "+user.getUserRole());
		
	}

}
