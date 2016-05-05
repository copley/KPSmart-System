package storage;

import java.util.ArrayList;
import java.util.List;

import model.events.BusinessEvent;

public class DataStore {

	private List<BusinessEvent> businessEvents;

	public DataStore() {
		businessEvents = new ArrayList<BusinessEvent>();
		businessEvents = Parser.readData();
	}

	/**
	 * Adds the business event 'be' to the database (businessEvents)
	 * @param be Business event to be added
	 * @return Returns true if added successfully
	 */
	public boolean addEvent(BusinessEvent be){
		return businessEvents.add(be);
	}

	public static void main(String[] args){
		new DataStore();
	}

}
