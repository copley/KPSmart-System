package model;

import storage.DataStore;
import model.EventProcessor;
import model.map.*;
import model.Package;
import model.employees.Employee;

/**
 * 
 * A single instance of the model class is created by the controller class This
 * class makes an instance of the DataStore class, which reads in all the other
 * data required to populate the model with historical data - including the
 * SiteMap.
 * 
 * All direct communication with the model from the controller happens via
 * methods in this class. The methods in this class action methods in other
 * parts of the model.
 * 
 * @author Nic, Bonnie, Joely
 *
 */
public class KPSmartModel {
	private SiteMap sitemap;// holds all the routes and sites and the
							// interrelation between the two
	// this is a link, for convenience, to the SiteMap stored in the DataStore.
	private DataStore db;// holds links to all the permanent data, and handles
							// the read and write to file.
	private EventProcessor eventProcessor;// manages the production of business
											// event records
	private int loggedInStaffID;// currently logged in staff member id

	public KPSmartModel() {
		db = new DataStore();
		sitemap = db.getSiteMap();
		eventProcessor = new EventProcessor(db);
		loggedInStaffID = -1;// no-one is logged in initially
	}

	public void save() {
		db.save();
	}

	// called from controller class, returns a boolean to indicate success
	// (true) or failure (false)
	public boolean changeCustomerPrice(String origin, String destination, String carrier, String typeString,
			String newWeightCostString, String newVolumeCostString) {
		// convert strings into needed types
		Type type = getType(typeString);
		// make sure type is valid - abort and return false if not!
		if (type == null) {
			return false;
		}
		double newWeightCost = Double.parseDouble(newWeightCostString);
		double newVolumeCost = Double.parseDouble(newVolumeCostString);
		// identify which route
		int routeID = sitemap.findRouteID(origin, destination, carrier, type);
		// make sure converted data is valid - abort and return false if not!
		if (routeID == -1) {
			return false;
		}
		// call event processor on that route, event processor returns true if
		// event was performed successfully
		// returns false if the event was aborted
		return eventProcessor.changeCustomerPrice(routeID, newWeightCost, newVolumeCost, this.loggedInStaffID);
	}

	// called from controller class - mc
	public void changeTransportPrice(String origin, String destination, String carrier, Type type, double newWeightCost,
			double newVolumeCost) {
		// identify which route
		int routeID = sitemap.findRouteID(origin, destination, carrier, type);
		// call event processor on that route
		eventProcessor.changeTransportCost(routeID, newWeightCost, newVolumeCost, this.loggedInStaffID);
	}

	// called from controller class - mc
	public boolean processMail(String origin, String destination, String weightString, String volumeString,
			String priorityString) {
		// work out the origin and destination IDs
		int originSiteID = this.sitemap.getSiteIDfromLocation(origin);
		int destSiteID = this.sitemap.getSiteIDfromLocation(destination);
		// convert strings into a needed types
		double weight = Double.parseDouble(weightString);
		double volume = Double.parseDouble(volumeString);
		Priority priority = getPriority(priorityString);
		// make sure converted data are valid - abort and return false if not!
		if (priority == null || originSiteID == -1 || destSiteID == -1) {
			return false;
		}

		// call event processor to make up the package and record the event
		return eventProcessor.processMail(originSiteID, destSiteID, weight, volume, priority, this.loggedInStaffID);
	}

	// called from controller class - mc
	public boolean addRoute(String origin, String destination, String company, String durationString, String typeString,
			String customerPriceWeight, String customerPriceVolume, String transportCostWeight,
			String transportCostVolume) {
		// note that we don't need to work out the origin and destination IDs,
		// if the sites do not already exist,
		// they will be created during the route addition
		// convert strings into needed types
		Type type = getType(typeString);
		// make sure converted data is valid - abort and return false if not!
		if (type == null) {
			return false;
		}
		double custPriceWeight = Double.parseDouble(customerPriceWeight);
		double custPriceVolume = Double.parseDouble(customerPriceVolume);
		double transCostWeight = Double.parseDouble(transportCostWeight);
		double transCostVolume = Double.parseDouble(transportCostVolume);
		double duration = Double.parseDouble(durationString);

		// call event processor to do the addition and record the event

		return eventProcessor.addRoute(origin, destination, company, type, duration, custPriceWeight, custPriceVolume,
				transCostWeight, transCostVolume, loggedInStaffID);

	}

	// called from controller class - mc
	public boolean discontinueRoute(String origin, String destination, String company, String typeString) {
		//convert strings into needed data types
		Type type = getType(typeString);
		// make sure type is valid - abort and return false if not!
		if (type == null) {
			return false;
		}
		// identify which route
		int routeID = sitemap.findRouteID(origin, destination, company, type);
		// make sure converted data is valid - abort and return false if not!
		if (routeID == -1) {
			return false;
		}
		
		// call event processor to do the discontinuation and record the event
		return eventProcessor.disconRoute(routeID, loggedInStaffID);
	}

	// ===============helper methods================

	private Priority getPriority(String priorityString) {
		switch (priorityString) {
		case "International Air":
			return Priority.INTERNATIONAL_AIR;
		case "International Standard":
			return Priority.INTERNATIONAL_STANDARD;
		case "Domestic Air":
			return Priority.DOMESTIC_AIR;
		case "Domestic Standard":
			return Priority.DOMESTIC_STANDARD;
		default:
			return null;
		}
	}

	private Type getType(String typeString) {
		switch (typeString) {
		case "AIR":
			return Type.AIR;
		case "SEA":
			return Type.SEA;
		case "LAND":
			return Type.LAND;
		default:
			return null;
		}
	}

}
