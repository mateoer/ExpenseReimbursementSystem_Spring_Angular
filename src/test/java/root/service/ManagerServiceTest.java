package root.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import root.dao.UserRepository;
import root.model.Reimbursement;
import root.model.User;
import root.model.UserRole;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;
import root.service.mail.EmailService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {

	
	@Mock
	private ReimbursementRepository reiRepo;
	
	
	@Mock
	private UserRepository userRepo;

	@Mock
	private EmailService emailService;

	@Mock
	private ManagerService mangService;

	@BeforeEach
	void setUp() {
		mangService = new ManagerService(reiRepo, userRepo, emailService);
	}


	@Test
	void getUserName() {
		User myUser1 = new User("sue", "abc123", "Sue", "Liz", "mateoer@kean.edu", UserRole.EMPLOYEE);
			myUser1.setUserId(1);
		User myUser2 = new User("mateoer", "abc123", "Eric", "Mateo", "ericmateo5675@gmail.com", UserRole.MANAGER);
				
		when(mangService.getUserName(myUser1)).thenReturn(myUser1);
		when(mangService.getUserName(myUser2)).thenReturn(null);
		
		User username1 = mangService.getUserName(myUser1);
		User username2 = mangService.getUserName(myUser2);

		System.out.println("\nIn the getUserName");			
		
		assertAll(
				() -> assertThat(username1, is(myUser1)), 
				() -> assertNotNull(username1),
				() -> assertNull(username2)
				);
		
	}

	
	@Test
	void listOfAllReimbursements() {
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
		
		when(reiRepo.findAll()).thenReturn(expectedReiList);

		// ACT
		List<Reimbursement> actualReiList = mangService.listOfAllReimbursements();

		System.out.println("\nIn the listOfAllReimbursements");	
		// ASSERT
		assertAll(
				 () -> assertThat(actualReiList.size(), is(expectedReiList.size()))
				 );
				
		
	}
	
	
	@Test
	void denyReimbursement() {
		User employeeWithGoodEmail = new User(1, "suechan", "abc123", "Sue", "Chan", "ericmateo5675@gmail.com", UserRole.EMPLOYEE, null, null, null);
		Reimbursement rei1 = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
			rei1.setReiId(1);
			rei1.setReiAuthor(employeeWithGoodEmail.getUserId());		
		
		
		User employeeWithBadEmail = new User(2, "anri", "abc123", "Anri", "Ban", "sdfsagm.com", UserRole.EMPLOYEE, null, null, null);
		Reimbursement rei2 = new Reimbursement(25, "burgers", ReiStatus.PENDING, ReiType.FOOD);
			rei2.setReiId(2);
			rei2.setReiAuthor(employeeWithBadEmail.getUserId());
			
		Reimbursement rei3 = new Reimbursement();
		
		User manager = new User(3, "eric", "abc123", "Eric", "Smith", "mateoer@kean.edu", UserRole.MANAGER, null, null, null);
		
			
		when(userRepo.findByUserId(manager.getUserId())).thenReturn(manager);
		when(userRepo.findByUserId(employeeWithGoodEmail.getUserId())).thenReturn(employeeWithGoodEmail);
		when(userRepo.findByUserId(employeeWithBadEmail.getUserId())).thenReturn(employeeWithBadEmail);
		
		when(reiRepo.findByReiId(rei1.getReiId())).thenReturn(rei1);
		when(reiRepo.findByReiId(rei2.getReiId())).thenReturn(rei2);
		
		when(reiRepo.findByReiId(rei3.getReiId())).thenReturn(null);
		
		
		
		when(emailService.validEmailAddress(employeeWithGoodEmail.getEmail())).thenReturn(true);
		when(emailService.validEmailAddress(employeeWithBadEmail.getEmail())).thenReturn(false).thenThrow(new RuntimeException("Invalid email format"));
		
		
		String success1 = mangService.denyReimbursement(rei1, manager);
		String successButNoMail = mangService.denyReimbursement(rei2, manager);
		String invalidRei = mangService.denyReimbursement(rei3, manager);
		String validateWEmp1 = mangService.denyReimbursement(rei2, employeeWithGoodEmail);
		String validateWEmp2 = mangService.denyReimbursement(rei2, employeeWithBadEmail);
		
		verify(emailService, times(1))
			.sendSimpleMessage(
					employeeWithGoodEmail.getEmail(), 
					"Reimbursement REJECTED", 
					"Your reimbursement has been REJECTED");
		
		assertAll(
				()-> assertThat(success1, is("Reimbursement was rejected successfully!")),
				()-> assertThat(successButNoMail, is("Reimbursement was rejected successfully, but author could not be notified")),
				()-> assertNull(invalidRei),
				()-> assertNull(validateWEmp1),
				()-> assertNull(validateWEmp2)
				);		
		
	}

	
	@Test
	void approveReimbursement() {
		User employeeWithGoodEmail = new User(1, "suechan", "abc123", "Sue", "Chan", "ericmateo5675@gmail.com", UserRole.EMPLOYEE, null, null, null);
		Reimbursement rei1 = new Reimbursement(20, "ice cream", ReiStatus.PENDING, ReiType.FOOD);
			rei1.setReiId(1);
			rei1.setReiAuthor(employeeWithGoodEmail.getUserId());		
		
		
		User employeeWithBadEmail = new User(2, "anri", "abc123", "Anri", "Ban", "sdfsagm.com", UserRole.EMPLOYEE, null, null, null);
		Reimbursement rei2 = new Reimbursement(25, "burgers", ReiStatus.PENDING, ReiType.FOOD);
			rei2.setReiId(2);
			rei2.setReiAuthor(employeeWithBadEmail.getUserId());
			
		Reimbursement rei3 = new Reimbursement();
		
		User manager = new User(3, "eric", "abc123", "Eric", "Smith", "mateoer@kean.edu", UserRole.MANAGER, null, null, null);
		
			
		when(userRepo.findByUserId(manager.getUserId())).thenReturn(manager);
		when(userRepo.findByUserId(employeeWithGoodEmail.getUserId())).thenReturn(employeeWithGoodEmail);
		when(userRepo.findByUserId(employeeWithBadEmail.getUserId())).thenReturn(employeeWithBadEmail);
		
		when(reiRepo.findByReiId(rei1.getReiId())).thenReturn(rei1);
		when(reiRepo.findByReiId(rei2.getReiId())).thenReturn(rei2);
		
		when(reiRepo.findByReiId(rei3.getReiId())).thenReturn(null);
		
		
		
		when(emailService.validEmailAddress(employeeWithGoodEmail.getEmail())).thenReturn(true);
		when(emailService.validEmailAddress(employeeWithBadEmail.getEmail())).thenReturn(false).thenThrow(new RuntimeException("Invalid email format"));
		
		
		String success1 = mangService.approveReimbursement(rei1, manager);
		String successButNoMail = mangService.approveReimbursement(rei2, manager);
		String invalidRei = mangService.approveReimbursement(rei3, manager);
		String validateWEmp1 = mangService.approveReimbursement(rei2, employeeWithGoodEmail);
		String validateWEmp2 = mangService.approveReimbursement(rei2, employeeWithBadEmail);
		
		verify(emailService, times(1))
			.sendSimpleMessage(
					employeeWithGoodEmail.getEmail(), 
					"Reimbursement APPROVED", 
					"Your reimbursement has been APPROVED");
		
		assertAll(
				()-> assertThat(success1, is("Reimbursement was approved successfully!")),
				()-> assertThat(successButNoMail, is("Reimbursement was approved successfully, but author could not be notified")),
				()-> assertNull(invalidRei),
				()-> assertNull(validateWEmp1),
				()-> assertNull(validateWEmp2)
				);		
		
		
	}
	
}
