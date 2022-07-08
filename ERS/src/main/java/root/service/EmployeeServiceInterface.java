package root.service;

import java.util.List;

import root.model.Reimbursement;

public interface EmployeeServiceInterface {
	
	public List<Reimbursement> getAllReimbursements();
	public Reimbursement addReimbursement(Reimbursement reimb);
}
