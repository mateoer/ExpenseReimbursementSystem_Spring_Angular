package root.service.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
	
	/*
	 * //#5
	 * 
	 * @Autowired private DataSource dataSource;
	 * 
	 * @Bean public UserDetailsManager authenticateUsers() {
	 * 
	 * UserDetails user = User.withUsername("username")
	 * .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(
	 * "password")).build(); JdbcUserDetailsManager users = new
	 * JdbcUserDetailsManager(dataSource); users.
	 * setAuthoritiesByUsernameQuery("select user_name,user_pwd,user_enabled from user where user_name=?"
	 * ); users.
	 * setUsersByUsernameQuery("select user_name,user_role from user where user_name=?"
	 * ); users.createUser(user); return users; }
	 */
	
	/*
	 * //#3
	 * 
	 * @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration
	 * authenticationConfiguration) throws Exception { return
	 * authenticationConfiguration.getAuthenticationManager(); }
	 */
    
	/*
	 * //#4
	 * 
	 * @Bean public WebSecurityCustomizer webSecurityCustomizer() { return (web) ->
	 * web.ignoring().antMatchers("/ignore1", "/ignore2"); }
	 */  
    
    //#1
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()					  
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/manager").permitAll()
			.antMatchers("/employee").permitAll()
			.and().httpBasic();
		return http.build();
	}
	
	//#2
	@Bean
    protected InMemoryUserDetailsManager configAuthentication() {

       List<UserDetails> users = new ArrayList<>();
       List<GrantedAuthority> adminAuthority = new ArrayList<>();
       adminAuthority.add(new SimpleGrantedAuthority("ADMIN"));
       UserDetails admin= new User("devs", "{noop}devs", adminAuthority);
       users.add(admin);

       List<GrantedAuthority> employeeAuthority = new ArrayList<>();
       adminAuthority.add(new SimpleGrantedAuthority("EMPLOYEE"));
       UserDetails employee= new User("ns", "{noop}ns", employeeAuthority);
       users.add(employee);

       List<GrantedAuthority> managerAuthority = new ArrayList<>();
       adminAuthority.add(new SimpleGrantedAuthority("MANAGER"));
       UserDetails manager= new User("vs", "{noop}vs", managerAuthority);
       users.add(manager);

       return new InMemoryUserDetailsManager(users);
    }
	

}
