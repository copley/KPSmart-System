package tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.*;
import model.events.inputs.MailProcessInput;
import model.events.inputs.NewRouteInput;
import model.map.Priority;
import model.map.Route;
import model.map.SiteMap;
import model.map.Type;

import org.junit.Test;

/**
 * 
 * Tests for figure generator
 * @author Joely,Nic
 *
 */

public class figureGeneratorTests {
	
	@Test
	public void checkExpenditure() {
		KPSmartModel model = setUpModel();
		FigureGenerator fg = model.getFG();
		SiteMap sitemap = model.getSiteMap();
		double currentExp = fg.getExpenditure();
		
		String origin = "Wellington";
		String dest = "Auckland";
		String weight = "4";
		String volume = "4";
		String prior = "International Air";
		
		//processing mail
		model.logIn("1", "1234");
		MailProcessInput mail = new MailProcessInput(origin,dest,weight,volume,prior);
		model.processMail(mail);
		double updatedExp = fg.getExpenditure();
		//getting the expenditure value of the mail
		int originID = sitemap.getSiteIDfromLocation(origin);
		int destID = sitemap.getSiteIDfromLocation(dest);
		Priority priority = model.getPriority(prior);		
		double w = Double.parseDouble(weight);
		double vol = Double.parseDouble(volume);
		List<Integer> compoundRoutes = sitemap.findCompoundRoute(originID, destID, priority);
		double packageExp = 0;
		for (int routeID : compoundRoutes) {
			Route route = sitemap.getRouteFromID(routeID);
			if (route.getType().equals(Type.AIR)) {
				packageExp += w * route.getTransPriceWeight();
			} else {
				packageExp += vol * route.getTransPriceVolume();
			}
		}
		
		assertTrue(updatedExp-currentExp==packageExp);
		
	}
	
	//average delivery time test
	@Test
	public void checkAvgDT() {
		KPSmartModel model = setUpModel();
		FigureGenerator fg = model.getFG();
		SiteMap sitemap = model.getSiteMap();
		double currentMailSize = fg.generateTotalMail();
		double currentAVGDelivery = fg.getAVGDelivery();
		double currentTotDT = currentAVGDelivery * currentMailSize;
		
		String origin = "Wellington";
		String dest = "Auckland";
		String weight = "4";
		String volume = "4";
		String prior = "International Air";
		
		//processing mail
		model.logIn("1", "1234");
		MailProcessInput mail = new MailProcessInput(origin,dest,weight,volume,prior);
		model.processMail(mail);
		double updatedMailSize = fg.generateTotalMail();
		double updatedAVGDelivery = fg.getAVGDelivery();
		double updatedTotDT = updatedAVGDelivery * updatedMailSize;
		
		
		//getting the expected total delivery time of mail
		int originID = sitemap.getSiteIDfromLocation(origin);
		int destID = sitemap.getSiteIDfromLocation(dest);
		Priority priority = model.getPriority(prior);		
		double w = Double.parseDouble(weight);
		double vol = Double.parseDouble(volume);		
		List<Integer> compoundRoutes = sitemap.findCompoundRoute(originID, destID, priority);
		int time = 0;
		if(compoundRoutes == null){System.out.println("routes null");}
		for (int routeID : compoundRoutes) {
			time += sitemap.getRouteFromID(routeID).getDuration();
		}
		
		double expectedTotDT = currentTotDT + time;	
		assertTrue(expectedTotDT == updatedTotDT);		
	}
	
	//check critical routes
	@Test
	public void checkCriticalRoutes(){
		KPSmartModel model = this.setUpModel();
		FigureGenerator fg = model.getFG();
		SiteMap sitemap = model.getSiteMap();
		
		List<Route> currentCR = fg.generateCriticalRoutes();
		
		String origin = "Wellington";
		String dest = "London";
		String company = "DHL";
		String duration = "1";
		String customerPriceWeight = "2";
		String customerPriceVolume = "2";
		String transportPriceWeight = "3";
		String transportPriceVolume = "3";
		String type = "AIR";
		
		NewRouteInput newRouteInput = new NewRouteInput(origin, dest, company, duration, type, customerPriceWeight, customerPriceVolume, transportPriceWeight, transportPriceVolume);
		model.addNewRoute(newRouteInput);
		List<Route> updatedCR = fg.generateCriticalRoutes();
		assertTrue("A new critical route should have been added", updatedCR.size() == (currentCR.size()+1));	
		
	}
	
	//check revenue
	@Test
	public void checkRevenue() {
		KPSmartModel model = setUpModel();
		double oldRevenue = model.getFG().getRevenue();
		int oldNumMails = model.getFG().generateTotalMail();
		System.out.println(model.getDestinations().toString());
		MailProcessInput mail = new MailProcessInput("Wellington","Auckland", "4","4","INTERNATIONAL_AIR");
		System.out.println(model.processMail(mail));
		System.out.println(model.getSiteMap().findRouteID("Wellington", "Auckland", "test", Type.AIR) );	
		double newRevenue = model.getFG().getRevenue();
		int newNumMails = model.getFG().generateTotalMail();
		assertTrue("revenue should have increased by 4", newRevenue == oldRevenue + 4);
		assertTrue("number of mail events should have increased by 1", newNumMails == oldNumMails+1);		
	}

	//===helper methods==========
	
		private KPSmartModel setUpModel(){
			KPSmartModel model = new KPSmartModel();
			model.logIn("1", "1234");
			NewRouteInput newRoute = new NewRouteInput("Wellington","Auckland","company","10","LAND","1","1","1","1");
			model.addNewRoute(newRoute);
			return model;
		}
}
