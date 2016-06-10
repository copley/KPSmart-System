package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.map.Route;
import model.map.Type;

/*
 * Route should be able to be created, information retrieved via getters, print to string
 * customer prices should be changeable
 * transport costs should be changeable
 * inService status should be changeable
 * duration,company,ID, origin and destination should be fixed (changed to these are
 * enacted by new route creation)s
 */
public class RouteTests {

	// test creation
	@Test
	public void testCreateSuccess() {
		// test route creation positive
		int routeID = 0;
		String origin = "Wellington";
		String destination = "Auckland";

		String company = "Bogus";
		int duration = 10;
		Type mode = Type.AIR;
		double custWeightPrice = 2.30;
		double custVolPrice = 3.00;
		double transWeightPrice = 1;
		double transVolPrice = 35.99;
		boolean inService = true;

		Route newRoute = new Route(routeID, origin, destination, company, duration, mode, inService, custWeightPrice,
				custVolPrice, transWeightPrice, transVolPrice);

	}

	// test updateCustomerPrice
	@Test
	public void testUpdateCustomerPrice() {
		Route testRoute = new Route(1, "Wellington", "Auckland", "testCompany", 1, Type.AIR, true, 1, 1, 1, 1);
		testRoute.updateCustomerPrices(2, 3);
		assertTrue("customer prices should be updated to 2 (weight)", testRoute.getCustPriceWeight() == 2);
		assertTrue("customer prices should be updated to 3 (volume)", testRoute.getCustPriceVolume() == 3);
	}

	// test updateTransportCost
	@Test
	public void testUpdateTransportCost() {
		Route testRoute = new Route(1, "Wellington", "Auckland", "testCompany", 1, Type.AIR, true, 1, 1, 1, 1);
		testRoute.updateTransportCosts(2, 3, 1);
		assertTrue("transport cost (weight) should be updated to 2", testRoute.getTransPriceWeight() == 2);
		assertTrue("transport cost (volume) should be updated to 3", testRoute.getTransPriceVolume() == 3);
	}
	
	// test toString
	@Test
	public void testToString() {
		Route testRoute = new Route(1, "Wellington", "Auckland", "testCompany", 1, Type.AIR, true, 1, 1, 1, 1);

		String shouldBe = "ID: 1 " + "Origin: Wellington " + "Destination: Auckland " + "Company: testCompany<br>"
				+ "Duration: 1.0 " + "Type: AIR " + "In service: true ";
		String testValue = testRoute.toString();
		System.out.println(shouldBe);
		System.out.println(testValue);
		assertTrue("Strings should be equal", testValue.equals(shouldBe));
	}

}
