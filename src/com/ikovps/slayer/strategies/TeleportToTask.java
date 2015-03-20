package com.ikovps.slayer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Tile;

import com.ikovps.slayer.data.Variables;
import com.ikovps.slayer.utils.Teleport;

/**
 * A class that teleports to an assigned Slayer task.
 * @author Masood
 * 
 */
public class TeleportToTask extends Variables implements Strategy {
	@Override
	public boolean activate() {
		return hasTask && !teleported;
	}

	@Override
	public void execute() {
		
		System.out.println("Teleporting to task location");
		
		status = "Getting to task...";
		
		if (Game.getOpenBackDialogId() != 2492) {
			
			//to prevent glitching with Mazachna dialogue.
			new Tile(3094, 3481).walkTo();
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Players.getMyPlayer().getLocation().equals(new Tile(3094, 3481));
				}
			}, 1000);
			
			Teleport.location(task.getTeleportLocation());

			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Game.getOpenBackDialogId() == 2492;
				}
			}, 3000);
		}

		if (Game.getOpenBackDialogId() == 2492) {
			if (task.getTeleportOptions().length > 1) {
				Teleport.clickOption(task.getTeleportOptions()[0]);
				Time.sleep(1000);
				Teleport.clickOption(task.getTeleportOptions()[1]);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Game.getOpenBackDialogId() != 2492;
					}
				}, 3000);
				Time.sleep(3000);
			} else {
				Teleport.clickOption(task.getTeleportOptions()[0]);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Game.getOpenBackDialogId() != 2492;
					}
				}, 3000);
				Time.sleep(3000);
			}
			teleported = true;
		}
	}
}
