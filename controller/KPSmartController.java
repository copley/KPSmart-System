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
		//TODO: make new gui here and pass the listeners through the constructor
		kpsmartGUI = new KPSmartFrame(new KeyAction(), new MouseAction() ,new ViewActionListener());
		kpsmartModel = new model;
		//TODO: Just commented this out for now - Bonnie
		//this.init_KPSmartGuiListeners();
	}


	//TODO: Just commented this out for now - Bonnie
//	public void init_KPSmartGuiListeners() {
//		KeyAction keyListener = new KeyAction();
//		MouseAction mouseListener =  new MouseAction();
//		ViewActionListener buttonListener = new KeyListener();
//
//		this.kpsmartGUI.addListeners(keyListener, mouseListener, buttonListener);
//	}

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

		}
	}

	/**
	 * MouseAction class to set actionListeners to components inside the GUI.
	 */
	 public class MouseAction implements MouseListener {
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
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println(ListenerCount + 107);
			// if actionEvent ==
			// else if ...





		}
	}
}
