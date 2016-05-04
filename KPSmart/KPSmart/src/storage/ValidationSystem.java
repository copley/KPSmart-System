package storage;

import model.*;

public class ValidationSystem {

	private ValidationSystem(){}

	public static boolean ValidateCustPriceEvent(CustPriceChangeEvent event){
		// validate customer price change event
		return true;
	}

	public static boolean ValidateMailProcessEvent(MailProcessEvent event){
		// validate mail process event
		return true;
	}

	public static boolean ValidateRouteAdditionEvent(RouteAdditionEvent event){
		// validate route addition event
		return true;
	}

	public static boolean ValidateRouteDiscEvent(RouteDiscEvent event){
		// validate route discontinued event
		return true;
	}

	public static boolean ValidateTransportCostEvent(TransportCostChangeEvent event){
		// validate transport cost event
		return true;
	}

}
