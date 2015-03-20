package com.ikovps.slayer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Game;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.Tile;

import com.ikovps.slayer.data.Constants;
import com.ikovps.slayer.data.SlayerTask;
import com.ikovps.slayer.data.Variables;
import com.ikovps.slayer.utils.Teleport;

public class ObtainTask extends Variables implements Strategy {

	@Override
	public boolean activate() {

		return !hasTask;
	}

	@Override
	public void execute() {

		System.out.println("Getting a slayer task!");
		
		if (status != "Getting a task...") {
		status = "Getting a task...";
		}
		
		if (slayerMaster == 0 && !Constants.mazachnaZone.inTheZone()) {

			Teleport.home();
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Players.getMyPlayer().getLocation().equals(new Tile(3093, 3493));
				}
			}, 5000);
			Time.sleep(1000);

			new Tile(3094, 3481).walkTo();
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Players.getMyPlayer().getLocation().equals(new Tile(3094, 3481));
				}
			}, 10000);
			

		} else if (slayerMaster == 1 && !Constants.vannakaZone.inTheZone()) {

			Teleport.location("slayer");

			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Game.getOpenBackDialogId() == 2492;
				}
			}, 3000);

			Teleport.clickOption(1);

			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Constants.vannakaZone.inTheZone();
				}
			}, 3000);

		} else {

			Npc[] master = {};
			try {
				master = Npcs.getNearest(Constants.MAZACHNA, Constants.VANNAKA);
			} catch (NullPointerException e) {
				System.out.println("Null pointer caught");
			}

			if (master.length > 0 && master[0] != null && Game.getOpenBackDialogId() != 4887) {
				master[0].interact(0);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Game.getOpenBackDialogId() == 4887;
					}
				}, 5000);
			}

			if (Game.getOpenBackDialogId() == 4887) {
				Menu.sendAction(679, 38, 320, 4892, 398);
				Time.sleep(new SleepCondition() {
					@Override
					public boolean isValid() {
						return Game.getOpenBackDialogId() == 2492 || Game.getOpenBackDialogId() == 2469;
					}
				}, 5000);

				if (resetTask) {

					if (Game.getOpenBackDialogId() == 2492) {
						status = "Resetting task...";
						System.out.println("Resetting task");
						Teleport.clickOption(3);
						Time.sleep(new SleepCondition() {
							@Override
							public boolean isValid() {
								return Game.getOpenBackDialogId() != 2492;
							}
						}, 5000);
						resetTask = false;
					}

					if (Game.getOpenBackDialogId() == 2469) {
						status = "Resetting task...";
						System.out.println("Resetting task");
						Time.sleep(200);
						Menu.sendAction(315, 0, 0, 2473, 0);
						Time.sleep(new SleepCondition() {
							@Override
							public boolean isValid() {
								return Game.getOpenBackDialogId() != 2469;
							}
						}, 5000);
						resetTask = false;
					}
				} else {

					if (Game.getOpenBackDialogId() == 2492) {
						Teleport.clickOption(0);
						Time.sleep(new SleepCondition() {
							@Override
							public boolean isValid() {
								return Game.getOpenBackDialogId() == 4887;
							}
						}, 5000);
					}

					if (Game.getOpenBackDialogId() == 2469) {
						Time.sleep(200);
						Menu.sendAction(315, 0, 0, 2471, 0);
						Time.sleep(new SleepCondition() {
							@Override
							public boolean isValid() {
								return Game.getOpenBackDialogId() == 4887;
							}
						}, 5000);
					}

					String getTask = Loader.getClient().getInterfaceCache()[4890].getMessage();
					String[] i = getTask.split("kill ");
					
					System.out.println("" + getTask);

					for (SlayerTask s : SlayerTask.values()) {
						if (getTask.toLowerCase().contains(s.getName().toLowerCase())) {
							task = s;
							hasTask = true;
							String[] z = i[1].toLowerCase().split(" " + s.getName().toLowerCase());
							try {
								taskAmount = Integer.parseInt(z[0]);
							} catch (NumberFormatException e) {
								System.out.println("excpetion caught");
							}
							System.out.println("Task set to: " + taskAmount + " " + task.getName());
						}
					}

					Menu.sendAction(679, 38, 320, 4892, 398);
					Time.sleep(new SleepCondition() {
						@Override
						public boolean isValid() {
							return Game.getOpenBackDialogId() != 4887;
						}
					}, 5000);

					if (!hasTask) {
						resetTask = true;
					}
				}
			}
		}
	}
}