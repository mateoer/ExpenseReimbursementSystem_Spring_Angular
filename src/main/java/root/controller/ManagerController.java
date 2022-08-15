package root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import root.model.Reimbursement;
import root.model.User;
import root.service.ManagerService;

@RestController
@CrossOrigin(origins = "http://localhost:9050/")
public class ManagerController {

	private ManagerService mangService;

	@Autowired
	public ManagerController(ManagerService mangService) {
		this.mangService = mangService;
	}

	// getUserName
	@PostMapping("/getmngusername")
	public User getUserName(@RequestBody User reqUser) {
		System.out.println("\nUser name retrieved\n");
		return mangService.getUserName(reqUser);
	}

	
	@GetMapping("/getlistofreimbursements")
	public List<Reimbursement> viewListOfReimbursements() {
		System.out.println("\nList of all Reimbursements retrieved\n");
		return mangService.listOfAllReimbursements();
	}

	// approveReimbursements
	@PostMapping("/approvereimbursement")
	@ResponseStatus(HttpStatus.OK)
	public Reimbursement approveReimbursement(@RequestBody Reimbursement reimb) {
		System.out.println("\nApproved\n");
		return mangService.approveReimbursement(reimb);
	}

	// denyReimbursements
	@PostMapping("/denyreimbursement")
	@ResponseStatus(HttpStatus.OK)
	public Reimbursement denyReimbursement(@RequestBody Reimbursement reimb) {
		System.out.println("\nDenied\n");
		return mangService.denyReimbursement(reimb);
	}	

	
}
