package root.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import root.dao.UserRepository;
import root.model.User;

//@Controller
@RestController
public class SessionController {
	
	private UserRepository userRepo;
	
	@Autowired
	public SessionController(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@GetMapping("/getName")
	public User getLoggedInUser (HttpSession httpSession) {
		System.out.println("\n\n\nIn the getLoggedInUser method");
		
		User currentUser = (User)httpSession.getAttribute("currentUser");
		
		if (currentUser == null) {
			return new User(null, null, null, null, null, null);
		}
		
		return currentUser;
	}
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public String login (HttpSession httpSession, @RequestBody User incomingUser) {
		System.out.println("\n\n\nIn the login method");
		
		User dbUser = loginAuthentication(incomingUser);
		if (dbUser == null) {
			return "User not found";
		}
		httpSession.setAttribute("currentUser", dbUser);
		return "Login successful";
		
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("\n\n\nIn the logout method");
		System.out.println(request.getRequestURL());
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		
		return "Session ended";
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
}
