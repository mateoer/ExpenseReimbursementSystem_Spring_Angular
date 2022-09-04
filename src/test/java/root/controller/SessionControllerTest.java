package root.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import root.model.NewPasswordContextClass;
import root.model.User;
import root.model.UserResponse;
import root.model.UserRole;
import root.service.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SessionControllerTest {
	
	@Mock
	private UserService userService;
	
	@Mock
	private SessionController sessionController;
	
	private User oldUser = new User(3, "eric", "abc123", "Eric", "Smith", "dshkjgasda@afdfdfg", UserRole.MANAGER, null, null, null);
	private User newUser = new User("lea", "abc123", "Lea", "Ortiz", "dshkjfadagasda@afdfdfg.edu", UserRole.EMPLOYEE);
	private User emptyUser = new User();
	
	@BeforeEach
	void setUp() throws Exception {
		sessionController = new SessionController(userService);
	}
	
	@Test
	void getUserCredentials() {
		
		when(userService.getUserByUsernameAndPassword(oldUser)).thenReturn(oldUser);
		when(userService.getUserByUsernameAndPassword(newUser)).thenReturn(null);
		when(userService.getUserByUsernameAndPassword(emptyUser)).thenReturn(null);		
		
		UserResponse oldUserResponse = sessionController.getUserCredentials(oldUser);
		UserResponse newUserResponse = sessionController.getUserCredentials(newUser);
		UserResponse emptyUserResponse = sessionController.getUserCredentials(emptyUser);
		
		
		assertAll(
				()-> assertThat(oldUserResponse.isFound(), is(true)),
				()-> assertThat(oldUserResponse.getUser(), is(oldUser)),
				()-> assertThat(newUserResponse.isFound(), is(false)),
				()-> assertNull(newUserResponse.getUser()),
				()-> assertThat(emptyUserResponse.isFound(), is(false)),
				()-> assertNull(emptyUserResponse.getUser())
				);			
	}
	
	@Test
	void greetings() {
		assertThat(sessionController.greetings(), is(""));
	}
	
	@Test
	void validateUserWithEmail() {
		
		when(userService.getUserByUsernameAndEmail(oldUser)).thenReturn(oldUser);
		when(userService.getUserByUsernameAndEmail(newUser)).thenReturn(null);
		when(userService.getUserByUsernameAndEmail(emptyUser)).thenReturn(null);
		
		//the others will never reach this because they will return null
		when(userService.savePasswordResetTokenAndSendEmail(oldUser)).thenReturn(oldUser);
		
		String success = "An email has been sent to "+ oldUser.getEmail();
		String failure = "No user found";
		
				
		assertAll(
				()-> assertThat(sessionController.validateUserWithEmail(oldUser), is(success)),
				()-> assertThat(sessionController.validateUserWithEmail(newUser), is(failure)),
				()-> assertThat(sessionController.validateUserWithEmail(emptyUser), is(failure))
				);
	}
	
	@Test
	void validateUserWithPassword() {
		
		NewPasswordContextClass oldUserNPCC = new NewPasswordContextClass();
		oldUserNPCC.setUser(oldUser);
		oldUserNPCC.setNewPassword("abc456");
		
		NewPasswordContextClass newUserNPCC = new NewPasswordContextClass();
		newUserNPCC.setUser(newUser);
		
		NewPasswordContextClass emptyUserNPCC = new NewPasswordContextClass();
		emptyUserNPCC.setUser(emptyUser);
		
		
		assertThat(sessionController.validateUserWithPassword(oldUserNPCC), is(instanceOf(String.class)));
		assertThat(sessionController.validateUserWithPassword(newUserNPCC), is(instanceOf(String.class)));
		assertThat(sessionController.validateUserWithPassword(emptyUserNPCC), is(instanceOf(String.class)));
		assertNull(userService.updateUserPassword(emptyUser, null));
		assertNull(userService.updateUserPassword(newUser, "alalala"));		
	}
	
	@Test
	void validateResetToken() {
		
		NewPasswordContextClass oldUserNPCC = new NewPasswordContextClass();
		oldUserNPCC.setUser(oldUser);
		oldUserNPCC.setNewPassword("abc456");
		
		NewPasswordContextClass newUserNPCC = new NewPasswordContextClass();
		newUserNPCC.setUser(newUser);
		
		NewPasswordContextClass emptyUserNPCC = new NewPasswordContextClass();
		emptyUserNPCC.setUser(emptyUser);
		
		String resetToken = new String(UUID.randomUUID() + "");
		oldUser.setPasswordResetToken(resetToken);
		when(userService
				.getUserByResetToken(oldUserNPCC.getNewPassword(), oldUser.getPasswordResetToken()))
					.thenReturn(oldUser);
		
		//a new user and empty user would not have a valid reset token stored in db, hence no setup for their cases
		
		assertThat(sessionController.validateResetToken(oldUserNPCC), is(instanceOf(String.class)));
		assertThat(sessionController.validateResetToken(oldUserNPCC), is("Password has been reset"));
		assertThat(sessionController.validateResetToken(newUserNPCC), is(instanceOf(String.class)));
		assertThat(sessionController.validateResetToken(newUserNPCC), is("Error"));
		assertThat(sessionController.validateResetToken(emptyUserNPCC), is(instanceOf(String.class)));
		assertThat(sessionController.validateResetToken(emptyUserNPCC), is("Error"));
		assertNotNull(userService.getUserByResetToken(oldUserNPCC.getNewPassword(), oldUser.getPasswordResetToken()));
		assertNull(userService.getUserByResetToken(newUserNPCC.getNewPassword(), newUser.getPasswordResetToken()));
		assertNull(userService.getUserByResetToken(emptyUserNPCC.getNewPassword(), emptyUser.getPasswordResetToken()));	
		
	}
	
	@Test
	void usernameAvailable() {
		User emp1 = new User(1, "sue", "abc123", "Sue", "Liz", "fdsdfr@kesdafdfsan.edu", UserRole.EMPLOYEE, null, null, null);		
		User emp2 = new User(2, "anri", "abc123", "Anri", "Ban", "sdfsagm.com", UserRole.EMPLOYEE, null, null, null);
		
		List<User> usersList = new ArrayList<>();
		usersList.add(oldUser);
		usersList.add(emp1);
		usersList.add(emp2);
		
		String notAvailable = "Username is not available";
		String available = "Username is available";		
		
		when(userService.getAllUsernames()).thenReturn(usersList);
		
		assertThat(sessionController.usernameAvailable(emp1.getUsername()), is(notAvailable));
		assertThat(sessionController.usernameAvailable("veronica"), is(available));
		assertThat(userService.getAllUsernames().size(), is(usersList.size()));
	}
	
	@Test
	void emailAvailable() {
		
		User emp1 = new User(1, "sue", "abc123", "Sue", "Liz", "mateoer@kean.edu", UserRole.EMPLOYEE, null, null, null);		
		User emp2 = new User(2, "anri", "abc123", "Anri", "Ban", "sdfsagm.com", UserRole.EMPLOYEE, null, null, null);
		
		List<User> usersList = new ArrayList<>();
		usersList.add(oldUser);
		usersList.add(emp1);
		usersList.add(emp2);
		
		String notAvailable = "Email is not available";
		String available = "";		
		
		when(userService.getAllUsernames()).thenReturn(usersList);
		
		assertThat(sessionController.emailAvailable(emp1.getEmail()), is(notAvailable));
		assertThat(sessionController.emailAvailable("veronica@gm.com"), is(available));
		assertThat(userService.getAllUsernames().size(), is(usersList.size()));
	}

	@Test
	void createNewUser() {
		
		boolean found = true;
		UserResponse newUserResponse = new UserResponse();
		newUserResponse.setUser(newUser);
		newUserResponse.setFound(found);
		
		when(userService.createNewUser(newUser)).thenReturn(newUser);
		
		UserResponse freshUser = sessionController.createNewUser(newUserResponse);
		
		assertNotNull(freshUser);
		assertThat(freshUser, is(newUserResponse));
		
	}

}
