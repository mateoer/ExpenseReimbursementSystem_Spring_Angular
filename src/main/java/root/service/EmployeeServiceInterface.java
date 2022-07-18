package root.service;

import java.util.List;

import root.model.Reimbursement;
import root.model.User;

public interface EmployeeServiceInterface {
	
	public List<Reimbursement> getAllReimbursements();
	public Reimbursement addReimbursement(Reimbursement reimb, User user);
	List<Reimbursement> getAllReimbursementsByUserId(User user);
}
