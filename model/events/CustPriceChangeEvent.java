package model.events;

public class CustPriceChangeEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private String priority;
	private int newWeightCost;
	private int newVolumeCost;

	public CustPriceChangeEvent(int day, int month, int year, int time, String staff, String origin,
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
		string.append("CUSTOMER PRICE CHANGE EVENT\n");
		string.append("Date: " + day + ", " + month + ", " + year + "\n");
		string.append("Time: " + time + "\n");
		string.append("Staff responsible: " + employee + "\n");
		string.append("Origin: " + origin + "\n");
		string.append("Destination: " + destination + "\n");
		string.append("Priority: " + priority + "\n");
		string.append("Weight Cost: " + newWeightCost + "\n");
		string.append("Volume Cost: " + newVolumeCost);
		return string.toString();
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getPriority() {
		return priority;
	}

	public int getNewWeightCost() {
		return newWeightCost;
	}

	public int getNewVolumeCost() {
		return newVolumeCost;
	}



}
