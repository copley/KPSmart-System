package model.events;

import model.Package;
import model.Staff;

public class MailProcessEvent extends BusinessEvent {
	private Package mailItem;

	public MailProcessEvent(int date, double time, int staffID, Package mailItem) {
		super(date, time, staffID);
		this.mailItem = mailItem;
	}

}
