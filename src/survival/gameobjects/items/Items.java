package survival.gameobjects.items;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.gameobjects.GameObject;
import survival.gameobjects.gameplay.Player;

public abstract class Items extends GameObject {
	
	private static Player player;
	protected String name;
	
	public Items() {
		
	}
	
	public abstract void action();
	
	public void pick() {
		// this.destroy();
		// to do
		// objet rammassé par joueur --> inventaire
	}
	
}
