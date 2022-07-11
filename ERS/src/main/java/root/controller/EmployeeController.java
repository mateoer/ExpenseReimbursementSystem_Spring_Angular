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
import root.service.EmployeeService;

@RestController
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//addNewReimbursement
	@PostMapping("/addreimbursement")
	@ResponseStatus(HttpStatus.CREATED)
	public Reimbursement addReimbursement(@RequestBody Reimbursement newReimbursement) {
		
//		if (newReimbursement.getRei_amount() == 0 || 
//			newReimbursement.getReiStatus() == null || 
//			newReimbursement.getReiType() == null  ) {
//			
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create new reimbursement");
//		}
		
		return employeeService.addReimbursement(newReimbursement);
	}
	
	//getAllReimbursements
	@GetMapping("/getreimbursements")
	public List<Reimbursement> getAllReimbursements (){
		return employeeService.getAllReimbursements();
	}
	
}
