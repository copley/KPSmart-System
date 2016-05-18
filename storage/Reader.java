package storage;

import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.*;

import model.*;
import model.employees.Employee;
import model.employees.Employees;
import model.events.*;
import model.exceptions.*;
import model.map.Route;
import model.map.Site;
import model.map.SiteMap;
import model.map.Type;

/**
 * Class responsible for reading the data from the log file and data files
 *
 * @author Bonnie Liao
 */
public class Reader {

	private Reader() {
	}

	/**
	 * Reads the data from the log file, into business events and stores them
	 * into a list
	 *
	 * @return List of all the business events from the log file
	 */
	public static List<BusinessEvent> readBusinessEvents() {
		System.out.println("Reading data...'");

		// Read data from xml file
		List<BusinessEvent> businessEvents = new ArrayList<BusinessEvent>();

		try {
			// create the SAX builder
			SAXBuilder saxBuilder = new SAXBuilder();
			// create jdom document
			Document document = saxBuilder.build(DataStore.EVENT_FILE);
			// get root element
			Element systemElement = document.getRootElement();

			// get all the children elements
			List<Element> eventList = systemElement.getChildren();

			for (int i = 0; i < eventList.size(); i++) {
				Element event = eventList.get(i);

				// read business event and add to list
				businessEvents.add(readEvent(event));
			}
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		} catch (IllegalEventException e) {
			e.printStackTrace();
		} catch (IllegalPriorityException e) {
			e.printStackTrace();
		}

		System.out.println("Finished reading data");

		return businessEvents;
	}

	public static Employees readEmployee() {
		System.out.println("Reading Employees...");
		Employees employees = new Employees();

		try {
			// create the SAX builder
			SAXBuilder saxBuilder = new SAXBuilder();
			// create jdom document
			Document document = saxBuilder.build(DataStore.EMPLOYEE_FILE);
			// get root element
			Element systemElement = document.getRootElement();

			// get all the children elements
			List<Element> employeeList = systemElement.getChildren();

			for (int i = 0; i < employeeList.size(); i++) {
				Element event = employeeList.get(i);
				// read business event and add to list
				employees.addEmployee(readEmployee(event));
			}
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		} catch (IllegalEmployeeException e) {
			e.printStackTrace();
		}

		System.out.println("Finished reading employees");
		return employees;
	}

	private static Employee readEmployee(Element event) throws IllegalEmployeeException {
		int id = Integer.parseInt(event.getChild("id").getText());
		String name = event.getChild("name").getText();
		String password = event.getChild("password").getText();
		boolean isManager = Boolean.parseBoolean(event.getChild("isManager").getText());
		Employee emp = new Employee(id, name, password, isManager);
		if (!ValidationSystem.validateEmployee(emp)) {
			throw new IllegalEmployeeException("Invalid employee!");
		}
		return emp;
	}

	/**
	 * Creates a Site map object and and reads and stores the sites and routes
	 *
	 * @return Site map object with all sites and routes
	 */
	public static SiteMap readMap() {
		System.out.println("Reading map...");
		SiteMap map = new SiteMap();

		try {
			// create the SAX builder
			SAXBuilder saxBuilder = new SAXBuilder();
			// create jdom document
			Document document = saxBuilder.build(DataStore.MAP_FILE);
			// get root element
			Element systemElement = document.getRootElement();

			// get all the site elements
			List<Element> sitesList = systemElement.getChild("sites").getChildren();
			// get all the routes elements
			List<Element> routesList = systemElement.getChild("routes").getChildren();

			readSites(map, sitesList);
			readRoutes(map, routesList);

		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		} catch (IllegalSiteException e) {
			e.printStackTrace();
		} catch (IllegalRouteException e) {
			e.printStackTrace();
		}

		System.out.println("Finished reading map");
		return map;
	}

	/**
	 * Read the sites from the file sites.txt and adds it to the map
	 *
	 * @param map
	 *            Site map of the sites and routes
	 * @param sitesList
	 * @throws IllegalSiteException
	 */
	private static void readSites(SiteMap map, List<Element> sitesList) throws IllegalSiteException {
		for (int i = 0; i < sitesList.size(); i++) {
			Element site = sitesList.get(i);
			// read business event and add to list
			Site s = readSite(site);
			if (!ValidationSystem.validateSite(s)) {
				throw new IllegalSiteException("Invalid site!");
			}
			map.addSite(s);
		}
	}

	/**
	 * Makes a site object from the site element
	 *
	 * @param site
	 *            element with the site information
	 * @return site object
	 */
	private static Site readSite(Element site) {
		int id = Integer.parseInt(site.getChild("id").getText());
		String location = site.getChild("location").getText();
		return new Site(id, location);
	}

	/**
	 * Reads sites from 'routes.txt' and adds each route into the map
	 *
	 * @param map
	 *            Site map of the sites and routes
	 * @param routesList
	 * @throws IllegalRouteException
	 */
	private static void readRoutes(SiteMap map, List<Element> routesList) throws IllegalRouteException {
		for (int i = 0; i < routesList.size(); i++) {
			Element route = routesList.get(i);
			// read business event and add to list
			Route r = readRoute(route);
			if (!ValidationSystem.validateRoute(r)) {
				throw new IllegalRouteException("Invalid route!");
			}
			map.addRoute(r);
		}
	}

	/**
	 * Reads a route and makes a route object
	 *
	 * @param route
	 *            Scanner of the route information
	 * @return Route object
	 */
	private static Route readRoute(Element route) {
		int id = Integer.parseInt(route.getChild("id").getText());
		String destination = route.getChild("destination").getText();
		String origin = route.getChild("origin").getText();
		String company = route.getChild("company").getText();
		int duration = Integer.parseInt(route.getChild("duration").getText());
		Type type = Type.valueOf(route.getChild("type").getText());
		double custPriceWeight = Double.parseDouble(route.getChild("custPriceWeight").getText());
		double custPriceVolume = Double.parseDouble(route.getChild("custPriceVolume").getText());
		double transPriceWeight = Double.parseDouble(route.getChild("transPriceWeight").getText());
		double transPriceVolume = Double.parseDouble(route.getChild("transPriceVolume").getText());
		boolean inService = Boolean.parseBoolean(route.getChild("inService").getText());
		return new Route(id, origin, destination, company, duration, type, inService, custPriceWeight, custPriceVolume,
				transPriceWeight, transPriceVolume);
	}

	/**
	 * Reads an event and makes the appropriate type of event based on the
	 * element type
	 *
	 * @param event
	 *            Event to be parsed
	 * @return A business event
	 * @throws IllegalEventException
	 *             If the event is invalid
	 * @throws IllegalPriorityException
	 */
	private static BusinessEvent readEvent(Element event) throws IllegalEventException, IllegalPriorityException {
		BusinessEvent businessEvent = null;

		// get the eventType
		String eventType = event.getName();

		// read the timestamp and employee responsible
		int day = Integer.parseInt(event.getChild("day").getText());
		int month = Integer.parseInt(event.getChild("month").getText());
		int year = Integer.parseInt(event.getChild("year").getText());
		int time = Integer.parseInt(event.getChild("time").getText());
		String employee = event.getChild("employee").getText();

		// read event based on the element type
		switch (eventType) {
		case "price":
			CustPriceChangeEvent priceEvent = readPrice(event, day, month, year, time, employee);
			if (!ValidationSystem.validateCustPriceEvent(priceEvent)) {
				throw new IllegalEventException("Event contains incorrect information");
			}
			businessEvent = priceEvent;
			break;
		case "mail":
			MailProcessEvent mailEvent = readMail(event, day, month, year, time, employee);
			if (!ValidationSystem.validateMailProcessEvent(mailEvent)) {
				throw new IllegalEventException("Event contains incorrect information");
			}
			businessEvent = mailEvent;
			break;
		case "add":
			RouteAdditionEvent addEvent = readAdd(event, day, month, year, time, employee);
			if (!ValidationSystem.validateRouteAdditionEvent(addEvent)) {
			}
			businessEvent = addEvent;
			break;
		case "discontinue":
			RouteDiscEvent discEvent = readDiscontinue(event, day, month, year, time, employee);
			if (!ValidationSystem.validateRouteDiscEvent(discEvent)) {
				throw new IllegalEventException("Event contains incorrect information");
			}
			businessEvent = discEvent;
			break;
		case "cost":
			TransportCostChangeEvent costEvent = readCost(event, day, month, year, time, employee);
			if (!ValidationSystem.validateTransportCostEvent(costEvent)) {
				throw new IllegalEventException("Event contains incorrect information");
			}
			businessEvent = costEvent;
			break;
		default:
			throw new IllegalEventException("Invalid Event Type");
		}
		return businessEvent;
	}

	/**
	 * Reads the Customer price change event
	 *
	 * @param event
	 *            Event to be parsed
	 * @param day
	 *            Day the event was created
	 * @param month
	 *            Month the event was created
	 * @param year
	 *            Year the event was created
	 * @param time
	 *            Time the event was created
	 * @param employee
	 *            The employee responsible for the event
	 * @return A Customer price change event
	 * @throws IllegalPriorityException
	 */
	private static CustPriceChangeEvent readPrice(Element event, int day, int month, int year, int time,
			String employee) throws IllegalPriorityException {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		Priority priority = readPriority(event.getChild("priority").getText());
		int weightcost = Integer.parseInt(event.getChild("weightcost").getText());
		int volumecost = Integer.parseInt(event.getChild("volumecost").getText());

		return new CustPriceChangeEvent(day, month, year, time, employee, origin, destination, priority, weightcost,
				volumecost);
	}

	/**
	 * Reads the Mail process event
	 *
	 * @param event
	 *            Event to be parsed
	 * @param day
	 *            Day the event was created
	 * @param month
	 *            Month the event was created
	 * @param year
	 *            Year the event was created
	 * @param time
	 *            Time the event was created
	 * @param employee
	 *            The employee responsible for the event
	 * @return A Mail process event
	 * @throws IllegalPriorityException
	 */
	private static MailProcessEvent readMail(Element event, int day, int month, int year, int time, String employee) throws IllegalPriorityException {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		double weight = Double.parseDouble(event.getChild("weight").getText());
		double volume = Double.parseDouble(event.getChild("volume").getText());
		Priority priority = readPriority(event.getChild("priority").getText());

		return new MailProcessEvent(day, month, year, time, employee, origin, destination, weight, volume, priority);
	}

	/**
	 * Reads the Route addition event
	 *
	 * @param event
	 *            Event to be parsed
	 * @param day
	 *            Day the event was created
	 * @param month
	 *            Month the event was created
	 * @param year
	 *            Year the event was created
	 * @param time
	 *            Time the event was created
	 * @param employee
	 *            The employee responsible for the event
	 * @return A Route Addition event
	 */
	private static RouteAdditionEvent readAdd(Element event, int day, int month, int year, int time, String employee) {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		String company = event.getChild("company").getText();
		String type = event.getChild("type").getText();
		int weightcost = Integer.parseInt(event.getChild("weightcost").getText());
		int volumecost = Integer.parseInt(event.getChild("volumecost").getText());
		String departure = event.getChild("departure").getText();
		int frequency = Integer.parseInt(event.getChild("frequency").getText());
		int duration = Integer.parseInt(event.getChild("duration").getText());

		return new RouteAdditionEvent(day, month, year, time, employee, origin, destination, company, type, weightcost,
				volumecost, departure, frequency, duration);
	}

	/**
	 * Reads the Route discontinued event
	 *
	 * @param event
	 *            Event to be parsed
	 * @param day
	 *            Day the event was created
	 * @param month
	 *            Month the event was created
	 * @param year
	 *            Year the event was created
	 * @param time
	 *            Time the event was created
	 * @param employee
	 *            The employee responsible for the event
	 * @return A Route discontinued event
	 */
	private static RouteDiscEvent readDiscontinue(Element event, int day, int month, int year, int time,
			String employee) {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		String company = event.getChild("company").getText();
		String type = event.getChild("type").getText();

		return new RouteDiscEvent(day, month, year, time, employee, origin, destination, company, type);
	}

	/**
	 * Reads the Transport cost change event
	 *
	 * @param event
	 *            Event to be parsed
	 * @param day
	 *            Day the event was created
	 * @param month
	 *            Month the event was created
	 * @param year
	 *            Year the event was created
	 * @param time
	 *            Time the event was created
	 * @param employee
	 *            The employee responsible for the event
	 * @return A Transport cost change event
	 */
	private static TransportCostChangeEvent readCost(Element event, int day, int month, int year, int time,
			String employee) {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		String company = event.getChild("company").getText();
		String type = event.getChild("type").getText();
		int weightcost = Integer.parseInt(event.getChild("weightcost").getText());
		int volumecost = Integer.parseInt(event.getChild("volumecost").getText());
		String departure = event.getChild("departure").getText();
		int frequency = Integer.parseInt(event.getChild("frequency").getText());
		int duration = Integer.parseInt(event.getChild("duration").getText());

		return new TransportCostChangeEvent(day, month, year, time, employee, origin, destination, company, type,
				weightcost, volumecost, departure, frequency, duration);
	}

	private static Priority readPriority(String text) throws IllegalPriorityException {
		switch(text){
		case "International Air":
			return Priority.INTERNATIONAL_AIR;
		case "International Standard":
			return Priority.INTERNATIONAL_STANDARD;
		case "Domestic Air":
			return Priority.DOMESTIC_AIR;
		case "Domestic Standard":
			return Priority.DOMESTIC_STANDARD;
			default:
				throw new IllegalPriorityException("Invalid Priority!");
		}
	}
}
