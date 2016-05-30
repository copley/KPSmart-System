package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.KPSmartController.KeyAction;
import controller.KPSmartController.MouseAction;
import controller.KPSmartController.ViewActionListener;

@SuppressWarnings("serial")
public class HomepagePanel extends AbstractMainDisplayPanel {

	private static final String IMG_PATH = "src/images/backgraoundIMG.png";

	JLabel label;

	public HomepagePanel(KeyAction keyAction, MouseAction mouseAction, ViewActionListener viewActionListener) {
		super(keyAction, mouseAction, viewActionListener);

		ImageIcon icon = getImageIcon();
        label = new JLabel(icon);

        initialiseLayout();
	}

	private ImageIcon getImageIcon() {

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(IMG_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
        ImageIcon icon = new ImageIcon(img);

		return icon;
	}

	@Override
	protected void initialiseLayout() {
		//add(label);
	}

	@Override
	public void resetTextFields() {
	}

}
