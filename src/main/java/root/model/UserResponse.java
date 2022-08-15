package root.model;

import lombok.Data;

@Data
public class UserResponse {
	private User user;
	private boolean found = true;
}
