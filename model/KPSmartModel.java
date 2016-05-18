package model;

import storage.DataStore;
import model.EventProcessor;
import model.map.SiteMap;
import model.Package;

public class KPSmartModel {
	private SiteMap sitemap;
	private DataStore db;
	private EventProcessor eventProcessor;
	private int loggedInStaffID;

	public KPSmartModel() {
		db = new DataStore();
		sitemap = db.getSiteMap();
		eventProcessor = new EventProcessor(db);
		loggedInStaffID = -1;//no-one is logged in
	}

	public void save() {
		db.save();
	}

	// called from controller class - mc
	public void ChangeCustomerPrice(String origin, String destination, String carrier, model.map.Type type,
			double newWeightCost, double newVolumeCost) {
		// identify which route
		int routeID = sitemap.findRouteID(origin, destination, carrier, type);
		// call event processor on that route
		eventProcessor.changeCustomerPrice(routeID, newWeightCost, newVolumeCost, this.loggedInStaffID);
	}

	// called from controller class - mc
	public void ChangeTransportPrice(String origin, String destination, String carrier, model.map.Type type,
			double newWeightCost, double newVolumeCost) {
		// identify which route
		int routeID = sitemap.findRouteID(origin, destination, carrier, type);
		// call event processor on that route
		eventProcessor.changeTransportCost(routeID, newWeightCost, newVolumeCost, this.loggedInStaffID);
	}
	
	// called from controller class - mc
	public void ProcessMail(String origin, String destination, 
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
		model.Priority priority = model.Priority.valueOf(priorityString);
		
		
		//call event processor to make up the package
		eventProcessor.processMail(originSiteID, destSiteID, weight, volume, priority, this.loggedInStaffID); 
					
	}

	// called from controller class - mc
	public void AddRoute(String originSelection, String destinationSelection, String companySelection,
			String typeSelection, String newWeightCostSelection, String newVolumeSelection, String departureDay,
			String frequencySelection, String durationSelection) {
		// TODO Auto-generated method stub

	}

	// called from controller class - mc
	public void RemoveRoute(String originSelection, String destinationSelection, String companySelection,
			String typeSelection) {
		// TODO Auto-generated method stub

	}

}
