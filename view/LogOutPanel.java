package view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public class LogOutPanel extends AbstractPanel {

	private JButton logOutButton;

	public LogOutPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		logOutButton = new JButton("Log Out");

		this.addKeyListener(keyAction);
		this.addMouseListener(mouseAction);

		logOutButton.addActionListener(viewActionListener);

		initialise();
	}

	@Override
	protected void initialise() {
		add(logOutButton);
	}

	@Override
	protected void redraw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyboard(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
