package storage;

import java.util.*;
import model.*;
import model.exceptions.*;

public class SiteMap {
	private Map<Integer, Site> sites; // maps site to its id
	private Map<Integer, Route> routes; // maps route to its id
	private Map<Site, List<Route>> siteToRoutes; // maps sites to routes

	public SiteMap() {
		sites = new HashMap<Integer, Site>();
		routes = new HashMap<Integer, Route>();
		siteToRoutes = new HashMap<Site, List<Route>>();
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

	public void addSite(Site site) {
		sites.put(site.getID(), site);
		siteToRoutes.put(site, new ArrayList<Route>());
	}

	public void addRoute(Route route) throws IllegalRouteException {
		routes.put(route.getID(), route);
		Site s1 = sites.get(route.getOrigin());
		Site s2 = sites.get(route.getDestination());
		if (siteToRoutes.get(s1) == null || siteToRoutes.get(s2) == null) {
			throw new IllegalRouteException("Invalid Route! Can't find site");
		}
		siteToRoutes.get(s1).add(route);
		siteToRoutes.get(s2).add(route);
	}
}
