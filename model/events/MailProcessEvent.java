package model.events;

public class MailProcessEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private int weight;
	private int volume;
	private String priority;

	public MailProcessEvent(int year, int month, int day, double time, int staffID, String origin, String destination,
			int weight, int volume, String priority) {
		super(year, month, day, time, staffID);
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
		this.volume = volume;
		this.priority = priority;
	}

}
