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
import root.model.UserReiContext;
import root.service.ManagerService;

@RestController
@CrossOrigin
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

	//viewListOfReimbursements
	@GetMapping("/getlistofreimbursements")
	public List<Reimbursement> viewListOfReimbursements() {
		System.out.println("\nList of all Reimbursements retrieved\n");
		return mangService.listOfAllReimbursements();
	}

	// approveReimbursements
	@PostMapping("/approvereimbursement")
	@ResponseStatus(HttpStatus.OK)
	public String approveReimbursement(@RequestBody UserReiContext userReiContext) {
		System.out.println("\nApproved\n");
		
		Reimbursement reimbToApprove = userReiContext.getReimbursement();
		User managerUser = userReiContext.getUser();
		
		if (reimbToApprove != null & managerUser != null) {
			
			Reimbursement dummyRei = mangService.approveReimbursement(reimbToApprove, managerUser);
			if (dummyRei != null) {
				return "Reimbursement was approved!";				
			}
		}
		return "Something went wrong";
	}

	// denyReimbursements
	@PostMapping("/denyreimbursement")
	@ResponseStatus(HttpStatus.OK)
	public String denyReimbursement(@RequestBody UserReiContext userReiContext) {
		System.out.println("\nDenied\n");

		Reimbursement reimbToDeny = userReiContext.getReimbursement();
		User managerUser = userReiContext.getUser();
		
		if (reimbToDeny != null & managerUser != null) {
			Reimbursement dummyRei = mangService.denyReimbursement(reimbToDeny, managerUser);
			if (dummyRei != null) {
				return "Reimbursement was rejected succesfully!";
			}
		}
		return "Something went wrong";
	}	

	
}
