package main;

import controller.KPSmartController;
import model.KPSmartModel;
import view.KPSmartGUI;

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
		KPSmartGUI kpsmartGui = new KPSmartGUI();
		KPSmartController cont = new KPSmartController(kpsmartGui, kpsmartModel);
	}
}
