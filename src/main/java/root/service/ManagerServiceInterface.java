package root.service;

import java.util.List;

import root.model.Reimbursement;
import root.model.User;

public interface ManagerServiceInterface {
	
	public User getUserName(User user);	
	public List<Reimbursement> listOfAllReimbursements ();
	public Reimbursement approveReimbursement (Reimbursement myReimbursement);
	public Reimbursement denyReimbursement (Reimbursement myReimbursement);
	
	
}
