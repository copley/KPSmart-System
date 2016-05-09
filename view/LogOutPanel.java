package view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class LogOutPanel extends AbstractPanel {

	private JButton logOutButton;

	@Override
	protected void initialise() {
		logOutButton = new JButton("Log Out");
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
