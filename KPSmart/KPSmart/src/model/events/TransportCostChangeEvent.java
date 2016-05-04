package model.events;

import model.Route;
import model.Staff;

public class TransportCostChangeEvent extends BusinessEvent {
	private Route affectedRoute;
	private double oldPerWeight;
	private double oldPerVolume;
	private double newPerWeight;
	private double newPerVolume;

	public TransportCostChangeEvent(int date, double time, Staff staff, Route affectedRoute, double oldPerWeight,
			double oldPerVolume, double newPerWeight, double newPerVolume) {
		super(date, time, staff);
		this.affectedRoute = affectedRoute;
		this.oldPerWeight = oldPerWeight;
		this.oldPerVolume = oldPerVolume;
		this.newPerWeight = newPerWeight;
		this.newPerVolume = newPerVolume;
	}

}
