package model;

public class Package {
	private int originSiteID;
	private int destSiteID;
	private double price;
	private double weight;
	private double volume;
	private int priority;// not sure what this one should be yet.. enum?
	
	public Package(int originSiteID, int destSiteID, double price, double weight, double volume, int priority) {
		super();
		this.originSiteID = originSiteID;
		this.destSiteID = destSiteID;
		this.price = price;
		this.weight = weight;
		this.volume = volume;
		this.priority = priority;
	}

}
