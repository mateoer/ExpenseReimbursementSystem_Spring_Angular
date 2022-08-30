//package root.controllertest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import root.controller.EmployeeController;
//import root.model.Reimbursement;
//import root.model.User;
//import root.model.UserReiContext;
//import root.model.UserRole;
//import root.model.enumscontainer.ReiStatus;
//import root.model.enumscontainer.ReiType;
//import root.service.EmployeeService;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class EmployeeControllerTest {
//
//	@Autowired
//	EmployeeController empController;
//	
//	@Autowired
//	UserReiContext	userReiContext;
//	
//	@Mock
//	// EmployeeServiceInterface works as well bc EmployeeService implements it
//	// but need to change the parameter type in the controller contructor to
//	// interface type as well
//	// so that Spring can do the autowiring through the interface instead of its
//	// implementation
//	EmployeeService empService;
//
//	
//
//	@BeforeEach
//	void setUp() throws Exception {
//		empController = new EmployeeController(empService);
//	}
//
//	@Test
//	void addReimbursementTest() {
//
//		// ARRANGE
//		Reimbursement initialRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
//		Reimbursement expectedRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
//
//		User myUser = new User("suechan", "abc123", "Sue", "Liz", "suechan123@revature.net", UserRole.EMPLOYEE);
//		
//		userReiContext.setUser(myUser);
//		userReiContext.setReimbursement(initialRei);
//		
//		when(empService.addReimbursement(initialRei, myUser)).thenReturn(initialRei);
//		
//		// ACT
//		Reimbursement actualRei = empController.addReimbursement(userReiContext);
//
//		// ASSERT
//		verify(empService, times(1)).addReimbursement(initialRei, myUser);
//		assertThat(expectedRei.equals(actualRei));
//
//	}
//
//
//	@Test
//	void getAllReimbursementsByUserIdTest() {
//		// ARRANGE
//		List<Reimbursement> initialReiList = new ArrayList<>();
//		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD));
//		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.PENDING, ReiType.GAS));
//		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
//		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
//		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));
//
//		User myUser = new User("mateoer", "abc123", "Eric", "Mateo", "eric234@revature.net", UserRole.EMPLOYEE);
////		userRepo.save(myUser);
//
//		for (Reimbursement reimbursement : initialReiList) {
//			reimbursement.setReiAuthor(myUser.getUserId());
//		}
//		
//		List<Reimbursement> expectedReiList = new ArrayList<>();
//		expectedReiList.addAll(initialReiList);
//
//		when(empService.getAllReimbursementsByUserId(myUser)).thenReturn(initialReiList);
//
//		// ACT
//		List<Reimbursement> actualReiList = empController.getAllReimbursements(myUser);
//
//		// ASSERT
//		verify(empService, times(1)).getAllReimbursementsByUserId(myUser);
//		assertEquals(expectedReiList, actualReiList);
//	}
//
//}
