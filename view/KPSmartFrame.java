package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.KPSmartController.*;
import model.events.inputs.*;
import view.eventsView.AddNewRoutePanel;
import view.eventsView.BusinessFiguresPanel;
import view.eventsView.CustomerPriceChangePanel;
import view.eventsView.MailDeliveryPanel;
import view.eventsView.ReviewEventsPanel;
import view.eventsView.RouteDiscontinuePanel;
import view.eventsView.TransportCostChangePanel;

/**
 * The outside frame of KPSmart.
 *
 * @author Shenbo Xuan
 *
 */

@SuppressWarnings("serial")
public final class KPSmartFrame extends JFrame {

	/**
	 * The theme colour for the system.
	 */
	public static final Color THEME_COLOR = new Color(220, 255, 255);

	private KPSmartCanvas canvas;
	private LogInPanel login;
	private static final int FRAME_WIDTH = 855;
	private static final int FRAME_HEIGHT = 650;

	public KPSmartFrame(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener,
			ViewWindowAdapter viewWindowAdapter, List<String> origins, List<String> destinations,
			List<String> companies) {

		super("KPSmart");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(true);

		setLayout(new BorderLayout());

		canvas = new KPSmartCanvas(this, keyAction, mouseAction, viewActionListener, origins, destinations, companies);
		add(canvas);

		pack();

		this.addWindowListener(viewWindowAdapter);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * Set the main display panel.
	 *
	 * @param panelName
	 */
	public void setMainDisplayPanel(String panelName) {
		remove(canvas);
		canvas.setMainDisplayPanel(panelName);
		add(canvas);
		validate();
		repaint();
	}

	public JPanel getCanvas() {
		return canvas;
	}

	public void resetTextFields() {
		canvas.resetTextFields();
	}

	public void updateSites(String origin, String destination) {
		canvas.updateSites(origin, destination);
	}

	public void updateCompanies(String company) {
		canvas.updateCompanies(company);
	}

	public void popupMessage(boolean successful, String message) {
		if (successful) {
			JOptionPane.showMessageDialog(this, message);
		} else {
			JOptionPane.showMessageDialog(this, message);
		}
	}

	/*
	 * =========================================================================
	 * START OF Methods to get information for the model
	 * =========================================================================
	 */
	public MailProcessInput getMailDeliveryInput() {
		MailDeliveryPanel panel = (MailDeliveryPanel) canvas.getMainDisplayPanel();

		String originSelection = panel.getOriginComboBoxString();
		String destinationSelection = panel.getDestinationComboBoxString();
		String weightSelection = panel.getWeightTextFieldString();
		String volumeSelection = panel.getVolumeTextFieldString();
		String prioritySelection = panel.getPriorityComboBoxString();

		return new MailProcessInput(originSelection, destinationSelection, weightSelection, volumeSelection,
				prioritySelection);
	}

	public NewRouteInput getNewRouteInput() {
		AddNewRoutePanel panel = (AddNewRoutePanel) canvas.getMainDisplayPanel();

		String origin = panel.getOriginTextFieldString();
		String destination = panel.getDestinationTextFieldString();
		String company = panel.getTransportCompanyTextFieldString();
		String duration = panel.getHoursToDeliverTextFieldString();
		String type = panel.getModeComboBoxString();
		String customerPriceWeight = panel.getCustomerPriceWeightTextFieldString();
		String customerPriceVolume = panel.getCustomerPriceVolumeTextFieldString();
		String transportPriceWeight = panel.getTransportPriceWeightTextFieldString();
		String transportPriceVolume = panel.getTransportPriceVolumeTextFieldString();

		return new NewRouteInput(origin, destination, company, duration, type, customerPriceWeight, customerPriceVolume,
				transportPriceWeight, transportPriceVolume);
	}

	public DiscontinueInput getDiscontinueInput() {
		RouteDiscontinuePanel panel = (RouteDiscontinuePanel) canvas.getMainDisplayPanel();

		String origin = panel.getOriginComboBoxString();
		String destination = panel.getDestinationComboBoxString();
		String company = panel.getTransportCompanyComboBoxString();
		String type = panel.getTypeComboBoxString();

		return new DiscontinueInput(origin, destination, company, type);
	}

	public CustomerPriceInput getCustomerPriceInput() {
		CustomerPriceChangePanel panel = (CustomerPriceChangePanel) canvas.getMainDisplayPanel();

		String origin = panel.getOriginComboBoxString();
		String destination = panel.getDestinationComboBoxString();
		String priority = panel.getPriorityComboBoxString();
		String weightCost = panel.getNewWeightCostTextFieldString();
		String volumeCost = panel.getNewVolumeCostTextFieldString();

		return new CustomerPriceInput(origin, destination, priority, weightCost, volumeCost);
	}

	public TransportCostInput getTransportCostInput() {
		TransportCostChangePanel panel = (TransportCostChangePanel) canvas.getMainDisplayPanel();

		String origin = panel.getOriginComboBoxString();
		String destination = panel.getDestinationComboBoxString();
		String company = panel.getTransportCompanyComboBoxString();
		String type = panel.getTypeComboBoxString();
		String weightCost = panel.getNewPriceWeightTextFieldString();
		String volumeCost = panel.getNewPriceVolumeTextFieldString();
		String duration = panel.getHoursToDeliverTextFieldString();

		return new TransportCostInput(origin, destination, company, type, weightCost, volumeCost, duration);
	}
	/*
	 * =========================================================================
	 * END OF Methods to get information for the model
	 * =========================================================================
	 */

	public void displayEvent(String eventString) {
		ReviewEventsPanel panel = (ReviewEventsPanel) canvas.getMainDisplayPanel();

		panel.setEventStringLabel(eventString);
		validate();
		repaint();
	}

	public String getUsername() {
		return login.getUserID();
	}

	public String getPassword() {
		return login.getPassword();
	}

	public void setLoggedIn(boolean isManager, String name) {
		login.showWindow(false);
		String title = "KPSmart - ";
		title += "Current log in: ";
		title += name;
		title += "(" + login.getUserID() + ") ";
		title += isManager ? "as a manager" : "as a clerk";
		setTitle(title);
		canvas.displayReviewEventButton(isManager);
		this.setVisible(true);
		login.clearInputs();
	}

	public void showLogin(ViewActionListener viewActionListener) {
		login = new LogInPanel(viewActionListener);
		// Show ourselves
		login.showWindow(true);
	}

	public void setLoggedOut() {
		this.setVisible(false);
		login.showWindow(true);
	}

	public void displayFigures(String figures) {
		BusinessFiguresPanel panel = (BusinessFiguresPanel) canvas.getMainDisplayPanel();

		panel.setFiguresLabel(figures);
		validate();
		repaint();
	}
}