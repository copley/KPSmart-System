package model;

import java.util.ArrayList;
import java.util.List;

import model.events.BusinessEvent;
import model.events.MailProcessEvent;
import model.map.Route;
import storage.DataStore;

public class FigureGenerator {

	private DataStore db;
	private List<MailProcessEvent> mailE = new ArrayList<MailProcessEvent>();
	private double totRevenue;
	private double totExpenditure;
	private double deliveryTimes;
	//private List<Route> criticalRoutes = new ArrayList<Route>();

	public FigureGenerator(DataStore db) {
		super();
		getMailEvents();	//generates all the process mail events in the database.
	}


	//need to finish this method
	public void getMailEvents(){
		for(BusinessEvent be:db.getBusinessEvents()){
			if(be.getClass().equals("MailProcessEvent")){	//how to get the type of class?
				MailProcessEvent mail = (MailProcessEvent)be;	//casting so can add to the list and store it as a Mail process event
				mailE.add(mail);
			}
		}
	}

	public void generateFigures() {
		if(mailE.size()!= 0){
			for(MailProcessEvent mail:mailE){
				totRevenue += mail.getRevenue();
				totExpenditure += mail.getExpenditure();
				deliveryTimes += mail.getDeliveryTime();
			}
		}

	};

	public double getExpenditure() {
		return totExpenditure;
	};

	public double getRevenue(){
		return totRevenue;
	}

	public double getAVGDelivery(){
		return deliveryTimes/mailE.size();
	}

	public List<Route> generateCriticalRoutes() {
		List<Route> criticalRoutes = new ArrayList<Route>();
		for(Route r:db.getSiteMap().getRoutes()){
			if(r.getTransPriceVolume() > r.getCustPriceVolume() || r.getTransPriceWeight() > r.getCustPriceWeight()){
				criticalRoutes.add(r);
			}
		}
		return criticalRoutes;
	};

	public int generateTotalMail() {
		return mailE.size();
	};

	public int generateTotalEvents() {
		return db.getBusinessEvents().size();
	};

}
