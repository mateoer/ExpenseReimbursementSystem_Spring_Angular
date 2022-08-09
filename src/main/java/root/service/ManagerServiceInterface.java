package root.service;

import java.util.List;
import java.util.Map;

import root.model.Reimbursement;
import root.model.User;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

public interface ManagerServiceInterface {
	
	public User getUserName(User user);

	public Map<User,List<Reimbursement>> viewReimbursements ();
	public Reimbursement approveReimbursement (Reimbursement myReimbursement);
	public Reimbursement denyReimbursement (Reimbursement myReimbursement);
	
	public List<Reimbursement> filterReimbursementsByStatus (ReiStatus status);
	public List<Reimbursement> filterReimbursementsByType (ReiType reiType);
	
}
