package survival.gameobjects.items;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.gameobjects.GameObject;

public abstract class ItemsInventory extends GameObject {

	//private static Player player;
	protected String name;
	
	public abstract void action();
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// render
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// update
	}
	
}
