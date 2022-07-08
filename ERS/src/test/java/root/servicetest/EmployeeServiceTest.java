package root.servicetest;

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

import root.dao.ReimbursementRepository;
import root.model.Reimbursement;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;
import root.service.EmployeeService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private ReimbursementRepository reiRepo;
	
	private EmployeeService empService;
	
	@BeforeEach
	void setUp() throws Exception {
		empService = new EmployeeService(reiRepo);
	}
	
	
	@Test
	void getAllReimbursementsTest() {
		//ARRANGE
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD));
		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.PENDING, ReiType.GAS));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));
		
		List<Reimbursement> expectedReiList = new ArrayList<>();
		expectedReiList.addAll(initialReiList);
		
		when(reiRepo.findAll()).thenReturn(initialReiList);
		
		//ACT
		List<Reimbursement> actualReiList = empService.getAllReimbursements();
		
		//ASSERT
		verify(reiRepo, times(1)).findAll();
		assertEquals(expectedReiList, actualReiList);
	}
	
	@Test
	void addReimbursementTest() {
		
		//ARRANGE
		Reimbursement initialRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		Reimbursement expectedRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		
		when(reiRepo.save(initialRei)).thenReturn(initialRei);
		
		//ACT
		Reimbursement actualRei = empService.addReimbursement(initialRei);
		
		//ASSERT
		verify(reiRepo, times(1)).save(initialRei);
		assertEquals(expectedRei, actualRei);
	}

}
