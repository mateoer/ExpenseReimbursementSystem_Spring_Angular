package root.service.interfaces;

import java.util.List;

import root.model.Reimbursement;
import root.model.User;

public interface ManagerServiceInterface {
	
	public User getUserName(User user);	
	public List<Reimbursement> listOfAllReimbursements ();
	public List<User> listOfReiAuthors ();
	public String approveReimbursement (Reimbursement myReimbursement, User user);
	public String denyReimbursement (Reimbursement myReimbursement, User user);
	
	
}
