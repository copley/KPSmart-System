package model.events.inputs;

public class CustomerPriceInput {

	private String origin;
	private String destination;
	private String priority;
	private String weightCost;
	private String volumeCost;

	public CustomerPriceInput(String origin, String destination, String priority, String weightCost,
			String volumeCost) {
		this.origin = origin;
		this.destination = destination;
		this.priority = priority;
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
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

	public String getWeightCost() {
		return weightCost;
	}

	public String getVolumeCost() {
		return volumeCost;
	}

}
