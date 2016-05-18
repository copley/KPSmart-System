package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import model.KPSmartModel;
import view.KPSmartFrame;

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
		//System.out.println("Calling from KeyAction");

		@Override
		public void keyTyped(KeyEvent e) {
			//System.out.println(ListenerCount + 42); // debugging purposes - mc
		}

		@Override
		public void keyPressed(KeyEvent e) {
			//System.out.println(ListenerCount + 46);  // debugging purposes - mc
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// example implementation.
			//System.out.println(ListenerCount + 49); // debugging purposes - mc

		}
	}

	/**
	 * MouseAction class to set actionListeners to components inside the GUI.
	 */
	 public class MouseAction implements MouseListener {
		 //System.out.println("Calling from MouseAction");

		 @Override
		public void mouseClicked(MouseEvent e) {
			//System.out.println(ListenerCount + 65); // debugging purposes - mc
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//System.out.println(ListenerCount + 69); // debugging purposes - mc
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//System.out.println(ListenerCount + 74); // debugging purposes - mc
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
		// TODO: add some stuff here to get input from gui and tell model to do something then output back to gui
		/**
		 * For ammendment, Created Public Strings for pre implementation.
		 * Necessary before GUI allows controller to access View Strings.
		 *
		 *
		 */

		private String originSelection = "" ;
		private String destinationSelection = "" ;
		private String weightSelection = "" ;
		private String volumeSelection = "" ;
		private String prioritySelection = "" ;

		// Bobo please add these fields into the forms.
		private String newWeightCostSelection = "" ;
		private String newVolumeSelection = "" ;
		private String companySelection = "" ;
		private String typeSelection = "" ;
		private String departureDay = "" ;
		private String frequencySelection = "" ;
		private String durationSelection = "" ;

		@Override
		public void actionPerformed(ActionEvent e) {

			// View Figures Panel
			if (e.getActionCommand().equals("Total Revenue")){
				System.out.println(ListenerCount++); //debugging - mc
				//kpsmartModel.getTotalRevenue(); //TODO: could this return INT, STRING
				//kpsmartGui.renderTotalRevenue();//TODO: Could pass in STRING , INT



			}else if (e.getActionCommand().equals("Total Expenditure")){
				System.out.println(ListenerCount++);//debugging - mc
				//kpsmartModel.getTotalExpenditure(); //TODO: could this return INT, STRING
				//kpsmartGui.renderTotalExpenditure();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Total No. of Events")){
				System.out.println(ListenerCount++);//debugging - mc
				//kpsmartModel.getTotalEvents(); //TODO: could this return INT, STRING
				//kpsmartGui.renderTotalEvents();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Amount of Mails")){
				System.out.println(ListenerCount++);//debugging - mc
				//kpsmartModel.getTotalMail(); //TODO: could this return INT, STRING
				//kpsmartGui.renderTotalMail();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("AVG Delivery Times")){
				System.out.println(ListenerCount++);//debugging - mc
				//kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Critical Routes")){
				System.out.println(ListenerCount++);//debugging - mc
				//kpsmartModel.getCriticalRoutes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderCriticalRoutes();//TODO: Could pass in STRING , INT

			}

			// Business Events Panel
			else if (e.getActionCommand().equals("Mail Delivery")){
				System.out.println(ListenerCount++);//debugging - mc
				//kpsmartModel.getMailDelivery(); //TODO: could this return INT, STRING
				//kpsmartGui.renderMailDelivery();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Route Discontinue")){
				System.out.println(ListenerCount++);//debugging - mc
				//kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Customer Price Update")){
				System.out.println(ListenerCount++);//debugging - mc
				//kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Transport Cost Update")){
				System.out.println(ListenerCount++);//debugging - mc
				//kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				//kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}
			else if(e.getActionCommand().equals("Weight:")){
				//	System.out.println(ListenerCount++); // able to be implemented if JTextField componenets have listeners.
			}
			else if(e.getActionCommand().equals("Volume:")){
				//	System.out.println(ListenerCount++); // able to be implemented if JTextField componenets have listeners.
			}
			//Submit on Mail Delivery
			else if (e.getActionCommand().equals("Submit")){
			System.out.println("Submit");//debugging - mc
			//	kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
			//	kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			// getter methods set inside
			String originSelection = kpsmartGUI.getCanvas().getMainDisplayPanel().getOriginComboBox().getSelectedItem().toString();
			String destinationSelection = kpsmartGUI.getCanvas().getMainDisplayPanel().getDestinationComboBox().getSelectedItem().toString();

			String weightSelection = kpsmartGUI.getCanvas().getMainDisplayPanel().getWeightTextField().getText();
			String volumeSelection = kpsmartGUI.getCanvas().getMainDisplayPanel().getVolumeTextField().getText();

			String prioritySelection = kpsmartGUI.getCanvas().getMainDisplayPanel().getPriorityComboBox().getSelectedItem().toString();

			System.out.println(originSelection + destinationSelection + weightSelection + volumeSelection + prioritySelection);

			//System.out.println(volumeSelection + weightSelection);
			//TODO:
			//	kpsmartModel.passOriginSelection("originSelection");
			//	kpsmartModel.passDestinationSelection("destinationSelection");
			//	kpsmartModel.passWeightSelection("weigthSelection");



//
//			kpsmartModel.passWeightSelection("weightSelection");
//			kpsmartModel.passVolumeSelection("volumeSelection");
//			kpsmartModel.addRoute(weightSelection, volumeSelection)
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
			//kpsmartModel.ChangeCustomerPrice(originSelection, destinationSelection, prioritySelection, newWeightCostSelection, newVolumeSelection);

			/**
			 * TODO: Process mail
			 * origin, destination, weight, volume, priority
			 */
			kpsmartModel.ProcessMail(originSelection, destinationSelection, weightSelection, volumeSelection, prioritySelection);
			/**
			 * TODO: Transport cost change
			 * origin, destination,company, type, new weight cost, new volume, departure day, frequency, duration
			 */
			//kpsmartModel.ChangeTransportPrice(originSelection, destinationSelection, companySelection, typeSelection, newWeightCostSelection, newVolumeSelection, departureDay ,frequencySelection, durationSelection);
			/**
			 * TODO: Route addition
			 * origin, destination,company, type, new weight cost, new volume, departure day, frequency, duration
			 */
			//kpsmartModel.AddRoute(originSelection, destinationSelection, companySelection, typeSelection, newWeightCostSelection, newVolumeSelection, departureDay,  frequencySelection, durationSelection);
			/**
			 * TODO: Route discontinue
			 * origin, destination,company, type
			 */
			//kpsmartModel.RemoveRoute(originSelection, destinationSelection, companySelection, typeSelection);
			}
		}
	}
}
