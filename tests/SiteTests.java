package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.map.Site;

/*
 * Site should be able to be created, information retrieved via getters, print to string
 * should be able to set isOrigin or isDestination to true (in use elsewher one should be false on creation)
 */
public class SiteTests {
	Site testSite;
    @Before public void initialize() {
       testSite= new Site(1,"Wellington", false, false);
    }
	
	// test creation success
	@Test
	public void testCreate() {
		int siteID = 0;
		String location = "Wellington";
		Site goodSite = new Site(siteID, location, true, false);
	}

	// test creation fail scenarios
	@Test(expected = Exception.class)
	public void testNullString() throws Exception {
		int siteID = 0;
		String location = null;// should fail if string null
		Site badSite = new Site(siteID, location, true, false);
	}

	@Test(expected = Exception.class)
	public void testEmptyString() throws Exception {
		int siteID = 0;
		String location = "";// should fail if empty string
		Site badSite = new Site(siteID, location,true, false);
	} 

	@Test(expected = Exception.class)
	public void testBadID() throws Exception {
		int siteID = -1;// ID should only be a positive integer
		String location = "Wellington";
		Site badSite = new Site(siteID, location, true, false);
	}
	// getters too simple to bother testing

	// test toString
	@Test
	public void testToString() {
	String correct = "ID: 0\nLocation: Wellington";
	assertTrue(testSite.toString().equals(correct));
	}

	// test setIsOrigin
	public void testSetIsOrigin(){
		assertTrue(testSite.isOrigin() == false);
		testSite.setIsOrigin();
		assertTrue(testSite.isOrigin() == true);
	} 
	
	// test setIsDestination
		public void testSetIsDestination(){
			assertTrue(testSite.isDestination() == false);
			testSite.setIsDestination();
			assertTrue(testSite.isDestination() == true);
		} 
		
	
	
	//later - integration tests to make sure don't give 2
	//sites the same name! (creation should check map class)
	
}
