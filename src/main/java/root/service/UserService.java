package root.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import root.dao.UserRepository;
import root.model.User;
import root.model.UserRole;
import root.service.mail.EmailService;

@Service
public class UserService implements UserServiceInterface {

	private UserRepository userRepo;
	private EmailService emailService;
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
		if (passwordEncoder.matches(user.getPassword(), myUser.getPassword()))
			return myUser;
		else {
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
		String password = passwordEncoder.encode(user.getPassword());
		User myUser = userRepo.findByUsernameAndPassword(user.getUsername(), password);
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
			userRepo.save(myUser);
		}
		return myUser;
	}
	
	public void passwordResetEmailNotification(User user,String resetKey) {
		String userEmail = user.getEmail();
		String subject = "Password RESET";
		String emailContent = "Reset token: "+ resetKey
				+ "\nUse this token to reset your password"
				+ "\nGo to:  'http://localhost:4200/finalizepasswordreset' ";
		emailService.sendSimpleMessage(userEmail, subject, emailContent);
	}


}


