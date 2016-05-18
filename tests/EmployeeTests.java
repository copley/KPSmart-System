package tests;

import static org.junit.Assert.*;

import org.junit.*;

import model.employees.Employee;

public class EmployeeTests {
	@Test
	public void checkLoginTrue() {
		Employee emp = new Employee(3,"Joely","123456",true);
		assertTrue("The username and password must match",emp.checkLogin("Joely", "123456"));
	}

	@Test
	public void checkLoginFalse() {
		Employee emp = new Employee(3,"Joely","123456",true);
		assertFalse("The username and password must match",emp.checkLogin("Joel", "123456"));
	}

	@Test
	public void TestToString() {
		Employee emp = new Employee(3,"Joely","123456",true);
		String string= ("ID: 3\nName: Joely\nPassword: 123456\nManager: true");
		assertEquals("The id must match",emp.toString(),string);
	}


}
