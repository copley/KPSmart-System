package model;

import java.time.LocalDateTime;

import model.events.BusinessEvent;
import model.events.CustPriceChangeEvent;
import model.events.MailProcessEvent;
import model.events.RouteAdditionEvent;
import model.events.RouteDiscEvent;
import model.events.TransportCostChangeEvent;
import storage.DataStore;

/**
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
	public void changeCustomerPrice(int routeID, double newCustPriceWeight, double newCustPriceVolume, int processorStaffID) {
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour()*10 + now.getMinute();
		String employee = db.getEmployees().getEmployeeFromID(processorStaffID).getName();
		String origin; //need something here
		String destination; //need something here
		Priority priority;	//need something here
		BusinessEvent ccpBusinessEvent = new CustPriceChangeEvent(day, month, year, time, employee, origin, destination,
				priority, newCustPriceWeight, newCustPriceVolume);
		db.addEvent(ccpBusinessEvent);
		
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
	public void processMail(int originSiteID, int destSiteID, double weight, double volume, model.Priority priority, int processorStaffID) {
		Package thepackage = new Package(originSiteID, destSiteID, weight, volume, priority, db.getSiteMap());
		LocalDateTime now = LocalDateTime.now();
		int day = now.getDayOfMonth();
		int month = now.getMonthValue();
		int year = now.getYear();
		int time = now.getHour()*10 + now.getMinute();
		String employee = db.getEmployees().getEmployeeFromID(processorStaffID).getName();
		String origin = db.getSiteMap().getSitefromID(originSiteID);
		String destination =  db.getSiteMap().getSitefromID(destSiteID);
		double revenue = thepackage.getPriceToCustomer();
		double expenditure = thepackage.getTransportCost();
		double deliveryTime = thepackage.getExpectedTravelTime();
		BusinessEvent pmBusinessEvent = new MailProcessEvent(day, month, year, time, employee, origin, destination,
				weight, volume, priority, revenue, expenditure, deliveryTime);
		db.addEvent(pmBusinessEvent);
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
