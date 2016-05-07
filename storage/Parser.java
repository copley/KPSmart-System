package storage;

import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.*;

import model.*;
import model.events.BusinessEvent;
import model.events.CustPriceChangeEvent;
import model.events.MailProcessEvent;
import model.events.RouteAdditionEvent;
import model.events.RouteDiscEvent;
import model.events.TransportCostChangeEvent;
import model.exceptions.IllegalEventException;
import model.exceptions.IllegalRouteException;
import model.exceptions.IllegalSiteException;

/**
 * Class responsible for reading the data from the log file
 *
 * @author Bonnie Liao
 */
public class Parser {

	public static final File DATA_FILE = new File("src/KPSmart_log.xml");
	public static final File SITES_FILE = new File("src/sites.txt");
	public static final File ROUTES_FILE = new File("src/routes.txt");

	private Parser() {
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
			// create document
			Document document = saxBuilder.build(DATA_FILE);
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
		}

		System.out.println("Finished reading data");

		return businessEvents;
	}

	/**
	 * Creates a Site map object and and reads and stores the sites and routes
	 * @return Site map object with all sites and routes
	 */
	public static SiteMap readMap() {
		System.out.println("Reading map...");
		SiteMap map = new SiteMap();

		readSites(map);
		readRoutes(map);

		System.out.println("Finished reading map");
		return map;
	}

	/**
	 * Read the sites from the file sites.txt and adds it to the map
	 * @param map Site map of the sites
	 */
	private static void readSites(SiteMap map) {
		try {
			Scanner sc = new Scanner(new FileReader(SITES_FILE));
			sc.useDelimiter("\\t|\n");
			while(sc.hasNext()){
				Site s = readSite(sc);
				if(!ValidationSystem.validateSite(s)){
					throw new IllegalSiteException("Invalid site!");
				}
				map.addSite(s);
			}
		} catch (FileNotFoundException | IllegalSiteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads a site from the scanner
	 * @param sc Scanner for the file
	 * @return Site object
	 */
	private static Site readSite(Scanner sc) {
		int id = sc.nextInt();
		String location = sc.next();
		return new Site(id, location);
	}

	private static void readRoutes(SiteMap map) {
		try {
			Scanner sc = new Scanner(new FileReader(ROUTES_FILE));
			sc.useDelimiter("\\t|\n");
			while(sc.hasNext()){
				Route r = readRoute(sc);
				if(!ValidationSystem.validateRoute(r)){
					throw new IllegalRouteException("Invalid Route!");
				}
				map.addRoute(r);
			}
		} catch (FileNotFoundException | IllegalRouteException e) {
			e.printStackTrace();
		}

	}

	private static Route readRoute(Scanner sc) {
		int id = sc.nextInt();
		int dest = sc.nextInt();
		int origin = sc.nextInt();
		String company = sc.next();
		int duration = sc.nextInt();
		boolean inService = sc.nextBoolean();
		return new Route(id, dest, origin, company, duration, inService);
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
	 */
	private static BusinessEvent readEvent(Element event) throws IllegalEventException {
		BusinessEvent businessEvent = null;

		// get the eventType
		String eventType = event.getName();

		// read the timestamp and staff responsible
		int day = Integer.parseInt(event.getChild("day").getText());
		int month = Integer.parseInt(event.getChild("month").getText());
		int year = Integer.parseInt(event.getChild("year").getText());
		int time = Integer.parseInt(event.getChild("time").getText());
		String staff = event.getChild("staff").getText();

		// read event based on the element type
		switch (eventType) {
		case "price":
			CustPriceChangeEvent priceEvent = readPrice(event, day, month, year, time, staff);
			if (!ValidationSystem.validateCustPriceEvent(priceEvent)) {
				throw new IllegalEventException("Event contains incorrect information");
			}
			businessEvent = priceEvent;
			break;
		case "mail":
			MailProcessEvent mailEvent = readMail(event, day, month, year, time, staff);
			if (!ValidationSystem.validateMailProcessEvent(mailEvent)) {
				throw new IllegalEventException("Event contains incorrect information");
			}
			businessEvent = mailEvent;
			break;
		case "add":
			RouteAdditionEvent addEvent = readAdd(event, day, month, year, time, staff);
			if (!ValidationSystem.validateRouteAdditionEvent(addEvent)) {
			}
			businessEvent = addEvent;
			break;
		case "discontinue":
			RouteDiscEvent discEvent = readDiscontinue(event, day, month, year, time, staff);
			if (!ValidationSystem.validateRouteDiscEvent(discEvent)) {
				throw new IllegalEventException("Event contains incorrect information");
			}
			businessEvent = discEvent;
			break;
		case "cost":
			TransportCostChangeEvent costEvent = readCost(event, day, month, year, time, staff);
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
	 * @param staff
	 *            The staff responsible for the event
	 * @return A Customer price change event
	 */
	private static CustPriceChangeEvent readPrice(Element event, int day, int month, int year, int time, String staff) {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		String priority = event.getChild("priority").getText();
		int weightcost = Integer.parseInt(event.getChild("weightcost").getText());
		int volumecost = Integer.parseInt(event.getChild("volumecost").getText());

		return new CustPriceChangeEvent(day, month, year, time, staff, origin, destination, priority, weightcost,
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
	 * @param staff
	 *            The staff responsible for the event
	 * @return A Mail process event
	 */
	private static MailProcessEvent readMail(Element event, int day, int month, int year, int time, String staff) {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		int weight = Integer.parseInt(event.getChild("weight").getText());
		int volume = Integer.parseInt(event.getChild("volume").getText());
		String priority = event.getChild("priority").getText();

		return new MailProcessEvent(day, month, year, time, staff, origin, destination, weight, volume, priority);
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
	 * @param staff
	 *            The staff responsible for the event
	 * @return A Route Addition event
	 */
	private static RouteAdditionEvent readAdd(Element event, int day, int month, int year, int time, String staff) {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		String company = event.getChild("company").getText();
		String type = event.getChild("type").getText();
		int weightcost = Integer.parseInt(event.getChild("weightcost").getText());
		int volumecost = Integer.parseInt(event.getChild("volumecost").getText());
		String departure = event.getChild("departure").getText();
		int frequency = Integer.parseInt(event.getChild("frequency").getText());
		int duration = Integer.parseInt(event.getChild("duration").getText());

		return new RouteAdditionEvent(day, month, year, time, staff, origin, destination, company, type, weightcost,
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
	 * @param staff
	 *            The staff responsible for the event
	 * @return A Route discontinued event
	 */
	private static RouteDiscEvent readDiscontinue(Element event, int day, int month, int year, int time, String staff) {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		String company = event.getChild("company").getText();
		String type = event.getChild("type").getText();

		return new RouteDiscEvent(day, month, year, time, staff, origin, destination, company, type);
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
	 * @param staff
	 *            The staff responsible for the event
	 * @return A Transport cost change event
	 */
	private static TransportCostChangeEvent readCost(Element event, int day, int month, int year, int time,
			String staff) {
		String origin = event.getChild("origin").getText();
		String destination = event.getChild("destination").getText();
		String company = event.getChild("company").getText();
		String type = event.getChild("type").getText();
		int weightcost = Integer.parseInt(event.getChild("weightcost").getText());
		int volumecost = Integer.parseInt(event.getChild("volumecost").getText());
		String departure = event.getChild("departure").getText();
		int frequency = Integer.parseInt(event.getChild("frequency").getText());
		int duration = Integer.parseInt(event.getChild("duration").getText());

		return new TransportCostChangeEvent(day, month, year, time, staff, origin, destination, company, type,
				weightcost, volumecost, departure, frequency, duration);
	}
}
