package model;
	/**
	 * Implement Class Methods.
	 *
	 * If this current implementation does not work,
	 * We are always able to copy functionality methods INSIDE EventProcessor into the KPSmartModel
	 *
	 *
	 */
public class EventProcessor {

	public static String debuggingString = "Class EventProcessor";
	public static int debuggingInt = 0;

	public EventProcessor(){

	}

	public void changePrice() {
		System.out.println(debuggingString + debuggingInt + 3);
	}

	public void changeRoute(int routeID, String updatedCarrier, double updatedDuration, double updatedCustPriceWeight, double custPriceVolume,
			double transPriceWeight, double transPriceVolume) {
		System.out.println(debuggingString + debuggingInt + 1);
	};

	public void addRoute(int toSiteID, int fromSiteID, String carrier, double duration, double custPriceWeight, double custPriceVolume,
			double transPriceWeight, double transPriceVolume) {
		System.out.println(debuggingString + debuggingInt + 2);
	};

	//is this different from changeRoute?? should we separate all change types out?
	//should this cover customer price, transport price.. both?

	//1
	public void processMail(int originSiteID, int destSiteID, double price, double weight, double volume, int priority) {
		System.out.println(debuggingString + debuggingInt + 4);
	}

	public static void disconRoute(int routeID) {
		System.out.println(debuggingString + debuggingInt + 5);
	}







	public static void pushEvent() {
		//System.out.println("BEP.pushEvent not yet implemented");
	}

	public static void createEvent() {
		//System.out.println("BEP.createEvent not yet implemented");
	}
}
