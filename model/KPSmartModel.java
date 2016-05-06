package model;

public class KPSmartModel {

	// Fields containing all Model components e.g database, map etc.
	// map
	// database
	BusinessEventProcessor busEventProcessor;
	FigureGenerator figGen;
	SiteMap siteMap;
	Employees employees;
	storage.DataStore dataStore;// ***is this how we access the data
								// loader/saver?
	storage.Parser parser;// ***or is this how we access the data loader/saver?

	public KPSmartModel() {
		// make the utility objects needed (objects that make/modify other objects)
		busEventProcessor = new BusinessEventProcessor();
		figGen = new FigureGenerator();
		// *** something to allow data loading and saving****

		// load up the stored data (populate the objects)
		// ***activate the data loader*****
	}

	// ****what does this bit do?***
	public void Database_Do_Something_With_String(String str) {
		// database.ActonString(str);
		System.out.println("You have reached the Model, printing :" + str);

	}

}
