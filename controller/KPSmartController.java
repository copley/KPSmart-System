package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.KPSmartGUI;
import model.KPSmartModel;

public class KPSmartController {

	private KPSmartGUI kpsmartGUI;
	private KPSmartModel kpsmartModel;
	private int ListenerCount = 1; // debugging purposes // used to print where in the code the
									// listener was called.

	public KPSmartController(KPSmartGUI gui, KPSmartModel model) {
		kpsmartGUI = gui;
		kpsmartModel = model;
		this.init_KPSmartGuiListeners();
	}

	public void init_KPSmartGuiListeners() {
		ButtonListener bl = new ButtonListener();
		KeyListener saction = new KeyAction();
		MyMouseAction maction = new MyMouseAction();
		MenuActionListener ma = new MenuActionListener();
		this.kpsmartGUI.initializeMenuListeners(ma);

		// this.setUpGui.setListener(bl);
		kpsmartGUI.getFrame().addKeyListener(saction);
	}

	public class KeyAction implements KeyListener {

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
			if (e.getKeyCode() == KeyEvent.VK_A) {
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
			}
		}
	}

	/**
	 * MouseAction class to set actionListeners to components inside the GUI.
	 */
	public class MyMouseAction implements MouseListener {
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

	public void setGameGUIVisible(boolean bool) {
		this.kpsmartGUI.setVisiblity(bool);
	}

	/**
	 * Button Listener for the Setup
	 */
	public class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	/**
	 * Action listener class for the menu.
	 */
	class MenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println(ListenerCount + 107);

			if (actionEvent.getActionCommand().equalsIgnoreCase("LogOut")) {
				System.out.println("Log out requested - to be implemented");
				// KPSmartGUI.requestLogOutConfirmation();

			} else if (actionEvent.getActionCommand().equalsIgnoreCase("LogOutConfirmed")) {
				System.out.println("Log out confirmed - to be implemented");

				// KPSmartModel.saveData();
				// KPSmartGUI.logInView();
			} else if (actionEvent.getActionCommand().equalsIgnoreCase("LogIn")) {
				System.out.println("Log in requested - to be implemented");
				// KPSmartGUI.requestLoginDetails();
			} else if (actionEvent.getActionCommand().equalsIgnoreCase("LogInDetailsProvided")) {
				System.out.println("Log in details provided - to be implemented");
				//KPSmartModel.Employees.confirmDetails(somehowGetValues...);
			}

		}
	}
}
