package model;

import storage.DataStore;

import java.util.List;

import model.EventProcessor;
import model.employees.Employee;
import model.events.BusinessEvent;
import model.events.MailProcessEvent;
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
	private SiteMap sitemap;
	private DataStore db;
	private EventProcessor eventProcessor;
	private FigureGenerator fg;
	private int loggedInUserID;
	private int currentEvent;

	public KPSmartModel() {
		db = new DataStore();
		sitemap = db.getSiteMap();
		fg = new FigureGenerator(db);
		eventProcessor = new EventProcessor(db);
		loggedInUserID = -1;// no-one is logged in initially
		currentEvent = db.getBusinessEventStrings().size() - 1;
	}

	public void save() {
		db.save();
	}

	/*
	 * =========================================================================
	 * START OF Methods to Log in and out - Called by the controller
	 * =========================================================================
	 */

	/**
	 * Controller needs to know (a) if the login was successful - if so (b) name
	 * of staff (so they pass to GUI) (c) what type of employee (so they can set
	 * up GUI appropriately)
	 *
	 * The name of the staff is already known by the controller (it is passed to
	 * this method as an argument) The other 2 pieces of information can both be
	 * booleans (success/failure, and manager/not-manager)
	 *
	 * @return a 2-ary boolean array [loginSuccessful,isManager] - first item is
	 *         success of login (true) or failure of login (false) - second item
	 *         is manager (true) or not-manager (false)
	 *
	 */
	public boolean[] logIn(String name, String password) {
		boolean[] response = new boolean[2];
		int userID;
		try {
			userID = Integer.parseInt(name);
		} catch (NumberFormatException nfe) {
			response[0] = false;
			return response;
		}

		Employee emp = db.getEmployees().getEmployeeFromID(userID);

		if (emp == null || !emp.getPassword().equals(password)) {
			response[0] = false;
			return response;
		}
		// if found, update loggedInUserID and prepare found response
		this.loggedInUserID = userID;
		response[0] = true;
		response[1] = emp.isManager();
		return response;
	}

	/*
	 * Unlike the other controller called methods, there is no return value,
	 * this method always succeeds!
	 */
	public void logOut() {
		// reset loggedInUserID to default (no-one logged in) value
		db.save();
		this.loggedInUserID = -1;
	}

	/*
	 * =========================================================================
	 * END OF Methods to Log in and out - Called by the controller
	 * =========================================================================
	 */

	/*
	 * =========================================================================
	 * START OF Methods to Generate Figures - Called by the controller
	 * =========================================================================
	 */
	public String getFigures() {
		return fg.toString();
	}
	/*
	 * =========================================================================
	 * END OF Methods to Generate Figures - Called by the controller
	 * =========================================================================
	 */

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
		// convert strings into needed types
		double weight;
		double volume;
		try {
			weight = Double.parseDouble(input.getWeight());
			volume = Double.parseDouble(input.getVolume());
		} catch (NumberFormatException nfe) {
			return false;
		}

		Priority priority = getPriority(input.getPriority());
		// make sure converted data are valid - abort and return false if not!
		if (priority == null || originID == -1 || destinationID == -1 || weight <= 0 || volume <= 0) {
			return false;
		}

		// call event processor to make up the package and record the event
		MailProcessEvent be = eventProcessor.processMail(originID, input.getOrigin(), destinationID,
				input.getDestination(), weight, volume, priority, this.loggedInUserID);
		if (be != null) {
			fg.addEvent(be);
			return true;
		}
		return false;
	}

	/**
	 * Takes in some information and adds a route. Also creates an event and
	 * stores it into the data store
	 *
	 * @param input
	 * @return
	 */
	public boolean addNewRoute(NewRouteInput input) {

		double custPriceWeight;
		double custPriceVolume;
		double transCostWeight;
		double transCostVolume;
		double duration;

		try {
			custPriceWeight = Double.parseDouble(input.getCustomerPriceWeight());
			custPriceVolume = Double.parseDouble(input.getCustomerPriceVolume());
			transCostWeight = Double.parseDouble(input.getTransportPriceWeight());
			transCostVolume = Double.parseDouble(input.getTransportPriceVolume());
			duration = Double.parseDouble(input.getDuration());
		} catch (NumberFormatException nfe) {
			return false;
		}

		Type type = getType(input.getType());
		// make sure converted data is valid - abort and return false if not!
		if (type == null || custPriceWeight <= 0 || custPriceVolume <= 0 || transCostWeight <= 0 || transCostVolume <= 0
				|| duration <= 0) {
			return false;
		}

		// call event processor to do the addition and record the event

		return eventProcessor.addRoute(input.getOrigin(), input.getDestination(), input.getCompany(), type, duration,
				custPriceWeight, custPriceVolume, transCostWeight, transCostVolume, loggedInUserID);
	}

	/**
	 * Takes in some information and discontinues a route. Also creates an event
	 * and stores it into the data store
	 *
	 * @param input
	 * @return
	 */
	public boolean discontinueRoute(DiscontinueInput input) {
		// convert strings into needed data types
		Type type = getType(input.getType());
		// make sure type is valid - abort and return false if not!
		if (type == null) {
			return false;
		}
		// identify which route
		int routeID = sitemap.findRouteID(input.getOrigin(), input.getDestination(), input.getCompany(), type);
		// make sure converted data is valid - abort and return false if not!
		if (routeID == -1) {
			return false;
		}

		// call event processor to do the discontinuation and record the event
		return eventProcessor.disconRoute(routeID, loggedInUserID);
	}

	/**
	 * Takes in some information and does a customer price change. Also creates
	 * an event and stores it into the data store
	 *
	 * @param input
	 * @return
	 */
	public boolean changeCustomerPrice(CustomerPriceInput input) {
		double newWeightCost;
		double newVolumeCost;

		try {
			newWeightCost = Double.parseDouble(input.getWeightCost());
			newVolumeCost = Double.parseDouble(input.getVolumeCost());
		} catch (NumberFormatException nfe) {
			return false;
		}

		Priority priority = getPriority(input.getPriority());

		// make sure converted data is valid - abort and return false if not!
		if (priority == null || newWeightCost <= 0 || newVolumeCost <= 0) {
			return false;
		}

		// event processor to change the customer price and record the event
		return eventProcessor.changeCustomerPrice(input.getOrigin(), input.getDestination(), priority, newWeightCost,
				newVolumeCost, this.loggedInUserID);
	}

	/**
	 * Takes in some information and does a transport cost change. Also creates
	 * an event and stores it into the data store
	 *
	 * @param input
	 * @return
	 */
	public boolean changeTransportCost(TransportCostInput input) {
		double newWeightCost;
		double newVolumeCost;
		double duration;

		try {
			newWeightCost = Double.parseDouble(input.getWeightCost());
			newVolumeCost = Double.parseDouble(input.getVolumeCost());
			duration = Double.parseDouble(input.getDuration());

		} catch (NumberFormatException nfe) {
			return false;
		}

		Type type = getType(input.getType());

		// make sure converted data is valid - abort and return false if not!
		if (type == null || newWeightCost <= 0 || newVolumeCost <= 0) {
			return false;
		}

		// find the route
		int routeID = sitemap.findRouteID(input.getOrigin(), input.getDestination(), input.getCompany(), type);
		// make sure converted data is valid - abort and return false if not!
		if (routeID == -1) {
			return false;
		}
		// call event processor on that route
		return eventProcessor.changeTransportCost(routeID, newWeightCost, newVolumeCost, duration, this.loggedInUserID);
	}
	/*
	 * =========================================================================
	 * END OF Methods to process events - Called by the controller
	 * =========================================================================
	 */

	/*
	 * =========================================================================
	 * START OF Methods to provide information to the GUI - Called by the
	 * controller
	 * =========================================================================
	 */
	public List<String> getOrigins() {
		return sitemap.getOrigins();
	}

	public List<String> getDestinations() {
		return sitemap.getDestinations();
	}

	/**
	 * Gets the newly added sites to update the gui
	 *
	 * @return
	 */
	public String getNewOrigin() {
		return db.getSiteMap().getNewOrigin();
	}

	public String getNewDestination() {
		return db.getSiteMap().getNewDestination();
	}

	public String getNewCompany() {
		return db.getSiteMap().getNewCompany();
	}

	/**
	 * Gets a list of all the companies
	 *
	 * @return
	 */
	public List<String> getCompanies() {
		return db.getSiteMap().getCompanies();
	}

	/**
	 * Gets a linked list of strings representing each recorded business event
	 *
	 * @return
	 */
	public List<String> getBusinessEventStrings() {
		return db.getBusinessEventStrings();
	}

	/**
	 * Gets the latest business event (Readable format)
	 *
	 * @return
	 */
	public String getLatestEvent() {
		if (db.getBusinessEventStrings().size() == 0) {
			return "No events found";
		}
		return db.getBusinessEventStrings().get(db.getBusinessEventStrings().size() - 1);
	}

	public String getPreviousEvent() {
		if (db.getBusinessEventStrings().size() == 0) {
			return "No events found";
		}

		if (currentEvent > 0)
			currentEvent--;
		return db.getBusinessEventStrings().get(currentEvent);
	}

	public String getNextEvent() {
		if (db.getBusinessEventStrings().size() == 0) {
			return "No events found";
		}

		if (currentEvent < (db.getBusinessEventStrings().size() - 1))
			currentEvent++;
		return db.getBusinessEventStrings().get(currentEvent);
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
	public static Priority getPriority(String priorityString) {
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

	public String getCurrentUser() {
		return db.getEmployees().getEmployeeFromID(loggedInUserID).getName();
	}

}
