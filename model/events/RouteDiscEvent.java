package model.events;

import model.Route;
import model.Staff;

public class RouteDiscEvent extends BusinessEvent {
	private int discontinuedRouteID;

	public RouteDiscEvent(int date, double time, int staffID, int discontinuedRouteID) {
		super(date, time, staffID);
		this.discontinuedRouteID = discontinuedRouteID;
	}

}
