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
		string.append("ROUTE ADDITION EVENT\n");
		string.append("Date: " + day + ", " + month + ", " + year + "\n");
		string.append("Time: " + time + "\n");
		string.append("Staff responsible: " + employee + "\n");
		string.append("Origin: " + origin + "\n");
		string.append("Destination: " + destination + "\n");
		string.append("Company: " + company + "\n");
		string.append("Type: " + type.name() + "\n");
		string.append("Duration: " + duration);
		string.append("Customer price per gram: " + customerPriceWeight + "\n");
		string.append("Customer price per cubic cm: " + customerPriceVolume + "\n");
		string.append("Transport cost per gram: " + transportCostWeight + "\n");
		string.append("Transport cost per cubic cm: " + transportCostVolume + "\n");
		return string.toString();
	}

}
