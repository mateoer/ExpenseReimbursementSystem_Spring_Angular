package root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.dao.UserRepository;
import root.model.User;
import root.model.UserRole;

@Service
public class UserService implements UserServiceInterface {

	private UserRepository userRepo;
	
	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	@Override
	public UserRole getUserRole(int userId) {		
		User myUserRole = userRepo.findByUserId(userId);		
		return myUserRole.getUserRole();
	}

	@Override
	public User getUser(User user) {
		User myUser = userRepo.findByUsername(user.getUsername());
		return myUser;
	}

	@Override
	public User getUser2(User user) {
		User myUser = userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		return myUser;
	}	
	
}


