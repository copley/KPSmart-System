package tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import model.*;
import model.events.inputs.MailProcessInput;
import model.map.Priority;
import model.map.SiteMap;

import org.junit.Test;

public class figureGeneratorTests {
	
	@Test
	public void checkExpenditure() {
		KPSmartModel model = new KPSmartModel();
		FigureGenerator fg = model.getFG();
		EventProcessor ep = model.getEP();
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
		double updatedExp = fg.getExpenditure();
		
		//getting the expenditure value of the mail
		int originID = sitemap.getSiteIDfromLocation(origin);
		int destID = sitemap.getSiteIDfromLocation(dest);
		Priority priority = model.getPriority(prior);		
		double w = Double.parseDouble(weight);
		double vol = Double.parseDouble(volume);		
		List<Integer> compoundRoutes = sitemap.findCompoundRoute(originID, destID, priority);
		double packageExp = ep.findExpenditure(compoundRoutes,w,vol);
		
		assertTrue(updatedExp-currentExp==packageExp);
		
	}

}
