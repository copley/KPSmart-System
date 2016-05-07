package model.events;

public class RouteAdditionEvent extends BusinessEvent {
	private int addedRouteID;

	public RouteAdditionEvent(int year, int month, int day, double time, int staffID, int addedRouteID) {
		super(year, month, day, time, staffID);
		this.addedRouteID = addedRouteID;
	}

}
