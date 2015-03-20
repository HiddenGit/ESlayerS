package com.ikovps.slayer.utils;

import java.util.ArrayList;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * A utility class containing useful methods.
 * @author Masood
 *
 */
public class Utils {
	 
	/**
	 * Deposits all items except the listed ids.
	 * @param itemIDs the item ids to not deposit.
	 */
	public static void depositAllExcept(int... itemIDs) {
		final ArrayList<Integer> dontDeposit = new ArrayList<Integer>();
		if (Inventory.getCount(false) <= 2) {
			return;
		} else {
			for (int i : itemIDs) {
				dontDeposit.add(i);
			}
		}
		for (Item inventoryItem : Inventory.getItems()) {
			if (!dontDeposit.contains(inventoryItem.getId())) {
				Menu.sendAction(431, inventoryItem.getId() - 1, inventoryItem.getSlot(), 5064, 2213, 3);
				Time.sleep(80);
			}
		}
	}



	/**
	 * Lets us know if the bank interface is open.
	 * @return true if bank interface is open.
	 */
	public static boolean bankIsOpen() {
		return Game.getOpenInterfaceId() == 5292;
	}
	/**
	 * A wrapper sleep method.
	 * @param condition the condition.
	 */
	public static void sleep(boolean condition) {
		sleep(condition, 30000);
	}

	/**
	 * Calls a sleep condition.
	 * @param condition the condition to meet.
	 */
	public static void sleep(final boolean condition, int timeout) {
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return condition;
			}
		}, timeout);
	}
	/**
	 * Calls an interaction with a bank booth.
	 */
	public static void bank() {
		SceneObject[] booth = SceneObjects.getNearest(2213);
		if (Game.getOpenInterfaceId() != 5292) {
			if (booth.length > 0) {
				while (Game.getOpenInterfaceId() != 5292 && booth[0] != null) {
					booth[0].interact(1);
					SleepCondition condition = new SleepCondition() {
						@Override
						public boolean isValid() {
							return Game.getOpenInterfaceId() == 5292;
						}
					};
					Time.sleep(condition, 8000);
				}
			}
		}
	}
	
	/**
	 * Eats food at a given HP percentage.
	 * @param itemIds the item ids.
	 * @param hpPercent the hp percent.
	 */
	public static void eat(int[] itemIds, final int hpPercent) {
		while (getHpPercent() < hpPercent && Inventory.getItems(itemIds) != null) {
			for (int i = 0; i < Inventory.getItems(itemIds).length; i++) {
				Menu.sendAction(74, itemIds[i] - 1, Inventory.getItems(itemIds)[i].getSlot(), 3214);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return getHpPercent() > hpPercent;
					}
				}, 1500);
			}
		}
	}
	
	/**
	 * Drinks a potion.
	 * When prayer is set true 
	 * a potion will be drank,
	 * if a player's prayer points are less than the lvl parameter.
	 * @param potionId the potion id.
	 * @param prayer is set true if the potion being drank is for prayer.
	 * @param lvl the prayer level to have in order for a potion to be consumed.
	 */
	public static void drink(int potionId, boolean prayer, final int lvl) {
		if (prayer && Inventory.getItems(potionId) != null) {
			while (Skill.PRAYER.getLevel() < lvl) {
				for (Item i : Inventory.getItems(potionId)) {
					Menu.sendAction(74, i.getId() - 1, i.getSlot(), 3214);
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return Skill.PRAYER.getLevel() > lvl;
						}
					}, 1500);
				}
			}
		} else {
			if (Inventory.getItems(potionId) != null) {
				for (final Item i : Inventory.getItems(potionId)) {
					if (i != null) {
						Menu.sendAction(74, i.getId() - 1, i.getSlot(), 3214);
						Time.sleep(80);
					}
				}
			}
		}
	}
	
	/**
	 * Gets the player's hp percentage.
	 * @return the hp percentage.
	 */
	public static int getHpPercent() {
		if (Skill.HITPOINTS.getRealLevel() > 99) {
			return (Skill.HITPOINTS.getLevel() * 100) / 99;
		} else {
			return (Skill.HITPOINTS.getLevel() * 100) / Skill.HITPOINTS.getRealLevel();
		}
	}
	
	//Drop All Except
		public static void dropAllExcept(int... itemIDs) {
			final ArrayList<Integer> dontDrop = new ArrayList<>();
			if (Inventory.getCount(false) <= 2) {
				return;
			} else {
				for (int i : itemIDs) {
					dontDrop.add(i);
				}
			}
			for (Item inventoryItem : Inventory.getItems()) {
				if (!dontDrop.contains(inventoryItem.getId())) {
					Menu.sendAction(847, inventoryItem.getId() - 1,
							inventoryItem.getSlot(), 3214);
					Time.sleep(80);
				}
			}
		}
	/**
	 * Formats a number as a string.
	 * @param number the number to format.
	 * @return the string formatted.
	 */
	public static String formatNumb(int number) {
		String numberString = String.valueOf(number);
		if (Integer.parseInt(numberString) < 1000) {
			return numberString;
		} else if (Integer.parseInt(numberString) > 1000 && Integer.parseInt(numberString) < 10000) {
			return numberString.charAt(0) + "." + numberString.charAt(1) + "k";
		} else if (Integer.parseInt(numberString) > 10000 && Integer.parseInt(numberString) < 100000) {
			return numberString.substring(0, 2) + "." + numberString.charAt(2) + "k";
		} else if (Integer.parseInt(numberString) > 100000 && Integer.parseInt(numberString) < 1000000) {
			return numberString.substring(0, 3) + "." + numberString.charAt(3) + "k";
		} else if (Integer.parseInt(numberString) > 1000000 && Integer.parseInt(numberString) < 10000000) {
			return numberString.charAt(0) + "." + numberString.charAt(1) + "m";
		} else if (Integer.parseInt(numberString) > 10000000 && Integer.parseInt(numberString) < 100000000) {
			return numberString.substring(0, 2) + "." + numberString.charAt(2) + "m";
		} else if (Integer.parseInt(numberString) > 100000000 && Integer.parseInt(numberString) < 1000000000) {
			return numberString.substring(0, 3) + "." + numberString.charAt(3) + "m";
		} else if (Long.valueOf(numberString) > 1000000000L && Long.valueOf(numberString) < 10000000000L) {
			return numberString.charAt(0) + "." + numberString.charAt(1) + "b";
		} else if (Long.valueOf(numberString) > 10000000000L && Long.valueOf(numberString) < 100000000000L) {
			return numberString.substring(0, 2) + "." + numberString.charAt(2) + "b";
		} else {
			return numberString;
		}
	}

}
