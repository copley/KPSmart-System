package tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.*;
import model.events.inputs.MailProcessInput;
import model.map.Priority;
import model.map.Route;
import model.map.SiteMap;
import model.map.Type;

import org.junit.Test;

public class figureGeneratorTests {
	
	@Test
	public void checkExpenditure() {
		KPSmartModel model = new KPSmartModel();
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
		KPSmartModel model = new KPSmartModel();
		FigureGenerator fg = model.getFG();
		SiteMap sitemap = model.getSiteMap();
		double currentMailSize = fg.generateTotalMail();
		double currentAVGDelivery = fg.getAVGDelivery();
		double totalDeliveryTime = currentAVGDelivery * currentMailSize;
		
		String origin = "Wellington";
		String dest = "Auckland";
		String weight = "4";
		String volume = "4";
		String prior = "International Air";
		
		//processing mail
		model.logIn("1", "1234");
		MailProcessInput mail = new MailProcessInput(origin,dest,weight,volume,prior);
		model.processMail(mail);
		double updatedAVGDelivery = fg.getAVGDelivery();
		//getting the average delivery time of mail
		int originID = sitemap.getSiteIDfromLocation(origin);
		int destID = sitemap.getSiteIDfromLocation(dest);
		Priority priority = model.getPriority(prior);		
		double w = Double.parseDouble(weight);
		double vol = Double.parseDouble(volume);		
		List<Integer> compoundRoutes = sitemap.findCompoundRoute(originID, destID, priority);
		int time = 0;
		for (int routeID : compoundRoutes) {
			time += sitemap.getRouteFromID(routeID).getDuration();
		}
		
		double expectedDT = 0;
		double updatedTotDT = totalDeliveryTime + time;		
		expectedDT = updatedTotDT/(currentMailSize + 1);
		assertTrue(currentAVGDelivery==expectedDT);
		
	}

}
