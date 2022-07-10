package root.controllertest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	void viewReimbursementsTest() {
		// ARRANGE
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD));
		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.PENDING, ReiType.GAS));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));

		List<Reimbursement> expectedReiList = new ArrayList<>();
		expectedReiList.addAll(initialReiList);

		when(mangService.viewReimbursements()).thenReturn(initialReiList);

		// ACT
		List<Reimbursement> actualReiList = mangController.viewReimbursements();

		// ASSERT
		verify(mangService, times(1)).viewReimbursements();
		assertEquals(expectedReiList, actualReiList);
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

	
	@Test
	void filterByStatusTest() {
		// ARRANGE
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.APPROVED, ReiType.FOOD));
		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.DENIED, ReiType.GAS));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.APPROVED, ReiType.OTHER));
		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));

		// NOTE: TO use stream on an collection the stream must be specified at the
		// declaration,
		// otherwise it will not work
		// ie List<Reimbursement> expectedReiList = new ArrayList<>();
		// expectedReiList = initialReilist.stream().... //this doesn't work
		List<Reimbursement> expectedReiList = initialReiList.stream()
				.filter(e -> e.getReiStatus().equals(ReiStatus.PENDING)).collect(Collectors.toList());

		when(mangService.filterReimbursementsByStatus(ReiStatus.PENDING)).thenReturn(expectedReiList);

		// ACT
		List<Reimbursement> actualReiList = mangController.filterByStatus(ReiStatus.PENDING);

		// ASSERT
		verify(mangService, times(1)).filterReimbursementsByStatus(ReiStatus.PENDING);
		assertNotEquals(initialReiList, actualReiList);
		assertEquals(expectedReiList, actualReiList);

		System.out.println("Initial");
		for (Reimbursement reimbursement : initialReiList) {
			System.out.println("ID: " + reimbursement.getReiId() + "\tStatus: " + reimbursement.getReiStatus());
		}
		System.out.println("Expected");
		for (Reimbursement reimbursement : expectedReiList) {
			System.out.println("ID: " + reimbursement.getReiId() + "\tStatus: " + reimbursement.getReiStatus());
		}
		System.out.println("Actual");
		for (Reimbursement reimbursement : actualReiList) {
			System.out.println("ID: " + reimbursement.getReiId() + "\tStatus: " + reimbursement.getReiStatus());
		}
	}

	
	@Test
	void filterByTypeTest() {
		// ARRANGE
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.APPROVED, ReiType.FOOD));
		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.DENIED, ReiType.GAS));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.APPROVED, ReiType.OTHER));
		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));

		List<Reimbursement> expectedReiList = initialReiList.stream()
				.filter(e -> e.getReiType().equals(ReiType.LODGING)).collect(Collectors.toList());

		when(mangService.filterReimbursementsByType(ReiType.LODGING)).thenReturn(expectedReiList);

		// ACT
		List<Reimbursement> actualReiList = mangController.filterByType(ReiType.LODGING);

		// ASSERT
		verify(mangService, times(1)).filterReimbursementsByType(ReiType.LODGING);
		assertNotEquals(initialReiList, actualReiList);
		assertEquals(expectedReiList, actualReiList);

		System.out.println("Initial");
		for (Reimbursement reimbursement : initialReiList) {
			System.out.println("ID: " + reimbursement.getReiId() + "\tStatus: " + reimbursement.getReiType());
		}
		System.out.println("Expected");
		for (Reimbursement reimbursement : expectedReiList) {
			System.out.println("ID: " + reimbursement.getReiId() + "\tStatus: " + reimbursement.getReiType());
		}
		System.out.println("Actual");
		for (Reimbursement reimbursement : actualReiList) {
			System.out.println("ID: " + reimbursement.getReiId() + "\tStatus: " + reimbursement.getReiType());
		}
	}

}
