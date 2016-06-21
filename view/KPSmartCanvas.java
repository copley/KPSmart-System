package view;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;
import view.eventsView.AddNewRoutePanel;
import view.eventsView.BusinessFiguresPanel;
import view.eventsView.CustomerPriceChangePanel;
import view.eventsView.MailDeliveryPanel;
import view.eventsView.ReviewEventsPanel;
import view.eventsView.RouteDiscontinuePanel;
import view.eventsView.TransportCostChangePanel;

import javax.swing.GroupLayout.Alignment;

/**
 * The canvas on the frame of KPSmart contains all other GUI elements.
 *
 * @author Shenbo Xuan
 *
 */

@SuppressWarnings("serial")
public class KPSmartCanvas extends JPanel {

	/**
	 * represents the canvas width
	 */
	private final int canvasWidth;

	/**
	 * represents the canvas height
	 */
	private final int canvasHeight;

	/**
	 * the parent frame
	 */
	private final JFrame frame;

	private AbstractMainDisplayPanel mainDisplayPanel;
	private JPanel operationPanel;

	private HomepagePanel homepagePanel;
	private AddNewRoutePanel addNewRoutePanel;
	private CustomerPriceChangePanel customerPriceChangePanel;
	private MailDeliveryPanel mailDeliveryPanel;
	private RouteDiscontinuePanel routeDiscontinuePanel;
	private TransportCostChangePanel transportCostChangePanel;
	private ReviewEventsPanel reviewEventsPanel;
	private BusinessFiguresPanel businessFiguresPanel;

	/**
	 * construct an empty KPSmart canvas
	 *
	 * @param frame
	 * @param viewActionListener
	 * @param mouseAction
	 * @param keyAction
	 * @param siteNames
	 */
	public KPSmartCanvas(JFrame frame, KeyAction keyAction, MouseAction mouseAction,
			ViewActionListener viewActionListener, List<String> origins, List<String> destinations,
			List<String> companies) {
		canvasWidth = frame.getWidth();
		canvasHeight = frame.getHeight();
		this.frame = frame;
		setBackground(new Color(77, 115, 166));
		operationPanel = new OperationPanel(keyAction, mouseAction, viewActionListener);

		// debug
		// mainDisplayPanel.add(new MailDeliveryPanel(keyAction, mouseAction,
		// viewActionListener));

		initialisePanels(keyAction, mouseAction, viewActionListener, origins, destinations, companies);
		mainDisplayPanel = homepagePanel;

		initialiseLayout();
	}

	/**
	 * Initialise the layout of the canvas
	 */
	protected void initialiseLayout() {

		removeAll();

		// utilise the group layout
		GroupLayout groupLayout = new GroupLayout(this);

		// set up the horizontal group
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(mainDisplayPanel, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE).addGap(18)
						.addComponent(operationPanel, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		// set up the vertical group
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(mainDisplayPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 506,
										Short.MAX_VALUE)
								.addComponent(operationPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		setLayout(groupLayout);
	}

	// initilise all the panels inside the canvas
	private void initialisePanels(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener,
			List<String> origins, List<String> destinations, List<String> companies) {
		homepagePanel = new HomepagePanel(keyAction, mouseAction, viewActionListener);
		addNewRoutePanel = new AddNewRoutePanel(keyAction, mouseAction, viewActionListener);
		customerPriceChangePanel = new CustomerPriceChangePanel(keyAction, mouseAction, viewActionListener, origins,
				destinations);
		mailDeliveryPanel = new MailDeliveryPanel(keyAction, mouseAction, viewActionListener, origins, destinations);
		routeDiscontinuePanel = new RouteDiscontinuePanel(keyAction, mouseAction, viewActionListener, origins,
				destinations, companies);
		transportCostChangePanel = new TransportCostChangePanel(keyAction, mouseAction, viewActionListener, origins,
				destinations, companies);
		reviewEventsPanel = new ReviewEventsPanel(keyAction, mouseAction, viewActionListener);
		businessFiguresPanel = new BusinessFiguresPanel(keyAction, mouseAction, viewActionListener);
	}

	/**
	 * Returns the Main Display Panel.
	 * @return
	 */
	public AbstractMainDisplayPanel getMainDisplayPanel() {
		return mainDisplayPanel;
	}

	/**
	 * Returns the operation panel.
	 * @return
	 */
	public JPanel getOperationPanel() {
		return operationPanel;
	}

	/**
	 * Set the main display panel.
	 * @param panelName
	 */
	public void setMainDisplayPanel(String panelName) {

		switch (panelName) {
		case "HomepagePanel":
			mainDisplayPanel = homepagePanel;
			break;
		case "AddNewRoutePanel":
			mainDisplayPanel = addNewRoutePanel;
			break;
		case "CustomerPriceChangePanel":
			mainDisplayPanel = customerPriceChangePanel;
			break;
		case "MailDeliveryPanel":
			mainDisplayPanel = mailDeliveryPanel;
			break;
		case "RouteDiscontinuePanel":
			mainDisplayPanel = routeDiscontinuePanel;
			break;
		case "TransportCostChangePanel":
			mainDisplayPanel = transportCostChangePanel;
			break;
		case "ReviewEventsPanel":
			mainDisplayPanel = reviewEventsPanel;
			break;
		case "BusinessFiguresPanel":
			mainDisplayPanel = businessFiguresPanel;
			break;
		default:
			System.out.println("Something went astray.");
		}

		initialiseLayout();
	}

	/**
	 * Reset all text fields in the main display panel
	 */
	public void resetTextFields() {
		mainDisplayPanel.resetTextFields();
	}

	/**
	 * Update the sites dropdown lists in main display panel.
	 *
	 * @param origin
	 * @param destination
	 */
	public void updateSites(String origin, String destination) {
		customerPriceChangePanel.addSites(origin, destination);
		mailDeliveryPanel.addSites(origin, destination);
		routeDiscontinuePanel.addSites(origin, destination);
		transportCostChangePanel.addSites(origin, destination);
	}

	/**
	 * Update the company dropdown list in main display panel.
	 *
	 * @param company
	 */
	public void updateCompanies(String company) {
		routeDiscontinuePanel.addCompanies(company);
		transportCostChangePanel.addCompanies(company);

	}

	/**
	 * Determine whether we display the review event button by the login type.
	 * if manager, display. Otherwise do not display.
	 *
	 * @param isManager
	 */
	public void displayReviewEventButton(boolean isManager) {
		((OperationPanel) operationPanel).setReviewEventButton(isManager);
		validate();
		repaint();
	}

}
