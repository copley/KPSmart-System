package model.events.inputs;

public class DiscontinueInput {

	private String origin;
	private String destination;
	private String company;
	private String type;

	public DiscontinueInput(String origin, String destination, String company, String type) {
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.type = type;
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

}
