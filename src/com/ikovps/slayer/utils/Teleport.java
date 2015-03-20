package com.ikovps.slayer.utils;

import org.parabot.environment.input.Keyboard;
import org.rev317.min.api.methods.Menu;


public class Teleport {

	public static void location(String locationName) {

		int locationAction = 1164;

		switch (locationName) {
		
		case "training":
			locationAction = 1164;
			break;
		case "minigame":
			locationAction = 1167;
			break;
		case "pking":
			locationAction = 1170;
			break;
		case "slayer":
			locationAction = 1174;
			break;
		case "city":
			locationAction = 1540;
			break;
		case "skilling":
			locationAction = 1541;
			break;
		case "boss":
			locationAction = 7455;
			break;
		}
		Menu.sendAction(315, 0, 0, locationAction, 0);
	}

	public static void clickOption(int option) {
		
		int optionAction = 2494;
		
		switch (option) {
		
		case 0:
			optionAction = 2494;
			break;
		case 1:
			optionAction = 2495;
			break;
		case 2:
			optionAction = 2496;
			break;
		case 3:
			optionAction = 2497;
			break;
		case 4:
			optionAction = 2498;
			break;
		}
		Menu.sendAction(315, 0, 0, optionAction, 0);
	}
	
	public static void home() {		
		Keyboard.getInstance().sendKeys("::home");
		
	}
}
