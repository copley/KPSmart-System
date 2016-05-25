package view.eventsView;

import java.awt.Font;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;
import view.AbstractMainDisplayPanel;

@SuppressWarnings("serial")
public class CustomerPriceChangePanel extends AbstractMainDisplayPanel {

	private JTextField newWeightCostTextField;
	private JTextField newVolumeCosttextField;

	private JLabel lblOrigin;
	private JLabel lblDestination;
	private JLabel lblPriority;
	private JLabel lblNewWeightCostper;
	private JLabel lblNewLabel;

	private JComboBox<String> originComboBox;
	private JComboBox<String> destinationComboBox;
	private JComboBox<String> priorityComboBox;

	private JButton resetButton;
	private JButton cancelButton;
	private JButton submitButton;

	public CustomerPriceChangePanel(KeyAction keyAction, MouseAction mouseAction,
			ViewActionListener viewActionListener, List<String> siteNames) {
		super(keyAction, mouseAction, viewActionListener);

		lblOrigin = new JLabel("Origin:");
		lblOrigin.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPriority = new JLabel("Priority:");
		lblPriority.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewWeightCostper = new JLabel("New Weight Cost(per gram):");
		lblNewLabel = new JLabel("<html>New Volume Cost(per cm<sup>3</sup>):</html>");

		originComboBox = new JComboBox<String>();
		destinationComboBox = new JComboBox<String>();
		priorityComboBox = new JComboBox<String>();

		resetButton = new JButton("Reset");
		resetButton.addActionListener(viewActionListener);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(viewActionListener);
		submitButton = new JButton("Submit");
		submitButton.addActionListener(viewActionListener);

		newWeightCostTextField = new JTextField();
		newWeightCostTextField.setColumns(10);
		newVolumeCosttextField = new JTextField();
		newVolumeCosttextField.setColumns(10);

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {
		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(
										groupLayout.createSequentialGroup().addContainerGap(173, Short.MAX_VALUE)
												.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 83,
														GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(resetButton)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 83,
												GroupLayout.PREFERRED_SIZE).addContainerGap())
				.addGroup(
						groupLayout.createSequentialGroup().addGap(57)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblOrigin)
										.addComponent(lblDestination)
										.addComponent(lblPriority, GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewWeightCostper).addComponent(lblNewLabel))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(newVolumeCosttextField).addComponent(newWeightCostTextField)
								.addComponent(priorityComboBox, 0, 153, Short.MAX_VALUE)
								.addComponent(destinationComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(originComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(27, Short.MAX_VALUE)));

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(26)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblOrigin).addComponent(
						originComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblDestination).addComponent(
						destinationComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPriority).addComponent(
						priorityComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewWeightCostper)
						.addComponent(newWeightCostTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel).addComponent(
						newVolumeCosttextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(submitButton)
						.addComponent(resetButton).addComponent(cancelButton))
				.addContainerGap()));

		setLayout(groupLayout);
	}

	public String getNewWeightCostTextFieldString() {
		return newWeightCostTextField.getText();
	}

	public String getNewVolumeCosttextFieldString() {
		return newVolumeCosttextField.getText();
	}

	public String getOriginComboBoxString() {
		return originComboBox.getSelectedItem().toString();
	}

	public String getDestinationComboBoxString() {
		return destinationComboBox.getSelectedItem().toString();
	}

	public String getPriorityComboBoxString() {
		return priorityComboBox.getSelectedItem().toString();
	}

}
