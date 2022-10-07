package root.service.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import root.dao.UserRepository;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepo;
	

	@Autowired
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return new UserDetailsServiceImpl(userRepo);
	}

	@Bean
	public AuthenticationSuccessHandler customSuccessHandler() {
		return new CustomSuccessHandler();
	}	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsServiceImpl(userRepo));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.csrf()
			.disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.and()
				.authorizeRequests()
					.antMatchers("/login/getcredentials", "/", "/checkUsername/**", "/login",
						"/checkEmail/**",
						"/validateUserEmail", "/login/registerNewUser", 
								 "/validateResetToken")
						.permitAll()
					.antMatchers("/greetings").hasRole("EMPLOYEE")
					.and()
				.formLogin().permitAll()
					.loginPage("/login")
					.loginProcessingUrl("/login/getcredentials").permitAll()
					.usernameParameter("username")
					.passwordParameter("password")
					.failureUrl("/error").permitAll()
					.successHandler(customSuccessHandler())
				.and()
				.httpBasic()
			;		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:9050"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
