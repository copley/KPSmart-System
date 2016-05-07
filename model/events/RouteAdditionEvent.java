package model.events;

public class RouteAdditionEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private String company;
	private String type;
	private int newWeightCost;
	private int newVolumeCost;
	private String departureDay;
	private int frequency;
	private int duration;

	public RouteAdditionEvent(int day, int month, int year, int time, String staff, String origin, String destination,
			String company, String type, int newWeightCost, int newVolumeCost, String departureDay, int frequency,
			int duration) {
		super(day, month, year, time, staff);
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.type = type;
		this.newWeightCost = newWeightCost;
		this.newVolumeCost = newVolumeCost;
		this.departureDay = departureDay;
		this.frequency = frequency;
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("ROUTE ADDITION EVENT\n");
		string.append("Date: " + day + ", " + month + ", " + year + "\n");
		string.append("Time: " + time + "\n");
		string.append("Staff responsible: " + staff + "\n");
		string.append("Origin: " + origin + "\n");
		string.append("Destination: " + destination + "\n");
		string.append("Company: " + company + "\n");
		string.append("Type: " + type + "\n");
		string.append("Weight Cost: " + newWeightCost + "\n");
		string.append("Volume Cost: " + newVolumeCost);
		string.append("Day pf Departure: " + departureDay + "\n");
		string.append("Frequency: " + frequency + "\n");
		string.append("Duration: " + duration);
		return string.toString();
	}

}
