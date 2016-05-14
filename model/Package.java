package model;

import java.util.List;

import model.map.Route;
import model.map.SiteMap;

public class Package {
	private int originSiteID;
	private int destSiteID;
	private double price;
	private double weight;
	private double volume;
	private Priority priority;
	private List<Integer> compoundRoute;
	private int expectedTravelTime;
	
	private SiteMap sitemap;
	
	public Package(int originSiteID, int destSiteID, double price, double weight, double volume, Priority priority) {
		super();
		this.originSiteID = originSiteID;
		this.destSiteID = destSiteID;
		this.price = price;
		this.weight = weight;
		this.volume = volume;
		this.priority = priority;
		findCompoundRoute();
		calculateExpectedTravelTime();
	}

	private void findCompoundRoute() {
		//need access to the one SiteMap somehow
		this.compoundRoute = sitemap.findCompoundRoute(originSiteID, destSiteID, priority);
		// TODO work out how to access the sitemap	
	}
	
	private void calculateExpectedTravelTime() {
		int time = 0;
		for (int routeID : compoundRoute){
			time += sitemap.getRouteFromID(routeID).getDuration();
		}
		this.expectedTravelTime = time;
	}
	
	public String printCompoundRoute(){
		String routeString = "";
		for (int routeID : compoundRoute){
			Route route = sitemap.getRouteFromID(routeID);
			String nextComponent = String.format("%s to %s: Company - %s, Class - %s%n", route.getOrigin(),route.getDestination(),route.getCompany(), route.getClass());
			routeString = routeString + nextComponent;
		}
		return routeString;
	}
	
	public int getExpectedTravelTime(){
		return expectedTravelTime;
	}
}
