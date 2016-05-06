package model;

import java.util.List;

public class SiteMap {
	private List<Site> sites;
	private List<Route> routes;

	public void addNewRoute(int toSiteID, int fromSiteID, String carrier, double duration, double custPriceWeight,
			double custPriceVolume, double transPriceWeight, double transPriceVolume) {
		// not yet implemented
		// should work out a unique int id during creation - eg routes.size()
		// should update routes 
		System.out.println("add new route not yet implemented");

	};

	public void discontinueRoute(int toDiscontinueID) {
		// not yet implemented
		System.out.println("Discontinue route is not yet implemented");

	};

	public void changeRoute(int toChangeID, Site newTo, Site newFrom, String newCarrier, double newDuration,
			double newCustPriceWeight, double newCustPriceVolume, double newTransPriceWeight,
			double newTransPriceVolume) {
		// not yet implemented
		System.out.println("change route not yet implemented");
	};
	
	public void addNewSite(String location) {
		// not yet implemented
		// should work out a unique int id during creation - eg sites.size()
		// should update sites 
		System.out.println("add new site not yet implemented");
	};
}
