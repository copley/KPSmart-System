package model.employees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.map.Route;

/**
 * This class contains the collection of employees, and allows access to individual employee data via the staff id
 * It also used to check login details.
 * @author Nic
 */
public class Employees {
	private Map<Integer, Employee> employees; // maps individual employees to their id
	
	public Employees() {
		employees = new HashMap<Integer, Employee>();
	}
	
	//=====building methods==============
	
	public void addEmployee(Employee toAdd){
		employees.put(toAdd.getID(), toAdd);
	}
	
	//=====utility methods===============
	
	public Employee getEmployeeFromID(int employeeID){
		return employees.get(employeeID);//returns void if id not valid
	}
	
	/*
	 * returns -1 if the login details do not match any employee
	 * returns the id of a matching employee otherwise
	 */
	public int getEmployeeFromLoginDetails(String name, String Password ){
		//TODO: not yet implemented
		return -1;
	}

	public Set<Employee> toSet() {
		return new HashSet<Employee>(employees.values());
	}
	
}
