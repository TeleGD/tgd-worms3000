package survival.gameobjects.items;

import survival.gameobjects.GameObject;
import survival.gameobjects.gameplay.Player;
import survival.utils.Vector2;

public abstract class Items extends GameObject {
	
	public static Player player;
	protected String name;
	private boolean onGround;
	
	public Items(Vector2 location) {
		this.onGround = true;
		this.location = location;
	}
	
	public abstract void action();
	
	public boolean getOnGround() {
		return this.onGround;
	}
	
	public void pick() {
		this.onGround=false;
		// Player.addItem();
		// this.destroy();
		// objet rammassé par joueur --> inventaire		
	}
	
}
