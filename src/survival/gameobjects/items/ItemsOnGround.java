package survival.gameobjects.items;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.gameobjects.GameObject;

public class ItemsOnGround extends GameObject {
	
	// private static Player player;
	
	public ItemsOnGround() {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// render
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// update
	}
	
	public void pick() {
		// this.destroy();
		// to do
		// objet rammassé par joueur --> inventaire
	}
	
}
