package root.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import root.model.User;
import root.model.UserResponse;
import root.service.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:9050/")
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
		User userDummy = myUserService.getUser2(userReq);
		UserResponse userResp = new UserResponse();
		
		if (userDummy == null) {
			userResp.setFound(false);
		}		
		userResp.setUser(userDummy);
		System.out.println("\nGetting credentials\n" );
		return userResp;
	}
	
	
	
}
