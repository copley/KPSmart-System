package model.map;

import java.util.*;
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

	// ================ methods directly related to business events============

	/*
	 * returns true if route successfully discontinued returns false if not
	 * successful (eg the ID was invalid) [supporting soft failure]
	 *
	 * ****does not take route out of routes or siteToRoutes******
	 *
	 */
	public boolean discontinueRoute(int toDiscontinueID) {
		// check route is valid
		if (!routes.containsKey(toDiscontinueID)) {
			return false;
		}
		;
		// call route to say it is discontinued
		routes.get(toDiscontinueID).discontinue();
		return true;
	}

	// TODO: Not quite sure if we'll need this method here
	/*
	 * returns true if route successfully changed returns false if not
	 * successful (eg the ID was invalid) [supporting soft failure]
	 *
	 * Only the following may be changed: customer prices, transit prices.
	 * sites, carrier and duration are final, if they need to be changed a new
	 * route should be made, and the old one discontinued.
	 */
	public boolean changeRoute(int toChangeID, double newCustPriceWeight, double newCustPriceVolume,
			double newTransPriceWeight, double newTransPriceVolume) {
		// check all input for correct values
		if (!routes.containsKey(toChangeID)) {
			return false;
		}
		;
		if (newCustPriceWeight <= 0 || newCustPriceVolume <= 0 || newTransPriceWeight <= 0
				|| newTransPriceVolume <= 0) {
			return false;
		}
		// call route to change its values
		routes.get(toChangeID).updatePrices(newCustPriceWeight, newCustPriceVolume, newTransPriceWeight,
				newTransPriceVolume);
		return true;
	}

	/*
	 * returns true if route successfully made returns false if not successful
	 * [supporting soft failure]
	 *
	 * Only the following may be changed: customer prices, transit prices.
	 * sites, carrier and duration are final, if they need to be changed a new
	 * route should be made, and the old one discontinued.
	 */
	public boolean makeNewRoute(String origin, String destination, String company, int duration, Type mode,
			double custPriceWeight, double custPriceVolume, double transPriceWeight, double transPriceVolume) {
		// check all input for values in correct format
		if (duration <= 0 || custPriceWeight <= 0 || custPriceVolume <= 0 || transPriceWeight <= 0
				|| transPriceVolume <= 0) {
			return false;
		}
		if (origin == null || destination == null || company == null || origin.equals("") || destination.equals("")
				|| company.equals("")) {
			return false;
		}
		if (origin.equals(destination)) {
			return false;
		}
		// find the site ids... make new sites if necessary
		int originID = -1;// value indicates not yet found
		int destinationID = -1;// value indicates not yet found
		for (Site site : sites.values()) {
			String location = site.getLocation();
			if (location.equals(origin)) {
				originID = site.getID();
			} 
			if (location.equals(destination)) {
				destinationID = site.getID();
			}
		}
		if (originID == -1) {// origin site does not exist yet... make it!
			originID = sites.size();// size is number of values already
									// existing..
			// IDs made sequentially from 0 so size should be free!
			Site originSite = new Site(originID, origin);
			addSite(originSite);
		}
		if (destinationID == -1) {// destination site does not exist yet... make
									// it!
			destinationID = sites.size();// size is number of values already
											// existing..
			// IDs made sequentially from 0 so size should be free!
			Site destinationSite = new Site(destinationID, destination);
			addSite(destinationSite);
		}
		// check to see if route already exists - abort route creation if it
		// does
		Set<Route> routes = getRoutes();
		for (Route route : routes) {
			if (route.getDestination() == destinationID && route.getOrigin() == originID
					&& route.getCompany().equals(company) && route.getDuration() == duration) {
				return false;// may need to do a price update instead
			}
		}
		// find the next available ID (current length of routes list!)
		int newRouteID = routes.size();// size is number of values already
										// existing..
		// IDs made sequentially from 0 so size should be free!
		// make route object
		Route newRoute = new Route(newRouteID, originID, destinationID, company, duration, mode, true, custPriceWeight,
				custPriceVolume, transPriceWeight, transPriceVolume);
		// add new route to the map
		try {
			addRoute(newRoute);
		} catch (IllegalRouteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// ======Dijkstra methods... for finding a compound route given from and to=======

	/**
	 * Uses the Dijkstra search method with duration as the value to minimise, to find
	 * the shortest path from "from" to "to". Priority 1 searches may use SEA,
	 * LAND and AIR routes whereas priority 2 searches may only use SEA and
	 * LAND.
	 *
	 * @param from:
	 *            site that the compound route must start
	 * @param to:
	 *            site that the compound route must finish
	 * @param priority:
	 *            allowable values are 1 and 2
	 * @return an ordered list of Route IDs that identify routes that compound to create a path
	 *         from "from" to "to"
	 */
	public List<Integer> findCompoundRoute(int fromSiteID, int toSiteID, model.Priority priority) {
		return new DijkstraSearchWithPriority(fromSiteID,toSiteID,this,priority).findShortestRoute();
	}


	// ========== internal helper methods for dealing with the map
	// structure=======

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
		List<Route> s1Routes = siteToRoutes.get(s1);
		s1Routes.add(route);
		siteToRoutes.put(s1,s1Routes);
		List<Route> s2Routes = siteToRoutes.get(s2);
		s2Routes.add(route);
		siteToRoutes.put(s2,s2Routes);
	}

	public Set<Site> getSites() {
		return new HashSet<Site>(sites.values());
	}

	public Set<Route> getRoutes() {
		return new HashSet<Route>(routes.values());
	}

	public List<Route> getRoutesOn(int siteID) {
		Site site = this.sites.get(siteID);
		return siteToRoutes.get(site);
	}
	
	public int getSiteIDfromLocation(String location){
		Set<Site> sites = getSites();
		for (Site site : sites) {
			if (site.getLocation().equals(location)) {
				return site.getID();
				
			}
		}
		return -1;
	}

	public Route getRouteFromID(int routeID) {
		return this.routes.get(routeID);
	}
}
