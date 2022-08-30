package root.model;

import lombok.Data;

/*
 * This class is used to validate the user at login.
 * Once it's found in DB "found" sends this response while
 * "user" carries the credentials to the front end.
 * 
 * Most of the logic that requires user to be authenticated relies on this
 * class * 
 */

@Data
public class UserResponse {
	private User user;
	private boolean found = true;
}
