package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import root.dao.UserRepository;

@Controller
public class MainController {
	
	private UserRepository userRepo;
	
	@Autowired
	public MainController(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	@PostMapping("/")
	public String routeLoginPage() {
		System.out.println("In the main login router");
		return "/login";
	}
	
	
	
}
