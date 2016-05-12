package model;

public class Route {
	private final int ID;
	private final int origin;
	private final int destination;
	private String company;
	private int duration;
	private double custPriceWeight;
	private double custPriceVolume;
	private double transPriceWeight;
	private double transPriceVolume;
	private boolean inService;

	public Route(int id, int origin, int destination, String company, int duration, boolean inService,
			double custPriceWeight, double custPriceVolume, double transPriceWeight, double transPriceVolume) {
		this.ID = id;
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.duration = duration;
		this.custPriceWeight = custPriceWeight;
		this.custPriceVolume = custPriceVolume;
		this.transPriceWeight = transPriceWeight;
		this.transPriceVolume = transPriceVolume;
		this.inService = true;
	}
	
	

	public int getID() {
		return ID;
	}

	public int getDestination() {
		return destination;
	}

	public int getOrigin() {
		return origin;
	}

	public String getCompany() {
		return company;
	}

	public int getDuration() {
		return duration;
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
		string.append("ID: " + ID + "\n");
		string.append("Destination: " + destination + "\n");
		string.append("Origin: " + origin + "\n");
		string.append("Company: " + company + "\n");
		string.append("duration: " + duration + "\n");
		string.append("In service: " + inService);
		return string.toString();
	}



	public void discontinue() {
		this.inService = false;
	}



	public void updatePrices(double newCustPriceWeight, double newCustPriceVolume, double newTransPriceWeight,
			double newTransPriceVolume) {
		this.custPriceWeight = newCustPriceWeight;
		this.custPriceVolume = newCustPriceVolume;
		this.transPriceWeight = newTransPriceWeight;
		this.transPriceVolume = newTransPriceVolume;
	}
}
