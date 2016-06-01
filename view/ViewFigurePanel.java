package view;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public class ViewFigurePanel extends AbstractPanel {

	private JButton businessFiguresButton;

	public ViewFigurePanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		this.addKeyListener(keyAction);
		this.addMouseListener(mouseAction);

		businessFiguresButton = new JButton("Business Figures");
		businessFiguresButton.setFont(new Font("Dialog", Font.BOLD, 11));
		businessFiguresButton.addActionListener(viewActionListener);

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout
						.createSequentialGroup().addGap(27).addComponent(businessFiguresButton,
								GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(14, Short.MAX_VALUE)));

		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addContainerGap().addComponent(businessFiguresButton).addContainerGap(187, Short.MAX_VALUE)));

		setLayout(groupLayout);
	}

}
