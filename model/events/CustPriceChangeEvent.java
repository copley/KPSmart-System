package model.events;

import model.Route;
import model.Staff;

public class CustPriceChangeEvent extends BusinessEvent {
	private int affectedRouteID;
	private double oldPerWeight;
	private double oldPerVolume;
	private double newPerWeight;
	private double newPerVolume;

	public CustPriceChangeEvent(int date, double time, int staffID, int affectedRouteID, double oldPerWeight,
			double oldPerVolume, double newPerWeight, double newPerVolume) {
		super(date, time, staffID);
		this.affectedRouteID = affectedRouteID;
		this.oldPerWeight = oldPerWeight;
		this.oldPerVolume = oldPerVolume;
		this.newPerWeight = newPerWeight;
		this.newPerVolume = newPerVolume;
	}

}
