package root.daotest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import root.dao.ReimbursementRepository;
import root.dao.UserRepository;
import root.model.Reimbursement;
import root.model.User;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

@DataJpaTest
class UserReiH2Test {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReimbursementRepository reiRepo;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void userReitest() {
		
		User myUser = new User();
		Reimbursement myRei = new Reimbursement();
		
		myUser.setUsername("mateoer");
		userRepo.save(myUser);
		
		myRei.setRei_amount(20);
		myRei.setRei_description("ice cream");
		myRei.setReiType(ReiType.FOOD);
		myRei.setReiStatus(ReiStatus.PENDING);
		myRei.setReiAuthor(myUser);
		reiRepo.save(myRei);
		
		
		assertAll(
				()-> assertNotNull(reiRepo.findById(myRei.getReiId())),
				()-> assertEquals(ReiStatus.PENDING, myRei.getReiStatus()),
				()-> assertEquals(ReiType.FOOD, myRei.getReiType()),
				()-> assertNotNull(userRepo.findByUsername(myUser.getUsername())),
				()-> assertEquals(myUser, userRepo.findByUsername("mateoer"))				
				);
		
		
		
	}

}
