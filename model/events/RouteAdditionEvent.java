package model.events;

import model.Route;
import model.Staff;

public class RouteAdditionEvent extends BusinessEvent {
	private Route addedRoute;

	public RouteAdditionEvent(int date, double time, int staffID, int addedRouteID) {
		super(date, time, staffID);
		this.addedRoute = addedRoute;
	}

}
