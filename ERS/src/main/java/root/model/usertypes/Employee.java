package root.model.usertypes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import root.model.User;
import root.model.enumscontainer.*;

@Entity
@DiscriminatorValue(value = UserRole.Values.EMPLOYEE)
public class Employee extends User {
	
	public Employee () {
		
	}
}
