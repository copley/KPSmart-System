package storage;

import java.io.File;
import java.util.ArrayList;
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

	public static final File EVENT_FILE = new File("data/KPSmart_log.xml");
	public static final File MAP_FILE = new File("data/map.xml");
	public static final File EMPLOYEE_FILE = new File("data/staff.xml");
	public static final File CITIES_FILE = new File("src/NZtownNames.txt");

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
		createDirectory();
		employees = Reader.readEmployee();
		map = Reader.readMap();
		businessEvents = Reader.readBusinessEvents();
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

	private void createDirectory() {
		File directory = new File("data");
		if (!directory.exists()) {
			try {
				directory.mkdir();
			} catch (SecurityException se) {
				System.out.println("Could not create folder");
			}
		}
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

	public SiteMap getSiteMap() {
		return map;
	}

	public Employees getEmployees() {
		return employees;
	}

	public List<String> getBusinessEventStrings() {
		List<String> eventStrings = new ArrayList<String>();
		for (BusinessEvent be : businessEvents) {
			eventStrings.add(be.toString());
		}
		return eventStrings;
	}

	public List<BusinessEvent> getBusinessEvents() {
		return businessEvents;
	}

}
