package model.events;

import model.Package;
import model.Staff;

public class MailProcessEvent extends BusinessEvent {
	private Package mailItem;

	public MailProcessEvent(int date, double time, Staff staff, Package mailItem) {
		super(date, time, staff);
		this.mailItem = mailItem;
	}

}
