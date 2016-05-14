package view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel {

	public AbstractPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
	}

	protected abstract void initialise();

	protected abstract void redraw();

	public abstract void onClick(MouseEvent e);

	public abstract void onKeyboard(KeyEvent e);
}
