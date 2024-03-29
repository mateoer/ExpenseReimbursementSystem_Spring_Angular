package root.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import root.dao.UserRepository;
import root.model.User;
import root.model.UserRole;
import root.service.interfaces.UserServiceInterface;
import root.service.mail.EmailService;

@Service
public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepo, EmailService emailService, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.emailService = emailService;	
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public UserRole getUserRole(int userId) {		
		User myUserRole = userRepo.findByUserId(userId);		
		return myUserRole.getUserRole();
	}

	@Override
	public User getUserByUsername(User user) {
		User myUser = userRepo.findByUsername(user.getUsername());
		return myUser;
	}

	@Override
	public User getUserByUsernameAndPassword(User user) {
		
		User myUser = userRepo.findByUsername(user.getUsername());
		if (myUser == null) return null;
		if (user.getPassword().equals(myUser.getPassword())) {
			boolean passwordsMatch = (user.getPassword().equals(myUser.getPassword()));
			System.out.println("\n\nIn the service that call session: passwords match? => "+passwordsMatch);
			return myUser;
		
		}else {
			return null;
		}		
	}


	@Override
	public User getUserByID(int userId) {
		User myUser = userRepo.findByUserId(userId);
		return myUser;
	}


	@Override
	public User getUserByUsernameAndEmail(User user) {
		User myUser = userRepo.findByUsernameAndEmail(user.getUsername(), user.getEmail());
		return myUser;
	}


	@Override
	public User updateUserPassword(User user, String newPassword) {
		
		User myUser = userRepo.findByUsername(user.getUsername());
		if (myUser != null) {
			myUser.setPassword(passwordEncoder.encode(newPassword));
			userRepo.save(myUser);
		}
		return myUser;
	}		


	@Override
	public User savePasswordResetTokenAndSendEmail(User user) {
		User myUser = userRepo.findByUsernameAndEmail(user.getUsername(), user.getEmail());
		String resetKey = UUID.randomUUID() + "";
		myUser.setPasswordResetToken(resetKey);
		userRepo.save(myUser);
		passwordResetEmailNotification(myUser,resetKey);
		return myUser;
	}
	
	@Override
	public User getUserByResetToken(String newPassword, String resetToken) {
		User myUser = userRepo.findByPasswordResetToken(resetToken);
		if (myUser != null) {
			myUser.setPassword(passwordEncoder.encode(newPassword));
			myUser.setPasswordResetToken(null);
			userRepo.save(myUser);
		}
		return myUser;
	}
	
	@Override
	public List<User> getAllUsernames(){
		return userRepo.findAll();
	}
	
	@Override
	public void passwordResetEmailNotification(User user,String resetKey) {
		String userEmail = user.getEmail();
		String subject = "Password RESET";
		String emailContent = "Reset token: "+ resetKey
				+ "\nUse this token to reset your password"
				+ "\nGo to:  http://localhost:9050/finalizepasswordreset/"+resetKey;
		emailService.sendSimpleMessage(userEmail, subject, emailContent);
	}


	@Override
	public User createNewUser(User newUserRequest) {		
		
		User checkUser = userRepo
					.findByUsernameOrEmail(newUserRequest.getUsername(), newUserRequest.getEmail());
		
		if (checkUser == null) {			
			User myNewUser = newUserRequest;
			myNewUser.setPassword(passwordEncoder.encode(newUserRequest.getPassword()));
//			myNewUser.setPassword(newUserRequest.getPassword());
			myNewUser.setProfilePicName(null);
			userRepo.save(myNewUser);
			return myNewUser;			
		} else {
			return null;
		}
		
		
	}


}


