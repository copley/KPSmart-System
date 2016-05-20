package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Package;
import model.Priority;
import model.map.Site;
import model.map.SiteMap;
import model.map.Type;

/*
 * package needs to be able to be constructed and printed to string
 */
public class PackageTests {

	SiteMap sitemap = makeTestMap();
	
	// test creation success
	@Test
	public void testCreate() {
		int originID = 0;
		int destID = 1;
		double weight = 5.0;//kg
		double volume = 1.0;//cubic metres
		Priority priority = Priority.DOMESTIC_AIR;
		Package goodPackage = new Package(originID, destID, weight, volume, priority, sitemap);
	}

	// test creation fail scenarios
	@Test(expected = Exception.class)
	public void testNegativeOrigID() throws Exception {
		int originID = -1;
		int destID = 1;
		double weight = 5.0;//kg
		double volume = 1.0;//cubic metres
		Priority priority = Priority.DOMESTIC_AIR;
		Package badPackage = new Package(originID, destID, weight, volume, priority, sitemap);
	}
	@Test(expected = Exception.class)
	public void testNegativeDestID() throws Exception {
		int originID = 0;
		int destID = -2;
		double weight = 5.0;//kg
		double volume = 1.0;//cubic metres
		Priority priority = Priority.DOMESTIC_AIR;
		Package badPackage = new Package(originID, destID, weight, volume, priority, sitemap);
	}
	@Test(expected = Exception.class)
	public void testNonPositivePrice() throws Exception {
		int originID = 0;
		int destID = 1;
		double weight = 5.0;//kg
		double volume = 1.0;//cubic metres
		Priority priority = Priority.DOMESTIC_AIR;
		Package badPackage = new Package(originID, destID, weight, volume, priority, sitemap);
	}
	
	@Test(expected = Exception.class)
	public void testNonPositiveWeight() throws Exception {
		int originID = 0;
		int destID = 1;
		double weight = -1.0;//kg
		double volume = 1.0;//cubic metres
		Priority priority = Priority.DOMESTIC_AIR;
		Package badPackage = new Package(originID, destID, weight, volume, priority, sitemap);
	}
	
	@Test(expected = Exception.class)
	public void testNonPositiveVolume() throws Exception {
		int originID = 0;
		int destID = 1;
		double weight = 5.0;//kg
		double volume = 0;//cubic metres
		Priority priority = Priority.DOMESTIC_AIR;
		Package badPackage = new Package(originID, destID, weight, volume, priority, sitemap);
	}
	
	@Test(expected = Exception.class)
	public void testNonSendableVolume() throws Exception {
		int originID = 0;
		int destID = 1;
		double weight = 5.0;//kg
		double volume = .0000009;//cubic metres - ie .9 cubic cm - too small to post
		Priority priority = Priority.DOMESTIC_AIR;
		Package badPackage = new Package(originID, destID, weight, volume, priority, sitemap);
	}
	
	
	// getters too simple to bother testing

	// test toString
	@Test
	public void testToString() {
		Package testPackage = new Package(1, 2, 5.0, .02, Priority.DOMESTIC_AIR, sitemap);
		String correct = "Origin ID: 1\n"
				+ "Destination ID:2\n"
				+ "Weight: 5.0\n"
				+ "Volume: 0.02\n"
				+ "Priority: DOMESTIC_AIR\n";
		assertTrue("testPackage toString should yield: "+correct, testPackage.toString().equals(correct));
	}

	//=======helper methods============
	
	private SiteMap makeTestMap() {
		// make a new site map:
		SiteMap siteMap = new SiteMap();

		// make the routes we need
		// route 0
		siteMap.makeNewRoute("A", "B", "Air Dingus", 20, Type.AIR, 100, 100, 100, 100);
		siteMap.discontinueRoute(0);
		// route 1
		siteMap.makeNewRoute("A", "B", "Trucky McTruck", 120, Type.LAND, 10, 10, 10, 10);
		// route 2
		siteMap.makeNewRoute("A", "C", "Air Dingus", 20, Type.AIR, 100, 100, 100, 100);
		// route 3
		siteMap.makeNewRoute("B", "C", "HMS BoatFace", 240, Type.SEA, 1, 1, 1, 1);
		// route 4
		siteMap.makeNewRoute("C", "D", "Air Dingus", 20, Type.AIR, 100, 100, 100, 100);
		// route 5
		siteMap.makeNewRoute("C", "D", "Trucky McTruck", 120, Type.LAND, 10, 10, 10, 10);
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
