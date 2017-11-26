package survival.gameobjects.items;

import survival.gameobjects.gameplay.Player;
import survival.utils.Vector2;
import org.newdawn.slick.Image;

public class Pistol extends Items {
	
	private Player player;
	private float damage;
	private float range;
	private int ammo;
	
	public Pistol(Vector2 location, float damage, float range, int ammo,Image sprite) {
		super(location,sprite);
		this.damage = damage;
		this.range = range;
		this.ammo = ammo;
	}
	
	public void action() {
		// Tir à distance
	}
	
}
