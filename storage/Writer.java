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
			outputter.output(doc, new FileOutputStream(DataStore.EVENT_FILE));
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
		event.addContent(new Element("time").setText("" + be.getTime()));
		event.addContent(new Element("employee").setText(be.getEmployee()));

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

	/**
	 * Takes a base business event element and adds the mail process attributes
	 * to it
	 *
	 * @param be
	 *            mail process event
	 * @param event
	 *            general business event element
	 * @return element with the mail process event information
	 */
	private static Element writeMailProcessEvent(MailProcessEvent be, Element event) {
		event.setName("mail");
		event.addContent(new Element("origin").setText(be.getOrigin()));
		event.addContent(new Element("destination").setText(be.getDestination()));
		event.addContent(new Element("weight").setText("" + be.getWeight()));
		event.addContent(new Element("volume").setText("" + be.getVolume()));
		event.addContent(new Element("priority").setText(be.getPriority()));
		return event;
	}

	/**
	 * Takes a base business event element and adds the route addition
	 * attributes to it
	 *
	 * @param be
	 *            Route addition event
	 * @param event
	 *            general business event element
	 * @return element with the route addition event information
	 */
	private static Element writeRouteAdditionEvent(RouteAdditionEvent be, Element event) {
		event.setName("add");
		event.addContent(new Element("origin").setText(be.getOrigin()));
		event.addContent(new Element("destination").setText(be.getDestination()));
		event.addContent(new Element("company").setText(be.getCompany()));
		event.addContent(new Element("type").setText(be.getType()));
		event.addContent(new Element("weightcost").setText("" + be.getNewWeightCost()));
		event.addContent(new Element("volumecost").setText("" + be.getNewVolumeCost()));
		event.addContent(new Element("departure").setText(be.getDepartureDay()));
		event.addContent(new Element("frequency").setText("" + be.getFrequency()));
		event.addContent(new Element("duration").setText("" + be.getDuration()));
		return event;
	}

	/**
	 * Takes a base business event element and adds the route discontinuation
	 * attributes to it
	 *
	 * @param be
	 *            route discontinuation event
	 * @param event
	 *            general business event element
	 * @return element with the route discontinuation event information
	 */
	private static Element writeRouteDiscEvent(RouteDiscEvent be, Element event) {
		event.setName("discontinue");
		event.addContent(new Element("origin").setText(be.getOrigin()));
		event.addContent(new Element("destination").setText(be.getDestination()));
		event.addContent(new Element("company").setText(be.getCompany()));
		event.addContent(new Element("type").setText(be.getType()));
		return event;
	}

	/**
	 * Takes a base business event element and adds the Transport cost
	 * attributes to it
	 *
	 * @param be
	 *            Transport cost event
	 * @param event
	 *            general business event element
	 * @return element with the Transport cost event information
	 */
	private static Element writeTransportCostChangeEvent(TransportCostChangeEvent be, Element event) {
		event.setName("cost");
		event.addContent(new Element("origin").setText(be.getOrigin()));
		event.addContent(new Element("destination").setText(be.getDestination()));
		event.addContent(new Element("company").setText(be.getCompany()));
		event.addContent(new Element("type").setText(be.getType()));
		event.addContent(new Element("weightcost").setText("" + be.getNewWeightCost()));
		event.addContent(new Element("volumecost").setText("" + be.getNewVolumeCost()));
		event.addContent(new Element("departure").setText(be.getDepartureDay()));
		event.addContent(new Element("frequency").setText("" + be.getFrequency()));
		event.addContent(new Element("duration").setText("" + be.getDuration()));
		return event;
	}

	public static void writeEmployees(List<Employee> employees) {
		// make a new jdom document
		Document doc = new Document();
		// set the root element of the document
		doc.setRootElement(new Element("employees"));
		// write each business event to the document
		for (Employee employee : employees) {
			doc.getRootElement().addContent(writeEmployee(employee));
		}
		// write the document to the file
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			outputter.output(doc, new FileOutputStream(DataStore.EMPLOYEE_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Element writeEmployee(Employee employee) {
		Element emp = new Element("employee");
		emp.addContent(new Element("id").setText("" + employee.getID()));
		emp.addContent(new Element("name").setText(employee.getName()));
		emp.addContent(new Element("password").setText(employee.getPassword()));
		emp.addContent(new Element("isManager").setText("" + employee.isManager()));
		return emp;
	}

	public static void writeMap(SiteMap map) {
		// TODO Auto-generated method stub

	}

}
