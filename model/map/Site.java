package model.map;

public class Site {
	private final int ID;
	private final String location;
	private boolean isOrigin;
	private boolean isDestination;

	public Site(int id, String location, boolean isOrigin, boolean isDestination) {
		this.ID = id;
		this.location = location;
		this.isOrigin = isOrigin;
		this.isDestination = isDestination;
	}

	public Integer getID() {
		return ID;
	}

	public String getLocation() {
		return location;
	}

	public boolean isOrigin() {
		return isOrigin;
	}

	public boolean isDestination() {
		return isDestination;
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("ID: " + ID + "\n");
		string.append("Location: " + location);
		return string.toString();
	}

	public void setIsOrigin() {
		isOrigin = true;
	}

	public void setIsDestination() {
		isDestination = true;
	}
}
