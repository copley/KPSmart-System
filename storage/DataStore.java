package storage;

import java.util.ArrayList;
import java.util.List;

import model.SiteMap;
import model.events.BusinessEvent;

/**
 * The Data store of the system. Responsible for storing all the data in the database
 * @author Bonnie Liao
 *
 */
public class DataStore {

	private List<BusinessEvent> businessEvents;
	private SiteMap map;

	public DataStore() {
		businessEvents = new ArrayList<BusinessEvent>();
		businessEvents = Parser.readBusinessEvents();
		map = Parser.readMap();
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
