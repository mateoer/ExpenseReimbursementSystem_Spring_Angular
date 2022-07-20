package root.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import root.dao.ReimbursementRepository;
import root.dao.UserRepository;
import root.model.Reimbursement;
import root.model.User;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

@Service
public class ManagerService implements ManagerServiceInterface {
	
	private ReimbursementRepository reiRepo;
	private UserRepository userRepo;
	
	public ManagerService (ReimbursementRepository reiRepo, UserRepository userRepo) {
		this.reiRepo = reiRepo;
		this.userRepo = userRepo;
	}

	@Override
	public Map<User, List<Reimbursement>> viewReimbursements() {
		List<Reimbursement> reiList = new ArrayList<>();
		reiList = getAllReimbursements();
		
		List<User> userList = new ArrayList<>();
		userList = getAllUsers();
		
		Map<User, List<Reimbursement>> tableMap = new HashMap<User, List<Reimbursement>>();
		
		for (User user : userList) {
			
			List<Reimbursement> tempList = new ArrayList<>();
			
			for (Reimbursement rei : reiList) {
				
				if (user.getUserId() == rei.getReiAuthor()) {
					tempList.add(rei);
				}
				
			}
			
			if (!(tempList.isEmpty())) {
				tableMap.put(user, tempList);
				
			}
			
		}
//		System.out.println("In the Manager Service");
		
		return tableMap;
	}
	
	public List<Reimbursement> getAllReimbursements() {
		return reiRepo.findAll();
	}
	public List<User> getAllUsers() {
		return userRepo.findAll();
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
