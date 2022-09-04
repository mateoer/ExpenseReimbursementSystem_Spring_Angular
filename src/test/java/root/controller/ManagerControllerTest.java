package root.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import root.service.ManagerService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ManagerControllerTest {

	private ManagerController mangController;

	@Mock
	private ManagerService mangService;
	
	private User employee1 = new User(1,"suechan", "abc123", "Sue", "Liz", "suechan123@rdssnet", UserRole.EMPLOYEE, null, null, null);
	private User employee2 = new User(2,"anri", "abc123", "Anri", "Ortiz", "sdads3@revatsdsaure.net", UserRole.EMPLOYEE, null, null, null);
	private User manager = new User(3, "mateoer", "abc123", "Eric", "Mateo", "eric234@revature.net", UserRole.MANAGER, null, null, null);

	@BeforeEach
	void setUp() throws Exception {
		mangController = new ManagerController(mangService);
	}

	@Test
	void getUserName() {
		
		when(mangService.getUserName(manager)).thenReturn(manager);
		
		assertThat(mangController.getUserName(manager), is(manager));		
	}
	
	@Test
	void viewListOfReimbursements() {
		
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
		emp2Reis.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		emp2Reis.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		emp2Reis.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));
		for (Reimbursement reimbursement : emp2Reis) {
			reimbursement.setReiAuthor(employee2.getUserId());
		}
		
		List<Reimbursement> allReimbursements = new ArrayList<>();
		allReimbursements.addAll(emp1Reis);
		allReimbursements.addAll(emp2Reis);
		
		when(mangService.listOfAllReimbursements()).thenReturn(allReimbursements);
		
		List<Reimbursement> actualList = mangController.viewListOfReimbursements();
		
		assertThat(actualList.size(), is(allReimbursements.size()));
		
	}
	
	@Test
	void approveReimbursement() {
		
		Reimbursement reiToApprove = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		reiToApprove.setReiAuthor(employee1.getUserId());
		
		UserReiContext approveRequest1 = new UserReiContext();
		approveRequest1.setReimbursement(reiToApprove);
		approveRequest1.setUser(manager);
		
		UserReiContext approveRequest2 = new UserReiContext();
		approveRequest2.setReimbursement(reiToApprove);
		approveRequest2.setUser(employee1);
		
		String serviceResponse = mangController.approveReimbursement(approveRequest1);
		String serviceResponse2 = mangController.approveReimbursement(approveRequest2);
		
		assertThat(serviceResponse, serviceResponse, is(instanceOf(String.class)));
		assertNotNull(serviceResponse);	
		assertThat(serviceResponse2, serviceResponse2, is(instanceOf(String.class)));
		assertThat(serviceResponse2, serviceResponse2, is("Something went wrong"));
		
	}
	
	@Test
	void denyReimbursement() {
		
		Reimbursement reiToDeny = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		reiToDeny.setReiAuthor(employee1.getUserId());
		
		UserReiContext denyRequest1 = new UserReiContext();
		denyRequest1.setReimbursement(reiToDeny);
		denyRequest1.setUser(manager);
		
		UserReiContext denyRequest2 = new UserReiContext();
		denyRequest2.setReimbursement(reiToDeny);
		denyRequest2.setUser(employee1);
		
		String serviceResponse = mangController.denyReimbursement(denyRequest1);
		String serviceResponse2 = mangController.denyReimbursement(denyRequest2);
		
		assertThat(serviceResponse, serviceResponse, is(instanceOf(String.class)));
		assertNotNull(serviceResponse);	
		assertThat(serviceResponse2, serviceResponse2, is(instanceOf(String.class)));
		assertThat(serviceResponse2, serviceResponse2, is("Something went wrong"));
		
	}
	
	

}
