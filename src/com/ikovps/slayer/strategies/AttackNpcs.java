package com.ikovps.slayer.strategies;

import org.parabot.environment.api.utils.Filter;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;

import com.ikovps.slayer.data.Variables;

/**
 * A class that attacks assigned Slayer monsters.
 * @author Masood
 * 
 */
public class AttackNpcs implements Strategy {
	@Override
	public boolean activate() {
		return Variables.arrived && !Players.getMyPlayer().isInCombat();
	}

	@Override
	public void execute() {
		
		//add looting for item 7957
		if (!Variables.decreaseTask) {
			Variables.decreaseTask = true;
		} else {
			Variables.taskAmount--;
		}
		
		System.out.println("Killing npcs");
		
		if (Variables.status != "Completing task...") {
		Variables.status = "Completing task...";
		}
		
		Npc[] slayerMonster = Npcs.getNearest(slayerMonsterFilter);

		try {
		slayerMonster[0].interact(1);
		} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
			
		} 
		
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return Players.getMyPlayer().isInCombat();
			}
		}, 5000);
		
		Time.sleep(new SleepCondition() {
			@Override
			public boolean isValid() {
				return !Players.getMyPlayer().isInCombat();
			}
		}, 5000);
	}
	
	private final Filter<Npc> slayerMonsterFilter = new Filter<Npc>() {
		@Override
		public boolean accept(Npc n) {
			int[] ids = Variables.task.getNpcIds();
			for (final int id : ids) {
				if (n.getDef().getId() == id && n != null && !n.isInCombat()) {
					return true;
				}
			}
			return false;
		}

	};

}
