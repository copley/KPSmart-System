package model;

import model.*;
import model.employees.Employee;
import model.events.BusinessEvent;
import model.events.CustPriceChangeEvent;
import model.events.MailProcessEvent;
import model.events.RouteAdditionEvent;
import model.events.RouteDiscEvent;
import model.events.TransportCostChangeEvent;
import model.map.Priority;
import model.map.Route;
import model.map.Site;
import model.map.Type;

public class ValidationSystem {

	private ValidationSystem() {
	}

	public static boolean validateCustPriceEvent(CustPriceChangeEvent event) {
		return validateTimestamp(event.getDay(), event.getMonth(), event.getYear(), event.getTime())
				&& validateMode(event.getType()) && event.getNewWeightCost() > 0 && event.getNewVolumeCost() > 0;
	}

	public static boolean validateMailProcessEvent(MailProcessEvent event) {
		return validateTimestamp(event.getDay(), event.getMonth(), event.getYear(), event.getTime())
				&& validatePriority(event.getPriority()) && event.getWeight() > 0 && event.getVolume() > 0;
	}

	public static boolean validateRouteAdditionEvent(RouteAdditionEvent event) {
		// validate route addition event
		return true;
	}

	public static boolean validateRouteDiscEvent(RouteDiscEvent event) {
		// validate route discontinued event
		return true;
	}

	public static boolean validateTransportCostEvent(TransportCostChangeEvent event) {
		// validate transport cost event
		return true;
	}

	public static boolean validateSite(Site s) {
		// TODO Auto-generated method stub
		return true;
	}

	public static boolean validateRoute(Route r) {
		// TODO Auto-generated method stub
		return true;
	}

	public static boolean validateEmployee(Employee s) {
		// TODO Auto-generated method stub
		return true;
	}

	private static boolean validateTimestamp(int day, int month, int year, int time) {
		return year > 0 && month > 0 && month <= 12 && day > 0
				&& ((month == 2 && day <= 28) || (((month <= 7 && month % 2 == 1) || month % 2 == 0) && day <= 31)
						|| (((month <= 6 && month % 2 == 0) || month % 2 == 1) && day <= 30))
				&& time >= 0000 || time <= 2359;
	}

	private static boolean validatePriority(String priority) {
		for(Priority p : Priority.values()){
			if(p.toString().equals(priority)) return true;
		}
		return false;
	}

	private static boolean validateMode(String mode) {
		for(Type p : Type.values()){
			if(p.toString().equals(mode)) return true;
		}
		return false;
	}

}
