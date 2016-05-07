package model.events;

public class BusinessEvent {
	private int year;
	private int month;
	private int day;
	private double time;
	private int staffID;

	public BusinessEvent(int year, int month, int day, double time, int staffID) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.time = time;
		this.staffID = staffID;
	}

}
