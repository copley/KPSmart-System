package tests;

import static org.junit.Assert.*;

import org.junit.*;

import model.Priority;
import model.events.*;
import model.map.Type;

// need to add JUnit4 library
public class ModelEventsTest {
	@Test
	public void invalidNumberOfPlayers() {
		int i = 1;
		assert(i == 1);
	}

	@Test
	public void RouteAddition() {
		RouteAdditionEvent route = new RouteAdditionEvent(8, 8, 2016, 1000, "Joely", "Wellington", "Auckland",
				"KPDeliver", Type.AIR, 10, 22,23,15,15.50);
			
		String string = "";
		string += ("ROUTE ADDITION EVENT\n");
		string += ("Date: 8, 8, 2016\n");
		string += ("Time: 1010\n");
		string += ("Staff responsible: Joely\n");
		string += ("Origin: Wellington\n");
		string += ("Destination: Auckland\n");
		string += ("Company: KPDeliver\n");
		string += ("Type: AIR\n");
		string += ("Duration: 10\n");
		string += ("Customer price per gram: 22\n");
		string += ("Customer price per cubic cm: 23\n");
		string += ("Transport cost per gram: 15\n");
		string += ("Transport cost per cubic cm: 15.5\n");
		

		assertTrue("String needs to equal", string.equals(route.toString()));
	}

	@Test
	public void CustPriceChange() {
		CustPriceChangeEvent event = new CustPriceChangeEvent(8, 8, 2016, 10, "Joely", "Wellington", "Auckland",
				"Air NewZealand",Type.AIR, 10, 15);

		StringBuilder string = new StringBuilder();
		string.append("CUSTOMER PRICE CHANGE EVENT\n");
		string.append("Date: 8, 8, 2016\n");
		string.append("Time: 10\n");
		string.append("Staff responsible: Joely\n");
		string.append("Origin: Wellington\n");
		string.append("Destination: Auckland\n");
		string.append("Carrier: Air NewZealand\n");		
		string.append("Mode: AIR\n");
		string.append("Weight Cost: 10\n");
		string.append("Volume Cost: 15");
		assertTrue("String needs to equal", string.toString().equals(event.toString()));
	}

	@Test
	public void MailProcessEvent() {
		MailProcessEvent event = new MailProcessEvent(8, 8, 2016, 10, "Joely", "Wellington", "Auckland", 10, 15,
				Priority.DOMESTIC_AIR,12,14.5,123);
		StringBuilder string = new StringBuilder();
		string.append("MAIL PROCESS EVENT\n");
		string.append("Date: 8, 8, 2016\n");
		string.append("Time: 10\n");
		string.append("Staff responsible: Joely\n");
		string.append("Origin: Wellington\n");
		string.append("Destination: Auckland\n");
		string.append("Weight: 10\n");
		string.append("Volume: 15\n");
		string.append("Priority: Domestic");
		string.append("Revenue: 15\n");
		string.append("Expenditure: 15\n");
		string.append("Deliver time: 15\n");
		
		
		assertTrue("String needs to equal", string.toString().equals(event.toString()));
	}

	@Test
	public void RouteDiscEvent() {
		RouteDiscEvent route = new RouteDiscEvent(8, 8, 2016, 10, "Joely", "Wellington", "Auckland", "KPDeliver",
				"Air");
		String string = "";
		string += ("ROUTE DISCONTINUED EVENT\n");
		string += ("Date: 8, 8, 2016\n");
		string += ("Time: 10\n");
		string += ("Staff responsible: Joely\n");
		string += ("Origin: Wellington\n");
		string += ("Destination: Auckland\n");
		string += ("Company: KPDeliver\n");
		string += ("Type: Air");

		assertTrue("String needs to equal", string.equals(route.toString()));
	}

	@Test
	public void TransportCostChangeEvent() {
		TransportCostChangeEvent event = new TransportCostChangeEvent(8, 8, 2016, 10, "Joely", "Wellington", "Auckland",
				"KPDeliver", "Air", 10, 15, "Monday", 3, 10);
		String string = "";
		string += ("TRANSPORT COST CHANGE EVENT\n");
		string += ("Date: 8, 8, 2016\n");
		string += ("Time: 10\n");
		string += ("Staff responsible: Joely\n");
		string += ("Origin: Wellington\n");
		string += ("Destination: Auckland\n");
		string += ("Company: KPDeliver\n");
		string += ("Type: Air\n");
		string += ("Weight Cost: 10\n");
		// new line here?
		string += ("Volume Cost: 15");
		// spelling
		string += ("Day pf Departure: Monday\n");
		string += ("Frequency: 3\n");
		string += ("Duration: 10");

		assertTrue("String needs to equal", string.equals(event.toString()));
	}
}
