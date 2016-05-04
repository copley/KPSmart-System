package model.events;

import model.Route;
import model.Staff;

public class RouteDiscEvent extends BusinessEvent {
	private Route discontinuedRoute;

	public RouteDiscEvent(int date, double time, Staff staff, Route discontinuedRoute) {
		super(date, time, staff);
		this.discontinuedRoute = discontinuedRoute;
	}

}
