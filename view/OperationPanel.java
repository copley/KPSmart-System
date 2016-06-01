package view;

import java.awt.Color;

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
	private JPanel reviewEventsButtonPanel;

	public OperationPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		businessEventPanel = new BusinessEventPanel(keyAction, mouseAction, viewActionListener);
		viewFigurePanel = new ViewFigurePanel(keyAction, mouseAction, viewActionListener);
		logOutPanel = new LogOutPanel(keyAction, mouseAction, viewActionListener);
		reviewEventsButtonPanel = new ReviewEventsButtonPanel(keyAction, mouseAction, viewActionListener);

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {

		// debug
		setBackground(Color.DARK_GRAY);

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(reviewEventsButtonPanel, GroupLayout.PREFERRED_SIZE, 141,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(logOutPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(
										groupLayout.createSequentialGroup()
												.addComponent(viewFigurePanel, GroupLayout.DEFAULT_SIZE, 198,
														Short.MAX_VALUE)
												.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup().addComponent(businessEventPanel,
										GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE).addGap(14))))));

		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addComponent(businessEventPanel, GroupLayout.PREFERRED_SIZE, 216,
												GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(viewFigurePanel, GroupLayout.PREFERRED_SIZE, 224,
										GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(reviewEventsButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
				.addComponent(logOutPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE).addContainerGap()));

		setLayout(groupLayout);
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

	public void setReviewEventButton(boolean isManager) {
		if (isManager) {
			reviewEventsButtonPanel.setVisible(true);
		} else {
			reviewEventsButtonPanel.setVisible(false);
		}
	}

}
