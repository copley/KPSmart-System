package view.eventsView;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;
import view.AbstractMainDisplayPanel;

@SuppressWarnings("serial")
public class BusinessFiguresPanel extends AbstractMainDisplayPanel {

	private JLabel label;

	public BusinessFiguresPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		label = new JLabel("");

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {
		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addContainerGap()));

		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
								.addContainerGap()));

		setLayout(groupLayout);
	}

	@Override
	public void resetTextFields() {
	}

}
