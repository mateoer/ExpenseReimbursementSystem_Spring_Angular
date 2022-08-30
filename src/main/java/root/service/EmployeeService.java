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
import root.service.interfaces.EmployeeServiceInterface;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	private ReimbursementRepository reiRepo;
	
	private UserRepository userRepo;
	
	@Autowired
    public EmployeeService(ReimbursementRepository reiRepo, UserRepository userRepo) {
        this.reiRepo = reiRepo;
        this.userRepo = userRepo;
    }
	
		
	@Override
	public List<Reimbursement> getAllReimbursementsByUserId(User user) {
		Integer reiAuthor = user.getUserId();
		return reiRepo.findByReiAuthor(reiAuthor);
	}

	@Override
	public Reimbursement addReimbursement(Reimbursement reimb, User user) {
		
		User reqUser = userRepo.findByUserId(user.getUserId());
		
		if (reqUser != null) {
			if (reqUser.getUserRole() == UserRole.EMPLOYEE) {
				reimb.setReiAuthor(user.getUserId());
				LocalDateTime lcdt = LocalDateTime.now();
				reimb.setRei_submitteDate(lcdt);
				reimb.setReiStatus(ReiStatus.PENDING);
				Reimbursement myReimb = reiRepo.save(reimb);
				
				return myReimb;
			}			
		}
		
		return null;
	}	

	@Override
	public User getUserName(User reqUser) {		
		return userRepo.findByUserId(reqUser.getUserId());
	}
	
	@Override
	public String cancelReimbursement(Reimbursement reimb) {
		Reimbursement reiToCancel = reiRepo.findByReiId(reimb.getReiId());
		if(reiToCancel != null) {
			reiRepo.delete(reiToCancel);
			return "Reimbursement was deleted";
		}
		return "Reimbursement not found";
	}
	
	@Override
	public Reimbursement getReiByIdAndAuthor(Reimbursement reimb, User user) {
		return reiRepo.findByReiIdAndReiAuthor(reimb.getReiId(), user.getUserId());
	}


	@Override
	public Reimbursement updateReimbursement(Reimbursement reimb, User user) {
		Reimbursement reiToUpdate = reiRepo.findByReiId(reimb.getReiId());
		User reqUser = userRepo.findByUserId(user.getUserId());
		if (reqUser != null & reiToUpdate != null) {
			if (reqUser.getUserRole() == UserRole.EMPLOYEE) {
				if (reiToUpdate != null) {
					reiToUpdate.setRei_amount(reimb.getRei_amount());
					reiToUpdate.setRei_description(reimb.getRei_description());
					reiToUpdate.setReiType(reimb.getReiType());
					reiRepo.save(reiToUpdate);
					return reiToUpdate;
				}				
			}
		}
		
		return null;
	}

}
