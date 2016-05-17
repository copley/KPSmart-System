package model;

import storage.DataStore;
import model.EventProcessor;


public class KPSmartModel {

	private DataStore db;
	private EventProcessor eventProcessor;

	public KPSmartModel() {
		db = new DataStore();
		eventProcessor = new EventProcessor();
	}

	public void save(){
		db.save();
	}

	public void ChangeCutomerPrice(){
		eventProcessor.changePrice();
	}


	// called from controller class - mc
	public void ChangeCustomerPrice(String originSelection, String destinationSelection, String prioritySelection,
			String newWeightCostSelection, String newVolumeSelection) {
		// TODO Auto-generated method stub

	}
	// called from controller class - mc
	public void ProcessMail(String originSelection, String destinationSelection, String weightSelection,
			String volumeSelection, String prioritySelection) {
		// TODO Auto-generated method stub

	}
	// called from controller class - mc
	public void AddRoute(String originSelection, String destinationSelection, String companySelection,
			String typeSelection, String newWeightCostSelection, String newVolumeSelection, String departureDay,
			String frequencySelection, String durationSelection) {
		// TODO Auto-generated method stub

	}
	// called from controller class - mc
	public void RemoveRoute(String originSelection, String destinationSelection, String companySelection,
			String typeSelection) {
		// TODO Auto-generated method stub

	}
	// called from controller class - mc
	public void ChangeTransportPrice(String originSelection, String destinationSelection, String companySelection,
			String typeSelection, String newWeightCostSelection, String newVolumeSelection, String departureDay,
			String frequencySelection, String durationSelection) {
		// TODO Auto-generated method stub

	}








}
