package root.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import root.service.security.JwtUtils;

@RestController
//@CrossOrigin("*")
@CrossOrigin("http//localhost:4200")
public class SessionController {

	private UserService myUserService;
	
	@Autowired
	  AuthenticationManager authenticationManager;




	  @Autowired
	  JwtUtils jwtUtils;

	@Autowired
	public SessionController(UserService myUserService) {
		this.myUserService = myUserService;
	}

	public SessionController() {
	}

	@PostMapping("/login/getcredentials")
	@ResponseBody
	public /*UserResponse*/ ResponseEntity<?> getUserCredentials(@RequestBody User userReq) {

		User userDummy = myUserService.getUserByUsernameAndPassword(userReq);
		UserResponse userResp = new UserResponse();

		if (userDummy == null) {
			userResp.setFound(false);
		}
		userResp.setUser(userDummy);
		System.out.println("\nGetting credentials\n");
		
		UserDetails userDetails = userDetailsService.loadUserByUsername (userDummy.getUsername());
		Authentication authentication = authenticationManager
		        .authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ()));

		    SecurityContextHolder.getContext().setAuthentication(authentication);

//		    MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

		    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		 return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//				 .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.SET_COOKIE)
//				 .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.COOKIE)
			        .body(userResp);
//		return userResp;
	}

	@GetMapping("/greetings")
	@PreAuthorize("hasRole('MANAGER')")
	public String greetings() {
		System.out.println("\nGreetings\n");
		return "¯\\_(ツ)_/¯";
//		return "";
	}

	@PostMapping("/validateUserEmail")
	public String validateUserWithEmail(@RequestBody User userReq) {

		System.out.println("\nValidating user for password reset");

		User userDummy = myUserService.getUserByUsernameAndEmail(userReq);
		if (userDummy != null) {
			myUserService.savePasswordResetTokenAndSendEmail(userDummy);
			return "An email has been sent to " + userDummy.getEmail();
		} else {
			return "No user found";
		}
	}

	@PostMapping("/validateUserPassword")
	@ResponseBody
	public String validateUserWithPassword(@RequestBody NewPasswordContextClass newPassReq) {
		System.out.println("\nValidating user with password");
		User myUser = newPassReq.getUser();
		String newPassword = newPassReq.getNewPassword();
		User userDummy = myUserService.updateUserPassword(myUser, newPassword);
		if (userDummy != null) {
			return "Password has been reset";
		} else {
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
		} else {
			return "Error";
		}
	}

	@GetMapping("/checkUsername/{username}")
	public String usernameAvailable(@PathVariable("username") String usernameToCheck) {

		List<User> takenUsernames = myUserService.getAllUsernames();

		for (User user : takenUsernames) {
			if (user.getUsername().equals(usernameToCheck)) {
				return "Username is not available";
			}
		}

		return "Username is available";
	}

	@GetMapping("/checkEmail/{email}")
	public String emailAvailable(@PathVariable("email") String emailToCheck) {

		List<User> takenUsernames = myUserService.getAllUsernames();

		for (User user : takenUsernames) {
			if (user.getEmail().equals(emailToCheck)) {
				return "Email is not available";
			}
		}

		return "";
	}

	 
	 @Autowired
	 UserDetailsService userDetailsService;
	
	@PostMapping("/login/registerNewUser")
	public /*UserResponse*/ ResponseEntity<?> createNewUser(@RequestBody UserResponse newUserRequest, HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		
		User newUserToCreate = newUserRequest.getUser();
		String username = newUserToCreate.getUsername();

		System.out.println(newUserToCreate.getUsername()+" "+newUserToCreate.getPassword());
		System.out.println(newUserToCreate);
		System.out.println();
		System.out.println(newUserRequest.toString());
		
		User newlyCreatedUser = myUserService.createNewUser(newUserToCreate);
		if (newlyCreatedUser == null) {
			return null;
		}
		
			UserDetails userDetails = userDetailsService.loadUserByUsername (username);
			Authentication auth = new UsernamePasswordAuthenticationToken (userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ());
			SecurityContextHolder.getContext().setAuthentication(auth);
//			request.getSession().setAttribute("username", newlyCreatedUser.getUsername());
//			request.getSession().setAttribute("password", newlyCreatedUser.getPassword());
//			request.getSession().setAttribute("role", "ROLE_"+newlyCreatedUser.getUserRole());
//			request.login(username, password);
			System.out.println();
			System.out.println("CREDENTIALS: "+ auth.getCredentials());
			System.out.println("PRINCIPAL: " + auth.getPrincipal());
			System.out.println("AUTHORITIES: "+ auth.getAuthorities());
			

//			auth.setAuthenticated(true);
		UserResponse userResp = new UserResponse();
		userResp.setUser(newlyCreatedUser);


		System.out.println("\nNew user registered\n");
		
		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		 return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//				 .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.SET_COOKIE)
//				 .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.COOKIE)
			        .body(userResp);
//		return userResp;

	}

}
