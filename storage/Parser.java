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
	public static List<BusinessEvent> readData() {
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

		return businessEvents;
	}

	private static BusinessEvent readEvent(Element event) throws IllegalEventError {
		BusinessEvent businessEvent = null;
		// get the eventType
		String eventType = event.getName();
		// read event based on the element type
		switch (eventType) {
		case "price":
			CustPriceChangeEvent priceEvent = readPrice(event);
			if(!ValidationSystem.ValidateCustPriceEvent(priceEvent)){
				throw new IllegalEventError("Event contains incorrect information");
			}
			businessEvent = priceEvent;
			break;
		case "mail":
			MailProcessEvent mailEvent = readMail(event);
			if(!ValidationSystem.ValidateMailProcessEvent(mailEvent)){
				throw new IllegalEventError("Event contains incorrect information");
			}
			businessEvent = mailEvent;
			break;
		case "add":
			RouteAdditionEvent addEvent = readAdd(event);
			if(!ValidationSystem.ValidateRouteAdditionEvent(addEvent)){
			}
			businessEvent =  addEvent;
			break;
		case "discontinue":
			RouteDiscEvent discEvent = readDiscontinue(event);
			if(!ValidationSystem.ValidateRouteDiscEvent(discEvent)){
				throw new IllegalEventError("Event contains incorrect information");
			}
			businessEvent = discEvent;
			break;
		case "cost":
			TransportCostChangeEvent costEvent = readCost(event);
			if(!ValidationSystem.ValidateTransportCostEvent(costEvent)){
				throw new IllegalEventError("Event contains incorrect information");
			}
			businessEvent = costEvent;
			break;
		default:
			throw new IllegalEventError("Invalid Event Type");
		}
		return businessEvent;
	}

	private static CustPriceChangeEvent readPrice(Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static MailProcessEvent readMail(Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static RouteAdditionEvent readAdd(Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static RouteDiscEvent readDiscontinue(Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static TransportCostChangeEvent readCost(Element event) {
		String company = event.getChild("company").getText();
		String to = event.getChild("to").getText();
		String from = event.getChild("from").getText();
		String type = event.getChild("type").getText();
		int weightCost = Integer.parseInt(event.getChild("weightcost").getText());
		int volumecost = Integer.parseInt(event.getChild("volumecost").getText());
		int maxWeight = Integer.parseInt(event.getChild("maxWeight").getText());
		int maxVolume = Integer.parseInt(event.getChild("maxVolume").getText());
		int duration = Integer.parseInt(event.getChild("duration").getText());
		int frequency = Integer.parseInt(event.getChild("frequency").getText());
		String day = event.getChild("day").getText();
		// return new event
		return null;
	}
}
