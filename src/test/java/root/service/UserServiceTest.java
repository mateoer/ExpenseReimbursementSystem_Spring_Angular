package root.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import root.dao.UserRepository;
import root.model.User;
import root.model.UserRole;
import root.service.mail.EmailService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepo;
	
	@Mock
	private EmailService emailService;
	
	@Mock
	private PasswordEncoder passwordEncoder;		
	
	@Mock
	private UserService userService;
	
	
	private User emp1 = new User(1, "sue", "abc123", "Sue", "Liz", "mateoer@kean.edu", UserRole.EMPLOYEE, null, null, null);
		
	private User emp2 = new User(2, "anri", "abc123", "Anri", "Ban", "sdfsagm.com", UserRole.EMPLOYEE, null, null, null);
	
	private User manager = new User(3, "eric", "abc123", "Eric", "Smith", "mateoer@kean.edu", UserRole.MANAGER, null, null, null);
	
	private User emptyUser = new User();	
	
		
	@BeforeEach
	void setUp() {
		userService = new UserService(userRepo, emailService,passwordEncoder);
	}
	
	@Test
	void getUserByUsername() {
		
		when(userRepo.findByUsername(emp1.getUsername())).thenReturn(emp1);
		when(userRepo.findByUsername(emp2.getUsername())).thenReturn(emp2);
		when(userRepo.findByUsername(manager.getUsername())).thenReturn(manager);
		when(userRepo.findByUsername(emptyUser.getUsername())).thenReturn(null);
		
		assertAll(
				()-> assertThat(userService.getUserByUsername(emp1), is(emp1)),
				()-> assertThat(userService.getUserByUsername(emp2), is(emp2)),
				()-> assertThat(userService.getUserByUsername(manager), is(manager)),
				()-> assertNull(userService.getUserByUsername(emptyUser))				
				);
			
	}
	
	@Test
	void getUserByUsernameAndPassword() {
		
		when(userRepo.findByUsername(emp1.getUsername())).thenReturn(emp1);
		when(passwordEncoder.matches(emp1.getPassword(), emp1.getPassword())).thenReturn(true);
		
		when(userRepo.findByUsername(emp2.getUsername())).thenReturn(emp2);
		when(passwordEncoder.matches(emp2.getPassword(), emp2.getPassword())).thenReturn(true);
		
		when(userRepo.findByUsername(manager.getUsername())).thenReturn(manager);
		when(passwordEncoder.matches(manager.getPassword(), manager.getPassword())).thenReturn(true);
		
		when(userRepo.findByUsername(emptyUser.getUsername())).thenReturn(null);		
		
		User fakeUser = new User();
		fakeUser.setUsername("sue");
		fakeUser.setPassword("dsakdkaj");
		
		when(userRepo.findByUsername(fakeUser.getUsername())).thenReturn(emp1);
		when(passwordEncoder.matches(fakeUser.getPassword(), emp1.getPassword())).thenReturn(false);
		
		assertAll(
				()-> assertThat(userService.getUserByUsernameAndPassword(emp1), is(emp1)),
				()-> assertThat(userService.getUserByUsernameAndPassword(emp2), is(emp2)),
				()-> assertThat(userService.getUserByUsernameAndPassword(manager), is(manager)),
				()-> assertNull(userService.getUserByUsernameAndPassword(emptyUser)),
				()-> assertNull(userService.getUserByUsernameAndPassword(fakeUser))
				);		
		
	}
	
	@Test
	void getUserByID() {
		
		when(userRepo.findByUserId(emp1.getUserId())).thenReturn(emp1);
		when(userRepo.findByUserId(emp2.getUserId())).thenReturn(emp2);
		when(userRepo.findByUserId(manager.getUserId())).thenReturn(manager);
		when(userRepo.findByUserId(emptyUser.getUserId())).thenReturn(null);		
		
		assertAll(
				()-> assertThat(userService.getUserByID(emp1.getUserId()), is(emp1)),
				()-> assertThat(userService.getUserByID(emp2.getUserId()), is(emp2)),
				()-> assertThat(userService.getUserByID(manager.getUserId()), is(manager)),
				()-> assertNull(userService.getUserByID(emptyUser.getUserId()))				
				);	
		
	}
	
	@Test
	void getUserByUsernameAndEmail() {
		
		when(userRepo.findByUsernameAndEmail(emp1.getUsername(), emp1.getEmail())).thenReturn(emp1);
		when(userRepo.findByUsernameAndEmail(emp2.getUsername(), emp2.getEmail())).thenReturn(emp2);
		when(userRepo.findByUsernameAndEmail(manager.getUsername(), manager.getEmail())).thenReturn(manager);
		when(userRepo.findByUsernameAndEmail(emptyUser.getUsername(), emptyUser.getEmail())).thenReturn(null);	
		
		assertAll(
				()-> assertThat(
						userService.getUserByUsernameAndEmail(emp1), is(emp1)),
				()-> assertThat(
						userService.getUserByUsernameAndEmail(emp2), is(emp2)),
				()-> assertThat(
						userService.getUserByUsernameAndEmail(manager), is(manager)),
				()-> assertNull(
						userService.getUserByUsernameAndEmail(emptyUser))				
				);			
	}
	
	@Test
	void updateUserPassword() {
		
		when(userRepo.findByUsername(emp1.getUsername())).thenReturn(emp1);		
		when(userRepo.findByUsername(emp2.getUsername())).thenReturn(emp2);		
		when(userRepo.findByUsername(manager.getUsername())).thenReturn(manager);		
		when(userRepo.findByUsername(emptyUser.getUsername())).thenReturn(null);	
		
		assertAll(
				()-> assertThat(emp1.getUsername(), userService.updateUserPassword(emp1, "asddas"), is(emp1)),
				()-> assertThat(emp2.getUsername(), userService.updateUserPassword(emp2, "lasakk"), is(emp2)),
				()-> assertThat(manager.getUsername(), userService.updateUserPassword(manager, "khsal"), is(manager)),
				()-> assertNull(userService.updateUserPassword(emptyUser, "kjsaha"))				
				);
		
	}
	
	@Test
	void savePasswordResetTokenAndSendEmail() {
		
		when(userRepo.findByUsernameAndEmail(emp1.getUsername(), emp1.getEmail())).thenReturn(emp1);		
		when(userRepo.findByUsernameAndEmail(emp2.getUsername(), emp2.getEmail())).thenReturn(emp2);		
		when(userRepo.findByUsernameAndEmail(manager.getUsername(), manager.getEmail())).thenReturn(manager);
		
		assertAll(
				()-> assertThat(emp1.getUsername(), userService.savePasswordResetTokenAndSendEmail(emp1), is(emp1)),
				()-> assertThat(emp2.getUsername(), userService.savePasswordResetTokenAndSendEmail(emp2), is(emp2)),
				()-> assertThat(manager.getUsername(), userService.savePasswordResetTokenAndSendEmail(manager), is(manager))
				);		
	}
	
	@Test
	void getUserByResetToken() {
		
		emp1.setPasswordResetToken(new String(UUID.randomUUID() + ""));
		emp2.setPasswordResetToken(new String(UUID.randomUUID() + ""));
		manager.setPasswordResetToken(new String(UUID.randomUUID() + ""));
		
		when(userRepo.findByPasswordResetToken(emp1.getPasswordResetToken())).thenReturn(emp1);
		when(userRepo.findByPasswordResetToken(emp2.getPasswordResetToken())).thenReturn(emp2);
		when(userRepo.findByPasswordResetToken(manager.getPasswordResetToken())).thenReturn(manager);
		when(userRepo.findByPasswordResetToken(emptyUser.getPasswordResetToken())).thenReturn(null);
		
		assertAll(
				()-> assertThat(emp1.getUsername(), userService.getUserByResetToken("asddas", emp1.getPasswordResetToken()), is(emp1)),
				()-> assertThat(emp2.getUsername(), userService.getUserByResetToken("lasakk", emp2.getPasswordResetToken()), is(emp2)),
				()-> assertThat(manager.getUsername(), userService.getUserByResetToken( "khsal", manager.getPasswordResetToken()), is(manager)),
				()-> assertNull(userService.getUserByResetToken("kjsaha", emptyUser.getPasswordResetToken()))				
				);		
		
	}
	
	@Test
	void getAllUsernames() {
		List<User> allUsers = new ArrayList<User>();
	    allUsers.add(emp1);
	    allUsers.add(emp2);
	    allUsers.add(manager);
	    
	    when(userRepo.findAll()).thenReturn(allUsers);
	    
	    assertThat(userService.getAllUsernames().toString(), is(allUsers.toString()));	    
		
	}
	
	
	@Test
	void passwordResetEmailNotification() {
		String resetKey = new String(UUID.randomUUID() + "");
		String subject = "Password RESET";
		String emailContent = "Reset token: "+ resetKey
				+ "\nUse this token to reset your password"
				+ "\nGo to:  'http://localhost:9050/finalizepasswordreset' ";
		
		userService.passwordResetEmailNotification(emp1, resetKey);		
		verify(emailService)
			.sendSimpleMessage(
				emp1.getEmail(), 
				subject, 
				emailContent);
				
		resetKey = new String(UUID.randomUUID() + "");
		emailContent = "Reset token: "+ resetKey
				+ "\nUse this token to reset your password"
				+ "\nGo to:  'http://localhost:9050/finalizepasswordreset' ";
		userService.passwordResetEmailNotification(emp2, resetKey);
		verify(emailService)
			.sendSimpleMessage(
				emp2.getEmail(), 
				subject, 
				emailContent);
		
		resetKey = new String(UUID.randomUUID() + "");
		emailContent = "Reset token: "+ resetKey
				+ "\nUse this token to reset your password"
				+ "\nGo to:  'http://localhost:9050/finalizepasswordreset' ";
		userService.passwordResetEmailNotification(manager, resetKey);
		verify(emailService)
			.sendSimpleMessage(
				manager.getEmail(), 
				subject, 
				emailContent);		
		
	}
	
	@Test
	void createNewUser() {
		User newUser = new User("lea", "abc123", "Lea", "Ortiz", "asdadas@gm.edu", UserRole.EMPLOYEE);
		
		when(userRepo.findByUsernameOrEmail(emp1.getUsername(), emp1.getEmail())).thenReturn(emp1);
		when(userRepo.findByUsernameOrEmail(emp2.getUsername(), emp2.getEmail())).thenReturn(emp2);
		when(userRepo.findByUsernameOrEmail(manager.getUsername(), manager.getEmail())).thenReturn(manager);
		when(userRepo.findByUsernameOrEmail(newUser.getUsername(), newUser.getEmail())).thenReturn(null);
		
		assertAll(
				 ()-> assertNull(userService.createNewUser(emp1)),
				 ()-> assertNull(userService.createNewUser(emp2)),
				 ()-> assertNull(userService.createNewUser(manager)),				
				 ()-> assertThat(userService.createNewUser(newUser), is(newUser))				
				);
		
		User newUserWithTakenUsername = 
				new User("lea", "abc123", "Lea", "Ortiz", "alalala@gml.com", UserRole.EMPLOYEE);
		when(userRepo.findByUsernameOrEmail(newUserWithTakenUsername.getUsername(), newUserWithTakenUsername.getEmail()))
			.thenReturn(newUser);				
		assertNull(userService.createNewUser(newUserWithTakenUsername));
		
		User newUserWithTakenEmail = 
				new User("lana", "abc123", "Lea", "Ortiz", "asdadas@gm.edu", UserRole.EMPLOYEE);
		when(userRepo.findByUsernameOrEmail(newUserWithTakenEmail.getUsername(), newUserWithTakenEmail.getEmail()))
		.thenReturn(newUser);	
		assertNull(userService.createNewUser(newUserWithTakenEmail));		
	}

}
