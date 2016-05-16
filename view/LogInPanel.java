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

import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.KeyStroke.getKeyStroke;

@SuppressWarnings("serial")
public class LogInPanel extends JFrame {

	private String userName;
	private String password;

	public LogInPanel() {
		super("KPSmart Log In");

		setSize(600, 600);

		// Show Authentication dialog
		AuthDialog ad = new AuthDialog(this, "Authentication");
		ad.setVisible(true);
		userName = ad.getUserName();
		password = ad.getDatabasePassword();

		// Show ourselves
		setVisible(true);
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public static void main(String[] args) {
		new LogInPanel();
	}

	class AuthDialog extends JDialog {
		private boolean okButtonClicked = false;

		String introText = "Please enter your username and database password";
		private JPanel labelPanel = new JPanel();
		private JPanel inputPanel = new JPanel();
		private JTextField usernameTf = new JTextField(20);
		private JPasswordField passwdTf = new JPasswordField(20);

		public AuthDialog() {
			this(null, "Authentication", false);
		}

		public AuthDialog(JFrame parent) {
			this(parent, "Authentication", true);
		}

		public AuthDialog(JFrame parent, String title) {
			this(parent, title, true);
		}

		public AuthDialog(final JFrame parent, String title, boolean modal) {
			super(parent, title, modal);

			// Set up close behaviour
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					if (!okButtonClicked)
						System.exit(0);
				}
			});

			// Set up OK button behaviour
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getUserName().length() == 0) {
						showMessageDialog(AuthDialog.this,
								"Please enter a username", "Format Error",
								ERROR_MESSAGE);
						return;
					}
					if (getDatabasePassword().length() == 0) {
						showMessageDialog(AuthDialog.this,
								"Please enter a password", "Format Error",
								ERROR_MESSAGE);
						return;
					}
					okButtonClicked = true;
					setVisible(false);
				}
			});

			JButton cancelButton = new JButton("Cancel");

			// Set up dialog contents
			labelPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 5));
			inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 20));

			labelPanel.setLayout(new GridLayout(2, 1));
			labelPanel.add(new JLabel("User Name: "));
			labelPanel.add(new JLabel("Password:"));
			inputPanel.setLayout(new GridLayout(2, 1));
			inputPanel.add(usernameTf);
			inputPanel.add(passwdTf);

			Box buttonPane = new Box(X_AXIS);
			buttonPane.add(Box.createHorizontalGlue());
			buttonPane.add(okButton);
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
			getRootPane().setDefaultButton(okButton);

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

		public String getUserName() {
			return usernameTf.getText();
		}

		public String getDatabasePassword() {
			return new String(passwdTf.getPassword());
		}

	}
}
