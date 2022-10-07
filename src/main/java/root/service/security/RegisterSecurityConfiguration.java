//package root.service.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import root.dao.UserRepository;
//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//@Order(Ordered.LOWEST_PRECEDENCE)
////@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class RegisterSecurityConfiguration extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	public UserDetailsService userDetailsService(UserRepository userRepo) {
//		return new UserDetailsServiceImpl(userRepo);
//	}
//	
//	@Autowired
//	private AuthenticationSuccessHandler customSuccessHandler;
//	
//	@Autowired
//	private UserRepository userRepo;
//	
//	@Bean
//	public AuthenticationSuccessHandler customRegistrationHandler() {
//		return new CustomRegistrationHandler();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(new UserDetailsServiceImpl(userRepo));
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.cors()
//			.and()
//			.csrf()
//			.disable()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//			.and()
////			.antMatcher("/login/registerNewUser")
//				.authorizeRequests()
//					.antMatchers("/login/registerNewUser")
//						.permitAll()
//					.and()
//				.formLogin().permitAll()
//					.loginPage("/register")
//					.loginProcessingUrl("/login/registerNewUser").permitAll()
//					.usernameParameter("username")
//					.passwordParameter("password")
//					.failureUrl("/error").permitAll()
//					.successHandler(customSuccessHandler)
//				.and()
//				.httpBasic()
//			;
//	}
//
//}
