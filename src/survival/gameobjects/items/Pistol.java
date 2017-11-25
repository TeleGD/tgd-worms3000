package survival.gameobjects.items;

import survival.gameobjects.gameplay.Player;

public class Pistol {
	
	private Player player;
	private float damage;
	private float range;
	private int ammo;
	
	public Pistol(float damage, float range, int ammo) {
		this.damage = damage;
		this.range = range;
		this.ammo = ammo;
	}
	
	public void action() {
		// Tir à distance
	}
	
}
