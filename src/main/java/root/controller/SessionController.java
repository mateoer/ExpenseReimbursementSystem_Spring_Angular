package root.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import root.model.NewPasswordContextClass;
import root.model.User;
import root.model.UserResponse;
import root.service.UserService;

@RestController
@CrossOrigin
public class SessionController {
	
	private UserService myUserService;
	
	
	@Autowired
	public SessionController(UserService myUserService) {
		this.myUserService = myUserService;		
	}
	
		
	@PostMapping("/getcredentials")
	@ResponseBody
	public UserResponse getUserCredentials2(@RequestBody User userReq){
		if(userReq == null) {
			return null;
		}
		User userDummy = myUserService.getUserByUsernameAndPassword(userReq);
		UserResponse userResp = new UserResponse();
		
		if (userDummy == null) {
			userResp.setFound(false);
		}		
		userResp.setUser(userDummy);
		System.out.println("\nGetting credentials\n" );
		return userResp;
	}
	
	
	@GetMapping("/greetings")
	public String greetings() {
		System.out.println("\nGreetings\n" );
		return "¯\\_(ツ)_/¯";
	}
	
	@PostMapping("/validateUserEmail")
	public String validateUserWithEmail(@RequestBody User userReq) {
		User userDummy = myUserService.getUserByUsernameAndEmail(userReq);
		if (userDummy != null) {
			myUserService.savePasswordResetTokenAndSendEmail(userDummy);
			return "An email has been sent to "+ userDummy.getEmail();
		}else {
			return "No user found";
		}
	}
	
	@PostMapping("/validateUserPassword")
	@ResponseBody
	public String validateUserWithPassword(@RequestBody NewPasswordContextClass newPassReq) {
		User myUser = newPassReq.getUser();
		String newPassword = newPassReq.getNewPassword();
		User userDummy = myUserService.updateUserPassword(myUser,newPassword);
		if (userDummy != null) {
			return "Password has been reset";
		}else {
			return "Error";
		}
	}
	
	@PostMapping("/validateResetToken")
	public String validateResetToken(@RequestBody NewPasswordContextClass newPassReq) {
		User myUser = newPassReq.getUser();
		String newPassword = newPassReq.getNewPassword();
		User userDummy = myUserService.getUserByResetToken(newPassword, myUser.getPasswordResetToken());
		if (userDummy != null) {
			return "Password has been reset";
		}else {
			return "Error";
		}
	}	

	
	@GetMapping("/checkUsername/{username}")
	public String usernameAvailable(@PathVariable ("username") String usernameToCheck) {	
		
		List<User> takenUsernames = myUserService.getAllUsernames();	
		
		for (User user : takenUsernames) {
			if (user.getUsername().equals(usernameToCheck)) {
				return "Username is not available";
			}
		}
		
		return "Username is available";		
	}
	
	@GetMapping("/checkEmail/{email}")
	public String emailAvailable(@PathVariable ("email") String emailToCheck) {	
		
		List<User> takenUsernames = myUserService.getAllUsernames();	
		
		for (User user : takenUsernames) {
			if (user.getEmail().equals(emailToCheck)) {
				return "Email is not available";
			}
		}
		
		return "";		
	}
	
	@PostMapping("/registerNewUser")
	public UserResponse createNewUser(@RequestBody UserResponse newUserRequest ) {
		User newUserToCreate = newUserRequest.getUser();
		User newlyCreatedUser = myUserService.createNewUser(newUserToCreate);
		if (newlyCreatedUser == null) {
			return null;
		}
		
		
		UserResponse userResp = new UserResponse();	
						
		userResp.setUser(newlyCreatedUser);
		System.out.println("\nNew user registered\n" );
		
		return userResp;
		

	}
	
	
	
}
