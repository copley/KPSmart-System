package storage;

import java.io.File;
import java.util.List;

import model.employees.Employee;
import model.employees.Employees;
import model.events.BusinessEvent;
import model.map.SiteMap;

/**
 * The Data store of the system. Responsible for storing all the data in the
 * database
 *
 * @author Bonnie Liao
 *
 */
public class DataStore {

	public static final File EVENT_FILE = new File("src/KPSmart_log.xml");
	public static final File MAP_FILE = new File("src/map.xml");
	public static final File EMPLOYEE_FILE = new File("src/staff.xml");

	private List<BusinessEvent> businessEvents;
	private Employees employees;
	private SiteMap map;

	public DataStore() {
		load();
	}

	/**
	 * Loads all the data into the data store
	 */
	public void load() {
		businessEvents = Reader.readBusinessEvents();
		employees = Reader.readEmployee();
		map = Reader.readMap();
		System.out.println("All data has been loaded");
	}

	/**
	 * Saves all the data from the data store
	 */
	public void save() {
		Writer.writeBusinessEvents(businessEvents);
		Writer.writeEmployees(employees);
		Writer.writeMap(map);
		System.out.println("All data has been saved");
	}

	/**
	 * Adds the business event 'be' to the database (businessEvents)
	 *
	 * @param be
	 *            Business event to be added
	 * @return Returns true if added successfully
	 */
	public boolean addEvent(BusinessEvent be) {
		return businessEvents.add(be);
	}

	public static void main(String[] args) {
		new DataStore();
	}

	public SiteMap getSiteMap() {
		return map;
		}
	
	public Employees getEmployees() {
		return employees;
		}

}
