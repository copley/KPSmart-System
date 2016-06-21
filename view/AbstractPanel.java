package view;

import javax.swing.JPanel;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

/**
 * An abstract class represents a panel in the user interface.
 *
 * @author Shenbo Xuan
 *
 */

@SuppressWarnings("serial")
public abstract class AbstractPanel extends JPanel {

	public AbstractPanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
	}

	/**
	 * Initialise the layout of the panel.
	 */
	protected abstract void initialiseLayout();

}
