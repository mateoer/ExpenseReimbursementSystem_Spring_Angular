package root.model.enumscontainer;

public enum UserRole {
	MANAGER (Values.MANAGER),
	EMPLOYEE (Values.EMPLOYEE);
	
	private UserRole (String val) {
		
	}
	
	public class Values {
		
		public static final String MANAGER = "M";
		public static final String EMPLOYEE = "E";
		
		
	}
}
