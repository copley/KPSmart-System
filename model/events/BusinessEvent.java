package model.events;

import model.Staff;

public class BusinessEvent {
	private int date;
	private double time;
	private Staff staff;

	public BusinessEvent(int date, double time, Staff staff) {
		super();
		this.date = date;
		this.time = time;
		this.staff = staff;
	}

}
