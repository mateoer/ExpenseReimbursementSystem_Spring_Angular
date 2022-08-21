package root.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;




@Configuration
public class UserDetailServiceConfiguration {

    @Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
    	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("suechan").password(bCryptPasswordEncoder.encode("abc123")).roles("EMPLOYEE").build());
        manager.createUser(User.withUsername("mateoer").password(bCryptPasswordEncoder.encode("abc123")).roles("MANAGER").build());
        return manager;
    }
    

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
