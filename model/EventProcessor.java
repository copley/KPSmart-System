package model;

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

	public EventProcessor() {

	}

	//the only things that can be changed on the route are the prices
	//if changes to the carrier, mode, or duration are needed, the route should be discontinued, 
	//and a new one made
	
	public void changeCustomerPrice(int routeID, double newCustPriceWeight,	double newCustPriceVolume) {
		System.out.println(debuggingString + debuggingInt + 1);
	};
	
	public void changeTransportCost(int routeID, double newTransCostWeight,	double newTransCostVolume) {
		System.out.println(debuggingString + debuggingInt + 2);
	};
	
	public void addRoute(int toSiteID, int fromSiteID, String carrier, double duration, double custPriceWeight,
			double custPriceVolume, double transPriceWeight, double transPriceVolume) {
		System.out.println(debuggingString + debuggingInt + 3);
	};

	// 1
	public void processMail(int originSiteID, int destSiteID, double weight, double volume,
			model.Priority priority) {
		System.out.println(debuggingString + debuggingInt + 4);
	}

	public static void disconRoute(int routeID) {
		System.out.println(debuggingString + debuggingInt + 5);
	}

	public static void pushEvent() {
		// System.out.println("BEP.pushEvent not yet implemented");
	}

	public static void createEvent() {
		// System.out.println("BEP.createEvent not yet implemented");
	}
}
