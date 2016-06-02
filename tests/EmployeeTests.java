package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.employees.Employee;
/**
 *
 * Test cases for employers
 *
 */
public class EmployeeTests {

	public Employee createManager() {
		return new Employee(3, "Joely", "123456", true);
	}

	public Employee createUser() {
		return new Employee(3, "Tim", "123456", false);
	}

	@Test
	public void checkLoginTrue() {
		Employee emp = new Employee(3, "Joely", "123456", true);
		assertTrue("The username and password must match", emp.checkLogin("Joely", "123456"));
	}

	@Test
	public void checkLoginFalse() {
		Employee emp = new Employee(3, "Joely", "123456", true);
		assertTrue("The username and password must match", emp.checkLogin("Joel", "12346"));
	}

	@Test
	public void testToString() {
		Employee emp = new Employee(3, "Joely", "123456", true);
		String string = ("ID: 3\nName: Joely\nPassword: 123456\nManager: true");
		assertEquals("The id must match", emp.toString(), string);
	}

	@Test
	public void testGetters() {
		Employee user = createUser();
		Employee manager = createManager();

		int IDint = 3;

		assertTrue("The ID 2 must match", (user.getID() == IDint));
		assertTrue("The ID 3 must match", (manager.getID() == (IDint)));

		assertTrue("The name must match", user.getName().equals("Tim"));
		assertTrue("The name must match", manager.getName().equals("Joely"));

		assertTrue("The password must match", user.getPassword().equals("123456"));
		assertTrue("The password must match", manager.getPassword().equals("123456"));

		assertFalse("Must be false", user.isManager());
		assertTrue("Must be true", manager.isManager());

	}

}
