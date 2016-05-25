package model.events.inputs;

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
