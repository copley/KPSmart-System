package model.events;

import model.map.Type;

public class RouteAdditionEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private String company;
	private Type type;
	private double duration;
	private double customerPriceWeight;
	private double customerPriceVolume;
	private double transportCostWeight;
	private double transportCostVolume;

	public RouteAdditionEvent(int day, int month, int year, int time, String staff, String origin, String destination,
			String company, Type type, double duration, double custPriceWeight, double custPriceVolume, double transCostWeight,
			double transCostVolume) {
		super(day, month, year, time, staff);
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.type = type;
		this.customerPriceWeight = custPriceWeight;
		this.customerPriceVolume = custPriceVolume;
		this.transportCostWeight = transCostVolume;
		this.transportCostVolume = transCostVolume;
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


	public double getDuration() {
		return duration;
	}


	public double getCustomerPriceWeight() {
		return customerPriceWeight;
	}


	public double getCustomerPriceVolume() {
		return customerPriceVolume;
	}


	public double getTransportCostWeight() {
		return transportCostWeight;
	}


	public double getTransportCostVolume() {
		return transportCostVolume;
	}


	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("<html>");
		string.append("ROUTE ADDITION EVENT<br>");
		string.append("Date: " + day + ", " + month + ", " + year + "<br>");
		string.append("Time: " + time + "<br>");
		string.append("Staff responsible: " + employee + "<br>");
		string.append("Origin: " + origin + "<br>");
		string.append("Destination: " + destination + "<br>");
		string.append("Company: " + company + "<br>");
		string.append("Type: " + type.name() + "<br>");
		string.append("Duration: " + duration);
		string.append("Customer price per gram: " + customerPriceWeight + "<br>");
		string.append("Customer price per cubic cm: " + customerPriceVolume + "<br>");
		string.append("Transport cost per gram: " + transportCostWeight + "<br>");
		string.append("Transport cost per cubic cm: " + transportCostVolume + "<br>");
		string.append("</html>");
		return string.toString();
	}

}
