package root.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import root.dao.ReimbursementRepository;
import root.dao.UserRepository;
import root.model.Reimbursement;
import root.model.User;
import root.model.UserRole;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;
import root.service.EmployeeService;
import root.service.interfaces.EmployeeServiceInterface;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private ReimbursementRepository reiRepo;
	
	
	
	@Autowired
	private UserRepository userRepo;

	private EmployeeServiceInterface empService;

	@BeforeEach
	void setUp() throws Exception {
		empService = new EmployeeService(reiRepo,userRepo);		
	}

	

	@Test
	void addReimbursementTest() {

		// ARRANGE
		Reimbursement initialRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		Reimbursement expectedRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);

		User myUser = new User("suechan", "abc123", "Sue", "Liz", "suechan123@revature.net", UserRole.EMPLOYEE);
		userRepo.save(myUser);
		
		when(reiRepo.save(initialRei)).thenReturn(initialRei);

		// ACT
		Reimbursement actualRei = empService.addReimbursement(initialRei, myUser);
		
		//set expectedRei's submitted date and author equals actualRei's bc, since expectedRei was 
		//entered manually then these parameters would not match with what the repository produces
		//randomly
		
		expectedRei.setRei_submitteDate(actualRei.getRei_submitteDate());
		expectedRei.setReiAuthor(myUser.getUserId());
		
		
		// ASSERT
		verify(reiRepo, times(1)).save(initialRei);
		assertThat(expectedRei.equals(actualRei));
		System.out.println("User Id:    " + myUser.getUserId());
		System.out.println("Rei author: " + actualRei.getReiAuthor());
	}

	@Test
	void getAllReimbursementsByUserIdTest() {
		// ARRANGE
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD));
		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.PENDING, ReiType.GAS));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));
		
		User myUser = new User("mateoer", "abc123", "Eric", "Mateo", "eric234@revature.net", UserRole.EMPLOYEE);
		userRepo.save(myUser);
		
		for (Reimbursement reimbursement : initialReiList) {
			reimbursement.setReiAuthor(myUser.getUserId());
		}
		
		List<Reimbursement> expectedReiList = new ArrayList<>();
		expectedReiList.addAll(initialReiList);

		
		when(reiRepo.findByReiAuthor(myUser.getUserId())).thenReturn(initialReiList);

		// ACT
		List<Reimbursement> actualReiList = empService.getAllReimbursementsByUserId(myUser);

		// ASSERT
		verify(reiRepo, times(1)).findByReiAuthor(myUser.getUserId());
		assertEquals(expectedReiList, actualReiList);
		
		System.out.println("myUser ID: "+myUser.getUserId());
		for (Reimbursement reimbursement : actualReiList) {
			System.out.print(reimbursement.getReiAuthor()+" ");
		}
		System.out.println();

	}

}
