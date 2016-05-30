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
			String company, Type type, double duration, double custPriceWeight, double custPriceVolume,
			double transCostWeight, double transCostVolume) {
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
		string.append("<h2>ROUTE ADDITION EVENT</h2><br>");
		string.append("Date: " + "<em>" + day + "/" + month + "/" + year + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Time: " + "<em>" + time + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Staff responsible: " + "<em>" + employee + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Origin: " + "<em>" + origin + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Destination: " + "<em>" + destination + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Company: " + "<em>" + company + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Type: " + "<em>" + type.name() + "</em>" + "<sup>&zwnj</sup><br>");
		string.append("Duration: " + "<em>" + duration + "</em>" + " hours" + "<sup>&zwnj</sup><br>");
		string.append(
				"Customer price: $" + "<em>" + customerPriceWeight + "</em>" + " per gram" + "<sup>&zwnj</sup><br>");
		string.append("Customer price: $" + "<em>" + customerPriceVolume + "</em>" + " per cm<sup>3</sup>" + "<br>");
		string.append(
				"Transport cost: $" + "<em>" + transportCostWeight + "</em>" + " per gram" + "<sup>&zwnj</sup><br>");
		string.append("Transport cost: $" + "<em>" + transportCostVolume + "</em>" + " per cm<sup>3</sup>");
		string.append("</html>");
		return string.toString();
	}

}
