package root.model;

import lombok.Data;

/*
 * This class is used to receive user's credentials 
 * together with the new password in a single request body
 */

@Data
public class NewPasswordContextClass {
	private User user;
	private String newPassword;
}
