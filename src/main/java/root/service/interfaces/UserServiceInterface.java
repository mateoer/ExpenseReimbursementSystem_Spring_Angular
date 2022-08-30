package root.service.interfaces;

import java.util.List;

import root.model.User;
import root.model.UserRole;

public interface UserServiceInterface {
	
	public UserRole getUserRole(int userId);
	public User getUserByUsername(User user);
	public User getUserByID(int userId);
	public User getUserByUsernameAndPassword(User user);
	public User getUserByUsernameAndEmail(User user);
	public User updateUserPassword(User user, String newPassword);
	public User savePasswordResetTokenAndSendEmail(User user);
	public User getUserByResetToken(String newPassword, String resetToken);
	List<User> getAllUsernames();
	public void passwordResetEmailNotification(User user,String resetKey);
	public User createNewUser(User newUser);
}
