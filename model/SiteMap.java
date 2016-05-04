package model;

import java.util.List;

public class SiteMap {
	private List<Site> sites;
	private List<Route> routes;

	public void addNewRoute(Site to, Site from, String carrier, double duration, double custPriceWeight,
			double custPriceVolume, double transPriceWeight, double transPriceVolume) {
		// not yet implemented
		System.out.println("add new route not yet implemented");

	};

	public void discontinueRoute(Route toDiscontinue) {
		// not yet implemented
		System.out.println("Discontinue route is not yet implemented");

	};

	public void changeRoute(Route toChange, Site newTo, Site newFrom, String newCarrier, double newDuration,
			double newCustPriceWeight, double newCustPriceVolume, double newTransPriceWeight,
			double newTransPriceVolume) {
		// not yet implemented
		System.out.println("change route not yet implemented");
	};
}
