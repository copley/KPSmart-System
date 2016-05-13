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


	//TODO: Not quite sure if we'll need this method here
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
	public boolean makeNewRoute(String origin, String destination, String company, int duration, Route.Mode mode, double custPriceWeight,
			double custPriceVolume, double transPriceWeight, double transPriceVolume) {
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
		int originID = -1;//value indicates not yet found
		int destinationID = -1;//value indicates not yet found
		Set<Site> sites = getSites();
		for(Site site : sites){
			if (site.getLocation().equals(origin)){
				originID=site.getID();
			}
			else if (site.getLocation().equals(destination)){
				destinationID=site.getID();
			}
		}
		if (originID == -1){//origin site does not exist yet... make it!
			originID = sites.size();//size is number of values already existing..
			// IDs made sequentially from 0 so size should be free!
			Site originSite = new Site(originID,origin);
			addSite(originSite);
		}
		if (destinationID == -1){//destination site does not exist yet... make it!
			destinationID = sites.size();//size is number of values already existing..
			// IDs made sequentially from 0 so size should be free!
			Site destinationSite = new Site(destinationID,destination);
			addSite(destinationSite);
		}
		// check to see if route already exists - abort route creation if it does
		Set<Route> routes = getRoutes();
		for(Route route : routes){
			if (
					route.getDestination() == destinationID
					&& route.getOrigin() == originID
					&& route.getCompany().equals(company)
					&& route.getDuration() == duration
					){
				return false;//may need to do a price update instead
			}
		}
		// find the next available ID (current length of routes list!)
		int newRouteID = routes.size();//size is number of values already existing..
				// IDs made sequentially from 0 so size should be free!
		// make route object
		Route newRoute = new Route(newRouteID, originID, destinationID, company, duration, mode, true,
				custPriceWeight, custPriceVolume, transPriceWeight, transPriceVolume );
		// add new route to the map
		try {
			addRoute(newRoute);
		} catch (IllegalRouteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
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
		siteToRoutes.get(s1).add(route);
		siteToRoutes.get(s2).add(route);
	}

	public Set<Site> getSites() {
		return new HashSet<Site>(sites.values());
	}

	public Set<Route> getRoutes() {
		return new HashSet<Route>(routes.values());
	}
}
