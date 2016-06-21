package view;

import java.awt.Color;

import javax.swing.JButton;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

/**
 * A panel that contains the logout button.
 *
 * @author Shenbo Xuan
 *
 */

@SuppressWarnings("serial")
public class LogOutPanel extends AbstractPanel {

	private JButton logOutButton;

	public LogOutPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		logOutButton = new JButton("Log Out");

		this.addKeyListener(keyAction);
		this.addMouseListener(mouseAction);

		logOutButton.addActionListener(viewActionListener);

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {
		setBackground(Color.DARK_GRAY);
		add(logOutButton);
	}

}
