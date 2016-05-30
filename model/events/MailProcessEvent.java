package model.events;

import model.map.Priority;

public class MailProcessEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private double weight;
	private double volume;
	private Priority priority;
	private double revenue;
	private double expenditure;
	private double deliveryTime;

	public MailProcessEvent(int day, int month, int year, int time, String staff, String origin, String destination,
			double weight, double volume, Priority priority, double revenue, double expenditure, double deliveryTime) {
		super(day, month, year, time, staff);
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
		this.volume = volume;
		this.priority = priority;
		this.revenue = revenue;
		this.expenditure = expenditure;
		this.deliveryTime = deliveryTime;
		this.revenue = revenue;
		this.expenditure = expenditure;
		this.deliveryTime = deliveryTime;
	}

	// getters

	public double getDeliveryTime() {
		return deliveryTime;
	}

	public double getRevenue() {
		return revenue;
	}

	public double getExpenditure() {
		return expenditure;
	}

	public String getOriginID() {
		return origin;
	}

	public String getDestinationID() {
		return destination;
	}

	public double getWeight() {
		return weight;
	}

	public double getVolume() {
		return volume;
	}

	public String getPriority() {
		return priority.toString();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("<html>");
		string.append("<h2>MAIL PROCESS EVENT</h2><br>");
		string.append("Date: " + "<em>" + day + "</em>" + "/" + "<em>" + month + "</em>" + "/" + "<em>" + year + "</em>"
				+ "<sup>&zwnj</sup><br>");
		string.append("Time: " + "<em>" + time + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Staff responsible: " + "<em>" + employee + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Origin: " + "<em>" + origin + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Destination: " + "<em>" + destination + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Weight: " + "<em>" + weight + "</em>" + " grams" + "<sup>&zwnj</sup><br>");
		string.append("Volume: " + "<em>" + volume + "</em>" + " cm<sup>3</sup>" + "<br>");
		string.append("Priority: " + "<em>" + priority + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Revenue: $" + "<em>" + revenue + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Expenditure: $" + "<em>" + expenditure + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Delivery time: " + "<em>" + deliveryTime + "</em>" + " hours<sup>&zwnj</sup>");
		string.append("</html>");
		return string.toString();
	}

}
