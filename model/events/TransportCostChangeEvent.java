package model.events;

import model.map.Type;

public class TransportCostChangeEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private String company;
	private Type type;
	private double newWeightCost;
	private double newVolumeCost;
	private double duration;

	public TransportCostChangeEvent(int day, int month, int year, int time, String staff, String origin,
			String destination, String company, Type type, double newWeightCost, double newVolumeCost,
			double duration) {
		super(day, month, year, time, staff);
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.type = type;
		this.newWeightCost = newWeightCost;
		this.newVolumeCost = newVolumeCost;
		this.duration = duration;
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

	public double getNewWeightCost() {
		return newWeightCost;
	}

	public double getNewVolumeCost() {
		return newVolumeCost;
	}

	public double getDuration() {
		return duration;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("<html>");
		string.append("<h2>TRANSPORT COST CHANGE EVENT</h2><br>");
		string.append("Date: " + "<em>" + day + "/" + month + "/" + year + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Time: " + "<em>" + time + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Staff responsible: " + "<em>" + employee + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Origin: " + "<em>" + origin + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Destination: " + "<em>" + destination + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Company: " + "<em>" + company + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Type: " + "<em>" + type + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Weight Cost: " + "<em>" + newWeightCost + "</em>" + " per gram<sup>&zwnj</sup><br>");
		string.append("Volume Cost: " + "<em>" + newVolumeCost + "</em>" + " per cm<sup>3</sup>");
		string.append("</html>");
		return string.toString();
	}

}
