package root.service.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import root.model.User;
import root.model.UserResponse;
import root.service.UserService;

public class CustomRegistrationHandler implements AuthenticationSuccessHandler {

	
//	@Autowired
	private UserService myUserService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		
		ObjectMapper myMapper = new ObjectMapper();
		User newUserToCreate = myMapper.readValue(request.getInputStream(), User.class);
		User newlyCreatedUser = myUserService.createNewUser(newUserToCreate);
		
		
		UserResponse userResp = new UserResponse();						
		userResp.setUser(newlyCreatedUser);
		
		System.out.println("\nNew user registered\n" );
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.write(new ObjectMapper().writeValueAsString(userResp));
		out.flush();

	}

}
