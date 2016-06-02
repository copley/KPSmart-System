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
		string.append("<h2>ROUTE DISCONTINUED EVENT</h2><br>");
		string.append("Date: " + "<em>" + day + "/" + month + "/" + year + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Time: " + "<em>" + (time / 100) + ":" + String.format("%02d",(time % 100)) + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Staff responsible: " + "<em>" + employee + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Origin: " + "<em>" + origin + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Destination: " + "<em>" + destination + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Company: " + "<em>" + company + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Type: " + "<em>" + type + "</em>" + "<sup>&zwnj</sup>");
		string.append("</html>");
		return string.toString();
	}

}
