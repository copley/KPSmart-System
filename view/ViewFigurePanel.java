package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public class ViewFigurePanel extends AbstractPanel {

	private JTextPane txtpnViewFigures;
	private JButton totalRevenueBt;
	private JButton totalExpenditureBt;
	private JButton totalNbEventsBt;
	private JButton totalAmountMailBt;
	private JButton averageDeleveryTimesBt;
	private JButton criticalRoutesBt;

	public ViewFigurePanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		this.addKeyListener(keyAction);
		this.addMouseListener(mouseAction);

		txtpnViewFigures = new JTextPane();
		txtpnViewFigures.setEditable(false);
		txtpnViewFigures.setText("View Figures");
		txtpnViewFigures.setFont(new Font("DejaVu Serif Condensed", Font.BOLD | Font.ITALIC, 13));
		txtpnViewFigures.setBackground(new Color(238, 238, 238));

		totalRevenueBt = new JButton("Total Revenue");
		totalRevenueBt.addActionListener(viewActionListener);

		totalExpenditureBt = new JButton("Total Expenditure");
		totalExpenditureBt.setFont(new Font("Dialog", Font.BOLD, 10));
		totalExpenditureBt.addActionListener(viewActionListener);

		totalNbEventsBt = new JButton("Total No. of Events");
		totalNbEventsBt.setFont(new Font("Dialog", Font.BOLD, 10));
		totalNbEventsBt.addActionListener(viewActionListener);

		totalAmountMailBt = new JButton("Amount of Mail");
		totalAmountMailBt.setFont(new Font("Dialog", Font.BOLD, 11));
		totalAmountMailBt.addActionListener(viewActionListener);

		averageDeleveryTimesBt = new JButton("AVG Delivery Times");
		averageDeleveryTimesBt.setFont(new Font("Dialog", Font.BOLD, 10));
		averageDeleveryTimesBt.addActionListener(viewActionListener);

		criticalRoutesBt = new JButton("Critical Routes");
		criticalRoutesBt.addActionListener(viewActionListener);

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(27)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(criticalRoutesBt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(averageDeleveryTimesBt, 0, 0, Short.MAX_VALUE)
								.addComponent(totalAmountMailBt, 0, 0, Short.MAX_VALUE)
								.addComponent(totalNbEventsBt, 0, 0, Short.MAX_VALUE)
								.addComponent(totalExpenditureBt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(totalRevenueBt, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 157,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(14, Short.MAX_VALUE))
				.addGroup(groupLayout
						.createSequentialGroup().addContainerGap(63, Short.MAX_VALUE).addComponent(txtpnViewFigures,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(46)));

		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(txtpnViewFigures, GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(totalRevenueBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(totalExpenditureBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(totalNbEventsBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(totalAmountMailBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(averageDeleveryTimesBt)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(criticalRoutesBt)
								.addContainerGap(13, Short.MAX_VALUE)));

		setLayout(groupLayout);
	}

}
