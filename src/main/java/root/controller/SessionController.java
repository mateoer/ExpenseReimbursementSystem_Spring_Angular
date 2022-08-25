package root.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import root.model.NewPasswordContextClass;
import root.model.User;
import root.model.UserResponse;
import root.service.UserService;
import root.service.mail.EmailService;

@Controller
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
	@ResponseBody
	public String greetings() {
		System.out.println("\nGreetings\n" );
		return "Hi There :)";
	}
	
	@PostMapping("/validateUserEmail")
	@ResponseBody
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
	@ResponseBody
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
	
	
}
