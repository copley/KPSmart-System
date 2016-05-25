package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.Priority;
import model.map.Site;
import model.map.SiteMap;
import model.map.Type;

public class DijkstraTests {
	SiteMap testMap = makeTestMap();

	@Test
	public void testAirDirect() {
		List<Integer> correct = new LinkedList<Integer>();
		correct.add(2);
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("A"),
				testMap.getSiteIDfromLocation("C"), Priority.DOMESTIC_AIR);
		assertTrue("The compound route should be direct from A to C", compoundRoute.equals(correct));
	}

	@Test
	public void testNoAirDirect() {
		// should end up having to use the non-air route as the air route is
		// discontinued
		List<Integer> correct = new LinkedList<Integer>();
		correct.add(1);
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("A"),
				testMap.getSiteIDfromLocation("B"), Priority.DOMESTIC_AIR);
		assertTrue("The compound route should be land from A to B", compoundRoute.equals(correct));
	}

	@Test
	public void test2Airs() {
		// should end up finding 2 air routes one after the other
		List<Integer> correct = new LinkedList<Integer>();
		correct.add(2);
		correct.add(4);
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("A"),
				testMap.getSiteIDfromLocation("D"), Priority.DOMESTIC_AIR);
		assertTrue("The compound route should be air A to C to D", compoundRoute.equals(correct));
	}

	@Test
	public void test2Standards() {
		// should end up finding 2 standard routes one after the other
		List<Integer> correct = new LinkedList<Integer>();
		correct.add(1);
		correct.add(3);
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("A"),
				testMap.getSiteIDfromLocation("C"), Priority.DOMESTIC_STANDARD);
		assertTrue("The compound route should be road A to B then ship to C", compoundRoute.equals(correct));
	}

	@Test
	public void testNoStandard() {
		// should end up not finding a standard route
		List<Integer> compoundRoute = testMap.findCompoundRoute(testMap.getSiteIDfromLocation("A"),
				testMap.getSiteIDfromLocation("D"), Priority.DOMESTIC_STANDARD);
		assertTrue("The compound route should be null", compoundRoute == null);
	}

	private SiteMap makeTestMap() {
		// make a new site map:
		SiteMap siteMap = new SiteMap();

		// make the routes we need
		// route 0
		siteMap.makeNewRoute("A", "B", "Air Dingus", Type.AIR, 20, 100, 100, 100, 100);
		siteMap.discontinueRoute(0);
		// route 1
		siteMap.makeNewRoute("A", "B", "Trucky McTruck", Type.LAND, 120, 10, 10, 10, 10);
		// route 2
		siteMap.makeNewRoute("A", "C", "Air Dingus", Type.AIR, 20,100, 100, 100, 100);
		// route 3
		siteMap.makeNewRoute("B", "C", "HMS BoatFace", Type.SEA, 240, 1, 1, 1, 1);
		// route 4
		siteMap.makeNewRoute("C", "D", "Air Dingus", Type.AIR, 20, 100, 100, 100, 100);
		// route 5
		siteMap.makeNewRoute("C", "D", "Trucky McTruck", Type.LAND, 120, 10, 10, 10, 10);
		siteMap.discontinueRoute(5);

		System.out.println("Test Map Routes:");
		System.out.println(siteMap.getRoutes().toString());
		System.out.println("Test Map Sites: ");
		System.out.println(siteMap.getSites().toString());
		System.out.println("Test Map Site to Routes entries: ");
		for (Site site : siteMap.getSites()) {
			System.out.println(site.getLocation() + " routes on: " + siteMap.getRoutesOn(site.getID()));
		}

		return siteMap;
	}
}
