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
	//test creation
	@Test
	public void testCreateSuccess() {
		//test route creation positive
		int routeID = 0;
		String origin = "Wellington";
		String destination = "Auckland";

		String company = "Bogus";
		int duration = 10;
		Type mode = Type.AIR;
		double custWeightPrice = 2.30;
		double custVolPrice = 3.00;
		double transWeightPrice = 1;
		double transVolPrice =35.99;
		boolean inService = true;

		Route newRoute = new Route(routeID, origin, destination, company, duration, mode, inService,
				custWeightPrice, custVolPrice, transWeightPrice, transVolPrice);
		
	}


	//test changeCustomerPrice

	//test changeTransportPrice

	//test getID

	//test getOrigin

	//test getDestination

	//test toString



}
