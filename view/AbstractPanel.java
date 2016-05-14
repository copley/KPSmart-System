package view;

import javax.swing.JPanel;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel {

	public AbstractPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
	}

	protected abstract void initialiseLayout();

	protected abstract void redraw();

}
