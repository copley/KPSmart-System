package main;

import controller.KPSmart_Controller;
import gui.KPSmart_GUI;
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
		KPSmart_GUI kpsmartGui = new KPSmart_GUI();
		KPSmart_Controller cont = new KPSmart_Controller(kpsmartGui, kpsmartModel);
	}
}
