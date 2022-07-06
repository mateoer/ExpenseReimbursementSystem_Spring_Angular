package root.daotest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import root.model.vehiclestuff.Vehicle;
import root.model.vehiclestuff.VehicleRepository;
import root.model.vehiclestuff.VehicleType;

@DataJpaTest
//@Sql(scripts = "/vehicle-test.sql")
class VehicleH2Test {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Test
	void vehicleEnumTest() {		
		
		vehicleRepository.save(new Vehicle("123-ABC"));		
		assertThat(vehicleRepository.findByLicensePlate("123-ABC")).isNotNull();
		
		Vehicle myVehicle = vehicleRepository.findByLicensePlate("123-ABC");
		System.out.println("Vehicle type: "+ myVehicle.getVehicleType()
		+"\t\tLicense plate: "+myVehicle.getLicensePlate());
		
		myVehicle.setVehicleType(VehicleType.CAR);		
		assertEquals(myVehicle.getVehicleType(), VehicleType.CAR);
		System.out.println("Vehicle type: "+ myVehicle.getVehicleType()
						+"\t\tLicense plate: "+myVehicle.getLicensePlate());
		
		myVehicle.setVehicleType(VehicleType.MOTORCYCLE);		
		assertEquals(myVehicle.getVehicleType(), VehicleType.MOTORCYCLE);
		System.out.println("Vehicle type: "+ myVehicle.getVehicleType()
						+"\tLicense plate: "+myVehicle.getLicensePlate());
		
		
		
	}

}