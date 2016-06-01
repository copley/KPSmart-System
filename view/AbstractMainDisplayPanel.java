package view;

import java.awt.Color;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public abstract class AbstractMainDisplayPanel extends AbstractPanel {

	public AbstractMainDisplayPanel(KeyAction keyAction, MouseAction mouseAction,
			ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);
		setBackground(new Color(131, 176, 255));
	}

	public abstract void resetTextFields();
}
