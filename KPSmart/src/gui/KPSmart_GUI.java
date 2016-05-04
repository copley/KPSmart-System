package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuListener;

public class KPSmart_GUI {

	private JFrame frame;
	private JPanel contentPane;
	private JPanel lowerPanel;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem mntmStatgame;
	private JMenuItem mntmSavegame;
	private JMenu mnGame;
	private JMenuItem mntmExit;

	public KPSmart_GUI() { // package protected
		// we can pass in fields to the GUI
		// this.field = passedinFiedld;
		this.initialize();
	}

	private void initialize() {
		frame = new JFrame("KPSmart");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1500, 1120);

		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		lowerPanel = new JPanel();
		lowerPanel.setBackground(new Color(14, 34, 0));
		lowerPanel.setBounds(0, 820, 1500, 200);
		contentPane.add(lowerPanel);
		lowerPanel.setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1500, 20);
		contentPane.add(menuBar);

		menu = new JMenu("File");
		menuBar.add(menu);

		mntmStatgame = new JMenuItem("Log In");
		menu.add(mntmStatgame);
		mntmSavegame = new JMenuItem("Log Out");
		menu.add(mntmSavegame);

		mnGame = new JMenu("System");
		menuBar.add(mnGame);

		mntmExit = new JMenuItem("Exit");
		mnGame.add(mntmExit);

		frame.setVisible(true);
		frame.setResizable(false);

	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getLowerPanel() {
		return lowerPanel;
	}

	public JMenuBar getJMenuBar() {
		return menuBar;
	}

	public JMenu getMenu() {
		return menu;
	}

	public JMenuItem getMntmStatgame() {
		return mntmStatgame;
	}

	public JMenu getMnGame() {
		return mnGame;
	}

	public JMenuItem getMntmExit() {
		return mntmExit;
	}

	/**
	 * Initailization method to add listeners to individual components. This is
	 * set from the controller class.
	 *
	 * @param maction
	 *            MyMouseAction
	 * @param saction
	 *            KeyListener
	 */
	public void initializeMenuListeners(MenuListener maction, KeyListener saction) {

	}

	/**
	 * Initailization method to add listeners to individual components. This is
	 * set from the controller class.
	 *
	 * @param ma
	 *            ActionListener
	 */
	public void initializeMenuListeners(ActionListener ma) {
		mntmExit.addActionListener(ma);
		mntmStatgame.addActionListener(ma);
		mntmSavegame.addActionListener(ma);
	}

	public void setVisiblity(boolean bool) {
		frame.setVisible(bool);
	}

}
