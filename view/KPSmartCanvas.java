package view;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;
import model.events.TransportCostChangeEvent;
import view.eventsView.AddNewRoutePanel;
import view.eventsView.CustomerPriceChangePanel;
import view.eventsView.MailDeliveryPanel;
import view.eventsView.RouteDiscontinuePanel;
import view.eventsView.TransportCostChangePanel;

import javax.swing.GroupLayout.Alignment;

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

	private AddNewRoutePanel addNewRoutePanel;
	private CustomerPriceChangePanel customerPriceChangePanel;
	private MailDeliveryPanel mailDeliveryPanel;
	private RouteDiscontinuePanel routeDiscontinuePanel;
	private TransportCostChangePanel transportCostChangePanel;

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
			ViewActionListener viewActionListener, List<String> siteNames) {
		canvasWidth = frame.getWidth();
		canvasHeight = frame.getHeight();
		this.frame = frame;
		setBackground(new Color(77, 115, 166));
		operationPanel = new OperationPanel(keyAction, mouseAction, viewActionListener);

		// debug
		// mainDisplayPanel.add(new MailDeliveryPanel(keyAction, mouseAction,
		// viewActionListener));

		initialisePanels(keyAction, mouseAction, viewActionListener, siteNames);
		mainDisplayPanel = mailDeliveryPanel;

		initialiseLayout();
	}

	protected void initialiseLayout() {

		if (getLayout() != null) {
			removeAll();
		}

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(mainDisplayPanel, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE).addGap(18)
						.addComponent(operationPanel, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

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

	private void initialisePanels(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener,
			List<String> siteNames) {
		addNewRoutePanel = new AddNewRoutePanel(keyAction, mouseAction, viewActionListener);
		customerPriceChangePanel = new CustomerPriceChangePanel(keyAction, mouseAction, viewActionListener, siteNames);
		mailDeliveryPanel = new MailDeliveryPanel(keyAction, mouseAction, viewActionListener, siteNames);
		routeDiscontinuePanel = new RouteDiscontinuePanel(keyAction, mouseAction, viewActionListener, siteNames);
		transportCostChangePanel = new TransportCostChangePanel(keyAction, mouseAction, viewActionListener, siteNames);
	}

	public AbstractMainDisplayPanel getMainDisplayPanel() {
		return mainDisplayPanel;
	}

	public JPanel getOperationPanel() {
		return operationPanel;
	}

	public void setMainDisplayPanel(String panelName) {

		switch (panelName) {
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
		default:
			System.out.println("Something went astray.");
		}

		initialiseLayout();
	}

	public void updateSites(List<String> newSites) {
//		customerPriceChangePanel.addSites(newSites);
		mailDeliveryPanel.addSites(newSites);
		routeDiscontinuePanel.addSites(newSites);
//		transportCostChangePanel.addSites(newSites);
	}

}
