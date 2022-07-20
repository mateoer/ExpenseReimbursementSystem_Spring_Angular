package root.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import root.model.Reimbursement;
import root.model.enumscontainer.ReiStatus;
import root.model.enumscontainer.ReiType;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Integer> {

	public List<Reimbursement> findByReiStatus(ReiStatus status);	
	public List<Reimbursement> findByReiType(ReiType reiType);
	
//	@Query("SELECT * FROM User u INNER JOIN Reimbursement r ON u.userId = r.reiAuthor")
//	public List<Reimbursement> viewReimbursements();
	
	public List<Reimbursement> findByReiAuthor(int reiAuthor);	
	
}
