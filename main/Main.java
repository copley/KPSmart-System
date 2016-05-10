package main;

import controller.KPSmartController;
import model.KPSmartModel;
import view.KPSmartFrame;

/**
 *
 * SWEN301 KPSmart
 *
 * Welcome to KPSmart
 *
 * @author Max, Bonnie, Joely, Nic, Bobo
 *
 */

public class Main {

	public static void main(String[] args) {
		KPSmartModel kpsmartModel = new KPSmartModel();
		// KPSmartGUI kpsmartGui = new KPSmartGUI();
		KPSmartFrame view = new KPSmartFrame();
		KPSmartController cont = new KPSmartController(view, kpsmartModel);
	}
}
