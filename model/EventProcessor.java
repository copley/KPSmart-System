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
 * This Class processes events requested by the controller, and directed through the
 * KPSmartModel Class.  Any changes to the SiteMap data that are required by the events
 * are made here.  The appropriate business event object is made as record of each event,
 * and these events are pushed to the DataStore for storage (and retrieval if a request to
 * review events is made from the controller)
 * 
 * @author Nic, Joely
 * 
 * 
 * 
 * Implement Class Methods.
 *
 * If this current implementation does not work, We are always able to copy
 * functionality methods INSIDE EventProcessor into the KPSmartModel
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
	 * @param routeID --> in constructor, there is origin and destination???
	 * @param need a priority for constructor as well?
	 */
	public boolean changeCustomerPrice(int routeID, double newCustPriceWeight, double newCustPriceVolume, int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour()*10 + now.getMinute();
		//try to access the required data
		Employee employee = db.getEmployees().getEmployeeFromID(processorStaffID);
		Route route = db.getSiteMap().getRouteFromID(routeID);
		//if employee or route don't exist, this even fails! do not proceed!
		if(employee == null || route == null){
			return false;
		}
		//if we got here, employee and route are safe to call methods on..
		String employeeName = employee.getName(); 
		String origin = route.getOrigin();
		String destination = route.getDestination();
		String company = route.getCompany();
		Type mode = route.getType();
		//make the changes to the route
		route.updateCustomerPrices(newCustPriceWeight, newCustPriceVolume);
		//make the event to record the action
		BusinessEvent cpcBusinessEvent = new CustPriceChangeEvent(day, month, year, time, employeeName, origin, destination, company,
				mode, newCustPriceWeight, newCustPriceVolume);
		//store the event in the data store
		db.addEvent(cpcBusinessEvent);
		//report that the change was successfully made
		return true;
		
		//System.out.println(debuggingString + debuggingInt + 1);
	};

	public void changeTransportCost(int routeID, double newTransCostWeight, double newTransCostVolume, int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour()*10 + now.getMinute();
		String employee = db.getEmployees().getEmployeeFromID(processorStaffID).getName();
		String origin;
		String destination;
		String company;
		String type;
		String departureDay;
		int frequency;
		int duration;
		BusinessEvent ctcBusinessEvent = new TransportCostChangeEvent(day, month, year, time, employee, origin, destination,
				company, type, newTransCostWeight, newTransCostVolume, departureDay, frequency, duration);
		db.addEvent(ctcBusinessEvent);
		//System.out.println(debuggingString + debuggingInt + 2);
	};

	/**
	 * 
	 * @param toSiteID
	 * @param fromSiteID
	 * @param carrier
	 * @param duration
	 * @param custPriceWeight
	 * @param custPriceVolume
	 * @param transPriceWeight ??? Constructor does not have this field
	 * @param transPriceVolume ??? Constructor does not have this field
	 * @param processorStaffID
	 */
	public void addRoute(int toSiteID, int fromSiteID, String carrier, double duration, double custPriceWeight,
			double custPriceVolume, double transPriceWeight, double transPriceVolume, int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour()*10 + now.getMinute();
		String employee = db.getEmployees().getEmployeeFromID(processorStaffID).getName();
		String origin= db.getSiteMap().getSitefromID(fromSiteID);;
		String destination = db.getSiteMap().getSitefromID(toSiteID);;
		String company = carrier;
		String type;
		String departureDay;
		int frequency;
		BusinessEvent arBusinessEvent = new RouteAdditionEvent(day, month, year, time, employee, origin, destination,
				company, type, custPriceWeight, custPriceVolume, departureDay, frequency, duration);
		db.addEvent(arBusinessEvent);
		
		//System.out.println(debuggingString + debuggingInt + 3);
	};

	// 1
	public boolean processMail(int originSiteID, int destSiteID, double weight, double volume, model.Priority priority, int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour()*10 + now.getMinute();
		//try to access the referenced objects in the model
		Employee employee = db.getEmployees().getEmployeeFromID(processorStaffID);
		String origin = db.getSiteMap().getSitefromID(originSiteID);
		String destination =  db.getSiteMap().getSitefromID(destSiteID);
		//if any of the referenced objects don't exist in the model, the event fails, abort!
		if(employee == null || origin == null || destination == null){
			return false;
		}
		Package thepackage = new Package(originSiteID, destSiteID, weight, volume, priority, db.getSiteMap());
		//need to check that a compound route was available - if it wasn't, the event fails!
		//if no route was available, compound route will be null
		if(thepackage.getCompoundRoute() == null){
			return false;
		}		
		//if we get to here, it is safe to call methods on employee, origin and destination,
		//and also the delivery is possible!
		String employeeName = employee.getName();
		double revenue = thepackage.getPriceToCustomer();
		double expenditure = thepackage.getTransportCost();
		double deliveryTime = thepackage.getExpectedTravelTime();
		BusinessEvent pmBusinessEvent = new MailProcessEvent(day, month, year, time, employeeName, origin, destination,
				weight, volume, priority, revenue, expenditure, deliveryTime);
		db.addEvent(pmBusinessEvent);
		//indicate that the event was processed successfully
		return true;
	}
	
	public static void disconRoute(int routeID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour()*10 + now.getMinute();
		String employee = db.getEmployees().getEmployeeFromID(processorStaffID).getName();
		String origin;
		String destination;
		String company;
		String type;
		BusinessEvent drBusinessEvent = new RouteDiscEvent(day, month, year, time, employee, origin, destination,
				company, type);
		db.addEvent(drBusinessEvent);
		//System.out.println(debuggingString + debuggingInt + 5);
	}

	public static void pushEvent() {
		// System.out.println("BEP.pushEvent not yet implemented");
	}

	public static void createEvent() {
		// System.out.println("BEP.createEvent not yet implemented");
	}
}
