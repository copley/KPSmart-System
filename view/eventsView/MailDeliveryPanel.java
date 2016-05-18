package view.eventsView;

import java.awt.Font;
import java.beans.DesignMode;

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
public class MailDeliveryPanel extends AbstractMainDisplayPanel {

	private JTextField weightTextField;
	private JTextField volumeTextField;

	private JComboBox<String> originComboBox;
	private JComboBox<String> destinationComboBox;
	private JComboBox<String> priorityComboBox;

	private JLabel lblOrigin;
	private JLabel lblDestination;
	private JLabel lblWeight;
	private JLabel lblVolumn;
	private JLabel lblPriority;

	private JButton resetButton;
	private JButton cancelButton;
	private JButton submitButton;

	public MailDeliveryPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		lblOrigin = new JLabel("Origin:");
		lblOrigin.setFont(new Font("Dialog", Font.BOLD, 15));

		lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("Dialog", Font.BOLD, 15));

		lblWeight = new JLabel("Weight:");
		lblWeight.setFont(new Font("Dialog", Font.BOLD, 15));

		lblVolumn = new JLabel("Volume:");
		lblVolumn.setFont(new Font("Dialog", Font.BOLD, 15));

		lblPriority = new JLabel("Priority:");
		lblPriority.setFont(new Font("Dialog", Font.BOLD, 15));

		originComboBox = new JComboBox<String>();
		originComboBox.addItem("Wellington");
		originComboBox.addItem("Auckland");
		originComboBox.addItem("Beijing");

		destinationComboBox = new JComboBox<String>();
		destinationComboBox.addItem("Wellington");
		destinationComboBox.addItem("Auckland");
		destinationComboBox.addItem("Beijing");

		priorityComboBox = new JComboBox<String>();
		priorityComboBox.addItem("International Air");
		priorityComboBox.addItem("International Standard");
		priorityComboBox.addItem("Domestic Air");
		priorityComboBox.addItem("Domestic Standard");

		weightTextField = new JTextField();
		weightTextField.setColumns(10);

		volumeTextField = new JTextField();
		volumeTextField.setColumns(10);

		resetButton = new JButton("Reset");
		resetButton.addActionListener(viewActionListener);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(viewActionListener);

		submitButton = new JButton("Submit");
		submitButton.addActionListener(viewActionListener);

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {
		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(57)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblOrigin)
										.addComponent(lblDestination)
										.addComponent(lblPriority, GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWeight, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVolumn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
				.addGap(43)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(volumeTextField)
						.addComponent(weightTextField)
						.addComponent(destinationComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(originComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(priorityComboBox, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(98,
						Short.MAX_VALUE)).addGroup(
								groupLayout.createSequentialGroup().addContainerGap(165, Short.MAX_VALUE)
										.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 83,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(resetButton)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(cancelButton,
												GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addGap(18)));

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
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblWeight).addComponent(
						weightTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblVolumn).addComponent(
						volumeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPriority).addComponent(
						priorityComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(resetButton)
						.addComponent(cancelButton).addComponent(submitButton))
				.addContainerGap()));

		setLayout(groupLayout);
	}

	@Override
	protected void redraw() {
		// TODO Auto-generated method stub

	}

	public JTextField getWeightTextField() {
		return weightTextField;
	}

	public void setWeightTextField(JTextField weightTextField) {
		this.weightTextField = weightTextField;
	}

	public JTextField getVolumeTextField() {
		return volumeTextField;
	}

	public void setVolumeTextField(JTextField volumeTextField) {
		this.volumeTextField = volumeTextField;
	}

	public JComboBox<String> getOriginComboBox() {
		return originComboBox;
	}

	public void setOriginComboBox(JComboBox<String> originComboBox) {
		this.originComboBox = originComboBox;
	}

	public JComboBox<String> getDestinationComboBox() {
		return destinationComboBox;
	}

	public void setDestinationComboBox(JComboBox<String> destinationComboBox) {
		this.destinationComboBox = destinationComboBox;
	}

	public JComboBox<String> getPriorityComboBox() {
		return priorityComboBox;
	}

	public void setPriorityComboBox(JComboBox<String> priorityComboBox) {
		this.priorityComboBox = priorityComboBox;
	}

}
