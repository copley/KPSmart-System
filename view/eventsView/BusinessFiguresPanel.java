package view.eventsView;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;
import view.AbstractMainDisplayPanel;
import view.KPSmartFrame;

/**
 * A panel that is used to review the business figures.
 *
 * @author Shenbo Xuan
 *
 */

@SuppressWarnings("serial")
public class BusinessFiguresPanel extends AbstractMainDisplayPanel {

	private JLabel label;
	private JScrollPane scrollPane;

	public BusinessFiguresPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		label = new JLabel("");
		scrollPane = new JScrollPane(label);

		label.setBackground(KPSmartFrame.THEME_COLOR);
		label.setOpaque(true);
		scrollPane.setBackground(KPSmartFrame.THEME_COLOR);
		scrollPane.setOpaque(true);

		setAutoscrolls(true);

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {

		// utilise the group layout
		GroupLayout groupLayout = new GroupLayout(this);

		// set up the horizontal group
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addContainerGap()));

		// set up the vertical group
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
								.addContainerGap()));

		setLayout(groupLayout);
	}

	@Override
	public void resetTextFields() {
	}

	/**
	 * Set the text of figures label.
	 *
	 * @param figures
	 */
	public void setFiguresLabel(String figures) {
		label.setText(figures);
	}

}
