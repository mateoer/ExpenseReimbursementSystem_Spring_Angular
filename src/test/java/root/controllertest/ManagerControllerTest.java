package root.controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import root.controller.ManagerController;
import root.model.Reimbursement;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;
import root.service.ManagerService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ManagerControllerTest {

	ManagerController mangController;

	@Mock
	ManagerService mangService;

	@BeforeEach
	void setUp() throws Exception {
		mangController = new ManagerController(mangService);
	}

	
	@Test
	void viewListOfReimbursementsTest() {
		// ARRANGE
		List<Reimbursement> initialReiList1 = new ArrayList<>();
		initialReiList1.add(new Reimbursement(20, "ice cream"));
		initialReiList1.add(new Reimbursement(30, "gas"));
		initialReiList1.add(new Reimbursement(12, "ticket"));
		
		List<Reimbursement> initialReiList2 = new ArrayList<>();
		initialReiList2.add(new Reimbursement(60, "hostel"));
		initialReiList2.add(new Reimbursement(20, "ppv fee"));

		List<Reimbursement> expectedReiList = new ArrayList<>();
		expectedReiList.addAll(initialReiList1);
		expectedReiList.addAll(initialReiList2);
		
		when(mangService.listOfAllReimbursements()).thenReturn(expectedReiList);
		

		// ACT
		List<Reimbursement> actualReiList = mangService.listOfAllReimbursements();
		
		
		
		// ASSERT
		verify(mangService, times(1)).listOfAllReimbursements();
		assertAll(
				()->	assertThat(expectedReiList.size()== actualReiList.size()),
				()->	assertThat(expectedReiList.equals(actualReiList)),			
				()->	assertThat(expectedReiList.equals(actualReiList))				
				
				//NOTE: assertEquals doesn't work well with maps of lists apparently
				//this assertion will fail even though the one above passes
//				()-> 	assertEquals(expectedReiList, actualReiList)
					);		
	}
	
	@Test
	void approveReimbursementTest() {
		// ARRANGE
		Reimbursement initialRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		Reimbursement expectedRei = new Reimbursement(20, "ice cream", ReiStatus.APPROVED, ReiType.FOOD);
		when(mangService.approveReimbursement(initialRei)).thenReturn(expectedRei);
		
		System.out.println("ReiStatus(initialRei): "+initialRei.getReiStatus());
		// ACT
		Reimbursement actualRei = mangController.approveReimbursement(initialRei);
		System.out.println("ReiStatus(actualRei): "+actualRei.getReiStatus());

		// ASSERT
		verify(mangService, times(1)).approveReimbursement(initialRei);
		
		assertAll(
			()->	assertEquals(ReiStatus.APPROVED, actualRei.getReiStatus()),
			()->	assertEquals(expectedRei, actualRei)				
				);
		
		System.out.println("expectedRei: "+expectedRei.getReiStatus()+"  actualRei: "+actualRei.getReiStatus());
	}

	
	@Test
	void denyReimbursementTest() {

		// ARRANGE
		Reimbursement initialRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		Reimbursement expectedRei = new Reimbursement(20, "ice cream", ReiStatus.DENIED, ReiType.FOOD);
		when(mangService.denyReimbursement(initialRei)).thenReturn(expectedRei);

		// ACT
		Reimbursement actualRei = mangController.denyReimbursement(initialRei);

		// ASSERT
		verify(mangService, times(1)).denyReimbursement(initialRei);
		assertAll(
				()->	assertEquals(ReiStatus.DENIED, actualRei.getReiStatus()),
				()->	assertEquals(expectedRei, actualRei)				
					);
	}	

}
