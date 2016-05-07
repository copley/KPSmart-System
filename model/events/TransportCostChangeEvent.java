package model.events;

public class TransportCostChangeEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private String company;
	private String type;
	private int newWeightCost;
	private int newVolumeCost;
	private String departureDay;
	private int frequency;
	private int duration;

	public TransportCostChangeEvent(int year, int month, int day, double time, int staffID, String origin,
			String destination, String company, String type, int newWeightCost, int newVolumeCost, String departureDay,
			int frequency, int duration) {
		super(year, month, day, time, staffID);
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

}
