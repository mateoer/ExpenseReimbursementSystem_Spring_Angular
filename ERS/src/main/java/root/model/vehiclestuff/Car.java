package root.model.vehiclestuff;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = VehicleType.Values.CAR)
//@DiscriminatorColumn(name = "TYPE")
public class Car extends Vehicle {

//	@Id
//    @Column (name = "license_plate")
//    private String licensePlate;
//	private boolean runOnLpg;
	
	public Car(String licensePlate) {
		super(licensePlate);
		// TODO Auto-generated constructor stub
	}
}