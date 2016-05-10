package model.events;

public abstract class BusinessEvent {
	protected int day;
	protected int month;
	protected int year;
	protected int time;
	protected String employee;

	public BusinessEvent(int day, int month, int year, int time, String employee) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.time = time;
		this.employee = employee;
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

	public String getEmployee() {
		return employee;
	}

	public abstract String toString();

}
