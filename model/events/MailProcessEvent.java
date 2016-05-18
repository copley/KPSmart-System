package model.events;

import model.Priority;

public class MailProcessEvent extends BusinessEvent {
	private String origin;
	private String destination;
	private double weight;
	private double volume;
	private Priority priority;

	public MailProcessEvent(int day, int month, int year, int time, String staff, String origin, String destination,
			double weight, double volume, Priority priority) {
		super(day, month, year, time, staff);
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
		this.volume = volume;
		this.priority = priority;
	}

	public String getOriginID() {
		return origin;
	}

	public String getDestinationID() {
		return destination;
	}

	public double getWeight() {
		return weight;
	}

	public double getVolume() {
		return volume;
	}

	public String getPriority() {
		return priority.toString();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("MAIL PROCESS EVENT\n");
		string.append("Date: " + day + ", " + month + ", " + year + "\n");
		string.append("Time: " + time + "\n");
		string.append("Staff responsible: " + employee + "\n");
		string.append("Origin: " + origin + "\n");
		string.append("Destination: " + destination + "\n");
		string.append("Weight: " + weight + "\n");
		string.append("Volume: " + volume + "\n");
		string.append("Priority: " + priority);
		return string.toString();
	}

}
