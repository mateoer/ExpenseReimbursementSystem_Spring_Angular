package root.service;

import java.util.List;

import root.dao.ReimbursementRepository;
import root.model.Reimbursement;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

public class ManagerService implements ManagerServiceInterface {
	
	private ReimbursementRepository reiRepo;
	
	public ManagerService (ReimbursementRepository reiRepo) {
		this.reiRepo = reiRepo;
	}

	@Override
	public List<Reimbursement> viewReimbursements() {
		return reiRepo.findAll();
	}

	@Override
	public Reimbursement approveReimbursement(Reimbursement reimb) {
		reimb.setReiStatus(ReiStatus.APPROVED);
		Reimbursement myReimb = reiRepo.save(reimb);
		return myReimb;
	}

	@Override
	public Reimbursement denyReimbursement(Reimbursement reimb) {
		reimb.setReiStatus(ReiStatus.DENIED);
		Reimbursement myReimb = reiRepo.save(reimb);
		return myReimb;
	}

	@Override
	public List<Reimbursement> filterReimbursementsByStatus(ReiStatus status) {
		return reiRepo.findByReiStatus(status);
	}

	@Override
	public List<Reimbursement> filterReimbursementsByType(ReiType reiType) {		
		return reiRepo.findByReiType(reiType);
	}

}
