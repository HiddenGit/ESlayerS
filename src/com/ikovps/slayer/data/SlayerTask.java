package com.ikovps.slayer.data;

import org.rev317.min.api.wrappers.Tile;

public enum SlayerTask {
	
	BANSHEES("Banshees", "slayer", new int[] { 1 }, 15, -1, new int[] { 1612 }, new Tile[] { new Tile(3418, 3537), new Tile(3419, 3545), new Tile(3423, 3556), new Tile(3413, 3561), new Tile(3421, 3570), new Tile(3435, 3571), new Tile(3441, 3561), new Tile(3437, 3561) }, false),

	BASILISKS("Basilisks", "slayer", new int[] { 0 }, 40, 4499, new int[] { 1616 }, new Tile[] { new Tile(2798, 9996), new Tile(2786, 9996), new Tile(2777, 10005), new Tile(2784, 10017), new Tile(2798, 10017), new Tile(2802, 10030), new Tile(2793, 10034), new Tile(2783, 10034), new Tile(2774, 10030), new Tile(2766, 10036), new Tile(2758, 10028), new Tile(2766, 10017), new Tile(2760, 10007), new Tile(2756, 9995), new Tile(2747, 9998), new Tile(2743, 10010) }, false ),
	
	COCKATRICES("Cockatrices", "slayer", new int[] { 0 }, 25, 4499, new int[] { 1620 }, new Tile[] { new Tile(2798, 9996), new Tile(2786, 9996), new Tile(2777, 10005), new Tile(2784, 10017), new Tile(2798, 10017), new Tile(2802, 10030) }, false ),
	
	CRAWLING_HANDS("Crawling hands", "slayer", new int[] { 1 }, 5, -1, new int[] { 1648 }, new Tile[] { new Tile(3418, 3537), new Tile(3419, 3545) }, false ),
	
	HOBGOBLINS("Hobgoblins", "slayer", new int[] { 4, 0 }, 1, -1, new int[] { 122 }, new Tile[] { new Tile(3037, 9581), new Tile(3024, 9580), new Tile(3011, 9579) }, false ),
	
	ICE_GIANTS("Ice giants", "slayer", new int[] { 4, 0 }, 1, -1, new int[] { 111 }, new Tile[] { new Tile(3050, 9582) }, false ),
	
	ICE_WARRIORS("Ice warriors", "slayer", new int[] { 4, 0 }, 1, -1, new int[] { 125 }, new Tile[] { new Tile(3050, 9582) }, false ),
	
	JELLYS("Jellys", "slayer", new int[] { 0 }, 52, 4499, new int[] { 1637 }, new Tile[] { new Tile(2798, 9996), new Tile(2786, 9996), new Tile(2777, 10005), new Tile(2784, 10017), new Tile(2798, 10017), new Tile(2802, 10030), new Tile(2793, 10034), new Tile(2783, 10034), new Tile(2774, 10030), new Tile(2766, 10036), new Tile(2758, 10028), new Tile(2766, 10017), new Tile(2760, 10007), new Tile(2756, 9995), new Tile(2747, 9998), new Tile(2743, 10010), new Tile(2746, 10023), new Tile(2738, 10031), new Tile(2731, 10027), new Tile(2721, 10026), new Tile(2712, 10028) }, false ),
	
	PYREFIENDS("Pyrefiends", "slayer", new int[] { 0 }, 30, 4499, new int[] { 1633 }, new Tile[] { new Tile(2798, 9996), new Tile(2786, 9996), new Tile(2777, 10005), new Tile(2784, 10017), new Tile(2798, 10017), new Tile(2802, 10030), new Tile(2793, 10034), new Tile(2783, 10034), new Tile(2774, 10030), new Tile(2766, 10036), new Tile(2758, 10028), new Tile(2766, 10017), new Tile(2760, 10007) }, false ),
	
	TUROTHS("Turoths", "slayer", new int[] { 0 }, 25, 4499, new int[] { 1628, 1627, 1623 }, new Tile[] { new Tile(2798, 9996), new Tile(2786, 9996), new Tile(2777, 10005), new Tile(2784, 10017), new Tile(2798, 10017), new Tile(2802, 10030), new Tile(2793, 10034), new Tile(2783, 10034), new Tile(2774, 10030), new Tile(2766, 10036), new Tile(2758, 10028), new Tile(2766, 10017), new Tile(2760, 10007), new Tile(2756, 9995), new Tile(2747, 9998), new Tile(2743, 10010), new Tile(2746, 10023), new Tile(2738, 10031), new Tile(2731, 10027), new Tile(2721, 10026), new Tile(2712, 10028), new Tile(2704, 10025), new Tile(2710, 10012), new Tile(2721, 10007) }, false );
	
	//SKELETONS("skeleton", "slayer", new int[] { 4, 1 }, 1, 1759, 92, new Tile[] { new Tile(2884, 9802) } );
	
	private String name;
	private String teleportLocation;
	private int[] teleportOptions;
	private int[] npcIds;
	private int slayerLevel;
	private int object;
	private Tile[] tiles;
	private boolean secondFloor;

	SlayerTask(String name, String teleportLocation, int[] teleportOptions, int slayerLevel, int object, int[] npcIds, Tile[] tiles, boolean secondFloor) {
		this.name = name;
		this.teleportLocation = teleportLocation;
		this.teleportOptions = teleportOptions;
		this.npcIds = npcIds;
		this.slayerLevel = slayerLevel;
		this.object = object;
		this.tiles = tiles;
		this.secondFloor = secondFloor;
	}

	public String getName() {
		return name;
	}

	public String getTeleportLocation() {
		return teleportLocation;
	}

	public int[] getTeleportOptions() {
		return teleportOptions;
	}

	public int[] getNpcIds() {
		return npcIds;
	}

	public int getSlayerLevel() {
		return slayerLevel;
	}
	
	public int getObject() {
		return object;
	}
	
	public Tile[] getTiles() {
		return tiles;
	}
	
	public boolean isSecondFloor() {
		return secondFloor;
	}
	
}