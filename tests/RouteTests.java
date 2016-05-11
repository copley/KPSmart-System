package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Route;

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

		Route newRoute = new Route(routeID, destnID, originID, company,
				duration, custWeightPrice, custVolPrice, transWeightPrice, transVolPrice, inService);
}

	//test route creation fail scenarios
	@Test(expected=Exception.class)
	public void testBadClassNUllCompany() throws Exception {
		//test route creation positive
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

				Route newRoute = new Route(routeID, destnID, originID, company,
						duration, custWeightPrice, custVolPrice, transWeightPrice, transVolPrice, inService);
	}

	//test changeCustomerPrice

	//test changeTransportPrice

	//test getID

	//test getOrigin

	//test getDestination

	//test toString



}
