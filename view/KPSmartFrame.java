package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.KPSmartController.*;
import model.events.inputs.*;
import view.eventsView.MailDeliveryPanel;

@SuppressWarnings("serial")
public final class KPSmartFrame extends JFrame {

	private KPSmartCanvas canvas;
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 650;

	// public KPSmartFrame() {
	// super("KPSmart");
	// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	// setResizable(true);
	//
	// setLayout(new BorderLayout());
	//
	// canvas = new KPSmartCanvas(this);
	// add(canvas);
	//
	// createMenu();
	// pack();
	//
	// setVisible(true);
	//
	// this.addWindowListener(new WindowAdapter() {
	// @Override
	// public void windowClosing(WindowEvent we) {
	// String[] options = { "YES", "Cancel" };
	// JPanel panel = new JPanel();
	// JLabel label = new JLabel("Are you sure you want to quit the system?");
	// panel.add(label);
	// int selectedOption = JOptionPane.showOptionDialog(null, panel,
	// "Warning!!!", JOptionPane.DEFAULT_OPTION,
	// JOptionPane.WARNING_MESSAGE, null, options, options[1]);
	// if (selectedOption == 0) {
	// System.exit(0);
	// }
	// }
	// });
	// }

	public KPSmartFrame(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener, ViewWindowAdapter viewWindowAdapter, List<String> siteNames) {

		super("KPSmart");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);

		setLayout(new BorderLayout());

		canvas = new KPSmartCanvas(this, keyAction, mouseAction, viewActionListener, siteNames);
		add(canvas);

		createMenu();
		pack();

		setVisible(true);

		this.addWindowListener(viewWindowAdapter);
	}

	private void createMenu() {
		// set up the menu bar
		JMenuBar menu = new JMenuBar();

		final JMenu file = new JMenu("File");

		final JMenuItem quit = new JMenuItem("Quit");

		file.setMnemonic('F');
		quit.setMnemonic('Q');

		menu.add(file);
		file.add(quit);

		setJMenuBar(menu);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
	}

	public KPSmartCanvas getCanvas() {
		return canvas;
	}

	public void setMainDisplayPanel(String panelName) {
		remove(canvas);
		canvas.setMainDisplayPanel(panelName);
		add(canvas);
		validate();
		repaint();
	}

	public MailProcessInput getMailDeliveryInput() {
		MailDeliveryPanel panel = (MailDeliveryPanel) canvas.getMainDisplayPanel();

		String originSelection = panel.getOriginComboBoxString();
		String destinationSelection = panel.getDestinationComboBoxString();
		String weightSelection = panel.getWeightTextFieldString();
		String volumeSelection = panel.getVolumeTextFieldString();
		String prioritySelection = panel.getPriorityComboBoxString();

		MailProcessInput i = new MailProcessInput();
		i.setOrigin(originSelection);
		i.setDestination(destinationSelection);
		i.setWeight(weightSelection);
		i.setVolume(volumeSelection);
		i.setPriority(prioritySelection);

		return i;
	}

//	public Input getAddNewRouteInput() {
//		AddNewRoutePanel panel = (AddNewRoutePanel) canvas.getMainDisplayPanel();
//
//		String originSelection = panel.getOriginComboBoxString();
//		String destinationSelection = panel.getDestinationComboBoxString();
//		String weightSelection = panel.getWeightTextFieldString();
//		String volumeSelection = panel.getVolumeTextFieldString();
//		String prioritySelection = panel.getPriorityComboBoxString();
//
//		Input i = new Input();
//		i.setOrigin(originSelection);
//		i.setDestination(destinationSelection);
//		i.setWeight(weightSelection);
//		i.setVolume(volumeSelection);
//		i.setPriority(prioritySelection);
//
//		return i;
//	}

}