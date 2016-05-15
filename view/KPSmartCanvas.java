package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;
import view.eventsView.MailDeliveryPanel;

import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class KPSmartCanvas extends JPanel {

	/**
	 * represents the canvas width
	 */
	private final int canvasWidth;

	/**
	 * represents the canvas height
	 */
	private final int canvasHeight;

	/**
	 * the parent frame
	 */
	private final JFrame frame;

	private MailDeliveryPanel mainDisplayPanel;
	private JPanel operationPanel;

	/**
	 * construct an empty KPSmart canvas
	 *
	 * @param frame
	 * @param viewActionListener
	 * @param mouseAction
	 * @param keyAction
	 */
	public KPSmartCanvas(JFrame frame, KeyAction keyAction, MouseAction mouseAction,
			ViewActionListener viewActionListener) {
		canvasWidth = frame.getWidth();
		canvasHeight = frame.getHeight();
		this.frame = frame;
		setBackground(new Color(77, 115, 166));
		mainDisplayPanel = new MailDeliveryPanel(keyAction, mouseAction, viewActionListener);
		operationPanel = new OperationPanel(keyAction, mouseAction, viewActionListener);

		// debug
		//mainDisplayPanel.add(new MailDeliveryPanel(keyAction, mouseAction, viewActionListener));

		initialise();
	}

	protected void initialise() {

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(mainDisplayPanel, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE).addGap(18)
						.addComponent(operationPanel, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(mainDisplayPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 506,
										Short.MAX_VALUE)
								.addComponent(operationPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		setLayout(groupLayout);
	}

	public void redraw() {
		// TODO Auto-generated method stub
	}

	public MailDeliveryPanel getMainDisplayPanel() {
		return mainDisplayPanel;
	}

	public JPanel getOperationPanel() {
		return operationPanel;
	}

}
