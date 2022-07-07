package root.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import root.model.Reimbursement;

public interface ReimbursementRepository extends JpaRepository<Reimbursement, Integer> {
	
	
}
