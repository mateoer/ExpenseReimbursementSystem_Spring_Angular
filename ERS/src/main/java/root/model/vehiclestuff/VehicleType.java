package root.model.vehiclestuff;


public enum VehicleType {
	
	CAR(Values.CAR),
	MOTORCYCLE(Values.MOTORCYCLE);
	
	private VehicleType (String val) {
//		if (!this.name().equals(val))
//			throw new IllegalArgumentException("Incorrect. Check logic. This is a custom message");
	}
	
//	private String value;
	
	class Values {
		public static final String CAR = "C";
		public static final String MOTORCYCLE = "M";
		
		
	}
}
