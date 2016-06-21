package view;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

/**
 * The panel that contains the navigation bar of KPSmart.
 *
 * @author Shenbo Xuan
 *
 */

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

		setBackground(Color.DARK_GRAY);

		// utilise the group layout
		GroupLayout groupLayout = new GroupLayout(this);

		// set up the horizontal group
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(logOutPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(businessEventPanel, GroupLayout.DEFAULT_SIZE, 196,
												Short.MAX_VALUE)
										.addGap(14))
								.addGroup(
										groupLayout.createSequentialGroup()
												.addComponent(viewFigurePanel, GroupLayout.DEFAULT_SIZE, 198,
														Short.MAX_VALUE)
												.addContainerGap()))
						.addGroup(Alignment.TRAILING,
								groupLayout
										.createSequentialGroup().addComponent(reviewEventsButtonPanel,
												GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))));

		// set up the vertical group
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addComponent(businessEventPanel, GroupLayout.PREFERRED_SIZE, 216,
												GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(viewFigurePanel, GroupLayout.PREFERRED_SIZE, 53,
										GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
				.addComponent(reviewEventsButtonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(logOutPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE).addContainerGap()));

		setLayout(groupLayout);
	}

	/**
	 * Return the business event panel.
	 *
	 * @return
	 */
	public JPanel getBusinessEventPanel() {
		return businessEventPanel;
	}

	/**
	 * Return the view figure panel.
	 *
	 * @return
	 */
	public JPanel getViewFigurePanel() {
		return viewFigurePanel;
	}

	/**
	 * Return the logout panel.
	 *
	 * @return
	 */
	public JPanel getLogOutPanel() {
		return logOutPanel;
	}

	/**
	 * Determine whether we display the review event button by the login type.
	 * If manager, display it. Otherwise do not display.
	 *
	 * @param isManager
	 */
	public void setReviewEventButton(boolean isManager) {
		if (isManager) {
			reviewEventsButtonPanel.setVisible(true);
		} else {
			reviewEventsButtonPanel.setVisible(false);
		}
	}

}
