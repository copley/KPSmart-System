package model.events;

public class CustPriceChangeEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private String priority;
	private int newWeightCost;
	private int newVolumeCost;

	public CustPriceChangeEvent(int day, int month, int year, double time, int staffID, String origin,
			String destination, String priority, int newWeightCost, int newVolumeCost) {
		super(day, month, year, time, staffID);
		this.origin = origin;
		this.destination = destination;
		this.priority = priority;
		this.newWeightCost = newWeightCost;
		this.newVolumeCost = newVolumeCost;
	}

}
