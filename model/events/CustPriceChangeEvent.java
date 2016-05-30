package model.events;

import model.map.Priority;
import model.map.Type;

public class CustPriceChangeEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private Priority priority;
	private double newWeightCost;
	private double newVolumeCost;

	public CustPriceChangeEvent(int day, int month, int year, int time, String staff, String origin, String destination,
			Priority priority, double newWeightCost, double newVolumeCost) {
		super(day, month, year, time, staff);
		this.origin = origin;
		this.destination = destination;
		this.priority = priority;
		this.newWeightCost = newWeightCost;
		this.newVolumeCost = newVolumeCost;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("<html>");
		string.append("<h2>CUSTOMER PRICE CHANGE EVENT</h2><br>");
		string.append("Date: " + day + "/" + month + "/" + year + "<br>");
		string.append("Time: " + time + "<br>");
		string.append("Staff responsible: " + employee + "<br>");
		string.append("Origin: " + origin + "<br>");
		string.append("Destination: " + destination + "<br>");
		string.append("Weight Cost: " + newWeightCost + "<br>");
		string.append("Volume Cost: " + newVolumeCost);
		string.append("</html>");
		return string.toString();
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public Priority getPriority() {
		return priority;
	}

	public double getNewWeightCost() {
		return newWeightCost;
	}

	public double getNewVolumeCost() {
		return newVolumeCost;
	}

}
