package root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import root.model.Reimbursement;
import root.model.User;
import root.model.UserReiRequest;
import root.model.enumscontainer.ReiStatus;
import root.service.EmployeeService;

@RestController
@CrossOrigin("*")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//addNewReimbursement
	@PreAuthorize("hasRole('EMPLOYEE')")
	@PostMapping("/addreimbursement")
	@ResponseStatus(HttpStatus.CREATED)
	public String addReimbursement(@RequestBody UserReiRequest userReiContext){		
		Reimbursement newReimbursement = userReiContext.getReimbursement();
		User user =  userReiContext.getUser();
		
		if (newReimbursement != null & user != null) {
			Reimbursement dummyRei = employeeService.addReimbursement(newReimbursement, user);
			if (dummyRei != null) {
				System.out.println("\nNew Reimbursement Added\n");
				return "New reimbursement added!";
			}
		}		
		return "Something went wrong";
	}
	
	//getAllReimbursements
	@PreAuthorize("hasRole('EMPLOYEE')")
	@PostMapping("/getreimbursements")
	public List<Reimbursement>  getAllReimbursements (@RequestBody User user){		
		System.out.println("\nReimbursement list retrieved\n");
		return employeeService.getAllReimbursementsByUserId(user);
	}
	
	//getUserName
	@PostMapping("/getusername")
	public User getUserName(@RequestBody User reqUser) {
		System.out.println("\nUser name retrieved\n");
		return employeeService.getUserName(reqUser);
		
	}
	
	//deleteReimbursement
	@PreAuthorize("hasRole('EMPLOYEE')")
	@PostMapping("/deleteReimbursement")
	public String removeReimbursement(@RequestBody UserReiRequest userReiContext) {
		Reimbursement reimbToCancel = userReiContext.getReimbursement();
		User reiCreator = userReiContext.getUser();
		
		Reimbursement reimbExist = employeeService
				.getReiByIdAndAuthor(reimbToCancel, reiCreator);		
		
		System.out.println("\nDeleting reimbursement");
		if (reimbExist == null)
			return "Could not complete request";
		else if (reimbExist.getReiStatus() == ReiStatus.APPROVED || reimbExist.getReiStatus() == ReiStatus.DENIED)
			return "Cannot delete a processed request";
		else			
			return employeeService.cancelReimbursement(reimbExist);	
		
	}
	
	//editReimbursement
	@PreAuthorize("hasRole('EMPLOYEE')")
	@PostMapping("/updateReimbursement")
	public String editReimbursement(@RequestBody UserReiRequest userReiContext) {
		Reimbursement reimbToUpdate = userReiContext.getReimbursement();
		User reiCreator = userReiContext.getUser();
		System.out.println("/nEditing reimbursement");
		if (reimbToUpdate != null && reiCreator != null) {
			Reimbursement reiExists = employeeService.updateReimbursement(reimbToUpdate, reiCreator);
			if (reiExists != null) {
				return "Reimbursement was updated successfully!";
			}
		}
		return "Could not complete request";
	}
	
}
