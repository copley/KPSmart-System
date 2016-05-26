package model.map;

import java.util.*;

import model.exceptions.IllegalRouteException;

public class SiteMap {
	private Map<Integer, Site> sites; // maps site to its id
	private Map<Integer, Route> routes; // maps route to its id
	private Map<Site, List<Route>> siteToRoutes; // maps sites to routes
	private List<String> newSites;

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

	/*
	 * returns true if route successfully made returns false if not successful
	 * [supporting soft failure]
	 *
	 * Only the following may be changed: customer prices, transit prices.
	 * sites, carrier and duration are final, if they need to be changed a new
	 * route should be made, and the old one discontinued.
	 */
	public boolean addNewRoute(String origin, String destination, String company, Type type, double duration,
			double custPriceWeight, double custPriceVolume, double transPriceWeight, double transPriceVolume) {
		// check all input for values in correct format
		newSites = new ArrayList<String>();
		if (duration <= 0 || custPriceWeight <= 0 || custPriceVolume <= 0 || transPriceWeight <= 0
				|| transPriceVolume <= 0 || origin == null || destination == null || company == null
				|| origin.equals("") || destination.equals("") || company.equals("") || origin.equals(destination)) {
			return false;
		}

		// find the site ids and make new sites if necessary
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

		// Make the new sites if necessary
		if (originID == -1) {
			originID = sites.size() + 1;
			Site originSite = new Site(originID, origin);
			addSite(originSite);
			newSites.add(origin);
		}
		if (destinationID == -1) {
			destinationID = sites.size() + 1;
			Site destinationSite = new Site(destinationID, destination);
			addSite(destinationSite);
			newSites.add(destination);
		}

		// Check if route exists yet. Fail if it already does
		Set<Route> routes = getRoutes();
		for (Route route : routes) {
			if (route.getDestination() == destination && route.getOrigin() == origin
					&& route.getCompany().equals(company) && route.getDuration() == duration) {
				return false;// may need to do a price update instead
			}
		}

		// find the next available ID (current length of routes list!)
		int newRouteID = routes.size() + 1;
		// make route object
		Route newRoute = new Route(newRouteID, origin, destination, company, duration, type, true, custPriceWeight,
				custPriceVolume, transPriceWeight, transPriceVolume);
		// add new route to the map
		try {
			addRoute(newRoute);
		} catch (IllegalRouteException e) {
			e.printStackTrace();
		}
		return true;
	}

	// ======Dijkstra methods... for finding a compound route given from and
	// to=======

	/**
	 * Uses the Dijkstra search method with duration as the value to minimise,
	 * to find the shortest path from "from" to "to". Priority 1 searches may
	 * use SEA, LAND and AIR routes whereas priority 2 searches may only use SEA
	 * and LAND.
	 *
	 * @param from:
	 *            site that the compound route must start
	 * @param to:
	 *            site that the compound route must finish
	 * @param priority:
	 *            allowable values are 1 and 2
	 * @return an ordered list of Route IDs that identify routes that compound
	 *         to create a path from "from" to "to"
	 */
	public List<Integer> findCompoundRoute(int fromSiteID, int toSiteID, model.map.Priority priority) {
		return new DijkstraSearchWithPriority(fromSiteID, toSiteID, this, priority).findShortestRoute();
	}

	// ========== internal helper methods for dealing with the map
	// structure=======

	public void addSite(Site site) {
		sites.put(site.getID(), site);
		siteToRoutes.put(site, new ArrayList<Route>());
	}

	public void addRoute(Route route) throws IllegalRouteException {
		routes.put(route.getID(), route);
		Site s1 = sites.get(getSiteIDfromLocation(route.getOrigin()));
		Site s2 = sites.get(getSiteIDfromLocation(route.getDestination()));
		if (siteToRoutes.get(s1) == null || siteToRoutes.get(s2) == null) {
			throw new IllegalRouteException("Invalid Route! Can't find site");
		}
		List<Route> s1Routes = siteToRoutes.get(s1);
		s1Routes.add(route);
		siteToRoutes.put(s1, s1Routes);
		List<Route> s2Routes = siteToRoutes.get(s2);
		s2Routes.add(route);
		siteToRoutes.put(s2, s2Routes);
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

	public int getSiteIDfromLocation(String location) {
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

	public int findRouteID(String origin, String destination, String carrier, Type type) {
		// find originID
		int originID = getSiteIDfromLocation(origin);
		if (originID == -1)
			return -1;// origin does not exist in map
		// find destinationID
		int destinationID = getSiteIDfromLocation(destination);
		if (destinationID == -1)
			return -1;// destination does not exist in map
		// look through routes and find one whose details match! return first
		// found
		for (Route route : this.siteToRoutes.get(originID)) {
			if (route.getCompany().equalsIgnoreCase(carrier) && route.getDestination() == destination
					&& route.getType().equals(type)) {
				return route.getID();
			}
		}
		;
		return -1;// route was not found
	}

	public String getSitefromID(int id) {
		if (sites.containsKey(id))
			return sites.get(id).getLocation();
		return null;
	}

	/*
	 * =========================================================================
	 * START OF Methods to provide information to the GUI
	 * =========================================================================
	 */
	/**
	 * Loops through the list of sites and adds the names of the sites into a
	 * list. The list is then sorted alphabetically.
	 *
	 * @return a list of the site names
	 */
	public List<String> getSiteNames() {
		List<String> siteNames = new ArrayList<String>();
		for (Site site : sites.values()) {
			siteNames.add(site.getLocation());
		}
		Collections.sort(siteNames);
		return siteNames;
	}

	public List<String> getNewSites() {
		return newSites;
	}

	public List<String> getCompanies() {
		List<String> companies = new ArrayList<String>();
		for(Route r : routes.values()){
			if(!companies.contains(r.getCompany())) companies.add(r.getCompany());
		}
		return companies;
	}
	/*
	 * =========================================================================
	 * END OF Methods to provide information to the GUI
	 * =========================================================================
	 */




}
