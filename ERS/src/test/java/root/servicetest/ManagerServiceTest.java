package root.servicetest;

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

import root.dao.ReimbursementRepository;
import root.model.Reimbursement;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;
import root.service.ManagerService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {

	@Mock
	private ReimbursementRepository reiRepo;

	private ManagerService mangService;

	@BeforeEach
	void setUp() throws Exception {
		mangService = new ManagerService(reiRepo);
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

		when(reiRepo.findAll()).thenReturn(initialReiList);

		// ACT
		List<Reimbursement> actualReiList = mangService.viewReimbursements();

		// ASSERT
		verify(reiRepo, times(1)).findAll();
		assertEquals(expectedReiList, actualReiList);
	}

	@Test
	void approveReimbursementTest() {

		// ARRANGE
		Reimbursement initialRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		Reimbursement expectedRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);

		when(reiRepo.save(initialRei)).thenReturn(initialRei);

		// ACT
		Reimbursement actualRei = mangService.approveReimbursement(initialRei);

		// ASSERT
		verify(reiRepo, times(1)).save(initialRei);
		assertEquals(ReiStatus.APPROVED, actualRei.getReiStatus());
		assertNotEquals(expectedRei, actualRei);
	}

	@Test
	void denyReimbursementTest() {
		// ARRANGE
		Reimbursement initialRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		Reimbursement expectedRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);

		when(reiRepo.save(initialRei)).thenReturn(initialRei);

		// ACT
		Reimbursement actualRei = mangService.denyReimbursement(initialRei);

		// ASSERT
		verify(reiRepo, times(1)).save(initialRei);
		assertEquals(ReiStatus.DENIED, actualRei.getReiStatus());
		assertNotEquals(expectedRei, actualRei);
	}

	@Test
	void filterReimbursementByStatusTest() {
		// ARRANGE
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.APPROVED, ReiType.FOOD));
		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.DENIED, ReiType.GAS));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.APPROVED, ReiType.OTHER));
		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));

		List<Reimbursement> expectedReiList = initialReiList.stream()
				.filter(e -> e.getReiStatus().equals(ReiStatus.PENDING)).collect(Collectors.toList());

		when(reiRepo.findByReiStatus(ReiStatus.PENDING)).thenReturn(expectedReiList);

		// ACT
		List<Reimbursement> actualReiList = mangService.filterReimbursementsByStatus(ReiStatus.PENDING);

		// ASSERT
		verify(reiRepo, times(1)).findByReiStatus(ReiStatus.PENDING);
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
	void filterReimbursementByTypeTest() {
		
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

		when(reiRepo.findByReiType(ReiType.LODGING)).thenReturn(expectedReiList);

		// ACT
		List<Reimbursement> actualReiList = mangService.filterReimbursementsByType(ReiType.LODGING);

		// ASSERT
		verify(reiRepo, times(1)).findByReiType(ReiType.LODGING);
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
