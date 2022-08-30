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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
	
	 @Bean 
	  public PasswordEncoder passwordEncoder() { 
		  return new  BCryptPasswordEncoder(); 
	  }
	
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
