package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

	/**
	 * construct an empty cluedo canvas
	 *
	 * @param frame
	 */
	public KPSmartCanvas(JFrame frame) {
		canvasWidth = frame.getWidth();
		canvasHeight = frame.getHeight();
		this.frame = frame;
		setBackground(new Color(77, 115, 166));
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

	public void validateButtons() {
		// TODO Auto-generated method stub

	}
}
