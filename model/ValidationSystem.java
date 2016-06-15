package model;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.employees.Employee;
import model.events.CustPriceChangeEvent;
import model.events.MailProcessEvent;
import model.events.RouteAdditionEvent;
import model.events.RouteDiscEvent;
import model.events.TransportCostChangeEvent;
import model.map.Priority;
import model.map.Route;
import model.map.Site;
import model.map.Type;
import storage.DataStore;

public class ValidationSystem {

	private static List<String> cities;

	public ValidationSystem() {
		cities = new ArrayList<String>();
		try (Scanner scanner = new Scanner(DataStore.CITIES_FILE)) {
			while (scanner.hasNextLine()) {
				cities.add(scanner.nextLine());
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean validateCustPriceEvent(CustPriceChangeEvent event) {
		return (
				validateTimestamp(event.getDay(), event.getMonth(), event.getYear(), event.getTime())
				&& validateNonEmptyString(event.getEmployee())
				&& validateOrigin(event.getOrigin())
				&& validateNonEmptyString(event.getDestination())
				&& (event.getPriority() != null)
				&& validatePriority(event.getPriority().toString()) 
				&& event.getNewWeightCost() > 0
				&& event.getNewVolumeCost() > 0
				);
	}

	public static boolean validateMailProcessEvent(MailProcessEvent event) {
		return (
				validateTimestamp(event.getDay(), event.getMonth(), event.getYear(), event.getTime())
				&& validateNonEmptyString(event.getEmployee())
				&& validateOrigin(event.getOrigin())
				&& validateNonEmptyString(event.getDestination())
				&& (event.getPriority() != null)
				&& validatePriority(event.getPriority().toString()) 
				&& event.getWeight() > 0 
				&& event.getVolume() > 0
				&& event.getRevenue() > 0
				&& event.getExpenditure() > 0
				&& event.getDeliveryTime() > 0
				);
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

	public static boolean validateOrigin(Site s) {
		if(s == null){
			return false;
		}
		return cities.contains(s.getLocation());
	}
	
	/**
	 * @param originName
	 * @return true if non-null, non-empty, valid NZ placename, and in correct camel-case format
	 */
	public static boolean validateOrigin(String originName) {
		return cities.contains(originName);
	}

	public static boolean validateRoute(Route r) {
		// TODO Auto-generated method stub
		return true;
	}

	public static boolean validateEmployee(Employee s) {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	public static boolean validateTimestamp(int day, int month, int year, int time) {
		//make sure the time is a valid time
		boolean validTime = (
				year > 0 
				&& month > 0 
				&& month <= 12 
				&& day > 0
				&& ((month == 2 && day <= 28) 
						|| (((month <= 7 && month % 2 == 1) || month % 2 == 0) && day <= 31)
						|| (((month <= 6 && month % 2 == 0) || month % 2 == 1) && day <= 30)
						)
				&& (time >= 0000 && time <= 2359)
				);
		//now make sure the time is not in the future
		LocalDateTime now = LocalDateTime.now();
		boolean notFuture = true;
		if(now.getYear() < year){
			//System.out.println("in future by year");
			notFuture = false;
		}
		else if(now.getYear() == year){
				if(now.getMonth().getValue() < month){
					//System.out.println("in future by month");					
					notFuture = false;
				}
				else if(now.getMonth().getValue() == month){
					if(now.getDayOfMonth() < day){
						//System.out.println("in future by day");
						notFuture = false;
					}
					else if (now.getDayOfMonth() == day){
						if(now.getHour()*100+now.getMinute() < time){
							//System.out.println("in future by time");
							notFuture = false;
						}
					}			
				}
		}
		
		return validTime && notFuture;
	}

	public static boolean validatePriority(String priority) {
		if (priority == null){
			return false;
			}
		for (Priority p : Priority.values()) {
			if (p.toString().equals(priority))
				return true;
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	public static boolean validateType(String type) {
		for (Type t : Type.values()) {
			if (t.toString().equals(type))
				return true;
		}
		return false;
	}
	
	public static boolean validatePositiveDoubleString(String shouldBePlusDouble){
		if(shouldBePlusDouble == null){
			return false;
		}
		try {
			double num = Double.parseDouble(shouldBePlusDouble);
			if(num <=0){
				return false;
			}
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean validateNonEmptyString(String shouldBeNonEmpty){
		if (shouldBeNonEmpty == null || shouldBeNonEmpty == ""){
			return false;
		}
		return true;
	}
}
