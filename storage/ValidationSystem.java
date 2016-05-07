package storage;

import model.*;
import model.events.CustPriceChangeEvent;
import model.events.MailProcessEvent;
import model.events.RouteAdditionEvent;
import model.events.RouteDiscEvent;
import model.events.TransportCostChangeEvent;

public class ValidationSystem {

	private ValidationSystem(){}

	public static boolean validateCustPriceEvent(CustPriceChangeEvent event){
		// validate customer price change event
		return true;
	}

	public static boolean validateMailProcessEvent(MailProcessEvent event){
		// validate mail process event
		return true;
	}

	public static boolean validateRouteAdditionEvent(RouteAdditionEvent event){
		// validate route addition event
		return true;
	}

	public static boolean validateRouteDiscEvent(RouteDiscEvent event){
		// validate route discontinued event
		return true;
	}

	public static boolean validateTransportCostEvent(TransportCostChangeEvent event){
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

	public static boolean validateStaff(Staff s) {
		// TODO Auto-generated method stub
		return true;
	}

}
