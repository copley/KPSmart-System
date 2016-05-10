package storage;

import java.io.File;
import java.util.List;

import model.Employee;
import model.events.BusinessEvent;

/**
 * The Data store of the system. Responsible for storing all the data in the
 * database
 *
 * @author Bonnie Liao
 *
 */
public class DataStore {

	public static final File EVENT_FILE = new File("src/KPSmart_log.xml");
	public static final File SITES_FILE = new File("src/sites.txt");
	public static final File ROUTES_FILE = new File("src/routes.txt");
	public static final File EMPLOYEE_FILE = new File("src/staff.xml");

	// TEST FILES FOR DEBUGGING PURPOSES
//	public static final File EVENT_FILE_TEST = new File("src/KPSmart_logTEST.xml");
	public static final File SITES_FILE_TEST = new File("src/sitesTEST.txt");
	public static final File ROUTES_FILE_TEST = new File("src/routesTEST.txt");
//	public static final File EMPLOYEE_FILE_TEST = new File("src/staffTEST.xml");

	private List<BusinessEvent> businessEvents;
	private List<Employee> employees;
	private SiteMap map;

	public DataStore() {
		load();
		save();
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

}
