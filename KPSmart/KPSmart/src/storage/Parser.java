package storage;

import java.io.*;
import java.util.*;
import org.jdom2.*;
import org.jdom2.input.*;

import model.*;
import model.events.BusinessEvent;
import model.events.IllegalEventError;

public class Parser {

	public static final File FILE = new File("src/KPSmart_log.xml");

	private Parser(){}

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

			for(int i = 0; i < eventList.size(); i++){
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
		switch(eventType){
		case "price":
			return readPrice(event);
		case "mail":
			return readMail(event);
		case "discontinue":
			return readDiscontinue(event);
		case "cost":
			return readCost(event);
		default:
			throw new IllegalEventError("Invalid Event Type");
		}
	}

	private static BusinessEvent readCost(Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static BusinessEvent readDiscontinue(Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static BusinessEvent readMail(Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static BusinessEvent readPrice(Element event) {
		// TODO Auto-generated method stub
		return null;
	}
}
