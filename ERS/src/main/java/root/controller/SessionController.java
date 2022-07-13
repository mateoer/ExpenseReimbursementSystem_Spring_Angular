package root.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import root.dao.UserRepository;
import root.model.User;
import root.model.UserRole;

@Controller
//@RestController
public class SessionController {
	
	private UserRepository userRepo;
	
	@Autowired
	public SessionController(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
//	@GetMapping("/")
	@RequestMapping("/")
	public String routeLoginPage() {
		System.out.println("In the main login router");
		return "forward:/login";
	}

	@GetMapping("/getName")
	@ResponseBody
	public User getLoggedInUser (HttpSession httpSession) {
		System.out.println("\n\n\nIn the getLoggedInUser method");
		
		User currentUser = (User)httpSession.getAttribute("currentUser");
		
		if (currentUser == null) {
			return new User(null, null, null, null, null, null);
		}
		
		return currentUser;
	}
	
	@PostMapping("/login")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String login (HttpSession httpSession, @RequestBody User incomingUser) {
		System.out.println("\n\nIn the login method");
		
		User dbUser = loginAuthentication(incomingUser);
		if (dbUser == null) {
			return "No user found";
		}
		httpSession.setAttribute("currentUser", dbUser);		
		
		loginRedirect(dbUser);		
		return "Login successful";		
	}
	
//	@GetMapping("/logout")
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession) {
		System.out.println("\n\nIn the logout method");
		httpSession.invalidate();
		System.out.println("Session ended");
		return "redirect:/login";
	}
	
	//Helper methods
	
	public User loginAuthentication(User reqUser) {
		
		User dbUser = userRepo.findByUsername(reqUser.getUsername());		
		if (dbUser != null) {			
			if (dbUser.getPassword().equals(reqUser.getPassword())) {
				return dbUser;
			}
		}
		return null;
	}
	
	public String loginRedirect(User reqUser) {
		if (reqUser.getUserRole() == UserRole.MANAGER) {
			System.out.println("Manager found");
			return "/manager";
		} else {
			System.out.println("Employee found");
			return "/employee";
		}
	}
}
