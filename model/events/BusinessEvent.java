package model.events;

import model.Staff;

public class BusinessEvent {
	private int date;
	private double time;
	private int staffID;

	public BusinessEvent(int date, double time, int staffID) {
		super();
		this.date = date;
		this.time = time;
		this.staffID = staffID;
	}

}
