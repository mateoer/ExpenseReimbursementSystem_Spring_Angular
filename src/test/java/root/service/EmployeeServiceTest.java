package root.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private ReimbursementRepository reiRepo;	
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmployeeService empService;

	@BeforeEach
	void setUp() throws Exception {
		empService = new EmployeeService(reiRepo,userRepo);		
	}
	
	@AfterEach
	void emptyRepo() {
		userRepo.deleteAll();
	}

	@Test
	void getAllReimbursementsByUserId() {
		
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD));
		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.PENDING, ReiType.GAS));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));
		
		User myUser = new User("mateoer", "abc123", "Eric", "Mateo", "dvsdvd@xsaklhkjas", UserRole.EMPLOYEE);
		userRepo.save(myUser);
		
		for (Reimbursement reimbursement : initialReiList) {
			reimbursement.setReiAuthor(myUser.getUserId());
		}
		
		List<Reimbursement> expectedReiList = new ArrayList<>();
		expectedReiList.addAll(initialReiList);
		
		
		when(reiRepo.findByReiAuthor(myUser.getUserId())).thenReturn(initialReiList);
		
		
		List<Reimbursement> actualReiList = empService.getAllReimbursementsByUserId(myUser);		
		
		assertAll(				
				()-> assertEquals(expectedReiList, actualReiList)
				);		
		
	}

	@Test
	void addReimbursement() {
		
		Reimbursement initialRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		Reimbursement expectedRei = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);

		User myUser1 = new User("suechan", "abc123", "Sue", "Liz", "scazscsc@czdbfbgb.net", UserRole.EMPLOYEE);
		User myUser2 = new User("mateoer", "abc123", "Eric", "Mateo", "cscsccs@revcczczature.net", UserRole.MANAGER);
		userRepo.save(myUser1);
		userRepo.save(myUser2);
		
		when(reiRepo.save(initialRei)).thenReturn(initialRei);

		
		Reimbursement actualRei1 = empService.addReimbursement(initialRei, myUser1);
		Reimbursement actualRei2 = empService.addReimbursement(initialRei, myUser2);
		
				
		
		assertAll(				
				()-> assertEquals(initialRei.getRei_description(), expectedRei.getRei_description()),
				()-> assertEquals(initialRei.getRei_amount(), expectedRei.getRei_amount()),
				()-> assertEquals(initialRei.getReiStatus(), expectedRei.getReiStatus()),
				()-> assertEquals(initialRei.getReiType(), expectedRei.getReiType()),
				()-> assertNotEquals(actualRei1, actualRei2),
				()-> assertNull(actualRei2) //must be null since user2 is MANAGER
				);
		
	}

	@Test
	void getUserName() {
		User myUser1 = new User("sue", "abc123", "Sue", "Liz", "sue@htdhtdh.net", UserRole.EMPLOYEE);
		User myUser2 = new User("mateoer", "abc123", "Eric", "Mateo", "gfdgdfg34@hdfhhdf.net", UserRole.MANAGER);
		userRepo.save(myUser1);
		
		User username1 = empService.getUserName(myUser1);
		User username2 = empService.getUserName(myUser2);
		
		assertAll(				
				()-> assertNotEquals(username1, username2),
				()-> assertNull(username2) 
				);
	}

	@Test
	void cancelReimbursement() {
		Reimbursement rei1 = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		rei1.setReiId(1);
		reiRepo.save(rei1);
		
		Reimbursement rei2 = new Reimbursement(20, "sandwich", ReiStatus.PENDING, ReiType.FOOD);
		
		String resp1 = empService.cancelReimbursement(rei1);
		String resp2 = empService.cancelReimbursement(rei2);
		
		assertAll(				
				()-> assertThat(resp1.equals("Reimbursement was deleted")),
				()-> assertThat(resp2.equals("Reimbursement not found")),
				()-> assertThat(reiRepo.findById(1).isEmpty()) 
				);		
	}
	
	@Test
	void getReiByIdAndAuthor() {
		User myUser1 = new User("sue", "abc123", "Sue", "Liz", "sdzcdcde@revavsvvbture.net", UserRole.EMPLOYEE);
		myUser1.setUserId(1);
		Reimbursement rei1 = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		rei1.setReiAuthor(myUser1.getUserId());
		userRepo.save(myUser1);
		reiRepo.save(rei1);
		
		Reimbursement rei2 = new Reimbursement(20, "sandwich", ReiStatus.PENDING, ReiType.FOOD);
		rei2.setReiId(2);
		
		Reimbursement rei1resp = empService.getReiByIdAndAuthor(rei1, myUser1);
		Reimbursement rei2resp = empService.getReiByIdAndAuthor(rei2, myUser1);
		
		
		assertAll(				
				()-> assertThat(rei1.equals(rei1resp)),
				()-> assertNull(rei2resp)
				);	
		assertThatThrownBy(() -> empService.getReiByIdAndAuthor(rei1, null));
		assertThatThrownBy(() -> empService.getReiByIdAndAuthor(null, myUser1));
		assertThatThrownBy(() -> empService.getReiByIdAndAuthor(null, null));
		
	}
	
	@Test
	void updateReimbursement() {
		//EMPLOYEE
		User myUser1 = new User("sue", "abc123", "Sue", "Liz", "sdgsdgsgue@rgfgfdgge.net", UserRole.EMPLOYEE);
		myUser1.setUserId(1);
		Reimbursement rei1 = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
		Reimbursement rei1copy = rei1;
		rei1.setReiAuthor(myUser1.getUserId());
		userRepo.save(myUser1);
		reiRepo.save(rei1);
		
		//updating 
		rei1.setRei_amount(35);
		rei1.setRei_description("gas");
		rei1.setReiType(ReiType.GAS);
		
		//MANAGER
		User myUser2 = new User("mateoer", "abc123", "Eric", "Mateo", "fafdfsdfs4@refdsfsdgvature.net", UserRole.MANAGER);
		myUser2.setUserId(2);
		Reimbursement rei2 = new Reimbursement(20, "sandwich", ReiStatus.PENDING, ReiType.FOOD);
		rei2.setReiAuthor(myUser2.getUserId());
		userRepo.save(myUser2);
		reiRepo.save(rei2);
		
		//updating // this has no effect since when manager tries to update it will return null
		rei2.setRei_amount(45);
		rei2.setRei_description("convention");
		rei2.setReiType(ReiType.LODGING);
		
		Reimbursement reiResp1 = empService.updateReimbursement(rei1, myUser1);
		Reimbursement reiResp2 = empService.updateReimbursement(rei2, myUser2);
		Reimbursement reiResp3 = empService.updateReimbursement(rei1, myUser2);
		Reimbursement reiResp4 = empService.updateReimbursement(rei2, myUser1);
		
		assertAll(				
				()-> assertNotEquals(rei1, reiResp1),
				()-> assertNotEquals(rei1copy, reiResp1),
				()-> assertNotEquals(rei2, reiResp2),
				()-> assertNull(reiResp2),
				()-> assertNull(reiResp3),
				()-> assertNull(reiResp4)				
				);
		
		
	}
	
	
}
