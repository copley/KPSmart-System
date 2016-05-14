package model.map;

public class Site {
	private final int ID;
	private final String location;

	public Site(int id, String location) {
		this.ID = id;
		this.location = location;
	}

	public Integer getID() {
		return ID;
	}

	public String getLocation() {
		return location;
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("ID: " + ID + "\n");
		string.append("Location: " + location);
		return string.toString();
	}
}
