package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public class OperationPanel extends AbstractPanel {

	private JPanel businessEventPanel;
	private JPanel viewFigurePanel;
	private JPanel emptyPanel;
	private JPanel logOutPanel;

	public OperationPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		businessEventPanel = new BusinessEventPanel(keyAction, mouseAction, viewActionListener);
		viewFigurePanel = new ViewFigurePanel(keyAction, mouseAction, viewActionListener);
		emptyPanel = new JPanel();
		logOutPanel = new LogOutPanel(keyAction, mouseAction, viewActionListener);
	}

	@Override
	protected void initialise() {

		// debug
		setBackground(Color.GREEN);
		emptyPanel.setBackground(Color.GREEN);

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(110, Short.MAX_VALUE)
						.addComponent(logOutPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addComponent(businessEventPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGap(14))
				.addGroup(groupLayout.createSequentialGroup().addGap(10)
						.addComponent(viewFigurePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGap(14))
				.addGroup(groupLayout.createSequentialGroup().addGap(13)
						.addComponent(emptyPanel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(48, Short.MAX_VALUE)));

		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addComponent(businessEventPanel, GroupLayout.PREFERRED_SIZE, 186,
												GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(viewFigurePanel, GroupLayout.PREFERRED_SIZE, 224,
										GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(emptyPanel, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE).addComponent(logOutPanel,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

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

	public JPanel getBusinessEventPanel() {
		return businessEventPanel;
	}

	public JPanel getViewFigurePanel() {
		return viewFigurePanel;
	}

	public JPanel getEmptyPanel() {
		return emptyPanel;
	}

	public JPanel getLogOutPanel() {
		return logOutPanel;
	}

}
