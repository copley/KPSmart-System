package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import model.KPSmartModel;
import view.AbstractMainDisplayPanel;
import view.KPSmartFrame;
import view.eventsView.CustomerPriceChangePanel;
import view.eventsView.MailDeliveryPanel;
import view.eventsView.RouteDiscontinuePanel;
import view.eventsView.TransportCostChangePanel;

public class KPSmartController {

	private KPSmartFrame kpsmartGUI;
	private KPSmartModel kpsmartModel;
	private int ListenerCount = 1; // debugging purposes // used to print where in the code the
									// listener was called.
	public KPSmartController() {
		kpsmartGUI = new KPSmartFrame(new KeyAction(), new MouseAction() ,new ViewActionListener());
		kpsmartModel = new KPSmartModel();
		System.out.println("Calling from Controller"); //debugging
	}

	public class KeyAction implements KeyListener {


		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println(ListenerCount + 42); // debugging purposes - mc
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(ListenerCount + 46);  // debugging purposes - mc
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// example implementation.
			System.out.println(ListenerCount + 49); // debugging purposes - mc

		}
	}

	/**
	 * MouseAction class to set actionListeners to components inside the GUI.
	 */
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
			//System.out.println(ListenerCount + 78); // debugging purposes - mc
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//System.out.println(ListenerCount + 82); // debugging purposes - mc
		}
	}

	/**
	 * Action listener class for the menu.
	 */
	public class ViewActionListener implements ActionListener {

		private String originSelection = "" ;
		private String destinationSelection = "" ;
		private String weightSelection = "" ;
		private String volumeSelection = "" ;
		private String prioritySelection = "" ;

		// Bobo please add these fields into the forms.

		private String weightCost, weightPrice, volumeCost, volumePrice ;

		private String newWeightCostSelection = "" ;
		private String newVolumeSelection = "" ;
		private String companySelection = "" ;
		private String typeSelection = "" ;

		//private String departureDay = "" ;
		//private String frequencySelection = "" ;
		private String durationSelection = "" ;

		@Override
		public void actionPerformed(ActionEvent e) {

			// View Figures Panel
			if (e.getActionCommand().equals("Total Revenue")){
				System.out.println("Total Revenue"); //debugging - mc
				//kpsmartModel.getTotalRevenue(); //TODO: could this return INT, STRING
				//kpsmartGui.renderTotalRevenue();//TODO: Could pass in STRING , INT
			}else if (e.getActionCommand().equals("Total Expenditure")){
				System.out.println("Total Expenditure");//debugging - mc
				//kpsmartModel.getTotalExpenditure(); //TODO: could this return INT, STRING
				//kpsmartGui.renderTotalExpenditure();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Total No. of Events")){
				System.out.println("Total No. of Events");//debugging - mc
				//kpsmartModel.getTotalEvents(); //TODO: could this return INT, STRING
				//kpsmartGui.renderTotalEvents();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Amount of Mails")){
				System.out.println("Amount of Mails");//debugging - mc
				//kpsmartModel.getTotalMail(); //TODO: could this return INT, STRING
				//kpsmartGui.renderTotalMail();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("AVG Delivery Times")){
				System.out.println("AVG Delivery Times");//debugging - mc
				//kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Critical Routes")){
				System.out.println("Critical Routes");//debugging - mc
				//kpsmartModel.getCriticalRoutes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderCriticalRoutes();//TODO: Could pass in STRING , INT
			}



			// Business Events Panel
			else if (e.getActionCommand().equals("Mail Delivery")){
				System.out.println("Mail Delivery");//debugging - mc
				MailDeliveryPanel originSelection = (MailDeliveryPanel) kpsmartGUI.getCanvas().getMainDisplayPanel();
				kpsmartGUI.getCanvas().setMainDisplayPanel("MailDeliveryPanel");

				//kpsmartModel.getMailDelivery(); //TODO: could this return INT, STRING
				//kpsmartGui.renderMailDelivery();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Route Discontinue")){
				System.out.println("Route Discontinue");//debugging - mc
				RouteDiscontinuePanel originSelection = (RouteDiscontinuePanel) kpsmartGUI.getCanvas().getMainDisplayPanel();
				kpsmartGUI.getCanvas().setMainDisplayPanel("RouteDiscontinuePanel");


				//kpsmartModel.getAveragfeTimes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Customer Price Update")){
				System.out.println("Customer Price Update");//debugging - mc
				CustomerPriceChangePanel originSelection = (CustomerPriceChangePanel) kpsmartGUI.getCanvas().getMainDisplayPanel();
				kpsmartGUI.getCanvas().setMainDisplayPanel("CustomerPriceChangePanel");


				//kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Transport Cost Update")){
				System.out.println("Transport Cost Update");//debugging - mc
				TransportCostChangePanel originSelection = (TransportCostChangePanel) kpsmartGUI.getCanvas().getMainDisplayPanel();
				kpsmartGUI.getCanvas().setMainDisplayPanel("TransportCostChangePanel");



				//kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}
			else if(e.getActionCommand().equals("Weight:")){
					System.out.println("Weight:"); // able to be implemented if JTextField componenets have listeners.
			}
			else if(e.getActionCommand().equals("Volume:")){
					System.out.println("Volume:"); // able to be implemented if JTextField componenets have listeners.
			}
			//Submit on Mail Delivery
			else if (e.getActionCommand().equals("Submit")){
			System.out.println("Submit");//debugging - mc
			//	kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
			//	kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

//			"CustomerPriceChangePanel":
//			"MailDeliveryPanel":
//			"RouteDiscontinuePanel":
//			"TransportCostChangePanel":

			// getter methods set inside

			MailDeliveryPanel panel = (MailDeliveryPanel) kpsmartGUI.getCanvas().getMainDisplayPanel();

			String originSelection = panel.getOriginComboBoxString();
			String destinationSelection = panel.getDestinationComboBoxString();
			String weightSelection = panel.getWeightTextFieldString();
			String volumeSelection = panel.getVolumeTextFieldString();
			String prioritySelection = panel.getPriorityComboBoxString();

			System.out.println("Calling parameters from line 209 - Controller class" + originSelection + destinationSelection + weightSelection + volumeSelection + prioritySelection);



			/**
			 * Current Fields available from GUI,
			 *
			 * Origin 		// BLANK!! NEED FIELDS ADDED TO JCOMBOBOX SELECTION
			 * Destination  // BLANK!! NEED FIELDS ADDED TO JCOMBOBOX SELECTION
			 * Weight
			 * Volume
			 * Priority	    // BLANK!! NEED FIELDS ADDED TO JCOMBOBOX SELECTION
			 *
			 * BOBO implement these fields to the FORMS - max copley
			 *
			 * New Weight Cost -newWeightCostSelection
			 * New Volume Cost - newVolumeSelection
			 * Company - companySelection
			 * Type - typeSelection
			 * Departure Day - departureDay
			 *
			 */



			/**
			 * TODO: Customer price change
			 * Parameters:
			 * origin, destination, priority, new weight cost, new volume cost
			 */

			// waiting on GUI form implementation to return the parameters to the KPSmartMODEL
			//kpsmartModel.changeCustomerPrice(originSelection, destinationSelection, prioritySelection, newWeightCostSelection, newVolumeSelection);

			/**
			 * TODO: Process mail
			 * origin, destination, weight, volume, priority
			 */
			kpsmartModel.processMail(originSelection, destinationSelection, weightSelection, volumeSelection, prioritySelection);

			//	public boolean changeTransportPrice(String origin, String destination, String carrier, String typeString, String newWeightCostString,
			//String newVolumeCostString)

			kpsmartModel.changeTransportPrice(originSelection, destinationSelection, companySelection, typeSelection, newWeightCostSelection, newVolumeSelection);

//			public boolean addRoute(String origin, String destination, String company, String durationString, String typeString,
//					String customerPriceWeight, String customerPriceVolume, String transportCostWeight,
//					String transportCostVolume) { // add me, private String weightCost, weightPrice, volumeCost, volumePrice ; - transport Cost Panel

			kpsmartModel.addRoute(originSelection, destinationSelection, companySelection, durationSelection, typeSelection, weightCost, weightPrice, volumeCost, volumePrice );
			/**
			 * TODO: Route discontinue
			 * origin, destination,company, type
			 */
			kpsmartModel.discontinueRoute(originSelection, destinationSelection, companySelection, typeSelection);
			}
		}
	}
}
