package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.events.CustPriceChangeEvent;
import model.events.MailProcessEvent;
import model.map.Priority;
import model.map.Site;
import model.map.SiteMap;
import model.map.Type;
import model.ValidationSystem;

public class ValidationTests {
	SiteMap testMap = makeTestMap();

	@Test
	public void testValidateCustPriceEvent_time() {
		CustPriceChangeEvent testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertTrue("", ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(-1, 1, 2016, 0000, "Nic", "Wellington", "Auckland", Priority.DOMESTIC_AIR,
				10, 10);
		assertFalse("", ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 14, 2016, 0000, "Nic", "Wellington", "Auckland", Priority.DOMESTIC_AIR,
				10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, -1, 0000, "Nic", "Wellington", "Auckland", Priority.DOMESTIC_AIR, 10,
				10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 2500, "Nic", "Wellington", "Auckland", Priority.DOMESTIC_AIR,
				10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
	}

	@Test
	public void testValidateCustPriceEvent_strings() {
		CustPriceChangeEvent testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertTrue("", ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, null, "Wellington", "Auckland", Priority.DOMESTIC_AIR,
				10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Berlin", "Auckland", Priority.DOMESTIC_AIR, 10,
				10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", null, Priority.DOMESTIC_AIR, 10,
				10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
	}

	@Test
	public void testValidateCustPriceEvent_priority() {
		CustPriceChangeEvent testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertTrue("", ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland", null, 10, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
	}

	@Test
	public void testValidateCustPriceEvent_numbers() {
		CustPriceChangeEvent testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				Priority.DOMESTIC_AIR, 10, 10);
		assertTrue("", ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland", Priority.DOMESTIC_AIR,
				0, 10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
		testEvent = new CustPriceChangeEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland", Priority.DOMESTIC_AIR,
				10, -10);
		assertFalse(ValidationSystem.validateCustPriceEvent(testEvent));
	}

	@Test
	public void testValidateMailProcessEvent_time() {
		MailProcessEvent testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertTrue("correct data should pass", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(-1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("negative day should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(40, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("too high day should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, -1, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("negative year should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 3000, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("Future time should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, -1, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("negative time should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 2500, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("too big time should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		}
	
	@Test
	public void testValidateMailProcessEvent_processor() {
		MailProcessEvent testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertTrue("correct data should pass", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, null, "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("null processor name should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("empty processor name should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		}
	
	@Test
	public void testValidateMailProcessEvent_deliveryInfo() {
		MailProcessEvent testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertTrue("correct info should pass", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", null, "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("null origin should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("empty string origin should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "London", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("non NZ location should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", null,
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("null destination should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("empty string destination should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, null, 10, 10, 1);
		assertFalse("null priority should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		}
	@Test
	public void testValidateMailProcessEvent_busFigData() {
		MailProcessEvent testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertTrue("corrent info should pass", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				0, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("zero weight should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				-1, 1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("negative weight should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 0, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("zero volume should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, -1, Priority.DOMESTIC_AIR, 10, 10, 1);
		assertFalse("negative volume should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, -1, 10, 1);
		assertFalse("negative price should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, -1, 1);
		assertFalse("negative cost should fail", ValidationSystem.validateMailProcessEvent(testEvent));
		testEvent = new MailProcessEvent(1, 1, 2016, 0000, "Nic", "Wellington", "Auckland",
				1, 1, Priority.DOMESTIC_AIR, 10, 10, 0);
		assertFalse("zero duration should fail", ValidationSystem.validateMailProcessEvent(testEvent));
	}
	
	
	@Test
	public void testValidateOrigin_site() {
		Site testSite = null;
		assertFalse("null site should fail", ValidationSystem.validateOrigin(testSite));
		testSite = new Site(1, null, false, false);
		assertFalse("null name site should fail", ValidationSystem.validateOrigin(testSite));
		testSite = new Site(1, "Johannesburg", false, false);
		assertFalse("non NZ name site should fail", ValidationSystem.validateOrigin(testSite));
		testSite = new Site(1, "WeLLington", false, false);
		assertFalse("NZ name with wrong capitalisation site should fail", ValidationSystem.validateOrigin(testSite));
		testSite = new Site(1, "Wellington", false, false);
		assertTrue("NZ name with correct capitalisation site should pass", ValidationSystem.validateOrigin(testSite));
		}

	@Test
	public void testValidateOrigin_string() {
		String testOriginString = null;
		assertFalse("null string should fail", ValidationSystem.validateOrigin(testOriginString));
		testOriginString = "";
		assertFalse("null string should fail", ValidationSystem.validateOrigin(testOriginString));
		testOriginString = "Johannesburg";
		assertFalse("non-nz name string should fail", ValidationSystem.validateOrigin(testOriginString));
		testOriginString = "weLLington";
		assertFalse("valid NZ town name should not pass if not in correct capitalisation", ValidationSystem.validateOrigin(testOriginString));
		testOriginString = "Wellington";
		assertTrue("valid NZ town name should pass", ValidationSystem.validateOrigin(testOriginString));
	}

	@Test
	public void testValidatePositiveDoubleString() {
		String testNumString = null;
		assertFalse("null string should fail", ValidationSystem.validatePositiveDoubleString(testNumString));
		testNumString = "";
		assertFalse("empty string should fail", ValidationSystem.validatePositiveDoubleString(testNumString));
		testNumString = "-1";
		assertFalse("negative number string should fail", ValidationSystem.validatePositiveDoubleString(testNumString));
		testNumString = "0";
		assertFalse("zero number string should fail", ValidationSystem.validatePositiveDoubleString(testNumString));
		testNumString = "one";
		assertFalse("written number string should fail", ValidationSystem.validatePositiveDoubleString(testNumString));
		testNumString = "1E-06";
		assertTrue("float style number string should pass",
				ValidationSystem.validatePositiveDoubleString(testNumString));
		testNumString = "1.3";
		assertTrue(" 1.3 should pass", ValidationSystem.validatePositiveDoubleString(testNumString));
	}

	@Test
	public void testValidateNonEmptyString() {
		String testString = null;
		assertFalse("null string should fail", ValidationSystem.validateNonEmptyString(testString));
		testString = "";
		assertFalse("empty string should fail", ValidationSystem.validateNonEmptyString(testString));
		testString = "pass";
		assertTrue("non-empty string should pass", ValidationSystem.validateNonEmptyString(testString));
	}

	// ===helper methods==========

	private SiteMap makeTestMap() {
		SiteMap newMap = new SiteMap();
		// add in a test route, will make 2 sites as well!
		newMap.addNewRoute("Wellington", "Auckland", "test", Type.AIR, 1, 1, 1, 1, 1);
		return newMap;
	}
}
