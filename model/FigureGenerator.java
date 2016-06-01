package model;

import java.util.ArrayList;
import java.util.List;

import model.events.BusinessEvent;
import model.events.MailProcessEvent;
import model.map.Route;
import storage.DataStore;

public class FigureGenerator {

	private DataStore db;
	private List<MailProcessEvent> mailEvents = new ArrayList<MailProcessEvent>();
	private double totRevenue;
	private double totExpenditure;
	private double deliveryTimes;
	// private List<Route> criticalRoutes = new ArrayList<Route>();

	public FigureGenerator(DataStore db) {
		this.db = db;
	}

	// need to finish this method
	public void getMailEvents() {
		for (BusinessEvent be : db.getBusinessEvents()) {
			if (be.getClass().equals("MailProcessEvent")) { // how to get the
															// type of class?
				MailProcessEvent mail = (MailProcessEvent) be; // casting so can
																// add to the
																// list and
																// store it as a
																// Mail process
																// event
				mailEvents.add(mail);
			}
		}
	}

	public void generateFigures() {
		if (mailEvents.size() != 0) {
			for (MailProcessEvent mail : mailEvents) {
				totRevenue += mail.getRevenue();
				totExpenditure += mail.getExpenditure();
				deliveryTimes += mail.getDeliveryTime();
			}
		}

	};

	public double getExpenditure() {
		return totExpenditure;
	};

	public double getRevenue() {
		return totRevenue;
	}

	public double getAVGDelivery() {
		return deliveryTimes / mailEvents.size();
	}

	public List<Route> generateCriticalRoutes() {
		List<Route> criticalRoutes = new ArrayList<Route>();
		for (Route r : db.getSiteMap().getRoutes()) {
			if (r.getTransPriceVolume() > r.getCustPriceVolume() || r.getTransPriceWeight() > r.getCustPriceWeight()) {
				criticalRoutes.add(r);
			}
		}
		return criticalRoutes;
	};

	public int generateTotalMail() {
		return mailEvents.size();
	};

	public int generateTotalEvents() {
		return db.getBusinessEvents().size();
	}

	public void addEvent(MailProcessEvent be) {
		mailEvents.add(be);
	}

	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("<html>");
		string.append("<h2>Total Revenue</h2><br>");
		string.append("<em>" + getRevenue() + "</em><sup>&zwnj</sup><br>");
		string.append("<h2>Total Expenditure</h2><br>");
		string.append("<em>" + getExpenditure() + "</em><sup>&zwnj</sup><br>");
		string.append("<h2>Total Number of Events</h2><br>");
		string.append("<em>" + generateTotalEvents() + "</em><sup>&zwnj</sup><br>");
		string.append("<h2>Total Mail</h2><br>");
		string.append("<em>" + generateTotalMail() + "</em><sup>&zwnj</sup><br>");
		string.append("<h2>Average Delivery Times</h2><br>");
		string.append("<em>" + getAVGDelivery() + "</em><sup>&zwnj</sup><br>");
		string.append("<h2>Critical Routes</h2><br>");
		for(Route r : generateCriticalRoutes()){
			string.append(r.toString());
		}
		string.append("</html>");
		return string.toString();
	}

}
