package main;

import controller.KPSmartController;
import gui.KPSmartGUI;
import model.KPSmart_Model;

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

public class main {

	public static void main(String[] args) {
		KPSmart_Model kpsmartModel = new KPSmart_Model();
		KPSmartGUI kpsmartGui = new KPSmartGUI();
		KPSmartController cont = new KPSmartController(kpsmartGui, kpsmartModel);
	}
}
