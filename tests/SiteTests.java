package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.map.Site;

/*
 * Site should be able to be created, information retrieved via getters, print to string
 * should be able to set isOrigin or isDestination to true 
 * (in use elsewhere one should be false on creation)
 */
public class SiteTests {
	Site testSite;
    @Before public void initialize() {
       testSite= new Site(1,"Wellington", false, false);
    }
	
	// test creation success
	@Test
	public void testSuccessfulCreate() {
		int siteID = 0;
		String location = "Wellington";
		Site goodSite = new Site(siteID, location, true, false);
	}


	// test toString
	@Test
	public void testToString() {
	String correct = "ID: 1\nLocation: Wellington";
	assertTrue(testSite.toString().equals(correct));
	}

	// test setIsOrigin
	@Test
	public void testSetIsOriginSuccess(){
		assertTrue(testSite.isOrigin() == false);
		testSite.setIsOrigin();
		assertTrue(testSite.isOrigin() == true);
	} 
	
	// test setIsDestination
	@Test
	public void testSetIsDestinationSuccess(){
			assertTrue(testSite.isDestination() == false);
			testSite.setIsDestination();
			assertTrue(testSite.isDestination() == true);
		} 
		
	
	
	//later - integration tests to make sure don't give 2
	//sites the same name! (creation should check map class)
	
}
