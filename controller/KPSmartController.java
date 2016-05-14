package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.KPSmartModel;
import view.KPSmartFrame;

public class KPSmartController {

	private KPSmartFrame kpsmartGUI;
	private KPSmartModel kpsmartModel;
	private int ListenerCount = 1; // debugging purposes // used to print where in the code the
									// listener was called.
	public KPSmartController() {
		kpsmartGUI = new KPSmartFrame(new KeyAction(), new MouseAction() ,new ViewActionListener());
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
		@Override
		public void actionPerformed(ActionEvent e) {

			// View Figures Panel
			if (e.getActionCommand().equals("Total Revenue")){
				System.out.println(ListenerCount++); //debugging - mc
				kpsmartModel.getTotalRevenue(); //TODO: could this return INT, STRING
				kpsmartGui.renderTotalRevenue();//TODO: Could pass in STRING , INT



			}else if (e.getActionCommand().equals("Total Expenditure")){
				System.out.println(ListenerCount++);//debugging - mc
				kpsmartModel.getTotalExpenditure(); //TODO: could this return INT, STRING
				kpsmartGui.renderTotalExpenditure();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Total No. of Events")){
				System.out.println(ListenerCount++);//debugging - mc
				kpsmartModel.getTotalEvents(); //TODO: could this return INT, STRING
				kpsmartGui.renderTotalEvents();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Amount of Mails")){
				System.out.println(ListenerCount++);//debugging - mc
				kpsmartModel.getTotalMail(); //TODO: could this return INT, STRING
				kpsmartGui.renderTotalMail();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("AVG Delivery Times")){
				System.out.println(ListenerCount++);//debugging - mc
				kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Critical Routes")){
				System.out.println(ListenerCount++);//debugging - mc
				kpsmartModel.getCriticalRoutes(); //TODO: could this return INT, STRING
				kpsmartGui.renderCriticalRoutes();//TODO: Could pass in STRING , INT

			}

			// Business Events Panel
			else if (e.getActionCommand().equals("Mail Delivery")){
				System.out.println(ListenerCount++);//debugging - mc
				kpsmartModel.getMailDelivery(); //TODO: could this return INT, STRING
				kpsmartGui.renderMailDelivery();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Route Discontinue")){
				System.out.println(ListenerCount++);//debugging - mc
				kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Customer Price Update")){
				System.out.println(ListenerCount++);//debugging - mc
				kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}else if (e.getActionCommand().equals("Transport Cost Update")){
				System.out.println(ListenerCount++);//debugging - mc
				kpsmartModel.getAverageTimes(); //TODO: could this return INT, STRING
				kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING , INT

			}
		}
	}
}
