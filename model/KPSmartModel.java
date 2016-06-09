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
	public String processMail(MailProcessInput input) {
		// check the input for errors
		String errorString = input.findInputErrors();
		// if there are errors, abort at this stage returning the error string
		if (!errorString.isEmpty()) {
			return errorString;
		}
		// otherwise proceed! It is safe to convert the strings into their
		// needed types! as the findInputErrors would have found any errors.
		int originID = this.sitemap.getSiteIDfromLocation(input.getOrigin());
		int destinationID = this.sitemap.getSiteIDfromLocation(input.getDestination());
		double weight = Double.parseDouble(input.getWeight());
		double volume = Double.parseDouble(input.getVolume());
		Priority priority = getPriority(input.getPriority());

		// call event processor to make up the package and record the event
		String errors = eventProcessor.processMail(originID, input.getOrigin(), destinationID, input.getDestination(),
				weight, volume, priority, this.loggedInUserID);
		if (errors.isEmpty()) {
			// event was successfully processed
			// stimulate figure generator to re-read the data
			fg.getMailEvents();
		}
		return errors;
	}

	/**
	 * Takes in some information and adds a route. Also creates an event and
	 * stores it into the data store
	 *
	 * @param input
	 * @return
	 */
	public String addNewRoute(NewRouteInput input) {
		// check the input for errors
		String errorString = input.findInputErrors();
		// if there are errors, abort at this stage returning the error string
		if (!errorString.isEmpty()) {
			return errorString;
		}
		// otherwise proceed! It is safe to convert the strings into their
		// needed types! as the findInputErrors would have found any errors.
		double custPriceWeight = Double.parseDouble(input.getCustomerPriceWeight());
		double custPriceVolume = Double.parseDouble(input.getCustomerPriceVolume());
		double transCostWeight = Double.parseDouble(input.getTransportPriceWeight());
		double transCostVolume = Double.parseDouble(input.getTransportPriceVolume());
		double duration = Double.parseDouble(input.getDuration());
		Type type = getType(input.getType());
		// call event processor to do the addition and record the event
		// and return the error message ("" if no errors)
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
	public String discontinueRoute(DiscontinueInput input) {
		// no need to check the input for errors - all components are found from
		// drop-downs of valid input
		// Proceed! It is safe to convert the strings into their needed types!
		Type type = getType(input.getType());

		// Although the sites, company, and type are definitely valid, they may
		// not
		// correspond to an actual route
		int routeID = sitemap.findRouteID(input.getOrigin(), input.getDestination(), input.getCompany(), type);
		// make sure converted data is valid - abort and return false if not!
		if (routeID == -1) {
			return "There is no route corresponding to the given input";
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
	public String changeCustomerPrice(CustomerPriceInput input) {
		
		// check the input for errors
		String errorString = input.findInputErrors();
		// if there are errors, abort at this stage returning the error string
		if (!errorString.isEmpty()) {
			return errorString;
		}
		
		// otherwise proceed! It is safe to convert the strings into their
		// needed types! as the findInputErrors would have found any errors.
		double newWeightPrice = Double.parseDouble(input.getWeightPrice());
		double newVolumePrice = Double.parseDouble(input.getVolumePrice());
		Priority priority = getPriority(input.getPriority());

		// call event processor to change the customer price and record the
		// event
		return eventProcessor.changeCustomerPrice(input.getOrigin(), input.getDestination(), priority, newWeightPrice,
				newVolumePrice, this.loggedInUserID);
	}

	/**
	 * Takes in some information and does a transport cost change. Also creates
	 * an event and stores it into the data store
	 *
	 * @param input
	 * @return
	 */
	public String changeTransportCost(TransportCostInput input) {
		// check the input for errors
		String errorString = input.findInputErrors();
		// if there are errors, abort at this stage returning the error string
		if (!errorString.isEmpty()) {
			return errorString;
		}
		// otherwise proceed! It is safe to convert the strings into their
		// needed types! as the findInputErrors would have found any errors.
		double newWeightCost = Double.parseDouble(input.getWeightCost());
		double newVolumeCost = Double.parseDouble(input.getVolumeCost());
		double duration = Double.parseDouble(input.getDuration());
		Type type = getType(input.getType());
		
		// find the route
		int routeID = sitemap.findRouteID(input.getOrigin(), input.getDestination(), input.getCompany(), type);
		// make sure converted data is valid - abort and return false if not!
		if (routeID == -1) {
			return "There is no route corresponding to the given input";
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

	/*
	 * =========================================================================
	 * START OF Helper methods for testing
	 * =========================================================================
	 */
	
	public SiteMap getSiteMap(){
		return db.getSiteMap();
	}
	
	/*
	 * =========================================================================
	 * END OF Helper methods for testing
	 * =========================================================================
	 */
}
