package main;

import controller.KPSmartController;
import model.KPSmartModel;
import view.KPSmartGUI;

/**
 *
 * SWEN301 KPSmart
 *
 * Format all code in the repo with, Ctrl + Shift + F
 * Comments for ammendment. 03/May
 *
 * 	From Max Copley, please read my commit comments. Thank you.
 *
 *
 * @author Max, Bonnie, Joely, Nic, Bobo
 *
 */

public class Main {

	public static void main(String[] args) {
		KPSmartModel kpsmartModel = new KPSmartModel();
		KPSmartGUI kpsmartGui = new KPSmartGUI();
		KPSmartController cont = new KPSmartController(kpsmartGui, kpsmartModel);
	}
}
