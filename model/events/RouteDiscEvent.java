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
		string.append("ROUTE DISCONTINUED EVENT\n");
		string.append("Date: " + day + ", " + month + ", " + year + "\n");
		string.append("Time: " + time + "\n");
		string.append("Staff responsible: " + employee + "\n");
		string.append("Origin: " + origin + "\n");
		string.append("Destination: " + destination + "\n");
		string.append("Company: " + company + "\n");
		string.append("Type: " + type);
		return string.toString();
	}

}
