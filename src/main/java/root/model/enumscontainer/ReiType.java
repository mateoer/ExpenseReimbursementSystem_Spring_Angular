package root.model.enumscontainer;

public enum ReiType {
	FOOD(Values.FOOD), 
	LODGING(Values.LODGING), 
	GAS(Values.GAS), 
	OTHER(Values.OTHER);
	
	private ReiType (String val) {
		
	}
	
	public class Values {

		public static final String FOOD = "F";
		public static final String LODGING = "L";
		public static final String GAS = "G";
		public static final String OTHER = "O";

	}
}
