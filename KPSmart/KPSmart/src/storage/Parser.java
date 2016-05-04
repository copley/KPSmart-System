package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.*;

public class Parser {

	public static final String FILENAME = "src/KPSmart_log.xml";

	private Parser(){}

	public static List<BusinessEvent> readData() {
		// Read data from xml file
		List<BusinessEvent> businessEvents = new ArrayList<BusinessEvent>();
		try {
			Scanner sc = new Scanner(new FileReader(new File(FILENAME)));
			while(sc.hasNext()){
				// parse the file for each event stored
				// validates if data is valid before adding to list
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		return businessEvents;
	}
}
