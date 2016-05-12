package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Route;

/*
 * Route should be able to be created, information retrieved via getters, print to string
 * customer prices should be changeable
 * transport costs should be changeable
 * inService status should be changeable
 * duration,company,ID, origin and destination should be fixed (changed to these are 
 * enacted by new route creation)s  
 */
public class RouteTests {
	//test creation
	@Test
	public void testCreate() {
		//test route creation positive
		int routeID = 0;
		int originID = 1;
		int destnID = 2;

		String company = "Bogus";
		int duration = 10;
		double custWeightPrice = 2.30;
		double custVolPrice = 3.00;
		double transWeightPrice = 1;
		double transVolPrice =35.99;
		boolean inService = true;

		Route newRoute = new Route(routeID, destnID, originID, company,	duration, inService, 
				custWeightPrice, custVolPrice, transWeightPrice, transVolPrice);
}

	//test route creation fail scenarios
	@Test(expected=Exception.class)
	public void testBadClassNUllCompany() throws Exception {
				int routeID = 0;
				int originID = 1;
				int destnID = 2;

				String company = null;
				int duration = 10;
				double custWeightPrice = 2.30;
				double custVolPrice = 3.00;
				double transWeightPrice = 1;
				double transVolPrice =35.99;
				boolean inService = true;

				Route newRoute = new Route(routeID, destnID, originID, company,	duration, inService, 
						custWeightPrice, custVolPrice, transWeightPrice, transVolPrice);
	}

	//test changeCustomerPrice

	//test changeTransportPrice

	//test getID

	//test getOrigin

	//test getDestination

	//test toString



}
