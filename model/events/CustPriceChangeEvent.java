package model.events;

public class CustPriceChangeEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private String priority;
	private int newWeightCost;
	private int newVolumeCost;

	public CustPriceChangeEvent(int day, int month, int year, double time, String staff, String origin,
			String destination, String priority, int newWeightCost, int newVolumeCost) {
		super(day, month, year, time, staff);
		this.origin = origin;
		this.destination = destination;
		this.priority = priority;
		this.newWeightCost = newWeightCost;
		this.newVolumeCost = newVolumeCost;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("CUSTOMER PRICE CHANGE EVENT");
		string.append("Date: " + day + ", " + month + ", " + year);
		string.append("Time: " + time);
		string.append("Staff responsible: " + staff);
		string.append("Origin: " + origin);
		string.append("Destination: " + destination);
		string.append("Priority: " + priority);
		string.append("Weight Cost per gram: " + newWeightCost);
		string.append("Volume Cost per gram: " + newVolumeCost);
		return string.toString();
	}

}
