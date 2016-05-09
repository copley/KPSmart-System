package view;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class ViewFigurePanel extends AbstractPanel {

	private JTextPane txtpnViewFigures;
	private JButton totalRevenueBt;
	private JButton totalExpenditureBt;
	private JButton totalNbEventsBt;
	private JButton totalAmountMailsBt;
	private JButton averageDeleveryTimesBt;
	private JButton criticalRoutesBt;

	@Override
	protected void initialise() {

		txtpnViewFigures = new JTextPane();
		txtpnViewFigures.setEditable(false);
		txtpnViewFigures.setText("View Figures");
		txtpnViewFigures.setFont(new Font("DejaVu Serif Condensed", Font.BOLD | Font.ITALIC, 13));

		totalRevenueBt = new JButton("Total Revenue");

		totalExpenditureBt = new JButton("Total Expenditure");
		totalExpenditureBt.setFont(new Font("Dialog", Font.BOLD, 10));

		totalNbEventsBt = new JButton("Total No. of Events");
		totalNbEventsBt.setFont(new Font("Dialog", Font.BOLD, 10));

		totalAmountMailsBt = new JButton("Amount of Mails");
		totalAmountMailsBt.setFont(new Font("Dialog", Font.BOLD, 11));

		averageDeleveryTimesBt = new JButton("Average Delivery Times");
		averageDeleveryTimesBt.setFont(new Font("Dialog", Font.BOLD, 8));

		criticalRoutesBt = new JButton("Critical Routes");

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(39)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(criticalRoutesBt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(averageDeleveryTimesBt, 0, 0, Short.MAX_VALUE)
								.addComponent(totalAmountMailsBt, 0, 0, Short.MAX_VALUE)
								.addComponent(totalNbEventsBt, 0, 0, Short.MAX_VALUE)
								.addComponent(totalExpenditureBt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
						.addComponent(totalRevenueBt, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
				.addContainerGap(20, Short.MAX_VALUE))
				.addGroup(groupLayout
						.createSequentialGroup().addContainerGap(62, Short.MAX_VALUE).addComponent(txtpnViewFigures,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(53)));

		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(txtpnViewFigures, GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(totalRevenueBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(totalExpenditureBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(totalNbEventsBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(totalAmountMailsBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(averageDeleveryTimesBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(criticalRoutesBt)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

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
