package storage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import model.*;
import model.events.*;
import model.exceptions.*;

/**
 * Class responsible for writing all the data into the log file and data files
 *
 * @author Bonnie Liao
 *
 */
public class Writer {

	private Writer() {
	}

	/**
	 * Writes the business events to a file
	 *
	 * @param businessEvents
	 *            List of business events to be saved
	 */
	public static void writeBusinessEvents(List<BusinessEvent> businessEvents) {
		// make a new jdom document
		Document doc = new Document();
		// set the root element of the document
		doc.setRootElement(new Element("log"));

		// write each business event to the document
		for (BusinessEvent be : businessEvents) {
			try {
				doc.getRootElement().addContent(writeEvent(be));
			} catch (IllegalEventException e) {
				e.printStackTrace();
			}
		}

		// write the document to the file
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			outputter.output(doc, new FileOutputStream(DataStore.EVENT_FILE_TEST));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Determines which event is which and makes an element out of it
	 *
	 * @param be
	 *            the business event o be saved
	 * @return Element of the business event
	 * @throws IllegalEventException
	 */
	private static Element writeEvent(BusinessEvent be) throws IllegalEventException {
		// make initial event
		Element event = new Element("event");
		event.addContent(new Element("day").setText("" + be.getDay()));
		event.addContent(new Element("month").setText("" + be.getMonth()));
		event.addContent(new Element("year").setText("" + be.getYear()));
		event.addContent(new Element("time").setText("" + be.getDay()));
		event.addContent(new Element("staff").setText(be.getStaff()));

		// write appropriate event based on instance type
		if (be instanceof CustPriceChangeEvent) {
			return writeCustPriceChangeEvent((CustPriceChangeEvent) be, event);
		} else if (be instanceof MailProcessEvent) {
			return writeMailProcessEvent((MailProcessEvent) be, event);
		} else if (be instanceof RouteAdditionEvent) {
			return writeRouteAdditionEvent((RouteAdditionEvent) be, event);
		} else if (be instanceof RouteDiscEvent) {
			return writeRouteDiscEvent((RouteDiscEvent) be, event);
		} else if (be instanceof TransportCostChangeEvent) {
			return writeTransportCostChangeEvent((TransportCostChangeEvent) be, event);
		}
		throw new IllegalEventException("Not a valid business event");
	}

	/**
	 * Takes base business event element and adds the customer price attributes
	 * to it
	 *
	 * @param be
	 *            customer price change event
	 * @param event
	 *            general business event element
	 * @return element with the customer price change event information
	 */
	private static Element writeCustPriceChangeEvent(CustPriceChangeEvent be, Element event) {
		event.setName("price");
		event.addContent(new Element("origin").setText(be.getOrigin()));
		event.addContent(new Element("destination").setText(be.getDestination()));
		event.addContent(new Element("priority").setText(be.getPriority()));
		event.addContent(new Element("weightcost").setText("" + be.getNewWeightCost()));
		event.addContent(new Element("volumecost").setText("" + be.getNewVolumeCost()));
		return event;
	}

	private static Element writeMailProcessEvent(MailProcessEvent be, Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Element writeRouteAdditionEvent(RouteAdditionEvent be, Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Element writeRouteDiscEvent(RouteDiscEvent be, Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Element writeTransportCostChangeEvent(TransportCostChangeEvent be, Element event) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void writeStaff(List<Staff> staffList) {
		// TODO Auto-generated method stub

	}

	public static void writeMap(SiteMap map) {
		// TODO Auto-generated method stub

	}

}
