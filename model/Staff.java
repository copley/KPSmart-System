package model;

public class Staff {

	private final int ID;
	private String Name;
	private String Password;
	private boolean isManager;

	public Staff(int iD, String name, String password, boolean isManager) {
		ID = iD;
		Name = name;
		Password = password;
		this.isManager = isManager;
	}

	public boolean checkLogin(String name, String password) {
		// to be developed
		return true;
	}

}
