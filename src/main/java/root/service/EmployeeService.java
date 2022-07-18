package root.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import root.dao.ReimbursementRepository;
import root.model.Reimbursement;
import root.model.User;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	private ReimbursementRepository reiRepo;
	
	@Autowired
    public EmployeeService(ReimbursementRepository reiRepo) {
        this.reiRepo = reiRepo;
    }
	
	
	@Override
	public List<Reimbursement> getAllReimbursements() {
		return reiRepo.findAll();
	}
	@Override
	public List<Reimbursement> getAllReimbursementsByUserId(User user) {
		int reiAuthor = user.getUserId();
		return reiRepo.findByReiAuthor(reiAuthor);
	}

	@Override
	public Reimbursement addReimbursement(Reimbursement reimb, User user) {
		reimb.setRei_author(user.getUserId());
		LocalDateTime lcdt = LocalDateTime.now();
		reimb.setRei_submitteDate(lcdt);
		Reimbursement myReimb = reiRepo.save(reimb);
		return myReimb;
	}

}
