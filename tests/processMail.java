package tests;


import static org.junit.Assert.*;

import org.junit.Test;

import model.KPSmartModel;
import model.events.inputs.*;

public class processMail {


	@Test
	public void PM1() {
		KPSmartModel model = new KPSmartModel();
		MailProcessInput mail = new MailProcessInput("Wellington","Auckland", "4","4","INTERNATIONAL_AIR");
		model.processMail(mail);
	}

}
