package model.events;

import model.Route;
import model.Staff;

public class RouteAdditionEvent extends BusinessEvent {
	private Route addedRoute;

	public RouteAdditionEvent(int date, double time, Staff staff, Route addedRoute) {
		super(date, time, staff);
		this.addedRoute = addedRoute;
	}

}
