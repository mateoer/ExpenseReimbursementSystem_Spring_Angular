package root.model;

import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * This class is used to receive most request bodies
 * that do an operation on reimbursements but 
 * require some sort of validation first.
 * i.e.: adding reimbursements require the user id as well
 * as that the user role is 'EMPLOYEE'
 */

@Data
@Component
public class UserReiContext {
	private User user;
	private Reimbursement reimbursement;
	public UserReiContext() {
		super();
	}
	public UserReiContext(User user, Reimbursement reimbursement) {
		super();
		this.user = user;
		this.reimbursement = reimbursement;
	}
	
}
