package view;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public class HomepagePanel extends AbstractMainDisplayPanel {

	public HomepagePanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);
	}

	@Override
	protected void initialiseLayout() {
	}

	@Override
	public void resetTextFields() {
	}

}
