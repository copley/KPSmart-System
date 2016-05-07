package model.events;

public class BusinessEvent {
	private int day;
	private int month;
	private int year;
	private double time;
	private int staffID;

	public BusinessEvent(int day, int month, int year, double time, int staffID) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
		this.staffID = staffID;
	}

}
