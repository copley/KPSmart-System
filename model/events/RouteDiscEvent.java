package model.events;

import model.map.Type;

public class RouteDiscEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private String company;
	private Type type;

	public RouteDiscEvent(int day, int month, int year, int time, String staff, String origin, String destination,
			String company, Type type) {
		super(day, month, year, time, staff);
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.type = type;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getCompany() {
		return company;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("<html>");
		string.append("ROUTE DISCONTINUED EVENT<br>");
		string.append("Date: " + day + ", " + month + ", " + year + "<br>");
		string.append("Time: " + time + "<br>");
		string.append("Staff responsible: " + employee + "<br>");
		string.append("Origin: " + origin + "<br>");
		string.append("Destination: " + destination + "<br>");
		string.append("Company: " + company + "<br>");
		string.append("Type: " + type);
		string.append("</html>");
		return string.toString();
	}

}
