package root.daotest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import root.dao.ReimbursementRepository;
import root.model.Reimbursement;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

@DataJpaTest
class ReimbursementRepositoryH2Test {

	@Autowired
	private ReimbursementRepository reimbursementRepo;

	@Test
	void createReimbursementTest() {

		Reimbursement reiTest = new Reimbursement();
		reiTest.setRei_amount(20);
		reiTest.setReiStatus(ReiStatus.PENDING);
		reiTest.setRei_description("ice cream money");
		reiTest.setReiType(ReiType.FOOD);
		
		reimbursementRepo.save(reiTest);
		
		assertEquals(ReiStatus.PENDING, reiTest.getReiStatus());
		System.out.println("Status: "+reiTest.getReiStatus());
		
		assertEquals(ReiType.FOOD, reiTest.getReiType());
		System.out.println("Type: "+reiTest.getReiType());
		
	}

}
