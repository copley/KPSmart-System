package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.map.DijkstraNode;
import model.map.Priority;
import model.map.Site;
import model.map.SiteMap;
import model.map.Type;

public class DijkstraTests {
	SiteMap testMap = makeTestMap();

	@Test
	public void testAirDirect() {
		List<Integer> correct = new LinkedList<Integer>();
		correct.add(3);
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("Auckland"),
				testMap.getSiteIDfromLocation("Christchurch"), Priority.DOMESTIC_AIR);
		assertTrue("The compound route should be direct from Auckland to Christchurch", compoundRoute.equals(correct));
	}

	@Test
	public void testNoAirDirect() {
		// should end up having to use the non-air route as the air route is
		// discontinued
		List<Integer> correct = new LinkedList<Integer>();
		correct.add(2);
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("Auckland"),
				testMap.getSiteIDfromLocation("Bluff"), Priority.DOMESTIC_AIR);
		assertTrue("The compound route should be land from Auckland to Bluff", compoundRoute.equals(correct));
	}

	@Test
	public void test2Airs() {
		// should end up finding 2 air routes one after the other
		List<Integer> correct = new LinkedList<Integer>();
		correct.add(3);
		correct.add(5);
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("Auckland"),
				testMap.getSiteIDfromLocation("Dunedin"), Priority.DOMESTIC_AIR);
		if(compoundRoute == null){
			System.out.println("test2airs was null!");
		}
		assertTrue("The compound route should be air Auckland to Christchurch to Dundedin", compoundRoute.equals(correct));
	}

	@Test
	public void test2Standards() {
		// should end up finding 2 standard routes one after the other
		List<Integer> correct = new LinkedList<Integer>();
		correct.add(2);
		correct.add(4);
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("Auckland"),
				testMap.getSiteIDfromLocation("Christchurch"), Priority.DOMESTIC_STANDARD);
		assertTrue("The compound route should be road Auckland to Bluff then ship to Christchurch", compoundRoute.equals(correct));
	}

	@Test
	public void testNoStandard() {
		// should end up not finding a standard route
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("Auckland"),
				testMap.getSiteIDfromLocation("Dunedin"), Priority.DOMESTIC_STANDARD);
		assertTrue("The compound route should be null", compoundRoute == null);
	}
	
	@Test
	public void testNodeToString() {
		// should be able to convert to string!
		DijkstraNode node = new DijkstraNode(1,1,2,120);
		String shouldBe = "siteID: 1\n"+"previousSiteID: 1\n"+
		"durationFromStart: 120.0\n"+"previousRouteID: 2\n";
		assertTrue("Strings should be equal", node.toString().equals(shouldBe));
	}
	
	@Test
	public void testNodeCompare() {
		DijkstraNode nodeSmaller = new DijkstraNode(1,1,2,100);
		DijkstraNode nodeBigger = new DijkstraNode(1,1,2,200);
		assertTrue("nodeSmaller.compareTo(nodeBigger) should return -1", 
			nodeSmaller.compareTo(nodeBigger) == -1);
		assertTrue("nodeBigger.compareTo(nodeSmaller) should return 1", 
				nodeBigger.compareTo(nodeSmaller) == 1);
		assertTrue("nodeSmaller.compareTo(nodeSmaller) should return 0", 
				nodeSmaller.compareTo(nodeSmaller) == 0);
	}
	
	//==========helper methods

	private SiteMap makeTestMap() {
		// make a new site map:
		SiteMap siteMap = new SiteMap();

		// make the routes we need
		// route 1
		siteMap.addNewRoute("Auckland", "Bluff", "Air Dingus", Type.AIR, 20, 100, 100, 100, 100);
		siteMap.discontinueRoute(1);
		// route 2
		siteMap.addNewRoute("Auckland", "Bluff", "Trucky McTruck", Type.LAND, 120, 10, 10, 10, 10);
		// route 3
		siteMap.addNewRoute("Auckland", "Christchurch", "Air Dingus", Type.AIR, 20,100, 100, 100, 100);
		// route 4
		siteMap.addNewRoute("Bluff", "Christchurch", "HMS BoatFace", Type.SEA, 240, 1, 1, 1, 1);
		// route 5
		siteMap.addNewRoute("Christchurch", "Dunedin", "Air Dingus", Type.AIR, 20, 100, 100, 100, 100);
		
//		System.out.println("Test Map Routes:");
//		System.out.println(siteMap.getRoutes().toString());
//		System.out.println("Test Map Sites: ");
//		System.out.println(siteMap.getSites().toString());
//		System.out.println("Test Map Site to Routes entries: ");
//		for (Site site : siteMap.getSites()) {
//			System.out.println(site.getLocation() + " routes on: " + siteMap.getRoutesOn(site.getID()));
//		}

		return siteMap;
	}
}
