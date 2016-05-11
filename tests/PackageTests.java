package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Package;

/*
 * package needs to be able to be constructed and printed to string
 */
public class PackageTests {

	// test creation success
	@Test
	public void testCreate() {
		int originID = 0;
		int destID = 1;
		double price = 2.0;
		double weight = 5.0;//kg
		double volume = 1.0;//cubic metres
		int priority = 1;
		Package goodPackage = new Package(originID, destID, price, weight, volume, priority);
	}

	// test creation fail scenarios
	@Test(expected = Exception.class)
	public void testNegativeOrigID() throws Exception {
		int originID = -1;
		int destID = 1;
		double price = 2.0;
		double weight = 5.0;//kg
		double volume = 1.0;//cubic metres
		int priority = 1;
		Package badPackage = new Package(originID, destID, price, weight, volume, priority);
	}
	@Test(expected = Exception.class)
	public void testNegativeDestID() throws Exception {
		int originID = 0;
		int destID = -2;
		double price = 2.0;
		double weight = 5.0;//kg
		double volume = 1.0;//cubic metres
		int priority = 1;
		Package badPackage = new Package(originID, destID, price, weight, volume, priority);
	}
	@Test(expected = Exception.class)
	public void testNonPositivePrice() throws Exception {
		int originID = 0;
		int destID = 1;
		double price = 0.0;
		double weight = 5.0;//kg
		double volume = 1.0;//cubic metres
		int priority = 1;
		Package badPackage = new Package(originID, destID, price, weight, volume, priority);
	}
	@Test(expected = Exception.class)
	public void testNonPositiveWeight() throws Exception {
		int originID = 0;
		int destID = 1;
		double price = 2.0;
		double weight = -1.0;//kg
		double volume = 1.0;//cubic metres
		int priority = 1;
		Package badPackage = new Package(originID, destID, price, weight, volume, priority);
	}@Test(expected = Exception.class)
	public void testNonPositiveVolume() throws Exception {
		int originID = 0;
		int destID = 1;
		double price = 2.0;
		double weight = 5.0;//kg
		double volume = 0;//cubic metres
		int priority = 1;
		Package badPackage = new Package(originID, destID, price, weight, volume, priority);
	}
	@Test(expected = Exception.class)
	public void testNonSendableVolume() throws Exception {
		int originID = 0;
		int destID = 1;
		double price = 2.0;
		double weight = 5.0;//kg
		double volume = .0000009;//cubic metres - ie .9 cubic cm - too small to post
		int priority = 1;
		Package badPackage = new Package(originID, destID, price, weight, volume, priority);
	}
	@Test(expected = Exception.class)
	public void testNegativePriority() throws Exception {
		int originID = 0;
		int destID = 1;
		double price = 2.0;
		double weight = 5.0;//kg
		double volume = 1.0;//cubic metres
		int priority = -1;
		Package badPackage = new Package(originID, destID, price, weight, volume, priority);
	}
	
	// getters too simple to bother testing

	// test toString
	@Test
	public void testToString() {
		Package testPackage = new Package(1, 2, 10.00, 5.0, .02, 1);
		String correct = "Origin ID: 1\n"
				+ "Destination ID:2\n"
				+ "Price: 10.00\n"
				+ "Weight: 5.0\n"
				+ "Volume: 0.02\n"
				+ "Priority: 1\n";
		assertTrue("testPackage toString should yield: "+correct, testPackage.toString().equals(correct));
	}

}
