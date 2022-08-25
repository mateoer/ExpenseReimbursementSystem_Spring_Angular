package root.model;

import lombok.Data;

@Data
public class NewPasswordContextClass {
	private User user;
	private String newPassword;
}
