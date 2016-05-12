package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.EventProcessor;

public class BusEventProcTests {

//changeRoute
	@Test
	public void testChangeRoute() {
		int routeID = 1;
		String updatedCarrier = "KiwiRail";
		double updatedDuration = 9.0;//actually, I don't think we'll need to change this ever
		double updatedCustPriceWeight = 10.0;
		double custPriceVolume = 5.0;
		double transPriceWeight = 2.0;
		double transPriceVolume = 3.0;
		EventProcessor.changeRoute(routeID, updatedCarrier, updatedDuration, updatedCustPriceWeight, custPriceVolume, transPriceWeight, transPriceVolume);
	}

	@Test(expected=Exception.class)
	public void testChangeRouteNegativeRouteID() throws Exception{
		int routeID = -1;
		String updatedCarrier = "KiwiRail";
		double updatedDuration = 9.0;//actually, I don't think we'll need to change this ever
		double updatedCustPriceWeight = 10.0;
		double custPriceVolume = 5.0;
		double transPriceWeight = 2.0;
		double transPriceVolume = 3.0;
		EventProcessor.changeRoute(routeID, updatedCarrier, updatedDuration, updatedCustPriceWeight, custPriceVolume, transPriceWeight, transPriceVolume);
	}
	@Test(expected=Exception.class)
	public void testChangeRouteEmptyStringCarrier() throws Exception{
		int routeID = 1;
		String updatedCarrier = "";
		double updatedDuration = 9.0;//actually, I don't think we'll need to change this ever
		double updatedCustPriceWeight = 10.0;
		double custPriceVolume = 5.0;
		double transPriceWeight = 2.0;
		double transPriceVolume = 3.0;
		EventProcessor.changeRoute(routeID, updatedCarrier, updatedDuration, updatedCustPriceWeight, custPriceVolume, transPriceWeight, transPriceVolume);
	}
	@Test(expected=Exception.class)
	public void testChangeRouteNullCarrier() throws Exception{
		int routeID = 1;
		String updatedCarrier = null;
		double updatedDuration = 9.0;//actually, I don't think we'll need to change this ever
		double updatedCustPriceWeight = 10.0;
		double custPriceVolume = 5.0;
		double transPriceWeight = 2.0;
		double transPriceVolume = 3.0;
		EventProcessor.changeRoute(routeID, updatedCarrier, updatedDuration, updatedCustPriceWeight, custPriceVolume, transPriceWeight, transPriceVolume);
	}
	@Test(expected=Exception.class)
	public void testChangeRouteNonPositivePriceWeight() throws Exception{
		int routeID = 1;
		String updatedCarrier = "KiwiRail";
		double updatedDuration = 9.0;//actually, I don't think we'll need to change this ever
		double updatedCustPriceWeight = 0.0;
		double custPriceVolume = 5.0;
		double transPriceWeight = 2.0;
		double transPriceVolume = 3.0;
		EventProcessor.changeRoute(routeID, updatedCarrier, updatedDuration, updatedCustPriceWeight, custPriceVolume, transPriceWeight, transPriceVolume);
	}
	@Test(expected=Exception.class)
	public void testChangeRouteNonPositivePriceVolume() throws Exception{
		int routeID = 1;
		String updatedCarrier = "KiwiRail";
		double updatedDuration = 9.0;//actually, I don't think we'll need to change this ever
		double updatedCustPriceWeight = 10.0;
		double custPriceVolume = -.002;
		double transPriceWeight = 2.0;
		double transPriceVolume = 3.0;
		EventProcessor.changeRoute(routeID, updatedCarrier, updatedDuration, updatedCustPriceWeight, custPriceVolume, transPriceWeight, transPriceVolume);
	}
	@Test(expected=Exception.class)
	public void testChangeRouteNonPositiveTransPriceWeight() throws Exception{
		int routeID = 1;
		String updatedCarrier = "KiwiRail";
		double updatedDuration = 9.0;//actually, I don't think we'll need to change this ever
		double updatedCustPriceWeight = 10.0;
		double custPriceVolume = 5.0;
		double transPriceWeight = -12;
		double transPriceVolume = 3.0;
		EventProcessor.changeRoute(routeID, updatedCarrier, updatedDuration, updatedCustPriceWeight, custPriceVolume, transPriceWeight, transPriceVolume);
	}
	@Test(expected=Exception.class)
	public void testChangeRouteNonPositiveTransCostVolume() throws Exception{
		int routeID = 1;
		String updatedCarrier = "KiwiRail";
		double updatedDuration = 9.0;//actually, I don't think we'll need to change this ever
		double updatedCustPriceWeight = 10.0;
		double custPriceVolume = 5.0;
		double transPriceWeight = 2.0;
		double transPriceVolume = 0.0;
		EventProcessor.changeRoute(routeID, updatedCarrier, updatedDuration, updatedCustPriceWeight, custPriceVolume, transPriceWeight, transPriceVolume);
	}

	//addRoute

	//changePrice

	//processMail

	//disconRoute

	//pushEvent

	//createEvent

}
