package root.service.security;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import root.dao.UserRepository;
import root.model.User;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;	
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepo) {
	    this.userRepo = userRepo;
	}
	
	public UserDetailsServiceImpl() {	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		User user = userRepo.getUserByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        
        MyUserDetails myUser = new MyUserDetails(user); 
        
        System.out.println("\n\n In the loadUserByUsername method:");
        System.out.println(user.getUsername()+" "+user.getUserId()+" "+user.getFirstName()+" "+user.getUserRole()+"\n");
        System.out.println("CREDENTIALS = Password: "+ myUser.getPassword());
		System.out.println("PRINCIPAL = Username: " + myUser.getUsername());
		System.out.println("AUTHORITIES: "+ myUser.getAuthorities());
		System.out.println("-----------------------------------------------");
        
        
        return myUser;
	}
	
	

}
