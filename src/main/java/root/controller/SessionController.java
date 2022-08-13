package root.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import root.model.StringResponse;
import root.model.User;
import root.model.UserRole;
import root.service.UserService;

//@RestController
@Controller
@CrossOrigin
public class SessionController {
	
	private UserService myUserService;
	
	@Autowired
	public SessionController(UserService myUserService) {
		this.myUserService = myUserService;
	}
	
	@PostMapping("/typeofuser")
	@ResponseBody
	public StringResponse getUserRoleString(@RequestBody User user) {
		int myUserID = user.getUserId();
		UserRole myUR = myUserService.getUserRole(myUserID);
		StringResponse textResponse = new StringResponse();
		if (myUR == UserRole.EMPLOYEE) {
			textResponse.setStringResponse("EMPLOYEE");
		} else {
			textResponse.setStringResponse("MANAGER");
		}
		return textResponse;
	}
	
	

	
}
