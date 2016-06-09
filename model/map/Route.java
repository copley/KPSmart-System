package model.map;



public class Route {
	private final int ID;
	private final String origin;
	private final String destination;
	private final String company;
	private double duration;//hours
	private final Type type;
	private double custPriceWeight;
	private double custPriceVolume;
	private double transPriceWeight;
	private double transPriceVolume;
	private boolean inService;

/**
 * Inputs are required to be pre-verified before calling this method, internal validation is not applied
 * Within the KPSmart system, validation is performed by model.ValidationSystem
 * 
 * @param id - a non-negative integer
 * @param origin - a non-null, non-empty string, must be an official New Zealand town
 * @param destination - a non-null, non-empty string
 * @param company - a non-null, non-empty string
 * @param duration - a non-negative double, the target decimal hours to deliver
 * @param type - Type enum, LAND, SEA, AIR
 * @param inService - boolean, specifying whether or not the route is currently able to be used
 * @param custPriceWeight - a non-negative double, price per gram that will be charged to customer
 * @param custPriceVolume - a non-negative double, price per cubic cm that will be charged to customer
 * @param transPriceWeight - a non-negative double, cost per gram that transport company charges KPS
 * @param transPriceVolume - a non-negative double, cost per cubic cm that transport company charges KPS
 */
	public Route(int id, String origin, String destination, String company, double duration, Type type, boolean inService,
			double custPriceWeight, double custPriceVolume, double transPriceWeight, double transPriceVolume) {
		this.ID = id;
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.duration = duration;
		this.type = type;
		this.custPriceWeight = custPriceWeight;
		this.custPriceVolume = custPriceVolume;
		this.transPriceWeight = transPriceWeight;
		this.transPriceVolume = transPriceVolume;
		this.inService = true;
	}



	public int getID() {
		return ID;
	}

	public String getDestination() {
		return destination;
	}

	public String getOrigin() {
		return origin;
	}

	public String getCompany() {
		return company;
	}

	public double getDuration() {
		return duration;
	}

	public Type getType() {
		return type;
	}

	public double getCustPriceWeight() {
		return custPriceWeight;
	}

	public double getCustPriceVolume() {
		return custPriceVolume;
	}

	public double getTransPriceWeight() {
		return transPriceWeight;
	}

	public double getTransPriceVolume() {
		return transPriceVolume;
	}

	public boolean isInService() {
		return inService;
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("ID: " + ID + " ");
		string.append("Origin: " + origin + " ");
		string.append("Destination: " + destination + " ");
		string.append("Company: " + company + "<br>");
		string.append("duration: " + duration + " ");
		string.append("Type: " + type.name() + " ");
		string.append("In service: " + inService + " ");
		return string.toString();
	}



	public void discontinue() {
		this.inService = false;
	}



	public void updateTransportCosts(double newTransPriceWeight, double newTransPriceVolume, double duration) {
		this.transPriceWeight = newTransPriceWeight;
		this.transPriceVolume = newTransPriceVolume;
		this.duration = duration;
	}



	public void updateCustomerPrices(double newCustPriceWeight, double newCustPriceVolume) {
		this.custPriceWeight = newCustPriceWeight;
		this.custPriceVolume = newCustPriceVolume;
	}
}
