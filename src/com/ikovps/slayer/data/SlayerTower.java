package com.ikovps.slayer.data;

import org.rev317.min.api.wrappers.Tile;

/**
 * This enum is for tasks on the second floor of slayer tower.
 * @author Masood
 *
 */
public enum SlayerTower {
	
	BATTLE_MAGES("Battle Mages" , new Tile[] { new Tile(3418, 3537) });

	
	private String name;
	private Tile[] tiles;

	SlayerTower(String name, Tile[] tiles) {
		this.name = name;
		this.tiles = tiles;
	}

	public String getName() {
		return name;
	}
	
	public Tile[] getTiles() {
		return tiles;
	}	
}
