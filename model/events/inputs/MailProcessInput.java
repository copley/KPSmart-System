package model.events.inputs;

import model.ValidationSystem;
import model.map.SiteMap;

public class MailProcessInput {
	private String origin;
	private String destination;
	private String weight;
	private String volume;
	private String priority;


	public MailProcessInput(String origin, String destination, String weight,
			String volume, String priority) {
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
		this.volume = volume;
		this.priority = priority;
	}
	/*
	 * Checks each field and makes sure it meets requirements to be processed,
	 * Returns a string specifying errors.
	 */
	public String findInputErrors(){
		String errors = "";
		//no need to check origin, destination, or priority, these are all 
		//chosen by picklists of valid values from the model
		
		//check weight - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(weight)){
			errors += "Weight must be a positive number \n";
		}
		//check volume - has to be a positive double type
		if(!ValidationSystem.validatePositiveDoubleString(volume)){
			errors += "Volume must be a positive number \n";
		}
		return errors;
	}
	
	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getWeight() {
		return weight;
	}

	public String getVolume() {
		return volume;
	}

	public String getPriority() {
		return priority;
	}
}
