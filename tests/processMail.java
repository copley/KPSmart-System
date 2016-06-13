package tests;


import static org.junit.Assert.*;

import org.junit.Test;

import model.KPSmartModel;
import model.events.inputs.*;
import model.map.SiteMap;
import model.map.Type;

public class processMail {


	@Test
	public void PM1() {
		KPSmartModel model = setUpModel();
		double oldRevenue = model.getFG().getRevenue();
		double oldExpenditure = model.getFG().getExpenditure();
		int oldNumMails = model.getFG().generateTotalMail();
		System.out.println(model.getDestinations().toString());
		MailProcessInput mail = new MailProcessInput("Wellington","Figgentest", "4","4","INTERNATIONAL_AIR");
		System.out.println(model.processMail(mail));
		System.out.println(model.getSiteMap().findRouteID("Wellington", "Figgentest", "test", Type.AIR) );	
		double newRevenue = model.getFG().getRevenue();
		double newExpenditure = model.getFG().getExpenditure();
		int newNumMails = model.getFG().generateTotalMail();
		assertTrue("revenue should have increased by 4", newRevenue == oldRevenue + 4);
		assertTrue("expenditure should have increased by 4", newExpenditure == oldExpenditure + 4);
		assertTrue("number of mail events should have increased by 1", newNumMails == oldNumMails+1);		
	}
	
	//===helper methods==========
	
		private KPSmartModel setUpModel() {
			KPSmartModel ret = new KPSmartModel();
			ret.logIn("1", "1234");
			ret.addNewRoute(new NewRouteInput("Wellington", "Figgentest", "test", "1", "AIR", "1", "1", "1", "1"));
		    return ret;
	}

}
