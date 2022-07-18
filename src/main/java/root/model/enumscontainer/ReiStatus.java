package root.model.enumscontainer;

public enum ReiStatus {
	PENDING (Values.PENDING),
	DENIED (Values.DENIED),
	APPROVED (Values.APPROVED);
	
	private ReiStatus (String val) {
		
	}
	
	public class Values {
		
		public static final String PENDING = "P";
		public static final String DENIED = "D";
		public static final String APPROVED = "A";
		
		
	}
}
