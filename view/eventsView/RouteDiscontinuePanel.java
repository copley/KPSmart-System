package view.eventsView;

import java.awt.Font;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;
import model.map.Type;
import view.AbstractMainDisplayPanel;

@SuppressWarnings("serial")
public class RouteDiscontinuePanel extends AbstractMainDisplayPanel {

	private JLabel lblOrigin;
	private JLabel lblDestination;
	private JLabel lblTransportCompany;
	private JLabel lblType;

	private JButton resetButton;
	private JButton cancelButton;
	private JButton submitButton;

	private JComboBox<String> originComboBox;
	private JComboBox<String> destinationComboBox;
	private JComboBox<String> transportCompanyComboBox;
	private JComboBox<String> typeComboBox;

	public RouteDiscontinuePanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener, List<String> origins, List<String> destinations, List<String> companies) {
		super(keyAction, mouseAction, viewActionListener);

		lblOrigin = new JLabel("Origin:");
		lblOrigin.setFont(new Font("Dialog", Font.BOLD, 15));

		lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("Dialog", Font.BOLD, 15));

		lblTransportCompany = new JLabel("Transport Company:");

		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Dialog", Font.BOLD, 15));

		originComboBox = new JComboBox<String>();
		for(String s : origins){
			originComboBox.addItem(s);
		}
		destinationComboBox = new JComboBox<String>();
		for(String s : destinations){
			destinationComboBox.addItem(s);
		}
		transportCompanyComboBox = new JComboBox<String>();
		for(String c : companies){
			transportCompanyComboBox.addItem(c);
		}
		typeComboBox = new JComboBox<String>();
		for(Type t : Type.values()){
			typeComboBox.addItem(t.name());
		}

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
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblOrigin).addComponent(lblDestination)
														.addComponent(lblTransportCompany).addComponent(lblType))
								.addGap(4)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(destinationComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(transportCompanyComboBox, 0, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
								.addComponent(typeComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(
										originComboBox, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(125, Short.MAX_VALUE)));

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
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblTransportCompany)
						.addComponent(transportCompanyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblType).addComponent(
						typeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(submitButton)
						.addComponent(resetButton).addComponent(cancelButton))
				.addContainerGap()));

		setLayout(groupLayout);
	}

	public String getOriginComboBoxString() {
		return originComboBox.getSelectedItem().toString();
	}

	public String getDestinationComboBoxString() {
		return destinationComboBox.getSelectedItem().toString();
	}

	public String getTransportCompanyComboBoxString() {
		return transportCompanyComboBox.getSelectedItem().toString();
	}

	public String getTypeComboBoxString() {
		return typeComboBox.getSelectedItem().toString();
	}

	public void addSites(String origin, String destination) {
		if(origin != null) originComboBox.addItem(origin);
		if(destination != null) destinationComboBox.addItem(destination);
	}

	@Override
	public void resetTextFields() {
	}

	public void addCompanies(String company) {
		if(company != null) transportCompanyComboBox.addItem(company);
	}

}
