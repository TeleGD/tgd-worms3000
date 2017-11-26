package survival.gameobjects.items;

import survival.gameobjects.GameObject;
import survival.gameobjects.gameplay.Player;
import survival.utils.Vector2;
import survival.worlds.World;

import org.newdawn.slick.Image;

public abstract class Items extends GameObject {
	
	public static Player player;
	protected String name;
	private boolean onGround;
	
	public Items(Vector2 location,Image sprite) {
		this.sprite = sprite;
		this.onGround = true;
		this.location = location;
	}
	
	public abstract void action();
	
	public boolean getOnGround() {
		return this.onGround;
	}
	
	public void pick() {
		this.onGround=false;
		Player.addItem(this);
		this.destroy(this);
		// objet rammassé par joueur --> inventaire		
	}
	
}
