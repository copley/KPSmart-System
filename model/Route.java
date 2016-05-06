package model;

public class Route {
	private final int id;
	private final int toSiteID;
	private final int fromSiteID;
	private String carrier;
	private double duration;
	private double custPriceWeight;
	private double custPriceVolume;
	private double transPriceWeight;
	private double transPriceVolume;
	private boolean inService;

	public Route(int id, int toSiteID, int fromSiteID, String carrier, double duration, double custPriceWeight, double custPriceVolume,
			double transPriceWeight, double transPriceVolume) {
		super();
		this.id = id;
		this.toSiteID = toSiteID;
		this.fromSiteID = fromSiteID;
		this.carrier = carrier;
		this.duration = duration;
		this.custPriceWeight = custPriceWeight;
		this.custPriceVolume = custPriceVolume;
		this.transPriceWeight = transPriceWeight;
		this.transPriceVolume = transPriceVolume;
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
}
