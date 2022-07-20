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
import root.model.UserReiContext;
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
	public Reimbursement addReimbursement(@RequestBody UserReiContext userReiContext){		
		Reimbursement newReimbursement = userReiContext.getReimbursement();
		
		User user =  userReiContext.getUser();		
		return employeeService.addReimbursement(newReimbursement, user);
	}
	
	//getAllReimbursements
	@GetMapping("/getreimbursements")
	public List<Reimbursement> getAllReimbursements (@RequestBody User user){		
		return employeeService.getAllReimbursementsByUserId(user);
	}
	
	//getAllReimbursements associated to the user
	@GetMapping("/userreimbursements")
	public List<Reimbursement> getAllReimbursementsByUserId(User user){
		return employeeService.getAllReimbursementsByUserId(user);
	}
	
}
