package root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import root.model.Reimbursement;
import root.model.User;
import root.model.UserReiContext;
import root.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//addNewReimbursement
	@PostMapping("/addreimbursement")
	@ResponseStatus(HttpStatus.CREATED)
	public Reimbursement addReimbursement(@RequestBody UserReiContext userReiContext){		
		Reimbursement newReimbursement = userReiContext.getReimbursement();
		System.out.println("\nNew Reimbursement Added\n");
		User user =  userReiContext.getUser();		
		return employeeService.addReimbursement(newReimbursement, user);
	}
	
	//getAllReimbursements
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
	
}
