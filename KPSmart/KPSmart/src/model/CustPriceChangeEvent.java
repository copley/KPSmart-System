package model;

public class CustPriceChangeEvent extends BusinessEvent {
	private Route affectedRoute;
	private double oldPerWeight;
	private double oldPerVolume;
	private double newPerWeight;
	private double newPerVolume;

	public CustPriceChangeEvent(int date, double time, Staff staff, Route affectedRoute, double oldPerWeight,
			double oldPerVolume, double newPerWeight, double newPerVolume) {
		super(date, time, staff);
		this.affectedRoute = affectedRoute;
		this.oldPerWeight = oldPerWeight;
		this.oldPerVolume = oldPerVolume;
		this.newPerWeight = newPerWeight;
		this.newPerVolume = newPerVolume;
	}

}
