package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BusinessEventPanel extends AbstractPanel {

	private JButton mailDeliveryBt;
	private JButton routeDiscontinueBt;
	private JButton customerPriceUpdateBt;
	private JButton transportCostUpdateBt;
	private JTextPane txtpnBusinessEvents;

	@Override
	protected void initialise() {
		mailDeliveryBt = new JButton("Mail Delivery");

		routeDiscontinueBt = new JButton("Route Discontinue");
		routeDiscontinueBt.setFont(new Font("Dialog", Font.BOLD, 10));

		customerPriceUpdateBt = new JButton("Customer Price Update");
		customerPriceUpdateBt.setFont(new Font("Dialog", Font.BOLD, 9));

		transportCostUpdateBt = new JButton("Transport Cost Update");
		transportCostUpdateBt.setFont(new Font("Dialog", Font.BOLD, 9));

		txtpnBusinessEvents = new JTextPane();
		txtpnBusinessEvents.setEditable(false);
		txtpnBusinessEvents.setFont(new Font("DejaVu Serif Condensed", Font.BOLD | Font.ITALIC, 13));
		txtpnBusinessEvents.setText("Business Events");

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(26)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(mailDeliveryBt, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
										.addComponent(routeDiscontinueBt, GroupLayout.DEFAULT_SIZE, 160,
												Short.MAX_VALUE)
						.addComponent(customerPriceUpdateBt, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
						.addComponent(transportCostUpdateBt, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
				.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addContainerGap(48, Short.MAX_VALUE).addComponent(txtpnBusinessEvents,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(42)));

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addComponent(txtpnBusinessEvents, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(mailDeliveryBt)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(routeDiscontinueBt)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(customerPriceUpdateBt)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(transportCostUpdateBt)
						.addContainerGap(15, Short.MAX_VALUE)));

		setLayout(groupLayout);
	}

	@Override
	protected void redraw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyboard(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
