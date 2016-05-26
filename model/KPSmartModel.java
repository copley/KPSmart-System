package model;

import storage.DataStore;

import java.util.ArrayList;
import java.util.List;

import model.EventProcessor;
import model.events.inputs.*;
import model.map.*;

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

	/*
	 * =========================================================================
	 * START OF Methods to process events - Called by the controller
	 * =========================================================================
	 */
	/**
	 * Takes in some information and does a process mail delivery. Also creates
	 * an event and stores it into the data store
	 *
	 * @param input
	 * @return
	 */
	public boolean processMail(MailProcessInput input) {
		// work out the origin and destination IDs
		int originID = this.sitemap.getSiteIDfromLocation(input.getOrigin());
		int destinationID = this.sitemap.getSiteIDfromLocation(input.getDestination());

		// convert strings into a needed types
		double weight = Double.parseDouble(input.getWeight());
		double volume = Double.parseDouble(input.getVolume());
		Priority priority = getPriority(input.getPriority());
		// make sure converted data are valid - abort and return false if not!
		if (priority == null || originID == -1 || destinationID == -1) {
			return false;
		}

		// call event processor to make up the package and record the event
		return eventProcessor.processMail(originID, input.getOrigin(), destinationID, input.getDestination(), weight,
				volume, priority, this.loggedInStaffID);
	}

	/**
	 * Takes in some information and adds a route. Also creates an event and
	 * stores it into the data store
	 *
	 * @param input
	 * @return
	 */
	public boolean addNewRoute(NewRouteInput input) {
		Type type = getType(input.getType());
		// make sure converted data is valid - abort and return false if not!
		if (type == null) {
			return false;
		}
		double custPriceWeight = Double.parseDouble(input.getCustomerPriceWeight());
		double custPriceVolume = Double.parseDouble(input.getCustomerPriceVolume());
		double transCostWeight = Double.parseDouble(input.getTransportPriceWeight());
		double transCostVolume = Double.parseDouble(input.getTransportPriceVolume());
		double duration = Double.parseDouble(input.getDuration());

		// call event processor to do the addition and record the event

		return eventProcessor.addRoute(input.getOrigin(), input.getDestination(), input.getCompany(), type, duration,
				custPriceWeight, custPriceVolume, transCostWeight, transCostVolume, loggedInStaffID);
	}
	/*
	 * =========================================================================
	 * END OF Methods to process events - Called by the controller
	 * =========================================================================
	 */

	/**
	 * Takes in some information and does a customer price change. Also creates
	 * an event and stores it into the data store
	 *
	 * @param origin
	 * @param destination
	 * @param carrier
	 * @param typeString
	 * @param newWeightCostString
	 * @param newVolumeCostString
	 * @return
	 */
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

	/**
	 * Takes in some information and does a transport cost change. Also creates
	 * an event and stores it into the data store
	 *
	 * @param origin
	 * @param destination
	 * @param carrier
	 * @param typeString
	 * @param newWeightCostString
	 * @param newVolumeCostString
	 * @return
	 */
	public boolean changeTransportPrice(String origin, String destination, String carrier, String typeString,
			String newWeightCostString, String newVolumeCostString) {
		// identify which route
		Type type = getType(typeString);
		// make sure type is valid - abort and return false if not!
		if (type == null) {
			return false;
		}
		double newWeightCost = Double.parseDouble(newWeightCostString);
		double newVolumeCost = Double.parseDouble(newVolumeCostString);
		int routeID = sitemap.findRouteID(origin, destination, carrier, type);
		// call event processor on that route
		return eventProcessor.changeTransportCost(routeID, newWeightCost, newVolumeCost, this.loggedInStaffID);
	}

	/**
	 * Takes in some information and discontinues a route. Also creates an event
	 * and stores it into the data store
	 *
	 * @param origin
	 * @param destination
	 * @param company
	 * @param typeString
	 * @return
	 */
	public boolean discontinueRoute(String origin, String destination, String company, String typeString) {
		// convert strings into needed data types
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

	/*
	 * =========================================================================
	 * START OF Methods to provide information to the GUI - Called by the
	 * controller
	 * =========================================================================
	 */
	public List<String> getSiteNames() {
		return sitemap.getSiteNames();
	}

	/**
	 * Gets the newly added sites to update the gui
	 *
	 * @return
	 */
	public List<String> getNewSites() {
		return db.getSiteMap().getNewSites();
	}

	/**
	 * Gets a list of all the companies
	 *
	 * @return
	 */
	public List<String> getCompanies() {
		return db.getSiteMap().getCompanies();
	}
	/*
	 * =========================================================================
	 * END OF Methods to provide information to the GUI - Called by the
	 * controller
	 * =========================================================================
	 */

	/*
	 * =========================================================================
	 * START OF Helper methods for the model
	 * =========================================================================
	 */
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

	/*
	 * =========================================================================
	 * END OF Helper methods for the model
	 * =========================================================================
	 */

}
