package root.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import root.model.Reimbursement;
import root.model.User;
import root.model.UserReiContext;
import root.model.UserRole;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;
import root.service.EmployeeService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

	@Mock
	private EmployeeController empController;
		
	@Mock	
	private EmployeeService empService;		

	private User employee1 = new User(1,"suechan", "abc123", "Sue", "Liz", "suechagdgfn123@rdssnet", UserRole.EMPLOYEE, null, null, null);
	private User employee2 = new User(2,"anri", "abc123", "Anri", "Ortiz", "sdads3@revatsdsaure.net", UserRole.EMPLOYEE, null, null, null);
	
	@BeforeEach
	void setUp() throws Exception {
		empController = new EmployeeController(empService);
	}
	
	@Test
	void addReimbursement() {
		
		Reimbursement reiToApprove = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		reiToApprove.setReiAuthor(employee1.getUserId());
		
		UserReiContext addRequest1 = new UserReiContext();
		addRequest1.setReimbursement(reiToApprove);
		addRequest1.setUser(employee1);
		
		UserReiContext addRequest2 = new UserReiContext();
		
		when(empService.addReimbursement(reiToApprove, employee1)).thenReturn(reiToApprove);
		
		String serviceResponse1 = empController.addReimbursement(addRequest1);
		String serviceResponse2 = empController.addReimbursement(addRequest2);
		
		assertThat(serviceResponse1, serviceResponse1, is(instanceOf(String.class)));
		assertThat(serviceResponse1, is("New reimbursement added!"));
		assertThat(serviceResponse2, serviceResponse2, is(instanceOf(String.class)));
		assertThat(serviceResponse2, is("Something went wrong"));
	}
	
	@Test
	void getAllReimbursements() {
		
		List<Reimbursement> emp1Reis = new ArrayList<>();
		emp1Reis.add(new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD));
		emp1Reis.add(new Reimbursement(30, "gas", ReiStatus.PENDING, ReiType.GAS));
		emp1Reis.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		emp1Reis.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		emp1Reis.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));
		for (Reimbursement reimbursement : emp1Reis) {
			reimbursement.setReiAuthor(employee1.getUserId());
		}
		
		List<Reimbursement> emp2Reis = new ArrayList<>();
		emp2Reis.add(new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD));
		emp2Reis.add(new Reimbursement(30, "gas", ReiStatus.PENDING, ReiType.GAS));
		for (Reimbursement reimbursement : emp2Reis) {
			reimbursement.setReiAuthor(employee2.getUserId());
		}
		
		List<Reimbursement> allReimbursements = new ArrayList<>();
		allReimbursements.addAll(emp1Reis);
		allReimbursements.addAll(emp2Reis);
		
		when(empService.getAllReimbursementsByUserId(employee1)).thenReturn(emp1Reis);
		
		List<Reimbursement> actualList = empController.getAllReimbursements(employee1);
		
		assertThat(actualList, is(emp1Reis));
		assertThat(actualList, not(allReimbursements));
		assertThat(actualList, not(emp2Reis));		
	}
	
	@Test
	void getUserName() {
		
		when(empService.getUserName(employee1)).thenReturn(employee1);
		
		assertThat(empController.getUserName(employee1), is(employee1));	
		
	}
	
	@Test
	void removeReimbursement() {
		
		Reimbursement reiToDelete = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		reiToDelete.setReiAuthor(employee1.getUserId());
		
		UserReiContext delRequest = new UserReiContext();
		delRequest.setReimbursement(reiToDelete);
		delRequest.setUser(employee1);	
		
		
		when(empService.getReiByIdAndAuthor(reiToDelete, employee1)).thenReturn(reiToDelete);	
		when(empService.cancelReimbursement(reiToDelete)).thenReturn("Reimbursement was deleted");		
		
		String serviceResponse = empController.removeReimbursement(delRequest);
				
		assertThat(serviceResponse , is(instanceOf(String.class)));
		assertThat(serviceResponse , is(empService.cancelReimbursement(reiToDelete)));	
		
		
	}
	
	@Test
	void editReimbursement() {
		
		Reimbursement reiToEdit = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		reiToEdit.setReiAuthor(employee1.getUserId());
		
		UserReiContext editRequest = new UserReiContext();
		editRequest.setReimbursement(reiToEdit);
		editRequest.setUser(employee1);	
		
		when(empService.updateReimbursement(reiToEdit, employee1)).thenReturn(reiToEdit);
		
		String editResponse = empController.editReimbursement(editRequest);
		
		assertThat(editResponse, is("Reimbursement was updated successfully!"));
		
	}	

}
