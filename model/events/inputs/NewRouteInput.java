package model.events.inputs;

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
		this.transportPriceVolume = transportPriceVolume;
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
