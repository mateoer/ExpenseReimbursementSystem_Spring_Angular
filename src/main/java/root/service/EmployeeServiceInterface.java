package root.service;

import java.util.List;

import root.model.Reimbursement;
import root.model.User;

public interface EmployeeServiceInterface {
	
	public User getUserName(User user);
	public Reimbursement addReimbursement(Reimbursement reimb, User user);
	public List<Reimbursement> getAllReimbursementsByUserId(User user);
}
