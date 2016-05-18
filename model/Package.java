package model;

import java.util.List;

import model.map.Route;
import model.map.SiteMap;
import model.map.Type;

public class Package {
	//information given at creation
	private final int originSiteID;
	private final int destSiteID;
	private final double weight;
	private final double volume;
	private final Priority priority;
	
	//computed information
	private final List<Integer> compoundRoute;
	private final int expectedTravelTime;
	private final double priceToCustomer;
	private final double transportCost;
	
	private SiteMap sitemap;
	
	public Package(int originSiteID, int destSiteID, double weight, double volume, Priority priority, SiteMap sitemap) {
		super();
		this.originSiteID = originSiteID;
		this.destSiteID = destSiteID;
		this.weight = weight;
		this.volume = volume;
		this.priority = priority;
		this.sitemap = sitemap;
		this.compoundRoute = findCompoundRoute();
		this.priceToCustomer = calculatePriceToCustomer();
		this.transportCost = calculateTransportCost();
		this.expectedTravelTime = calculateExpectedTravelTime();
	}

	
	private List<Integer> findCompoundRoute() {
		return sitemap.findCompoundRoute(originSiteID, destSiteID, priority);
	}
	
	private double calculatePriceToCustomer() {
		//find the price for each individual component, and then add them up!
		double total = 0;
		for(int routeID : this.compoundRoute){
			Route route = sitemap.getRouteFromID(routeID);
			if(route.getType().equals(Type.AIR)){
				total += this.weight*route.getCustPriceWeight();
			}
			else{
				total += this.volume*route.getCustPriceVolume();
			}
		}
		return total;
	}

	private double calculateTransportCost() {
		//find the cost for each individual component, and then add them up!
		double total = 0;
		for(int routeID : this.compoundRoute){
			Route route = sitemap.getRouteFromID(routeID);
			if(route.getType().equals(Type.AIR)){
				total += this.weight*route.getTransPriceWeight();
			}
			else{
				total += this.volume*route.getTransPriceVolume();
			}
		}
		return total;
	}
	
	private int calculateExpectedTravelTime() {
		int time = 0;
		for (int routeID : compoundRoute){
			time += sitemap.getRouteFromID(routeID).getDuration();
		}
		return time;
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
	
	//==============getters==================
	public int getExpectedTravelTime(){
		return expectedTravelTime;
	}


	public int getOriginSiteID() {
		return originSiteID;
	}


	public int getDestSiteID() {
		return destSiteID;
	}


	public double getWeight() {
		return weight;
	}


	public double getVolume() {
		return volume;
	}


	public Priority getPriority() {
		return priority;
	}


	public List<Integer> getCompoundRoute() {
		return compoundRoute;
	}


	public double getPriceToCustomer() {
		return priceToCustomer;
	}


	public double getTransportCost() {
		return transportCost;
	}
	
	
}
