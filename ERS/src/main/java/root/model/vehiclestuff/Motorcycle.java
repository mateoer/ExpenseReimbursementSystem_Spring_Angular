package root.model.vehiclestuff;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = VehicleType.Values.MOTORCYCLE)
//@DiscriminatorColumn(name = "TYPE")
public class Motorcycle extends Vehicle {

//	@Id
//    @Column (name = "license_plate")
//    private String licensePlate;
//	private boolean hasSideCar;
	public Motorcycle(String licensePlate) {
		super(licensePlate);
		// TODO Auto-generated constructor stub
	}
}
