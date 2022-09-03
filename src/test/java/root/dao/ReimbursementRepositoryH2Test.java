package root.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
		System.out.println("Status: " + reiTest.getReiStatus());

		assertEquals(ReiType.FOOD, reiTest.getReiType());
		System.out.println("Type: " + reiTest.getReiType());

	}

	@Test
	void findByReiStatusTest() {

		// ARRANGE
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.APPROVED, ReiType.FOOD));
		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.DENIED, ReiType.GAS));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.APPROVED, ReiType.OTHER));
		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));

		reimbursementRepo.saveAll(initialReiList);

		List<Reimbursement> expectedReiList = new ArrayList<>();

		// ACT
		expectedReiList = reimbursementRepo.findByReiStatus(ReiStatus.PENDING);

		// ASSERT
		assertNotEquals(expectedReiList, initialReiList);

		System.out.println("Expected");
		for (Reimbursement reimbursement : expectedReiList) {
			System.out.println("ID: " + reimbursement.getReiId() + "\tStatus: " + reimbursement.getReiStatus());
		}
	}

	@Test
	void findByReiTypeTest() {
		// ARRANGE
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", ReiStatus.APPROVED, ReiType.FOOD));
		initialReiList.add(new Reimbursement(30, "gas", ReiStatus.DENIED, ReiType.GAS));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.PENDING, ReiType.OTHER));
		initialReiList.add(new Reimbursement(12, "ticket", ReiStatus.APPROVED, ReiType.OTHER));
		initialReiList.add(new Reimbursement(60, "hostel", ReiStatus.PENDING, ReiType.LODGING));
		initialReiList.add(new Reimbursement(20, "ppv fee", ReiStatus.PENDING, ReiType.LODGING));

		reimbursementRepo.saveAll(initialReiList);

		List<Reimbursement> expectedReiList = new ArrayList<>();

		// ACT
		expectedReiList = reimbursementRepo.findByReiType(ReiType.LODGING);

		// ASSERT
		assertNotEquals(expectedReiList, initialReiList);

		System.out.println("Expected");
		for (Reimbursement reimbursement : expectedReiList) {
			System.out.println("ID: " + reimbursement.getReiId() + "\tStatus: " + reimbursement.getReiType()
					+ "\tResolver: " + reimbursement.getRei_resolver());
		}

	}

	@Test
	void findByReiAuthorTest() {
		// ARRANGE
		List<Reimbursement> initialReiList = new ArrayList<>();
		initialReiList.add(new Reimbursement(20, "ice cream", 2));
		initialReiList.add(new Reimbursement(30, "gas", 2));
		initialReiList.add(new Reimbursement(12, "ticket", 2));
		initialReiList.add(new Reimbursement(12, "ticket", 2));
		initialReiList.add(new Reimbursement(60, "hostel", 2));
		initialReiList.add(new Reimbursement(20, "ppv fee", 2));

		Reimbursement reiFromOtherUser1 = new Reimbursement(55, "tequila", 1);
		Reimbursement reiFromOtherUser2 = new Reimbursement(20, "movies", 1);

		List<Reimbursement> reiList = new ArrayList<>();
		reiList.addAll(initialReiList);
		reiList.add(reiFromOtherUser2);
		reiList.add(reiFromOtherUser1);

		reimbursementRepo.saveAll(reiList);

		List<Reimbursement> expectedReiList = new ArrayList<>();

		// ACT
		expectedReiList = reimbursementRepo.findByReiAuthor(2);
		
		//ASSERT
		assertEquals(expectedReiList, initialReiList);
		assertNotEquals(expectedReiList, reiList);
		System.out.println("ExpectedReilist");
		for (Reimbursement reimbursement : expectedReiList) {
			System.out.println("amount: "+reimbursement.getRei_amount()+"\tdescription: "+reimbursement.getRei_description()+"\tauthor: "+reimbursement.getReiAuthor());
		}
		System.out.println("Reilist");
		for (Reimbursement reimbursement : reiList) {
			System.out.println("amount: "+reimbursement.getRei_amount()+"\tdescription: "+reimbursement.getRei_description()+"\tauthor: "+reimbursement.getReiAuthor());
		}

	}
}
