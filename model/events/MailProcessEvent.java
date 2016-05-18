package model.events;

import model.Priority;

public class MailProcessEvent extends BusinessEvent {
	private int originID;
	private int destinationID;
	private double weight;
	private double volume;
	private Priority priority;

	public MailProcessEvent(int day, int month, int year, int time, String staff, int originID, int destinationID,
			double weight, double volume, Priority priority) {
		super(day, month, year, time, staff);
		this.originID = originID;
		this.destinationID = destinationID;
		this.weight = weight;
		this.volume = volume;
		this.priority = priority;
	}

	public int getOriginID() {
		return originID;
	}

	public int getDestinationID() {
		return destinationID;
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
		string.append("Origin: " + originID + "\n");
		string.append("Destination: " + destinationID + "\n");
		string.append("Weight: " + weight + "\n");
		string.append("Volume: " + volume + "\n");
		string.append("Priority: " + priority);
		return string.toString();
	}

}
