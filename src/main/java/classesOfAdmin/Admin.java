package classesOfAdmin;

public class Admin {

	String adminName;
	String adminPassword;
	private static Admin admin= null;
	private Admin(String name, String password){
		adminName = name;
		adminPassword = password;
	}
	
	public static Admin createObjectForAdmin(String adminName, String adminPassword) {
		if(admin == null) {
			admin = new Admin(adminName, adminPassword);
		}
		return admin;
	}
	
}
