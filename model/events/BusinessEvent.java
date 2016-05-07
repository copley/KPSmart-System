package model.events;

public abstract class BusinessEvent {
	protected int year;
	protected int month;
	protected int day;
	protected double time;
	protected String staff;

	public BusinessEvent(int year, int month, int day, double time, String staff) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.time = time;
		this.staff = staff;
	}

	public abstract String toString();

}
