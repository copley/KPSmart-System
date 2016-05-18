package model.employees;

public class Employee {

	private final int ID;
	private String name;
	private String password;
	private boolean isManager;

	public Employee(int id, String name, String password, boolean isManager) {
		ID = id;
		this.name = name;
		this.password = password;
		this.isManager = isManager;
	}

	public boolean checkLogin(String name, String password) {
		// to be developed
		return true;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public boolean isManager() {
		return isManager;
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("ID: " + ID + "\n");
		string.append("Name: " + name + "\n");
		string.append("Password: " + password + "\n");
		string.append("Manager: " + isManager);
		return string.toString();
	}
}
