package root.model;

import org.springframework.stereotype.Component;

import lombok.Data;

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
