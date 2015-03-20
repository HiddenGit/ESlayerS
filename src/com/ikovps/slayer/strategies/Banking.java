package com.ikovps.slayer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;

import com.ikovps.slayer.data.Constants;
import com.ikovps.slayer.utils.Teleport;
import com.ikovps.slayer.utils.Utils;


/**
 * A class that attacks assigned Slayer monsters.
 * @author Masood
 * 
 */
public class Banking implements Strategy {
	@Override
	public boolean activate() {
		return Inventory.getCount(Constants.SHARK) < 2;
	}

	@Override
	public void execute() {
		System.out.println("Banking...");
		if (!Constants.bankZone.inTheZone()) {
			Teleport.home();
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Constants.bankZone.inTheZone();
				}
			}, 5000);
		}
		
		if (!Bank.isOpen() && Constants.bankZone.inTheZone()) {
			Utils.bank();
		}
		
		if (Bank.isOpen()) {
			System.out.println("Withdrawing sharks");
			Bank.withdraw(Constants.SHARK, 20, 100);
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Inventory.getCount(Constants.SHARK) > 2;
				}
			}, 5000);
			Bank.close();
		}
	}
}
