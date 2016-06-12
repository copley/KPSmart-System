package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.events.CustPriceChangeEvent;
import model.map.Priority;
import model.map.SiteMap;
import model.ValidationSystem;

public class ValidationTests {
	SiteMap testMap = makeTestMap();

	@Test
	public void testValidationSystem() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateCustPriceEvent_time() {
		CustPriceChangeEvent testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertTrue("",ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(-1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertFalse("",ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 14, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, -1, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 2500, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
	}
	
	@Test
	public void testValidateCustPriceEvent_strings() {
		CustPriceChangeEvent testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertTrue("",ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, null, "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Berlin", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", null,
				Priority.DOMESTIC_AIR, 10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
	}
	
	@Test
	public void testValidateCustPriceEvent_priority() {
		CustPriceChangeEvent testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertTrue("",ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				null, 10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
	}
	

	@Test
	public void testValidateCustPriceEvent_numbers() {
		CustPriceChangeEvent testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertTrue("",ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 0, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, -10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));		
	}

	@Test
	public void testValidateMailProcessEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateRouteAdditionEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateRouteDiscEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateTransportCostEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateOriginSite() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateOriginString() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateRoute() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateType() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidatePositiveDoubleString() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateNonEmptyString() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

	// ===helper methods==========

	private SiteMap makeTestMap() {
		SiteMap newMap = new SiteMap();
		/*
		 * //add in a test route, will make 2 sites as well!
		 * newMap.addNewRoute("Wellington", "Auckland", "test", Type.AIR, 1, 1,
		 * 1, 1, 1);
		 */ return newMap;
	}
}
