package model;

import java.time.LocalDateTime;

import model.employees.Employee;
import model.events.BusinessEvent;
import model.events.CustPriceChangeEvent;
import model.events.MailProcessEvent;
import model.events.RouteAdditionEvent;
import model.events.RouteDiscEvent;
import model.events.TransportCostChangeEvent;
import model.map.*;
import storage.DataStore;

/**
 * 
 * This Class processes events requested by the controller, and directed through
 * the KPSmartModel Class. Any changes to the SiteMap data that are required by
 * the events are made here. The appropriate business event object is made as
 * record of each event, and these events are pushed to the DataStore for
 * storage (and retrieval if a request to review events is made from the
 * controller)
 * 
 * @author Nic, Joely
 * 
 * 
 * 
 *         Implement Class Methods.
 *
 *         If this current implementation does not work, We are always able to
 *         copy functionality methods INSIDE EventProcessor into the
 *         KPSmartModel
 *
 *
 */
public class EventProcessor {

	public static String debuggingString = "Class EventProcessor";
	public static int debuggingInt = 0;

	public final DataStore db;

	public EventProcessor(DataStore db) {
		this.db = db;
	}

	// the only things that can be changed on the route are the prices
	// if changes to the carrier, mode, or duration are needed, the route should
	// be discontinued,
	// and a new one made

	/**
	 * 
	 * @param routeID
	 *            --> in constructor, there is origin and destination???
	 * @param need
	 *            a priority for constructor as well?
	 */
	public boolean changeCustomerPrice(int routeID, double newCustPriceWeight, double newCustPriceVolume,
			int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour() * 10 + now.getMinute();
		// try to access the required data
		Employee employee = db.getEmployees().getEmployeeFromID(processorStaffID);
		Route route = db.getSiteMap().getRouteFromID(routeID);
		// if employee or route don't exist, this even fails! do not proceed!
		if (employee == null || route == null) {
			return false;
		}
		// if we got here, employee and route are safe to call methods on..
		String employeeName = employee.getName();
		String origin = route.getOrigin();
		String destination = route.getDestination();
		String company = route.getCompany();
		Type type = route.getType();
		// make the changes to the route
		route.updateCustomerPrices(newCustPriceWeight, newCustPriceVolume);
		// make the event to record the action
		BusinessEvent cpcBusinessEvent = new CustPriceChangeEvent(day, month, year, time, employeeName, origin,
				destination, company, type, newCustPriceWeight, newCustPriceVolume);
		// store the event in the data store
		db.addEvent(cpcBusinessEvent);
		// report that the change was successfully made
		return true;

		// System.out.println(debuggingString + debuggingInt + 1);
	};

	public void changeTransportCost(int routeID, double newTransCostWeight, double newTransCostVolume,
			int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour() * 10 + now.getMinute();
		String employee = db.getEmployees().getEmployeeFromID(processorStaffID).getName();
		String origin;
		String destination;
		String company;
		String type;
		String departureDay;
		int frequency;
		int duration;
		BusinessEvent ctcBusinessEvent = new TransportCostChangeEvent(day, month, year, time, employee, origin,
				destination, company, type, newTransCostWeight, newTransCostVolume, departureDay, frequency, duration);
		db.addEvent(ctcBusinessEvent);
		// System.out.println(debuggingString + debuggingInt + 2);
	};

	/**
	 * @param origin
	 *            - string
	 * @param destination
	 *            - string
	 * @param carrier
	 *            - string
	 * @param type
	 *            - Type enum (mode of transport - AIR, SEA, LAND
	 * @param duration
	 *            - double, hours of travel time when this route is taken
	 * @param custPriceWeight
	 *            - double, price charged to customer per gram of weight
	 * @param custPriceVolume
	 *            - double, price charged to customer per cubic cm of volume
	 * @param transCostWeight
	 *            - double, cost that KPS incurs from transport company, per
	 *            gram of weight
	 * @param transCostVolume
	 *            - double, cost that KPS incurs from transport company, per
	 *            cubic cm of volume
	 * @param processorStaffID
	 *            - int, id of staff that was logged into the system when event
	 *            was generated
	 */
	public boolean addRoute(String origin, String destination, String company, Type type, double duration,
			double custPriceWeight, double custPriceVolume, double transCostWeight, double transCostVolume,
			int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour() * 10 + now.getMinute();
		// get logged in staff name
		Employee processor = db.getEmployees().getEmployeeFromID(processorStaffID);
		String processorName;
		if (processor == null) {
			// TODO: remove this and replace with an error once log-in function
			// is coded
			processorName = "no-one";// should only be used while there is no
										// proper log-in
		} else {
			processorName = processor.getName();
		}
		// make the changes to the siteMap (add route)
		// if makeNewRoute returns false, then abort the event procession and
		// report failure
		if (!db.getSiteMap().makeNewRoute(origin, destination, company, type, duration, custPriceWeight,
				custPriceVolume, transCostWeight, transCostVolume)) {
			return false;
		}
		// make the event to record the action
		BusinessEvent addRouteBusinessEvent = new RouteAdditionEvent(day, month, year, time, processorName, origin,
				destination, company, type, duration, custPriceWeight, custPriceVolume, transCostWeight,
				transCostVolume);
		// store the event in the data store
		db.addEvent(addRouteBusinessEvent);
		// report that the change was successfully made
		return true;
		// System.out.println(debuggingString + debuggingInt + 3);
	};

	// 1
	public boolean processMail(int originSiteID, int destSiteID, double weight, double volume, model.Priority priority,
			int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour() * 10 + now.getMinute();
		// get logged in staff name
		Employee processor = db.getEmployees().getEmployeeFromID(processorStaffID);
		String processorName;
		if (processor == null) {
			// TODO: remove this and replace with an error once log-in function
			// is coded
			processorName = "no-one";// should only be used while there is no
										// proper log-in
		} else {
			processorName = processor.getName();
		}
		// try to access the other referenced objects in the model
		String origin = db.getSiteMap().getSitefromID(originSiteID);
		String destination = db.getSiteMap().getSitefromID(destSiteID);
		// if any of the referenced objects don't exist in the model, the event
		// fails, abort!
		if (origin == null || destination == null) {
			return false;
		}
		Package thepackage = new Package(originSiteID, destSiteID, weight, volume, priority, db.getSiteMap());
		// need to check that a compound route was available - if it wasn't, the
		// event fails! if no route was available, compound route will be null
		if (thepackage.getCompoundRoute() == null) {
			return false;
		}
		// package creation was successful - extract required event information
		double revenue = thepackage.getPriceToCustomer();
		double expenditure = thepackage.getTransportCost();
		double deliveryTime = thepackage.getExpectedTravelTime();

		// make the event to record the action

		BusinessEvent pmBusinessEvent = new MailProcessEvent(day, month, year, time, processorName, origin, destination,
				weight, volume, priority, revenue, expenditure, deliveryTime);
		// store the event in the data store
		db.addEvent(pmBusinessEvent);
		// indicate that the event was processed successfully
		return true;
	}

	public boolean disconRoute(int routeID, int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour() * 10 + now.getMinute();
		// get logged in staff name
		Employee processor = db.getEmployees().getEmployeeFromID(processorStaffID);
		String processorName;
		if (processor == null) {
			// TODO: remove this and replace with an error once log-in function
			// is coded
			processorName = "no-one";// should only be used while there is no
										// proper log-in
		} else {
			processorName = processor.getName();
		}

		// make the changes to the siteMap (discontinue route)
		// if discontinueRoute returns false, then abort the event procession
		// and
		// report failure
		if (!db.getSiteMap().discontinueRoute(routeID)) {
			return false;
		}
		// get the data needed to fill out the event
		Route route = db.getSiteMap().getRouteFromID(routeID);
		String origin = route.getOrigin();
		String destination = route.getDestination();
		String company = route.getCompany();
		Type type = route.getType();
		// make the event to record the action

		BusinessEvent drBusinessEvent = new RouteDiscEvent(day, month, year, time, processorName, origin, destination,
				company, type);
		// store the event in the data store
		db.addEvent(drBusinessEvent);
		// indicate that the event was processed successfully
		return true;
		// System.out.println(debuggingString + debuggingInt + 5);
	}

	public static void pushEvent() {
		// System.out.println("BEP.pushEvent not yet implemented");
	}

	public static void createEvent() {
		// System.out.println("BEP.createEvent not yet implemented");
	}
}
