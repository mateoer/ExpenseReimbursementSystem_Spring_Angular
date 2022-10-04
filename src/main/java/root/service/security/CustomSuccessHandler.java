package root.service.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import root.dao.UserRepository;
import root.model.User;
import root.model.UserResponse;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
	
	
	@Autowired
	private UserRepository userRepo;	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		UserDetails userReq = (UserDetails) authentication.getPrincipal();
		String username = userReq.getUsername();
		String password = userReq.getPassword();
		Collection<? extends GrantedAuthority> authorities = userReq.getAuthorities();		
		
		User myUser = userRepo.findByUsernameAndPassword(username, password);
		
		
		UserResponse userResp = new UserResponse();
		if (myUser == null) {
			userResp.setFound(false);
		}
		userResp.setUser(myUser);
		
		System.out.println("\n\nOn Authentication Handler => "+username);
		System.out.println("\n\nOn Authentication Handler => "+password);
		System.out.println("\n\nOn Authentication Handler => "+authorities);
		System.out.println("\n\nOn Authentication Handler => "+userResp);
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.write(new ObjectMapper().writeValueAsString(userResp));
		out.flush();
		
	}

}
