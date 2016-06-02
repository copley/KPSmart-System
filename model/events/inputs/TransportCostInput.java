package model.events.inputs;

import model.ValidationSystem;

public class TransportCostInput {

	private String origin;
	private String destination;
	private String company;
	private String type;
	private String weightCost;
	private String volumeCost;
	private String duration;

	public TransportCostInput(String origin, String destination, String company, String type, String weightCost,
			String volumeCost, String duration) {
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.type = type;
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
		this.duration = duration;
	}

	/*
	 * Checks each field and makes sure it meets requirements to be processed,
	 * Returns a string specifying errors.
	 */
	public String findInputErrors(){
		String errors = "";
		//no need to check origin, destination, or priority, these are all 
		//chosen by picklists of valid values from the model
		
		//check weightCost - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(weightCost)){
			errors += "Cost per weight must be a positive number \n";
		}
		//check volumeCost - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(volumeCost)){
			errors += "Cost per volume must be a positive number \n";
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

	public String getType() {
		return type;
	}

	public String getWeightCost() {
		return weightCost;
	}

	public String getVolumeCost() {
		return volumeCost;
	}

	public String getDuration() {
		return duration;
	}

}
