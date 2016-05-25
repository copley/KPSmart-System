package controller;

import java.awt.event.*;

import model.Input;
import model.KPSmartModel;
import view.*;
import view.eventsView.*;

public class KPSmartController {

	private KPSmartFrame gui;
	private KPSmartModel model;
	private int ListenerCount = 1; // debugging purposes // used to print where
									// in the code the
									// listener was called.

	public KPSmartController() {
		model = new KPSmartModel();
		gui = new KPSmartFrame(new KeyAction(), new MouseAction(), new ViewActionListener());
		System.out.println("Calling from Controller"); // debugging
	}

	/*
	 * =========================================================================
	 * START OF Methods for debugging purposes
	 * =========================================================================
	 */
	public class KeyAction implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println(ListenerCount + 42);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(ListenerCount + 46);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// example implementation.
			System.out.println(ListenerCount + 49);
		}
	}

	public class MouseAction implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked"); // debugging purposes - mc
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("mousePressed"); // debugging purposes - mc
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("mouseReleased"); // debugging purposes - mc
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// System.out.println(ListenerCount + 78); // debugging purposes -
			// mc
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// System.out.println(ListenerCount + 82); // debugging purposes -
			// mc
		}
	}
	/*
	 * =========================================================================
	 * END OF Methods for debugging purposes
	 * =========================================================================
	 */


	/**
	 * Action listener class for the menu.
	 * @author Max Copley
	 */
	public class ViewActionListener implements ActionListener {

		private String originSelection = "";
		private String destinationSelection = "";
		private String weightSelection = "";
		private String volumeSelection = "";
		private String prioritySelection = "";

		// Bobo please add these fields into the forms.

		private String weightCost, weightPrice, volumeCost, volumePrice;

		private String newWeightCostSelection = "";
		private String newVolumeSelection = "";
		private String companySelection = "";
		private String typeSelection = "";

		// private String departureDay = "" ;
		// private String frequencySelection = "" ;
		private String durationSelection = "";

		@Override
		public void actionPerformed(ActionEvent e) {
			//==========================================
			// START OF View Business Figures buttons
			//==========================================
			if (e.getActionCommand().equals("Total Revenue")) {
				System.out.println("Total Revenue"); // debugging - mc
				// kpsmartModel.getTotalRevenue(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderTotalRevenue();//TODO: Could pass in STRING
				// , INT
			} else if (e.getActionCommand().equals("Total Expenditure")) {
				System.out.println("Total Expenditure");// debugging - mc
				// kpsmartModel.getTotalExpenditure(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderTotalExpenditure();//TODO: Could pass in
				// STRING , INT

			} else if (e.getActionCommand().equals("Total No. of Events")) {
				System.out.println("Total No. of Events");// debugging - mc
				// kpsmartModel.getTotalEvents(); //TODO: could this return INT,
				// STRING
				// kpsmartGui.renderTotalEvents();//TODO: Could pass in STRING ,
				// INT

			} else if (e.getActionCommand().equals("Amount of Mails")) {
				System.out.println("Amount of Mails");// debugging - mc
				// kpsmartModel.getTotalMail(); //TODO: could this return INT,
				// STRING
				// kpsmartGui.renderTotalMail();//TODO: Could pass in STRING ,
				// INT

			} else if (e.getActionCommand().equals("AVG Delivery Times")) {
				System.out.println("AVG Delivery Times");// debugging - mc
				// kpsmartModel.getAverageTimes(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING
				// , INT

			} else if (e.getActionCommand().equals("Critical Routes")) {
				System.out.println("Critical Routes");// debugging - mc
				// kpsmartModel.getCriticalRoutes(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderCriticalRoutes();//TODO: Could pass in
				// STRING , INT
			}
			//==========================================
			// END OF View Business Figures buttons
			//==========================================

			//==========================================
			// START OF Business Events buttons
			//==========================================
			else if (e.getActionCommand().equals("Mail Delivery")) {
				System.out.println("Mail Delivery");// debugging - mc
				gui.setMainDisplayPanel("MailDeliveryPanel");

				//TODO: Commented out these lines because we can just do the above^^^ - Bonnie
//				MailDeliveryPanel originSelection = (MailDeliveryPanel) gui.getCanvas().getMainDisplayPanel();
//				gui.getCanvas().setMainDisplayPanel("MailDeliveryPanel");

				// kpsmartModel.getMailDelivery(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderMailDelivery();//TODO: Could pass in STRING
				// , INT

			} else if (e.getActionCommand().equals("Add New Route")) {
				System.out.println("Add New Route");// debugging - mc
				gui.setMainDisplayPanel("AddNewRoutePanel");

			} else if (e.getActionCommand().equals("Route Discontinue")) {
				System.out.println("Route Discontinue");// debugging - mc
				gui.setMainDisplayPanel("RouteDiscontinuePanel");

				// kpsmartModel.getAveragfeTimes(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING
				// , INT

			} else if (e.getActionCommand().equals("Customer Price Update")) {
				System.out.println("Customer Price Update");// debugging - mc
				gui.setMainDisplayPanel("CustomerPriceChangePanel");

				// kpsmartModel.getAverageTimes(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING
				// , INT

			} else if (e.getActionCommand().equals("Transport Cost Update")) {
				System.out.println("Transport Cost Update");// debugging - mc
				gui.setMainDisplayPanel("TransportCostChangePanel");

				// kpsmartModel.getAverageTimes(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING
				// , INT

			} else if (e.getActionCommand().equals("Weight:")) {
				System.out.println("Weight:"); // able to be implemented if
												// JTextField componenets have
												// listeners.
			} else if (e.getActionCommand().equals("Volume:")) {
				System.out.println("Volume:"); // able to be implemented if
												// JTextField componenets have
												// listeners.
			}
			//==========================================
			// END OF Business Events buttons
			//==========================================

			//==========================================
			// START OF Submit for forms
			//==========================================
			else if (e.getActionCommand().equals("Submit")) {
				System.out.println("Submit");// debugging - mc
				// kpsmartModel.getAverageTimes(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING
				// , INT

				// "CustomerPriceChangePanel":
				// "MailDeliveryPanel":
				// "RouteDiscontinuePanel":
				// "TransportCostChangePanel":

				// getter methods set inside

				Input input = gui.getMailDeliveryInput();

				//TODO: Don't think this is a good idea so made a mailinput object which will store all the information from the gui
//				MailDeliveryPanel panel = (MailDeliveryPanel) gui.getCanvas().getMainDisplayPanel();
//
//				String originSelection = panel.getOriginComboBoxString();
//				String destinationSelection = panel.getDestinationComboBoxString();
//				String weightSelection = panel.getWeightTextFieldString();
//				String volumeSelection = panel.getVolumeTextFieldString();
//				String prioritySelection = panel.getPriorityComboBoxString();

				System.out.println("Calling parameters from line 209 - Controller class" + originSelection
						+ destinationSelection + weightSelection + volumeSelection + prioritySelection);

				/**
				 * Current Fields available from GUI,
				 *
				 * Origin // BLANK!! NEED FIELDS ADDED TO JCOMBOBOX SELECTION
				 * Destination // BLANK!! NEED FIELDS ADDED TO JCOMBOBOX
				 * SELECTION Weight Volume Priority // BLANK!! NEED FIELDS ADDED
				 * TO JCOMBOBOX SELECTION
				 *
				 * BOBO implement these fields to the FORMS - max copley
				 *
				 * New Weight Cost -newWeightCostSelection New Volume Cost -
				 * newVolumeSelection Company - companySelection Type -
				 * typeSelection Departure Day - departureDay
				 *
				 */

				/**
				 * TODO: Customer price change Parameters: origin, destination,
				 * priority, new weight cost, new volume cost
				 */

				// waiting on GUI form implementation to return the parameters
				// to the KPSmartMODEL
				// kpsmartModel.changeCustomerPrice(originSelection,
				// destinationSelection, prioritySelection,
				// newWeightCostSelection, newVolumeSelection);

				/**
				 * TODO: Process mail origin, destination, weight, volume,
				 * priority
				 */
//				model.processMail(originSelection, destinationSelection, weightSelection, volumeSelection,
//						prioritySelection);
				model.processMail(input);

				// public boolean changeTransportPrice(String origin, String
				// destination, String carrier, String typeString, String
				// newWeightCostString,
				// String newVolumeCostString)

				model.changeTransportPrice(originSelection, destinationSelection, companySelection,
						typeSelection, newWeightCostSelection, newVolumeSelection);

				// public boolean addRoute(String origin, String destination,
				// String company, String durationString, String typeString,
				// String customerPriceWeight, String customerPriceVolume,
				// String transportCostWeight,
				// String transportCostVolume) { // add me, private String
				// weightCost, weightPrice, volumeCost, volumePrice ; -
				// transport Cost Panel

				model.addRoute(originSelection, destinationSelection, companySelection, durationSelection,
						typeSelection, weightCost, weightPrice, volumeCost, volumePrice);
				/**
				 * TODO: Route discontinue origin, destination,company, type
				 */
				model.discontinueRoute(originSelection, destinationSelection, companySelection, typeSelection);
			}
			//==========================================
			// END OF Submit for forms
			//==========================================
		}
	}
}
