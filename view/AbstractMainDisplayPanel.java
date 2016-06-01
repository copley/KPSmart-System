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
		setBackground(KPSmartFrame.THEME_COLOR);
	}

	public abstract void resetTextFields();
}
