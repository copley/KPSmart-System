package controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.WindowAdapter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.KPSmartModel;
import model.events.inputs.*;
import view.KPSmartFrame;

public class KPSmartController {

	private KPSmartFrame gui;
	private KPSmartModel model;
	private int ListenerCount = 1; // debugging purposes // used to print where

	private boolean mailDeliveryPanel = true;
	private boolean newRoutePanel = false;
	private boolean customerPricePanel = false;
	private boolean transportCostChangePanel = false;
	private boolean discontinueRoutePanel = false;

	public KPSmartController() {
		model = new KPSmartModel();
		gui = new KPSmartFrame(new KeyAction(), new MouseAction(), new ViewActionListener(), new ViewWindowAdapter(),
				model.getOrigins(), model.getDestinations(), model.getCompanies());
		gui.showLogin(new ViewActionListener());
		System.out.println("Calling from Controller"); // debugging
	}

	public class ViewWindowAdapter extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			String[] options = { "YES", "Cancel" };
			JPanel panel = new JPanel();
			JLabel label = new JLabel("Are you sure you want to quit the system?");
			panel.add(label);
			int selectedOption = JOptionPane.showOptionDialog(null, panel, "Warning!!!", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (selectedOption == 0) {
				model.save();
				System.exit(0);
			}
		}
	}

	public class ViewActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// ==========================================
			// START OF View Business Figures buttons
			// ==========================================
			if (e.getActionCommand().equals("Business Figures")) {
				gui.setMainDisplayPanel("BusinessFiguresPanel");
				gui.displayFigures(model.getFigures());

				mailDeliveryPanel = false;
				newRoutePanel = false;
				customerPricePanel = false;
				transportCostChangePanel = false;
				discontinueRoutePanel = false;
			} else if (e.getActionCommand().equals("Review Events")) {
				gui.setMainDisplayPanel("ReviewEventsPanel");
				gui.displayEvent(model.getLatestEvent());

				mailDeliveryPanel = false;
				newRoutePanel = false;
				customerPricePanel = false;
				transportCostChangePanel = false;
				discontinueRoutePanel = false;
			}
			// ==========================================
			// END OF View Business Figures buttons
			// ==========================================
			// ==========================================
			// START OF Browsing Business Events buttons
			// ==========================================
			else if (e.getActionCommand().equals("Previous Event")) {
				gui.displayEvent(model.getPreviousEvent());
			} else if (e.getActionCommand().equals("Next Event")) {
				gui.displayEvent(model.getNextEvent());
			}
			// ==========================================
			// END OF Browsing Business Events buttons
			// ==========================================
			// ==========================================
			// START OF Business Events buttons
			// ==========================================
			else if (e.getActionCommand().equals("Mail Delivery")) {
				System.out.println("Mail Delivery");// debugging - mc
				gui.setMainDisplayPanel("MailDeliveryPanel");

				mailDeliveryPanel = true;
				newRoutePanel = false;
				customerPricePanel = false;
				transportCostChangePanel = false;
				discontinueRoutePanel = false;
				// kpsmartModel.getMailDelivery(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderMailDelivery();//TODO: Could pass in STRING
				// , INT

			} else if (e.getActionCommand().equals("Add New Route")) {
				System.out.println("Add New Route");// debugging - mc
				gui.setMainDisplayPanel("AddNewRoutePanel");
				newRoutePanel = true;
				mailDeliveryPanel = false;
				customerPricePanel = false;
				transportCostChangePanel = false;
				discontinueRoutePanel = false;

			} else if (e.getActionCommand().equals("Route Discontinue")) {
				System.out.println("Route Discontinue");// debugging - mc
				gui.setMainDisplayPanel("RouteDiscontinuePanel");
				discontinueRoutePanel = true;
				mailDeliveryPanel = false;
				newRoutePanel = false;
				customerPricePanel = false;
				transportCostChangePanel = false;
				// kpsmartModel.getAveragfeTimes(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING
				// , INT

			} else if (e.getActionCommand().equals("Customer Price Update")) {
				System.out.println("Customer Price Update");// debugging - mc
				gui.setMainDisplayPanel("CustomerPriceChangePanel");
				customerPricePanel = true;
				mailDeliveryPanel = false;
				newRoutePanel = false;
				transportCostChangePanel = false;
				discontinueRoutePanel = false;

				// kpsmartModel.getAverageTimes(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING
				// , INT

			} else if (e.getActionCommand().equals("Transport Cost Update")) {
				System.out.println("Transport Cost Update");// debugging - mc
				gui.setMainDisplayPanel("TransportCostChangePanel");
				transportCostChangePanel = true;
				mailDeliveryPanel = false;
				newRoutePanel = false;
				customerPricePanel = false;
				discontinueRoutePanel = false;

				// kpsmartModel.getAverageTimes(); //TODO: could this return
				// INT, STRING
				// kpsmartGui.renderAverageTimes();//TODO: Could pass in STRING
				// , INT

			} else if (e.getActionCommand().equals("Weight:")) {
				System.out.println("Weight:");
			} else if (e.getActionCommand().equals("Volume:")) {
				System.out.println("Volume:");
			}
			// ==========================================
			// END OF Business Events buttons
			// ==========================================

			// ==========================================
			// START OF Submit for forms
			// ==========================================
			else if (e.getActionCommand().equals("Submit")) {
				if (mailDeliveryPanel) {
					MailProcessInput input = gui.getMailDeliveryInput();
					String modelReturn = model.processMail(input);
					if (modelReturn.isEmpty()) {
						// Notify success and return to homescreen
						gui.popupMessage(true, "Mail has been successfully processed");
						gui.setMainDisplayPanel("HomepagePanel");
					} else {
						gui.popupMessage(false, modelReturn);
					}
				} else if (newRoutePanel) {
					NewRouteInput input = gui.getNewRouteInput();

					String modelReturn = model.addNewRoute(input);
					if (modelReturn.isEmpty()) {
						// Update the list of sites in the gui if successful
						gui.updateSites(model.getNewOrigin(), model.getNewDestination());
						gui.updateCompanies(model.getNewCompany());
						// Notify success and return to homescreen
						gui.popupMessage(true, "Route has been successfully added");
						gui.setMainDisplayPanel("HomepagePanel");

					} else {
						// Notify failure and return to input panel
						gui.popupMessage(false, modelReturn);
					}
				} else if (discontinueRoutePanel) {
					DiscontinueInput input = gui.getDiscontinueInput();
					String modelReturn = model.discontinueRoute(input);
					if (modelReturn.isEmpty()) {
						// Notify success and return to homescreen
						gui.popupMessage(true, "Route has been successfully discontinued");
						gui.setMainDisplayPanel("HomepagePanel");
					} else {
						gui.popupMessage(false, modelReturn);
					}
				} else if (customerPricePanel) {
					CustomerPriceInput input = gui.getCustomerPriceInput();
					String modelReturn = model.changeCustomerPrice(input);
					if (modelReturn.isEmpty()) {
						// Notify success and return to homescreen
						gui.popupMessage(true, "Customer prices have been updated");
						gui.setMainDisplayPanel("HomepagePanel");
					} else {
						gui.popupMessage(false, modelReturn);
					}
				} else {
					TransportCostInput input = gui.getTransportCostInput();
					String modelReturn = model.changeTransportCost(input);
					if (modelReturn.isEmpty()) {
						// Notify success and return to homescreen
						gui.popupMessage(true, "Transport costs have been updated");
						gui.setMainDisplayPanel("HomepagePanel");
					} else {
						gui.popupMessage(false, modelReturn);
					}
				}
				gui.resetTextFields();
			} else if (e.getActionCommand().equals("Cancel")) {
				gui.setMainDisplayPanel("HomepagePanel");
				gui.resetTextFields();
			} else if (e.getActionCommand().equals("Reset")) {
				gui.resetTextFields();
			} else if (e.getActionCommand().equals("Log In")) {
				if (gui.getUsername().length() == 0) {
					gui.popupMessage(false, "Please enter a username");
				} else if (gui.getPassword().length() == 0) {
					gui.popupMessage(false, "Please enter a password");
				} else {
					boolean[] loggedIn = model.logIn(gui.getUsername(), gui.getPassword());
					if (loggedIn[0] == true){
						gui.setMainDisplayPanel("HomepagePanel");
						gui.setLoggedIn(loggedIn[1], model.getCurrentUser());
					}else {
						gui.popupMessage(false, "Incorrect Login");
					}
				}
			} else if (e.getActionCommand().equals("Log Out")) {
				model.logOut();
				gui.setLoggedOut();
			}
			// ==========================================
			// END OF Submit for forms
			// ==========================================
		}
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
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// System.out.println(ListenerCount + 82); // debugging purposes -
		}
	}
	/*
	 * =========================================================================
	 * END OF Methods for debugging purposes
	 * =========================================================================
	 */
}
