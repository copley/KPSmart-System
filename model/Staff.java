package model;

public class Staff {

	private final int ID;
	private String Name;
	private String Password;

	public Staff(int iD, String name, String password) {
		super();
		ID = iD;
		Name = name;
		Password = password;
	}

	public boolean checkLogin(String name, String password) {
		// to be developed
		return true;
	}

}
