package view;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public abstract class AbstractMainDisplayPanel extends AbstractPanel {

	public AbstractMainDisplayPanel(KeyAction keyAction, MouseAction mouseAction,
			ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);
	}

	public abstract void resetTextFields();
}
