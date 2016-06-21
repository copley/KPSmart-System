package view;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

/**
 * An abstract class represents the main display part of the user interface.
 * @author Shenbo Xuan
 *
 */

@SuppressWarnings("serial")
public abstract class AbstractMainDisplayPanel extends AbstractPanel {

	public AbstractMainDisplayPanel(KeyAction keyAction, MouseAction mouseAction,
			ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);
		setBackground(KPSmartFrame.THEME_COLOR);
	}

	/**
	 * Reset all the text fields in the panel.
	 */
	public abstract void resetTextFields();
}
