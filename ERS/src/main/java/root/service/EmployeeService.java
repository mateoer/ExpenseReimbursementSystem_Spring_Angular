package root.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import root.dao.ReimbursementRepository;
import root.model.Reimbursement;

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
	public Reimbursement addReimbursement(Reimbursement reimb) {
		Reimbursement myReimb = reiRepo.save(reimb);
		return myReimb;
	}

}
