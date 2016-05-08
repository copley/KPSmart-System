package view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel {

	public AbstractPanel() {
		initialise();
	}

	protected abstract void initialise();

	protected abstract void redraw();

	public abstract void onClick(MouseEvent e);

	public abstract void onKeyboard(KeyEvent e);
}
