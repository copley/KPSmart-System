package view.eventsView;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;
import view.AbstractMainDisplayPanel;

/**
 * A panel that is used to review the business events.
 *
 * @author Shenbo Xuan
 *
 */

@SuppressWarnings("serial")
public class ReviewEventsPanel extends AbstractMainDisplayPanel {

	private JButton previousEventButton;
	private JButton nextEventButton;

	private JLabel eventStringLabel;

	public ReviewEventsPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		previousEventButton = new JButton("Previous Event");
		nextEventButton = new JButton("Next Event");

		previousEventButton.addActionListener(viewActionListener);
		nextEventButton.addActionListener(viewActionListener);

		eventStringLabel = new JLabel("");

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {

		// utilise the group layout
		GroupLayout groupLayout = new GroupLayout(this);

		// set up the horizontal group
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup().addContainerGap()
														.addComponent(previousEventButton)
														.addPreferredGap(ComponentPlacement.RELATED, 212,
																Short.MAX_VALUE)
														.addComponent(nextEventButton))
								.addGroup(groupLayout.createSequentialGroup().addGap(41).addComponent(eventStringLabel,
										GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));

		// set up the vertical group
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(23)
								.addComponent(eventStringLabel, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(previousEventButton).addComponent(nextEventButton))
				.addContainerGap()));

		setLayout(groupLayout);
	}

	@Override
	public void resetTextFields() {
	}

	/**
	 * Set the string for event string label.
	 *
	 * @param eventString
	 */
	public void setEventStringLabel(String eventString) {
		eventStringLabel.setText(eventString);
		validate();
		repaint();
	}

}
