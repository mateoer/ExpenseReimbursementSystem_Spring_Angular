package root.service;

import root.model.User;
import root.model.UserRole;

public interface UserServiceInterface {
	
	public UserRole getUserRole(int userId);
	public User getUser(User user);
	public User getUser2(User user);
}
