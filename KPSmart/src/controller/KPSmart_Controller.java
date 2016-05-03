package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.KPSmart_GUI;
import model.KPSmart_Model;

public class KPSmart_Controller {

	private KPSmart_GUI kpsmartGUI;
	private KPSmart_Model kpsmartModel;
	private int ListenerCount = 1; // used to print where in the code the
									// listener was called.

	public KPSmart_Controller(KPSmart_GUI gui, KPSmart_Model model) {
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
			System.out.println(ListenerCount + 65);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println(ListenerCount + 69);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println(ListenerCount + 74);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println(ListenerCount + 78);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println(ListenerCount + 82);
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
			System.out.println(ListenerCount + 97);
		}
	}

	/**
	 * Action listener class for the menu.
	 */
	public class MenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println(ListenerCount + 107);
			if (actionEvent.getActionCommand().equalsIgnoreCase("Exit")) {
				System.exit(1);
			} else if (actionEvent.getActionCommand().equalsIgnoreCase("Save Game")) {
			} else if (actionEvent.getActionCommand().equalsIgnoreCase("Start Game")) {
				kpsmartGUI.setVisiblity(true);
			}
		}
	}
}
