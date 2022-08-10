package root.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import root.dao.ReimbursementRepository;
import root.dao.UserRepository;
import root.model.Reimbursement;
import root.model.User;
import root.model.enumscontainer.ReiStatus;

@Service
public class ManagerService implements ManagerServiceInterface {
	
	private ReimbursementRepository reiRepo;
	private UserRepository userRepo;
	
	public ManagerService (ReimbursementRepository reiRepo, UserRepository userRepo) {
		this.reiRepo = reiRepo;
		this.userRepo = userRepo;
	}

	@Override
	public User getUserName(User reqUser) {		
		return userRepo.findByUserId(reqUser.getUserId());
	}	

	@Override
	public Reimbursement approveReimbursement(Reimbursement reimb) {		
		Reimbursement mdaReimbursement = new Reimbursement(); 
		mdaReimbursement = reiRepo.findByReiId(reimb.getReiId());
		mdaReimbursement.setReiStatus(ReiStatus.APPROVED);
		LocalDateTime lcdt = LocalDateTime.now();
		mdaReimbursement.setRei_resolvedDate(lcdt);
		reiRepo.save(mdaReimbursement);
		return reiRepo.findByReiId(mdaReimbursement.getReiId());	
	}

	@Override
	public Reimbursement denyReimbursement(Reimbursement reimb) {
		Reimbursement myReimbursement = reiRepo.findByReiId(reimb.getReiId()); 				
		myReimbursement.setReiStatus(ReiStatus.DENIED);
		LocalDateTime lcdt = LocalDateTime.now();
		myReimbursement.setRei_resolvedDate(lcdt);
		reiRepo.save(myReimbursement);
		return reiRepo.findByReiId(myReimbursement.getReiId());	
	}	

	@Override
	public List<Reimbursement> listOfAllReimbursements() {		
		return reiRepo.findAll();
	}


}
