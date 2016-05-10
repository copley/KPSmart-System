package model.events;

public abstract class BusinessEvent {
	protected int day;
	protected int month;
	protected int year;
	protected int time;
	protected String staff;

	public BusinessEvent(int day, int month, int year, int time, String staff) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
		this.staff = staff;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public int getTime() {
		return time;
	}

	public String getStaff() {
		return staff;
	}

	public abstract String toString();

}
