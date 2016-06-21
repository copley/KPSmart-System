package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

import javax.swing.JButton;

/**
 * A panel contains all buttons for business events.
 *
 * @author Shenbo Xuan
 *
 */

@SuppressWarnings("serial")
public class BusinessEventPanel extends AbstractPanel {

	private JButton mailDeliveryBt;
	private JButton addRouteBt;
	private JButton routeDiscontinueBt;
	private JButton customerPriceUpdateBt;
	private JButton transportCostUpdateBt;
	private JTextPane txtpnBusinessEvents;

	public BusinessEventPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		this.addKeyListener(keyAction);
		this.addMouseListener(mouseAction);

		mailDeliveryBt = new JButton("Mail Delivery");
		mailDeliveryBt.addActionListener(viewActionListener);

		addRouteBt = new JButton("Add New Route");
		addRouteBt.addActionListener(viewActionListener);

		routeDiscontinueBt = new JButton("Route Discontinue");
		routeDiscontinueBt.setFont(new Font("Dialog", Font.BOLD, 10));
		routeDiscontinueBt.addActionListener(viewActionListener);

		customerPriceUpdateBt = new JButton("Customer Price Update");
		customerPriceUpdateBt.setFont(new Font("Dialog", Font.BOLD, 9));
		customerPriceUpdateBt.addActionListener(viewActionListener);

		transportCostUpdateBt = new JButton("Transport Cost Update");
		transportCostUpdateBt.setFont(new Font("Dialog", Font.BOLD, 9));
		transportCostUpdateBt.addActionListener(viewActionListener);

		txtpnBusinessEvents = new JTextPane();
		txtpnBusinessEvents.setEditable(false);
		txtpnBusinessEvents.setFont(new Font("DejaVu Serif Condensed", Font.BOLD | Font.ITALIC, 13));
		txtpnBusinessEvents.setText("Business Events");
		txtpnBusinessEvents.setBackground(new Color(238, 238, 238));

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {

		// utilise the group layout
		GroupLayout groupLayout = new GroupLayout(this);

		// set up the horizontal group
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(26)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(mailDeliveryBt, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
										.addComponent(routeDiscontinueBt, GroupLayout.DEFAULT_SIZE, 158,
												Short.MAX_VALUE)
						.addComponent(customerPriceUpdateBt, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
				.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(addRouteBt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 158,
										Short.MAX_VALUE)
								.addComponent(transportCostUpdateBt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 158,
										Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(48, Short.MAX_VALUE).addComponent(txtpnBusinessEvents,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(34)));

		// set up the vertical group
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addComponent(txtpnBusinessEvents, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(mailDeliveryBt)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(routeDiscontinueBt)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(customerPriceUpdateBt)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(transportCostUpdateBt)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(addRouteBt)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		setLayout(groupLayout);
	}

}
