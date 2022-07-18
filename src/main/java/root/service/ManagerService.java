package root.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import root.dao.ReimbursementRepository;
import root.model.Reimbursement;
import root.model.User;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

@Service
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
	public Reimbursement approveReimbursement(Reimbursement reimb, User user) {
		reimb.setReiStatus(ReiStatus.APPROVED);
		reimb.setRei_resolver(user.getUserId());
		LocalDateTime lcdt = LocalDateTime.now();
		reimb.setRei_resolvedDate(lcdt);
		Reimbursement myReimb = reiRepo.save(reimb);
		return myReimb;
	}

	@Override
	public Reimbursement denyReimbursement(Reimbursement reimb, User user) {
		reimb.setReiStatus(ReiStatus.DENIED);
		reimb.setRei_resolver(user.getUserId());
		LocalDateTime lcdt = LocalDateTime.now();
		reimb.setRei_resolvedDate(lcdt);
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
