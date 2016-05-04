package model;

public class RouteAdditionEvent extends BusinessEvent {
	private Route addedRoute;

	public RouteAdditionEvent(int date, double time, Staff staff, Route addedRoute) {
		super(date, time, staff);
		this.addedRoute = addedRoute;
	}

}
