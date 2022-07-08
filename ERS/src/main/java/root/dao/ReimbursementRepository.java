package root.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.model.Reimbursement;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Integer> {

	List<Reimbursement> findByReiStatus(ReiStatus status);	
	List<Reimbursement> findByReiType(ReiType reiType);	
	
	
}
