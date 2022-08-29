package root.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import root.dao.ReimbursementRepository;
import root.dao.UserRepository;
import root.model.Reimbursement;
import root.model.User;
import root.model.UserRole;
import root.model.enumscontainer.ReiStatus;
import root.service.mail.EmailService;

@Service
public class ManagerService implements ManagerServiceInterface {
	
	private ReimbursementRepository reiRepo;
	private UserRepository userRepo;
	private EmailService emailService;
	

	public ManagerService (ReimbursementRepository reiRepo, UserRepository userRepo, EmailService emailService) {
		this.reiRepo = reiRepo;
		this.userRepo = userRepo;
		this.emailService = emailService;
	}

	@Override
	public User getUserName(User reqUser) {		
		return userRepo.findByUserId(reqUser.getUserId());
	}	

	@Override
	public Reimbursement approveReimbursement(Reimbursement reimb, User user) {	
		
		User dummyUser = userRepo.findByUserId(user.getUserId());
		Reimbursement mdaReimbursement = new Reimbursement(); 
		
		if (dummyUser != null | dummyUser.getUserRole() == UserRole.MANAGER) {
			mdaReimbursement = reiRepo.findByReiId(reimb.getReiId());
			mdaReimbursement.setReiStatus(ReiStatus.APPROVED);
			LocalDateTime lcdt = LocalDateTime.now();
			mdaReimbursement.setRei_resolvedDate(lcdt);
			reiRepo.save(mdaReimbursement);
			
			//EMAIL NOTIFICATION		
//		int userAuthorId = mdaReimbursement.getReiAuthor();
//		String userEmail = userRepo.findByUserId(userAuthorId).getEmail();
//		String subject = "Reimbursement ACCEPTED";
//		String emailContent = "Your reimbursement has been ACCEPTED";
//		emailService.sendSimpleMessage(userEmail, subject, emailContent);
			
		}		
		return reiRepo.findByReiId(mdaReimbursement.getReiId());	
	}

	@Override
	public Reimbursement denyReimbursement(Reimbursement reimb, User user) {
		
		User dummyUser = userRepo.findByUserId(user.getUserId());
		Reimbursement myReimbursement = new Reimbursement();
		
		if (dummyUser != null | dummyUser.getUserRole() == UserRole.MANAGER) {
		
			myReimbursement = reiRepo.findByReiId(reimb.getReiId()); 				
			myReimbursement.setReiStatus(ReiStatus.DENIED);
			LocalDateTime lcdt = LocalDateTime.now();
			myReimbursement.setRei_resolvedDate(lcdt);
			reiRepo.save(myReimbursement);
		
		//EMAIL NOTIFICATION
	//		int userAuthorId = myReimbursement.getReiAuthor();
	//		String userEmail = userRepo.findByUserId(userAuthorId).getEmail();
	//		String subject = "Reimbursement REJECTED";
	//		String emailContent = "Your reimbursement was REJECTED";
	//		emailService.sendSimpleMessage(userEmail, subject, emailContent);
			
		}
		return reiRepo.findByReiId(myReimbursement.getReiId());	
	}	

	@Override
	public List<Reimbursement> listOfAllReimbursements() {		
		return reiRepo.findAll();
	}


}
