package root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import root.model.Reimbursement;
import root.model.User;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;
import root.service.ManagerService;

@RestController
public class ManagerController {

	private ManagerService mangService;
	
	@Autowired
	public ManagerController(ManagerService mangService) {
		this.mangService = mangService;
	}	
	
	//viewReimbursements
	@GetMapping("/viewreimbursements")
	public List<Reimbursement> viewReimbursements (){
		return mangService.viewReimbursements();
	}
	
	//approveReimbursements
	@PostMapping("/approvereimbursement")
	@ResponseStatus(HttpStatus.OK)
	public Reimbursement approveReimbursement (@RequestBody Reimbursement reiToApprove, User user) {
		return mangService.approveReimbursement(reiToApprove, user);
	}
	
	//denyReimbursements
	@PostMapping("/denyreimbursement")
	@ResponseStatus(HttpStatus.OK)
	public Reimbursement denyReimbursement (@RequestBody Reimbursement reiToDeny, User user) {
		return mangService.denyReimbursement(reiToDeny, user);
	}
	
	//filterReimbursementsByStatus
	@GetMapping("/filterbystatus")
	public List<Reimbursement> filterByStatus (@RequestBody ReiStatus status){
		return mangService.filterReimbursementsByStatus(status);
	}
	
	//filterReimbursementsByType
	@GetMapping("/filterbytype")
	public List<Reimbursement> filterByType (@RequestBody ReiType reiType){
		return mangService.filterReimbursementsByType(reiType);
	}
}
