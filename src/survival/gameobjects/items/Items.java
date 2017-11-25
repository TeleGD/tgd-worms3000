package survival.gameobjects.items;

import survival.gameobjects.GameObject;
import survival.gameobjects.gameplay.Player;

public abstract class Items extends GameObject {
	
	public static Player player;
	protected String name;
	private boolean onGround;
	
	public Items() {
		
	}
	
	public abstract void action();
	
	public boolean getOnGround() {
		return this.onGround;
	}
	
	public void pick() {
		// this.destroy();
		// to do
		// objet rammassé par joueur --> inventaire
	}
	
}
