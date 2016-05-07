package model.events;

public class MailProcessEvent extends BusinessEvent {
	private int originSiteID;
	private int destSiteID;
	private double price;
	private double weight;
	private double volume;
	private int priority;

	public MailProcessEvent(int year, int month, int day, double time, int staffID, int originSiteID, int destSiteID,
			double price, double weight, double volume, int priority) {
		super(year, month, day, time, staffID);
		this.originSiteID = originSiteID;
		this.destSiteID = destSiteID;
		this.price = price;
		this.weight = weight;
		this.volume = volume;
		this.priority = priority;
	}

}
