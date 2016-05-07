package storage;

import java.util.Map;

import model.Route;
import model.Site;

public class SiteMap {
	private Map<Integer, Site> sites; // maps site to its id
	private Map<Integer, Route> routes; // maps route to its id
	private Map<Site, Route> siteToRoutes; // maps sites to routes

	public void addNewRoute(int toSiteID, int fromSiteID, String carrier, double duration, double custPriceWeight,
			double custPriceVolume, double transPriceWeight, double transPriceVolume) {
		// not yet implemented
		// should work out a unique int id during creation - eg routes.size()
		// should update routes
		System.out.println("add new route not yet implemented");

	}

	public void discontinueRoute(int toDiscontinueID) {
		// not yet implemented
		System.out.println("Discontinue route is not yet implemented");

	}

	public void changeRoute(int toChangeID, Site newTo, Site newFrom, String newCarrier, double newDuration,
			double newCustPriceWeight, double newCustPriceVolume, double newTransPriceWeight,
			double newTransPriceVolume) {
		// not yet implemented
		System.out.println("change route not yet implemented");
	}

	public void addNewSite(String location) {
		// not yet implemented
		// should work out a unique int id during creation - eg sites.size()
		// should update sites
		System.out.println("add new site not yet implemented");
	}

	public void addSite(Site site) {
		// TODO Auto-generated method stub

	}

	public void addRoute(Route r) {
		// TODO Auto-generated method stub

	}
}
