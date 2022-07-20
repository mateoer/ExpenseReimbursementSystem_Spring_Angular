package root.service;

import java.util.List;
import java.util.Map;

import root.model.Reimbursement;
import root.model.User;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

public interface ManagerServiceInterface {

	public Map<User,List<Reimbursement>> viewReimbursements ();
	public Reimbursement approveReimbursement (Reimbursement myReimbursement, User user);
	public Reimbursement denyReimbursement (Reimbursement myReimbursement, User user);
	
	public List<Reimbursement> filterReimbursementsByStatus (ReiStatus status);
	public List<Reimbursement> filterReimbursementsByType (ReiType reiType);
	
}
