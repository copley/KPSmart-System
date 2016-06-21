package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public class HomepagePanel extends AbstractMainDisplayPanel {

	private static final String IMG_PATH = "/resources/bgImage.png";

	private JLabel label;

	public HomepagePanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		ImageIcon icon = getImageIcon();
		label = new JLabel(icon);
		label.setMaximumSize(this.getMinimumSize());

		initialiseLayout();
	}

	private ImageIcon getImageIcon() {

		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource(IMG_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(img);

		return icon;
	}

	@Override
	protected void initialiseLayout() {
		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(label,
				Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE));

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(label,
				GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE));

		setLayout(groupLayout);
	}

	@Override
	public void resetTextFields() {
	}

}
