package model.map;

import java.util.*;

import model.ValidationSystem;
import model.exceptions.IllegalRouteException;

public class SiteMap {
	private ValidationSystem validationSystem = new ValidationSystem();

	private Map<Integer, Site> sites; // maps site to its id
	private Map<Integer, Route> routes; // maps route to its id
	private Map<Site, List<Route>> siteToRoutes; // maps sites to routes

	// fields used to update the gui
	private List<String> origins; // list of all the names of origins
	private List<String> destinations; // list of all the names of destinations
	private String newOrigin; // name of the newly added origin
	private String newDestination; // name of the newly added destination

	public SiteMap() {
		sites = new HashMap<Integer, Site>();
		routes = new HashMap<Integer, Route>();
		siteToRoutes = new HashMap<Site, List<Route>>();
		origins = new ArrayList<String>();
		destinations = new ArrayList<String>();
	}

	/*
	 * =========================================================================
	 * START OF Helper methods to update stuff for events
	 * =========================================================================
	 */
	/**
	 * Updates the customer price of all the routes with the same origin,
	 * destination and priority
	 *
	 * @param origin
	 *            Name of the origin
	 * @param destination
	 *            Name of the destination
	 * @param priority
	 *            Priority of the route
	 * @param newWeightPrice
	 *            New customer weight price
	 * @param newVolumePrice
	 *            New customer volume price
	 * @return true if at least one route has been updated
	 */
	public boolean updateCustomerPrices(String origin, String destination, Priority priority, double newWeightPrice,
			double newVolumePrice) {
		int routesUpdated = 0;
		for (Route route : routes.values()) {
			if (route.getOrigin().equals(origin) && route.getDestination().equals(destination)
					&& compareTypeAndPriority(route.getType(), priority)) {
				route.updateCustomerPrices(newWeightPrice, newVolumePrice);
				routesUpdated++;
			}
		}
		return routesUpdated > 0;
	}

	/**
	 * Updates the transport cost of the route, given its ID
	 *
	 * @param routeID
	 *            ID of the route to be updated
	 * @param newWeightCost
	 *            New transport weight cost
	 * @param newVolumeCost
	 *            New transport volume cost
	 */
	public void updateTransportCost(int routeID, double newWeightCost, double newVolumeCost) {
		routes.get(routeID).updateTransportCosts(newWeightCost, newVolumeCost);
	}
	/*
	 * =========================================================================
	 * END OF Helper methods to update stuff for events
	 * =========================================================================
	 */

	/*
	 * =========================================================================
	 * START OF Helper methods to deal with routes
	 * =========================================================================
	 */

	/**
	 * Sets a route to be out of service. Doesn't remove it from database
	 *
	 * @param toDiscontinueID
	 *            ID of the route to be discontinued
	 * @return true if successful, otherwise false
	 */
	public boolean discontinueRoute(int toDiscontinueID) {
		// check route is valid
		if (!routes.containsKey(toDiscontinueID)) {
			return false;
		}
		// call route to say it is discontinued
		routes.get(toDiscontinueID).discontinue();
		return true;
	}

	/**
	 * Makes a new route then adds it to the site map. This method will also
	 * make and add new sites if necessary
	 *
	 * @param origin
	 *            Name of origin
	 * @param destination
	 *            Name of destination
	 * @param company
	 *            Company of the route
	 * @param type
	 *            The type of route
	 * @param duration
	 *            The duration of the route
	 * @param custWeightPrice
	 *            Customer weight price
	 * @param custVolumePrice
	 *            Customer volume price
	 * @param transWeightCost
	 *            Transport weight cost
	 * @param transVolumeCost
	 *            Transport volume cost
	 * @return true if successful, otherwise false
	 */
	public boolean addNewRoute(String origin, String destination, String company, Type type, double duration,
			double custWeightPrice, double custVolumePrice, double transWeightCost, double transVolumeCost) {
		// check all input for values in correct format
		if (duration <= 0 || custWeightPrice <= 0 || custVolumePrice <= 0 || transWeightCost <= 0
				|| transVolumeCost <= 0 || origin == null || destination == null || company == null || origin.equals("")
				|| destination.equals("") || company.equals("") || origin.equals(destination)) {
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
			Site originSite = new Site(originID, origin, true, false);
			if (!addSite(originSite)) {
				return false;
			}
		}
		if (destinationID == -1) {
			destinationID = sites.size() + 1;
			Site destinationSite = new Site(destinationID, destination, false, true);
			if (!addSite(destinationSite)) {
				return false;
			}
		}

		// Check if route exists yet. Fail if it already does
		for (Route route : routes.values()) {
			if (route.getDestination() == destination && route.getOrigin() == origin
					&& route.getCompany().equals(company) && route.getDuration() == duration) {
				return false;// may need to do a price update instead
			}
		}

		// find the next available ID (current length of routes list!)
		int newRouteID = routes.values().size() + 1;
		// make route object
		Route newRoute = new Route(newRouteID, origin, destination, company, duration, type, true, custWeightPrice,
				custVolumePrice, transWeightCost, transVolumeCost);
		// add new route to the map
		try {
			addRoute(newRoute);
		} catch (IllegalRouteException e) {
			return false;
		}
		return true;
	}

	/**
	 * Adds the route to the site map. Updates the map of sites to routes as
	 * well as the fields to update the gui
	 *
	 * @param route
	 *            New Route to be added
	 * @throws IllegalRouteException
	 *             If a site from the route cannot be found in the map of sites
	 *             to routes
	 */
	public void addRoute(Route route) throws IllegalRouteException {
		Site origin = sites.get(getSiteIDfromLocation(route.getOrigin()));
		Site destination = sites.get(getSiteIDfromLocation(route.getDestination()));
		if (siteToRoutes.get(origin) == null || siteToRoutes.get(destination) == null) {
			throw new IllegalRouteException("Invalid Route! Can't find site");
		}
		routes.put(route.getID(), route);
		List<Route> routesToOrigin = siteToRoutes.get(origin);
		routesToOrigin.add(route);
		siteToRoutes.put(origin, routesToOrigin);
		List<Route> routesToDestination = siteToRoutes.get(destination);
		routesToDestination.add(route);
		siteToRoutes.put(destination, routesToDestination);
		// Update the sites so we know if they are an origin and/or destination
		origin.setIsOrigin();
		destination.setIsDestination();
		if (!origins.contains(origin.getLocation())) {
			origins.add(origin.getLocation());
			newOrigin = origin.getLocation();
		} else {
			newOrigin = null;
		}
		if (!destinations.contains(destination.getLocation())) {
			destinations.add(destination.getLocation());
			newDestination = destination.getLocation();
		} else {
			newDestination = null;
		}
	}
	/*
	 * =========================================================================
	 * END OF Helper methods to deal with routes
	 * =========================================================================
	 */

	/*
	 * =========================================================================
	 * START OF Helper methods to deal with sites
	 * =========================================================================
	 */
	/**
	 * Adds a site to the site map. Validates the site first before adding
	 *
	 * @param site
	 *            Site to be added
	 * @return true if successful, otherwise false
	 */
	public boolean addSite(Site site) {
		// make sure site is valid
		if (!validationSystem.validateSite(site)) {
			return false;
		}
		sites.put(site.getID(), site);
		siteToRoutes.put(site, new ArrayList<Route>());
		return true;
	}
	/*
	 * =========================================================================
	 * END OF Helper methods to deal with sites
	 * =========================================================================
	 */

	/*
	 * =========================================================================
	 * START OF Helper Methods for dijkstra
	 * =========================================================================
	 */
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
	public List<Integer> findCompoundRoute(int fromSiteID, int toSiteID, Priority priority) {
		return new DijkstraSearchWithPriority(fromSiteID, toSiteID, this, priority).findShortestRoute();
	}
	/*
	 * =========================================================================
	 * END OF Helper Methods for dijkstra
	 * =========================================================================
	 */

	/*
	 * =========================================================================
	 * START OF Methods to provide information to the GUI
	 * =========================================================================
	 */

	/**
	 * Returns the list of the names of all the origins
	 *
	 * @return List of all the names of the origins
	 */
	public List<String> getOrigins() {
		Collections.sort(origins);
		return origins;
	}

	/**
	 * Returns the list of the names of all the destinations
	 *
	 * @return List of all the names of the destinations
	 */
	public List<String> getDestinations() {
		Collections.sort(destinations);
		return destinations;
	}

	/**
	 * Get the name of the newly added origin
	 *
	 * @return Name of the new origin
	 */
	public String getNewOrigin() {
		return newOrigin;
	}

	/**
	 * Get the name of the newly added destination
	 *
	 * @return Name of the new destination
	 */
	public String getNewDestination() {
		return newDestination;
	}

	/**
	 * Loops through all the routes to get the names of all the companies and
	 * adds them into a list to be returned
	 *
	 * @return List of all the names of the companies
	 */
	public List<String> getCompanies() {
		List<String> companies = new ArrayList<String>();
		for (Route r : routes.values()) {
			if (!companies.contains(r.getCompany()))
				companies.add(r.getCompany());
		}
		return companies;
	}

	/**
	 * Returns a set of all the sites
	 *
	 * @return Set of all the site objects
	 */
	public Set<Site> getSites() {
		return new HashSet<Site>(sites.values());
	}

	/**
	 * Returns a set of all the routes
	 *
	 * @return Set of all the route objects
	 */
	public Set<Route> getRoutes() {
		return new HashSet<Route>(routes.values());
	}
	/*
	 * =========================================================================
	 * END OF Methods to provide information to the GUI
	 * =========================================================================
	 */

	/*
	 * =========================================================================
	 * START OF Helper Methods
	 * =========================================================================
	 */
	/**
	 * Checks the type with the priority
	 *
	 * @param type
	 * @param priority
	 * @return true if both the type and priority are "air", otherwise false
	 */
	private boolean compareTypeAndPriority(Type type, Priority priority) {
		if (type.equals(Type.AIR)) {
			return priority.equals(Priority.INTERNATIONAL_AIR) || priority.equals(Priority.DOMESTIC_AIR);
		}
		return priority.equals(Priority.INTERNATIONAL_STANDARD) || priority.equals(Priority.DOMESTIC_STANDARD);
	}

	/**
	 * Capitalises the each word in the given input and trims any extra white
	 * space
	 *
	 * @param input
	 *            String input
	 * @return Formatted string of the original input
	 */
	public static String uniformPrint(String input) {
		String output = "";
		String[] words = input.trim().toLowerCase().split(" ");
		for (int i = 0; i < words.length; ++i) {
			if (words[i].length() > 0) {
				char[] letters = words[i].toCharArray();
				letters[0] = Character.toUpperCase(letters[0]);
				words[i] = new String(letters);
			}
			output += words[i] + " ";
		}
		return output.trim();
	}

	/**
	 * Gets all the routes that go to a site, given the ID
	 *
	 * @param siteID
	 *            ID of the given site
	 * @return List of all the routes to the site
	 */
	public List<Route> getRoutesOn(int siteID) {
		Site site = this.sites.get(siteID);
		return siteToRoutes.get(site);
	}

	/**
	 * Gets the ID of the site given its name
	 *
	 * @param location
	 *            Name of the site
	 * @return ID of the site
	 */
	public int getSiteIDfromLocation(String location) {
		for (Site site : sites.values()) {
			if (site.getLocation().equals(location)) {
				return site.getID();
			}
		}
		return -1;
	}

	/**
	 * Gets the route object given its ID
	 *
	 * @param routeID
	 *            ID of the route
	 * @return Route object
	 */
	public Route getRouteFromID(int routeID) {
		return this.routes.get(routeID);
	}

	/**
	 * Find the ID of a route given its origin, destination, company and type
	 *
	 * @param origin
	 *            Name of the origin
	 * @param destination
	 *            Name of the destination
	 * @param company
	 *            Name of the company
	 * @param type
	 *            Type of route
	 * @return ID of the route
	 */
	public int findRouteID(String origin, String destination, String company, Type type) {
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
		for (Route route : this.siteToRoutes.get(sites.get(originID))) {
			if (route.getCompany().equals(company) && route.getDestination().equals(destination)
					&& route.getType().equals(type)) {
				return route.getID();
			}
		}
		;
		return -1;// route was not found
	}

	/**
	 * Get the name of the site from its ID
	 *
	 * @param ID
	 *            ID of the site
	 * @return Name of the site
	 */
	public String getSiteNamefromID(int ID) {
		if (sites.containsKey(ID))
			return sites.get(ID).getLocation();
		return null;
	}
	/*
	 * =========================================================================
	 * END OF Helper Methods
	 * =========================================================================
	 */
}
