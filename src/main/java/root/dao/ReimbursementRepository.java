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
	public Reimbursement findByReiId(int reiId);
	public List<Reimbursement> findByReiAuthor(Integer reiAuthor);	
	public Reimbursement findByReiIdAndReiAuthor(int reiId, int reiAuthor);
	public Reimbursement findByReiAuthorAndReceiptPicName(int reiAuthor, String receiptPicName);
}
