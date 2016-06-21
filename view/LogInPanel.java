package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.KPSmartController.ViewActionListener;

import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
import static javax.swing.KeyStroke.getKeyStroke;

/**
 * The log in panel of KPSmart. Where users to enter their ID and password.
 *
 * @author Shenbo Xuan
 *
 */

@SuppressWarnings("serial")
public class LogInPanel extends JFrame {

	private AuthDialog ad;

	public LogInPanel(ViewActionListener viewActionListener) {
		super("KPSmart Log In");

		setSize(600, 600);

		// Show Authentication dialog
		ad = new AuthDialog(this, "KPSmart Login", viewActionListener);
	}

	/**
	 * Return the user id.
	 * @return
	 */
	public String getUserID() {
		return ad.getUserID();
	}

	/**
	 * Return the user password.
	 * @return
	 */
	public String getPassword() {
		return ad.getDatabasePassword();
	}

	/**
	 * the login dialog.
	 *
	 * @author Shenbo Xuan
	 *
	 */
	class AuthDialog extends JDialog {
		private boolean loginButtonClicked = false;

		String introText = "Please enter your user ID and password";
		private JPanel labelPanel = new JPanel();
		private JPanel inputPanel = new JPanel();
		private JTextField userIDTextField = new JTextField(20);
		private JPasswordField passwdTextField = new JPasswordField(20);

		public AuthDialog(JFrame parent, String title, ViewActionListener viewActionListener) {
			this(parent, title, true, viewActionListener);
		}

		public AuthDialog(final JFrame parent, String title, boolean modal, ViewActionListener viewActionListener) {
			super(parent, title, modal);

			// Set up close behaviour
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					if (!loginButtonClicked)
						System.exit(0);
				}
			});

			// Set up OK button behaviour
			JButton loginButton = new JButton("Log In");
			loginButton.addActionListener(viewActionListener);

			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			// Set up dialog contents
			labelPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 5));
			inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 20));

			labelPanel.setLayout(new GridLayout(2, 1));
			labelPanel.add(new JLabel("User ID: "));
			labelPanel.add(new JLabel("Password:"));
			inputPanel.setLayout(new GridLayout(2, 1));
			inputPanel.add(userIDTextField);
			inputPanel.add(passwdTextField);

			Box buttonPane = new Box(X_AXIS);
			buttonPane.add(Box.createHorizontalGlue());
			buttonPane.add(loginButton);
			buttonPane.add(Box.createHorizontalStrut(5));
			buttonPane.add(cancelButton);
			buttonPane.add(Box.createHorizontalGlue());
			buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

			JLabel introLabel = new JLabel(introText);
			introLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			getContentPane().add(introLabel, BorderLayout.NORTH);
			getContentPane().add(labelPanel, BorderLayout.WEST);
			getContentPane().add(inputPanel, BorderLayout.CENTER);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			// Ensure the enter key triggers the OK button
			getRootPane().setDefaultButton(loginButton);

			// And that the escape key exits
			InputMap inputMap = getRootPane().getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
			ActionMap actionMap = getRootPane().getActionMap();
			inputMap.put(getKeyStroke("ESCAPE"), "exitAction");
			actionMap.put("exitAction", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			// Pack it all
			pack();

			// Center on the screen
			setLocationRelativeTo(null);
		}

		public String getUserID() {
			return userIDTextField.getText();
		}

		public String getDatabasePassword() {
			return new String(passwdTextField.getPassword());
		}

		public void clearInput() {
			this.userIDTextField.setText("");
			this.passwdTextField.setText("");
		}

	}

	public void showWindow(boolean b) {
		ad.setVisible(b);
	}

	public void clearInputs() {
		ad.clearInput();
	}
}
