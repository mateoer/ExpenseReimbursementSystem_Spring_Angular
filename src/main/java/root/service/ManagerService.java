package root.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.dao.ReimbursementRepository;
import root.dao.UserRepository;
import root.model.Reimbursement;
import root.model.User;
import root.model.UserRole;
import root.model.enumscontainer.ReiStatus;
import root.service.interfaces.ManagerServiceInterface;
import root.service.mail.EmailService;

@Service
public class ManagerService implements ManagerServiceInterface {

	private ReimbursementRepository reiRepo;
	private UserRepository userRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	public ManagerService(ReimbursementRepository reiRepo, UserRepository userRepo, EmailService emailService) {
		this.reiRepo = reiRepo;
		this.userRepo = userRepo;
		this.emailService = emailService;
	}

	@Override
	public User getUserName(User reqUser) {
		return userRepo.findByUserId(reqUser.getUserId());
	}

	@Override
	public String approveReimbursement(Reimbursement reimb, User user) {

		User dummyUser = userRepo.findByUserId(user.getUserId());
		Reimbursement mdaReimbursement = reiRepo.findByReiId(reimb.getReiId());

		String responseMessage = "Reimbursement was approved successfully!";

		if (dummyUser != null) {
			if (dummyUser.getUserRole() == UserRole.MANAGER) {

				mdaReimbursement.setReiStatus(ReiStatus.APPROVED);
				LocalDateTime lcdt = LocalDateTime.now();
				mdaReimbursement.setRei_resolvedDate(lcdt);

				// EMAIL NOTIFICATION

				try {
					String userEmail = userRepo.findByUserId(mdaReimbursement.getReiAuthor()).getEmail();
					
					if (emailService.validEmailAddress(userEmail) == false) {
						throw new Exception("Invalid email format");
					}
					
					String subject = "Reimbursement ACCEPTED";
					String emailContent = "Your reimbursement has been ACCEPTED";
					emailService.sendSimpleMessage(userEmail, subject, emailContent);

				} catch (Exception e) {
					System.err.println(e);
					responseMessage = "Reimbursement was approved successfully, but author could not be notified";
				}

				reiRepo.save(mdaReimbursement);

				return responseMessage;
			}

		}
		return null;
	}

	@Override
	public String denyReimbursement(Reimbursement reimb, User user) {

		User dummyUser = userRepo.findByUserId(user.getUserId());
		Reimbursement myReimbursement = new Reimbursement();

		String responseMessage = "Reimbursement was rejected successfully!";

		if (dummyUser != null) {
			if (dummyUser.getUserRole() == UserRole.MANAGER) {

				myReimbursement = reiRepo.findByReiId(reimb.getReiId());
				myReimbursement.setReiStatus(ReiStatus.DENIED);
				LocalDateTime lcdt = LocalDateTime.now();
				myReimbursement.setRei_resolvedDate(lcdt);

				//EMAIL NOTIFICATION		
				
				try {			
					
					String userEmail = userRepo.findByUserId(myReimbursement.getReiAuthor()).getEmail();
					
					if (emailService.validEmailAddress(userEmail) == false) {
						throw new Exception("Invalid email format");
					}
					
					String subject = "Reimbursement REJECTED";
					String emailContent = "Your reimbursement has been REJECTED";
					emailService.sendSimpleMessage(userEmail, subject, emailContent);

				} catch (Exception e) {
					System.err.println(e);
					responseMessage = "Reimbursement was rejected successfully, but author could not be notified";
				}

				reiRepo.save(myReimbursement);

				return responseMessage;
			}

		}
		return null;
	}

	@Override
	public List<Reimbursement> listOfAllReimbursements() {
		return reiRepo.findAll();
	}

}
