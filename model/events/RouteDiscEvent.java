package model.events;

public class RouteDiscEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private String company;
	private String type;

	public RouteDiscEvent(int day, int month, int year, double time, int staffID, String origin, String destination,
			String company, String type) {
		super(day, month, year, time, staffID);
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.type = type;
	}

}
