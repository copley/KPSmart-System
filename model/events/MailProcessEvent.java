package model.events;

import model.Package;
import model.Staff;

public class MailProcessEvent extends BusinessEvent {
	private Package mailItem;

	public MailProcessEvent(int year, int month, int day, double time, int staffID, Package mailItem) {
		super(year, month, day, time, staffID);
		this.mailItem = mailItem;
	}

}
