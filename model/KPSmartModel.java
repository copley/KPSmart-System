package model;

import storage.DataStore;

public class KPSmartModel {

	private DataStore db;
	private EventProcessor eventProcessor;

	public KPSmartModel() {
		db = new DataStore();
	}

	public void save(){
		db.save();
	}

}
