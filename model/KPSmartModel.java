package model;

import storage.DataStore;
import model.EventProcessor;
import model.map.SiteMap;
import model.Package;
/**
 * 
 * A single instance of the model class is created by the controller class
 * This class makes an instance of the DataStore class, which reads in all the other data
 * required to populate the model with historical data - including the SiteMap.
 * 
 * All direct communication with the model from the controller happens via methods
 * in this class.  The methods in this class action methods in other parts of the model.
 * 
 * @author Nic, Bonnie, Joely
 *
 */
public class KPSmartModel {
	private SiteMap sitemap;//holds all the routes and sites and the interrelation between the two
	//this is a link, for convenience, to the SiteMap stored in the DataStore.
	private DataStore db;//holds links to all the permanent data, and handles the read and write to file.
	private EventProcessor eventProcessor;//manages the production of business event records
	private int loggedInStaffID;//currently logged in staff member id

	public KPSmartModel() {
		db = new DataStore();
		sitemap = db.getSiteMap();
		eventProcessor = new EventProcessor(db);
		loggedInStaffID = -1;//no-one is logged in initially
	}

	public void save() {
		db.save();
	}

	// called from controller class, returns a boolean to indicate success (true) or failure (false)
	public boolean changeCustomerPrice(String origin, String destination, String carrier, model.map.Type type,
			double newWeightCost, double newVolumeCost) {
		// identify which route
		int routeID = sitemap.findRouteID(origin, destination, carrier, type);
		// call event processor on that route, event processor returns true if event was performed successfully
		// returns false if the event was aborted
		return eventProcessor.changeCustomerPrice(routeID, newWeightCost, newVolumeCost, this.loggedInStaffID);
	}

	// called from controller class - mc
	public void changeTransportPrice(String origin, String destination, String carrier, model.map.Type type,
			double newWeightCost, double newVolumeCost) {
		// identify which route
		int routeID = sitemap.findRouteID(origin, destination, carrier, type);
		// call event processor on that route
		eventProcessor.changeTransportCost(routeID, newWeightCost, newVolumeCost, this.loggedInStaffID);
	}

	// called from controller class - mc
	public boolean processMail(String origin, String destination,
			String weightString, String volumeString, String priorityString) {
		//work out the origin ID
		int originSiteID = this.sitemap.getSiteIDfromLocation(origin);
		//work out the destination ID
		int destSiteID = this.sitemap.getSiteIDfromLocation(destination);
		//convert weight string into a double
		double weight = Double.parseDouble(weightString);
		//convert volume string into a double
		double volume = Double.parseDouble(volumeString);
		//convert prority string into a priority type
		Priority priority = getPriority(priorityString);


		//call event processor to make up the package
		return eventProcessor.processMail(originSiteID, destSiteID, weight, volume, priority, this.loggedInStaffID);

	}

	private Priority getPriority(String priorityString) {
		switch(priorityString){
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

	// called from controller class - mc
	public void addRoute(String originSelection, String destinationSelection, String companySelection,
			String typeSelection, String newWeightCostSelection, String newVolumeSelection, String departureDay,
			String frequencySelection, String durationSelection) {
		// TODO Auto-generated method stub

	}

	// called from controller class - mc
	public void removeRoute(String originSelection, String destinationSelection, String companySelection,
			String typeSelection) {
		// TODO Auto-generated method stub

	}

}
