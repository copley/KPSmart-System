package model.events.inputs;

import model.ValidationSystem;

public class CustomerPriceInput {

	private String origin;
	private String destination;
	private String priority;
	private String weightPrice;
	private String volumePrice;

	public CustomerPriceInput(String origin, String destination, String priority, String weightPrice,
			String volumePrice) {
		this.origin = origin;
		this.destination = destination;
		this.priority = priority;
		this.weightPrice = weightPrice;
		this.volumePrice = volumePrice;
	}

	/*
	 * Checks each field and makes sure it meets requirements to be processed,
	 * Returns a string specifying errors.
	 */
	public String findInputErrors(){
		String errors = "";
		//no need to check origin, destination, or priority, these are all 
		//chosen by picklists of valid values from the model
		
		//check weightPrice - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(weightPrice)){
			errors += "Price per weight must be a positive number \n";
		}
		//check volumePrice - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(volumePrice)){
			errors += "Price per volume must be a positive number \n";
		}
		return errors;
	}
	
	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getPriority() {
		return priority;
	}

	public String getWeightPrice() {
		return weightPrice;
	}

	public String getVolumePrice() {
		return volumePrice;
	}

}
