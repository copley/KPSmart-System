package tests;

import static org.junit.Assert.*;
import model.exceptions.IllegalRouteException;

import java.util.List;

import org.junit.Test;

import model.map.Priority;
import model.map.Route;
import model.map.SiteMap;
import model.map.Type;

public class SiteMapTests {

	@Test
	public void testUpdateCustomerPrices() {
		SiteMap test = makeTestMap();
		test.updateCustomerPrices("Wellington", "Auckland", Priority.DOMESTIC_AIR, 2, 3);
		assertTrue("customer price should be updated to 2 (weight)", test.getRouteFromID(1).getCustPriceWeight() == 2);
		assertTrue("customer price should be updated to 3 (volume)", test.getRouteFromID(1).getCustPriceVolume() == 3);
	}

	@Test
	public void testUpdateTransportCost() {
		SiteMap test = makeTestMap();
		test.updateTransportCost(1, 2, 3, 1);   
		assertTrue("transport cost (weight) should be updated to 2", test.getRouteFromID(1).getTransPriceWeight() == 2);
		assertTrue("transport cost (volume) should be updated to 3", test.getRouteFromID(1).getTransPriceVolume() == 3);
		}

	@Test
	public void testDiscontinueRoute() {
		SiteMap test = makeTestMap();
		
		assertTrue("Route 1 should be discontinuable", test.discontinueRoute(1));
		assertFalse("Route 2 does not exist, should not be discontinuable", test.discontinueRoute(2));
		assertFalse("Route 1 should be discontinued", test.getRouteFromID(1).isInService());
	}

	@Test
	public void testAddNewRoute() {
		SiteMap test = makeTestMap();
		assertTrue("Route should not yet exist", test.findRouteID("Wellington", "Nelson", "test", Type.LAND)== -1 );
		
		test.addNewRoute("Wellington", "Nelson", "test", Type.LAND, 1, 1, 1, 1,1);
		assertTrue("Route should now exist", test.findRouteID("Wellington", "Nelson", "test", Type.LAND)!= -1 );
		assertTrue("Nelson site should now exist", test.getSiteIDfromLocation("Nelson") != -1 );
		//try out failure scenarios
		assertFalse("duration must be positive", test.addNewRoute("Wellington", "Nelson", "test", Type.LAND, -1, 1, 1, 1,1));
		assertFalse("custWeightPrice must be positive", test.addNewRoute("Wellington", "Nelson", "test", Type.LAND, 1, -1, 1, 1,1));
		assertFalse("custVolPrice must be positive", test.addNewRoute("Wellington", "Nelson", "test", Type.LAND, 1, 1, -1, 1,1));
		assertFalse("transWeightCost must be positive", test.addNewRoute("Wellington", "Nelson", "test", Type.LAND, 1, 1, 1, -1,1));
		assertFalse("transVolCost must be positive", test.addNewRoute("Wellington", "Nelson", "test", Type.LAND, 1, 1, 1, 1,-1));
		assertFalse("origin can't be null", test.addNewRoute(null, "Nelson", "test", Type.LAND, 1, 1, 1, 1,1));
		assertFalse("origin can't be empty string", test.addNewRoute("", "Nelson", "test", Type.LAND, 1, 1, 1, 1,1));
		assertFalse("destination can't be null", test.addNewRoute("Wellington", null, "test", Type.LAND, 1, 1, 1, 1,1));
		assertFalse("destination can't be empty string", test.addNewRoute("Wellington", "", "test", Type.LAND, 1, 1, 1, 1,1));
		assertFalse("company can't be null", test.addNewRoute("Wellington", "Nelson", null, Type.LAND, 1, 1, 1, 1,1));
		assertFalse("company can't be empty string", test.addNewRoute("Wellington","Nelson", "", Type.LAND, 1, 1, 1, 1,1));
		assertFalse("origin and destination can't be the same", test.addNewRoute("Nelson", "Nelson", "test", Type.LAND, 1, 1, 1, 1,1));
		assertFalse("origin has to be a valid NZ town", test.addNewRoute("NicLand", "Nelson", "test", Type.LAND, 1, 1, 1, 1,1));
		assertFalse("can't add a route that already exists", test.addNewRoute("Wellington", "Nelson", "test", Type.LAND, 1, 1, 1, 1,1));
		}

	//positive addRoute is covered by addNewRoute.... need to test attempt to add a bad route separately
	@Test(expected = IllegalRouteException.class)  
	public void testAddBadRoute() throws IllegalRouteException {
		SiteMap test = makeTestMap();
		test.addRoute(new Route(0, null, null, null, 0, null, false, 0, 0, 0, 0));
		}

	@Test
	public void testFindCompoundRoute() {
		SiteMap test = makeTestMap();
		test.addNewRoute("Auckland", "Nelson", "test", Type.LAND, 1, 1, 1, 1,1);
		//should be able to find an "air" route wellington to nelson with 2 components
		//should not be able to find a standard route wellington to nelson
		List<Integer> airRoute = test.findCompoundRoute(test.getSiteIDfromLocation("Wellington"), test.getSiteIDfromLocation("Nelson"), Priority.DOMESTIC_AIR);
		List<Integer> notAirRoute = test.findCompoundRoute(test.getSiteIDfromLocation("Wellington"), test.getSiteIDfromLocation("Nelson"), Priority.DOMESTIC_STANDARD);
		assertNull("There should not be a standard compound route from Wellington to Nelson", 
				notAirRoute);
		assertTrue("There should be an air priority compound route from Wellington to Nelson, with 2 segments", 
				airRoute.size()==2);
		}

	@Test
	public void testGetSiteIDfromLocation() {
		SiteMap test = makeTestMap();
		//Wellington should exist, and return a positive number
		assertTrue("Wellington should exist and return a positive number", 
				test.getSiteIDfromLocation("Wellington")!= -1);
		//Auckland should also exist and return a different positive number to Wellington!
		assertTrue("Auckland and wellington should exist and return different positive numbers", 
				test.getSiteIDfromLocation("Wellington")!= -1 &&
				test.getSiteIDfromLocation("Auckland")!= -1 &&
				test.getSiteIDfromLocation("Wellington")!= test.getSiteIDfromLocation("Auckland")		
				);		
		//Nelson should not exist, and return -1 
		assertTrue("Nelson should not exist, and return -1", 
				test.getSiteIDfromLocation("Nelson")== -1);
		}

	@Test
	public void testGetRouteFromID() {
		SiteMap test = makeTestMap();
		//should be only one route, with id 1, from wellingotn to auckland by air
		Route route1 = test.getRouteFromID(1);
		assertTrue("should be able to retrieve route 1, with origin Wellington and destination Auckland and type Air", 
				route1 != null && route1.getOrigin().equals("Wellington") &&
				route1.getDestination().equals("Auckland") &&
				route1.getType().equals(Type.AIR));
		assertNull("Route 2 should not exist, retrieval should be null", 
				test.getRouteFromID(2));
	}

	@Test
	public void testFindRouteID() {
		SiteMap test = makeTestMap();
		assertTrue("AIR Route Wellington to Auckland should exist and return a positive number", 
				test.findRouteID("Wellington", "Auckland", "test", Type.AIR)!= -1);
		assertTrue("AIR Route Timbuktu to Berlin should not exist and return -1", 
				test.findRouteID("Timbuktu", "Berlin", "test", Type.AIR)== -1);
		}

	@Test
	public void testGetSiteNamefromID() {
		SiteMap test = makeTestMap();
		//Wellington should exist, have a number and be able to be retrieved!
		assertTrue("Wellington should exist and name be able to be retrieved", 
				test.getSiteNamefromID(test.getSiteIDfromLocation("Wellington")).equals("Wellington"));
		assertFalse("Wellington should exist and name be able to be retrieved", 
				test.getSiteNamefromID(test.getSiteIDfromLocation("Wellington")).equals("Auckland"));
		}
//===helper methods==========
	
	private SiteMap makeTestMap(){
		SiteMap newMap = new SiteMap();
		//add in a test route, will make 2 sites as well!
		newMap.addNewRoute("Wellington", "Auckland", "test", Type.AIR, 1, 1, 1, 1, 1);
		return newMap;
	}
}
