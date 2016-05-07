package storage;

import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.*;

import model.*;
import model.events.BusinessEvent;
import model.events.CustPriceChangeEvent;
import model.events.IllegalEventError;
import model.events.MailProcessEvent;
import model.events.RouteAdditionEvent;
import model.events.RouteDiscEvent;
import model.events.TransportCostChangeEvent;

/**
 * Class responsible for reading the data from the log file
 *
 * @author Bonnie Liao
 */
public class Parser {

	public static final File FILE = new File("src/KPSmart_log.xml");

	private Parser() {
	}

	/**
	 * Reads the data from the log file, into business events and stores them
	 * into a list
	 *
	 * @return List of all the business events from the log file
	 */
	public static List<BusinessEvent> readBusinessEvents() {
		System.out.println("Reading data from 'KPSmart_log.xml'");

		// Read data from xml file
		List<BusinessEvent> businessEvents = new ArrayList<BusinessEvent>();

		try {
			// create the SAX builder
			SAXBuilder saxBuilder = new SAXBuilder();
			// create document
			Document document = saxBuilder.build(FILE);
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
		} catch (IllegalEventError e) {
			e.printStackTrace();
		}

		System.out.println("Finished reading data");

		return businessEvents;
	}



	public static SiteMap readMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Reads an event and makes the appropriate type of event based on the
	 * element type
	 *
	 * @param event
	 *            Event to be parsed
	 * @return A business event
	 * @throws IllegalEventError
	 *             If the event is invalid
	 */
	private static BusinessEvent readEvent(Element event) throws IllegalEventError {
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
			if (!ValidationSystem.ValidateCustPriceEvent(priceEvent)) {
				throw new IllegalEventError("Event contains incorrect information");
			}
			businessEvent = priceEvent;
			break;
		case "mail":
			MailProcessEvent mailEvent = readMail(event, day, month, year, time, staff);
			if (!ValidationSystem.ValidateMailProcessEvent(mailEvent)) {
				throw new IllegalEventError("Event contains incorrect information");
			}
			businessEvent = mailEvent;
			break;
		case "add":
			RouteAdditionEvent addEvent = readAdd(event, day, month, year, time, staff);
			if (!ValidationSystem.ValidateRouteAdditionEvent(addEvent)) {
			}
			businessEvent = addEvent;
			break;
		case "discontinue":
			RouteDiscEvent discEvent = readDiscontinue(event, day, month, year, time, staff);
			if (!ValidationSystem.ValidateRouteDiscEvent(discEvent)) {
				throw new IllegalEventError("Event contains incorrect information");
			}
			businessEvent = discEvent;
			break;
		case "cost":
			TransportCostChangeEvent costEvent = readCost(event, day, month, year, time, staff);
			if (!ValidationSystem.ValidateTransportCostEvent(costEvent)) {
				throw new IllegalEventError("Event contains incorrect information");
			}
			businessEvent = costEvent;
			break;
		default:
			throw new IllegalEventError("Invalid Event Type");
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
