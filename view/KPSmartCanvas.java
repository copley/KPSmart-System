package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

	private JPanel mainDisplayPanel = new JPanel();
	private JPanel operationPanel = new OperationPanel();

	/**
	 * construct an empty KPSmart canvas
	 *
	 * @param frame
	 */
	public KPSmartCanvas(JFrame frame) {
		canvasWidth = frame.getWidth();
		canvasHeight = frame.getHeight();
		this.frame = frame;
		setBackground(new Color(77, 115, 166));
		initialise();
	}

	protected void initialise() {

		mainDisplayPanel.setBackground(Color.BLACK);

		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(mainDisplayPanel, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE).addGap(18)
						.addComponent(operationPanel, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(mainDisplayPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 297,
										Short.MAX_VALUE)
								.addComponent(operationPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 297,
										Short.MAX_VALUE))
						.addContainerGap()));

		setLayout(groupLayout);
	}

	public void redraw() {
		// TODO Auto-generated method stub
	}

	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void onKeyboard(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
