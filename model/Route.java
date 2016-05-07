package model;

public class Route {
	private final int ID;
	private final int destinationID;
	private final int originID;
	private String company;
	private int duration;

	// Not sure if necessary
//	private double custPriceWeight;
//	private double custPriceVolume;
//	private double transPriceWeight;
//	private double transPriceVolume;

	private boolean inService;

	public Route(int id, int destinationID, int originID, String company, int duration, boolean inService
//			, double custPriceWeight, double custPriceVolume,
//			double transPriceWeight, double transPriceVolume
			) {
		this.ID = id;
		this.destinationID = destinationID;
		this.originID = originID;
		this.company = company;
		this.duration = duration;
//		this.custPriceWeight = custPriceWeight;
//		this.custPriceVolume = custPriceVolume;
//		this.transPriceWeight = transPriceWeight;
//		this.transPriceVolume = transPriceVolume;
		this.inService = true;
	}

	public void changeCustomerPrice(double perWeight, double perVolume) {
		// not yet implemented
		System.out.println("Change Customer Price is not yet implemented");
	}

	public void changeTransportCost(double perWeight, double perVolume) {
		// not yet implemented
		System.out.println("Change Transport Cost is not yet implemented");
	}

	public Integer getID() {
		return ID;
	}

	public int getOrigin() {
		return originID;
	}

	public Object getDestination() {
		return destinationID;
	}

	public String toString(){
		StringBuilder string = new StringBuilder();
		string.append("ID: " + ID + "\n");
		string.append("Destination: " + destinationID + "\n");
		string.append("Origin: " + originID + "\n");
		string.append("Company: " + company + "\n");
		string.append("duration: " + duration + "\n");
		string.append("In service: " + inService);
		return string.toString();
	}
}
