package root.model.usertypes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import root.model.User;
import root.model.enumscontainer.*;

@Entity
@DiscriminatorValue(value = UserRole.Values.MANAGER)
public class Manager extends User {

	public Manager () {
		
	}
}
