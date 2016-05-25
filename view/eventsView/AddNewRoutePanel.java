package view.eventsView;

import java.awt.Font;

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
public class AddNewRoutePanel extends AbstractMainDisplayPanel {

	private JTextField DestinationTextField;
	private JTextField originTextField;
	private JTextField transportCompanyTextField;
	private JTextField hoursToDeliverTextField;
	private JTextField customerPriceWeightTextField;
	private JTextField customerPriceVolumeTextField;
	private JTextField transportPriceWeightTextField;
	private JTextField transportPriceVolumeTextField;

	private JLabel lblOrigin;
	private JLabel lblDestination;
	private JLabel lblTransportCompany;
	private JLabel lblHoursToDeliver;
	private JLabel lblMode;
	private JLabel lblCustomerPriceperGram;
	private JLabel lblCustomerPriceperCm;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel lblTransportPriceperGram;
	private JLabel lblTransportPriceperCm;

	private JComboBox<String> modeComboBox;

	private JButton resetButton;
	private JButton cancelButton;
	private JButton submitButton;

	public AddNewRoutePanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		lblOrigin = new JLabel("Origin:");
		lblOrigin.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTransportCompany = new JLabel("Transport Company:");
		lblHoursToDeliver = new JLabel("Hours to Deliver:");
		lblHoursToDeliver.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMode = new JLabel("Type:");
		lblMode.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCustomerPriceperGram = new JLabel("Customer Price(per gram):");
		lblCustomerPriceperGram.setFont(new Font("Dialog", Font.BOLD, 11));
		lblCustomerPriceperCm = new JLabel("<html>Customer Price(per cm<sup>3</sup>):</html>");
		lblCustomerPriceperCm.setFont(new Font("Dialog", Font.BOLD, 11));
		lblTransportPriceperGram = new JLabel("Transport Price(per gram):");
		lblTransportPriceperGram.setFont(new Font("Dialog", Font.BOLD, 11));
		lblTransportPriceperCm = new JLabel("<html>Transport Price(per cm<sup>3</sup>):</html>");
		lblTransportPriceperCm.setFont(new Font("Dialog", Font.BOLD, 11));
		label = new JLabel("$");
		label_1 = new JLabel("$");
		label_2 = new JLabel("$");
		label_3 = new JLabel("$");

		resetButton = new JButton("Reset");
		resetButton.addActionListener(viewActionListener);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(viewActionListener);
		submitButton = new JButton("Submit");
		submitButton.addActionListener(viewActionListener);

		DestinationTextField = new JTextField();
		DestinationTextField.setColumns(10);
		originTextField = new JTextField();
		originTextField.setColumns(10);
		transportCompanyTextField = new JTextField();
		transportCompanyTextField.setColumns(10);
		hoursToDeliverTextField = new JTextField();
		hoursToDeliverTextField.setColumns(10);
		customerPriceWeightTextField = new JTextField();
		customerPriceWeightTextField.setColumns(10);
		customerPriceVolumeTextField = new JTextField();
		customerPriceVolumeTextField.setColumns(10);
		transportPriceWeightTextField = new JTextField();
		transportPriceWeightTextField.setColumns(10);
		transportPriceVolumeTextField = new JTextField();
		transportPriceVolumeTextField.setColumns(10);

		modeComboBox = new JComboBox<String>();
		modeComboBox.addItem("LAND");
		modeComboBox.addItem("SEA");
		modeComboBox.addItem("AIR");

		initialiseLayout();

	}

	@Override
	protected void initialiseLayout() {
		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap(231, Short.MAX_VALUE)
										.addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 83,
												GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(resetButton)
								.addPreferredGap(
										ComponentPlacement.UNRELATED)
				.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 83,
						GroupLayout.PREFERRED_SIZE).addContainerGap()).addGroup(
								groupLayout
										.createSequentialGroup().addGap(
												57)
										.addGroup(
												groupLayout.createParallelGroup(Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(lblOrigin, Alignment.LEADING)
																		.addComponent(lblDestination, Alignment.LEADING)
																		.addComponent(lblTransportCompany,
																				Alignment.LEADING)
												.addComponent(lblHoursToDeliver, Alignment.LEADING)
												.addComponent(lblMode, Alignment.LEADING)
												.addComponent(lblCustomerPriceperGram, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE).addComponent(lblCustomerPriceperCm,
																Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblTransportPriceperGram, GroupLayout.PREFERRED_SIZE, 180,
								GroupLayout.PREFERRED_SIZE).addComponent(lblTransportPriceperCm,
										GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 8,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(transportPriceVolumeTextField, GroupLayout.PREFERRED_SIZE, 94,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 8,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(transportPriceWeightTextField, GroupLayout.PREFERRED_SIZE, 94,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup().addComponent(label_1)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(customerPriceVolumeTextField, 0, 0, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup().addComponent(label)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(customerPriceWeightTextField, 0, 0, Short.MAX_VALUE))
										.addComponent(originTextField).addComponent(hoursToDeliverTextField)
										.addComponent(transportCompanyTextField)
										.addComponent(modeComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(DestinationTextField))).addContainerGap(157, Short.MAX_VALUE)));

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(26)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblOrigin).addComponent(
						originTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblDestination).addComponent(
						DestinationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblTransportCompany)
						.addComponent(transportCompanyTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblHoursToDeliver)
						.addComponent(hoursToDeliverTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblMode).addComponent(
						modeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblCustomerPriceperGram)
						.addComponent(label).addComponent(customerPriceWeightTextField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblCustomerPriceperCm)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
								.addComponent(customerPriceVolumeTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTransportPriceperGram, GroupLayout.PREFERRED_SIZE, 14,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2).addComponent(transportPriceWeightTextField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTransportPriceperCm, GroupLayout.PREFERRED_SIZE, 19,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3).addComponent(transportPriceVolumeTextField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(submitButton)
						.addComponent(resetButton).addComponent(cancelButton))
				.addContainerGap()));

		setLayout(groupLayout);
	}

	public String getDestinationTextFieldString() {
		return DestinationTextField.getText();
	}

	public String getOriginTextFieldString() {
		return originTextField.getText();
	}

	public String getTransportCompanyTextFieldString() {
		return transportCompanyTextField.getText();
	}

	public String getHoursToDeliverTextFieldString() {
		return hoursToDeliverTextField.getText();
	}

	public String getCustomerPriceWeightTextFieldString() {
		return customerPriceWeightTextField.getText();
	}

	public String getCustomerPriceVolumeTextFieldString() {
		return customerPriceVolumeTextField.getText();
	}

	public String getTransportPriceWeightTextFieldString() {
		return transportPriceWeightTextField.getText();
	}

	public String getTransportPriceVolumeTextFieldString() {
		return transportPriceVolumeTextField.getText();
	}

	public String getModeComboBoxString() {
		return modeComboBox.getSelectedItem().toString();
	}

	@Override
	public void resetTextFields() {
		DestinationTextField.setText("");
		originTextField.setText("");;
		transportCompanyTextField.setText("");;
		hoursToDeliverTextField.setText("");;
		customerPriceWeightTextField.setText("");
		customerPriceVolumeTextField.setText("");;
		transportPriceWeightTextField.setText("");;
		transportPriceVolumeTextField.setText("");;
	}

}
