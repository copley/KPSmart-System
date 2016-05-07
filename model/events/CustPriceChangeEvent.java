package model.events;

public class CustPriceChangeEvent extends BusinessEvent {
	private int affectedRouteID;
	private double oldPerWeight;
	private double oldPerVolume;
	private double newPerWeight;
	private double newPerVolume;

	public CustPriceChangeEvent(int year, int month, int day, double time, int staffID, int affectedRouteID, double oldPerWeight,
			double oldPerVolume, double newPerWeight, double newPerVolume) {
		super(year, month, day, time, staffID);
		this.affectedRouteID = affectedRouteID;
		this.oldPerWeight = oldPerWeight;
		this.oldPerVolume = oldPerVolume;
		this.newPerWeight = newPerWeight;
		this.newPerVolume = newPerVolume;
	}

}
