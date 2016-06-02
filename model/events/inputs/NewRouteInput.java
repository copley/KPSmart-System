package model.events.inputs;

import model.ValidationSystem;
import model.map.Priority;
import model.map.SiteMap;

public class NewRouteInput {
	private String origin;
	private String destination;
	private String company;
	private String duration;
	private String type;
	private String customerPriceWeight;
	private String customerPriceVolume;
	private String transportPriceWeight;
	private String transportPriceVolume;

	public NewRouteInput(String origin, String destination, String company, String duration, String type, String customerPriceWeight,
			String customerPriceVolume, String transportPriceWeight, String transportPriceVolume) {
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.duration = duration;
		this.type = type;
		this.customerPriceWeight = customerPriceWeight;
		this.customerPriceVolume = customerPriceVolume;
		this.transportPriceWeight = transportPriceWeight;
		this.transportPriceVolume = transportPriceWeight;
	}

	/*
	 * Checks each field and makes sure it meets requirements to be processed,
	 * Returns a string specifying errors.
	 */
	public String findInputErrors(){
		String errors = "";
		//check origin - must match an NZ place
		if(!(new ValidationSystem().validateOrigin(SiteMap.uniformPrint(origin)))){
		errors = errors + "Origin must be a recognised NZ town or city\n";
		}
		//check destination - just has to be non-empty
		if(!ValidationSystem.validateNonEmptyString(destination)){
			errors = errors + "Destination must contain a value\n";
		}
		//check company - just has to be non-empty
		if(!ValidationSystem.validateNonEmptyString(company)){
			errors = errors + "Company must contain a value\n";
		}
		//check duration - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(duration)){
			errors += "Duration must be a positive number \n";
		}
		//check type - has to be of correct form - is from a pick-list should be impossible to have an error!
		if(!ValidationSystem.validateType(type)){
			errors +="Type must be one of AIR SEA LAND \n";
		}
		//check customerPriceWeight - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(customerPriceWeight)){
			errors += "Customer price per weight must be a positive number \n";
		}
		//check customerPriceVolume - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(customerPriceVolume)){
			errors += "Customer price per volume must be a positive number \n";
		}
		//check transportPriceWeight - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(transportPriceWeight)){
			errors += "Transport cost per weight must be a positive number \n";
		}
		//check transportPriceVolumehas to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(transportPriceVolume)){
			errors += "Transport cost per volume must be a positive number \n";
		}
		return errors;
	}
	
	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getCompany() {
		return company;
	}

	public String getDuration() {
		return duration;
	}

	public String getType(){
		return type;
	}

	public String getCustomerPriceWeight() {
		return customerPriceWeight;
	}

	public String getCustomerPriceVolume() {
		return customerPriceVolume;
	}

	public String getTransportPriceWeight() {
		return transportPriceWeight;
	}

	public String getTransportPriceVolume() {
		return transportPriceVolume;
	}

}
