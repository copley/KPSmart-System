package view;

import java.awt.Color;

import javax.swing.JButton;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public class ReviewEventsButtonPanel extends AbstractPanel {

	private JButton reviewEventsButton;

	public ReviewEventsButtonPanel(KeyAction keyAction, MouseAction mouseAction,
			ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		reviewEventsButton = new JButton("Review Events");

		initialiseLayout();
	}

	@Override
	protected void initialiseLayout() {
		setBackground(Color.DARK_GRAY);
		add(reviewEventsButton);
	}

}
