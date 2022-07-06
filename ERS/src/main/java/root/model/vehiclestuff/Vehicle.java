package root.model.vehiclestuff;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public class Vehicle {
	






	@Id
    @Column (name = "license_plate")
    private String licensePlate;
    
    
    
	@Enumerated  (EnumType.STRING) 
    private VehicleType vehicleType;
    
	
	public Vehicle() {
		
	}
	
	public Vehicle(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	
//    @Column (name = "type" , nullable = true)
//    private String type;
//    public enum VehicleType {	
//    	CAR ,
//    	MOTORCYCLE 	
//    }
}

