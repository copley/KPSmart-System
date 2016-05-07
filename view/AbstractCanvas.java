package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractCanvas extends JPanel {

	/**
	 * represents the canvas width
	 */
	private final int canvasWidth;

	/**
	 * represents the canvas height
	 */
	private final int canvasHeight;

	/**
	 * redraw the whole main canvas
	 */
	protected abstract void redraw();


	/**
	 * Is called when the mouse is clicked (actually, when the mouse is
	 * released), and is passed the MouseEvent object for that click.
	 *
	 * @param e
	 */
	protected abstract void onClick(MouseEvent e);

	/**
	 * Is called whenever key is pressed
	 */
	protected abstract void onKeyboard(KeyEvent e);

	/**
	 * update the buttons status
	 */
	protected abstract void validateButtons();

	/**
	 * Constructor for the canvas. Takes only one JFrame
	 */
	public AbstractCanvas(JFrame frame) {
		canvasWidth = frame.getWidth();
		canvasHeight = frame.getHeight();
		setBackground(new Color(77, 115, 166));
	}

}
