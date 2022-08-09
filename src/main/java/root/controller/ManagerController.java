package root.controller;

import java.util.List;
import java.util.Map;

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
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;
import root.service.ManagerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

	// viewReimbursements
	@GetMapping("/viewreimbursements")
	public Map<User, List<Reimbursement>> viewReimbursements() {
		System.out.println("\nReimbursement list retrieved\n");
		return mangService.viewReimbursements();
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
	
	
	
	/////NOT USING THESE METHODS. FILTERING IS DONE AT CLIENT SIDE

	// filterReimbursementsByStatus
	@GetMapping("/filterbystatus")
	public List<Reimbursement> filterByStatus(@RequestBody ReiStatus status) {
		return mangService.filterReimbursementsByStatus(status);
	}

	// filterReimbursementsByType
	@GetMapping("/filterbytype")
	public List<Reimbursement> filterByType(@RequestBody ReiType reiType) {
		return mangService.filterReimbursementsByType(reiType);
	}
}
