package survival.worlds;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import survival.gameobjects.GameObject;

public abstract class World extends BasicGameState {
	public static World activeWorld;
	
	protected LinkedList<GameObject> objects;
	
	public World()
	{
		
	}
	
	public void update()
	{
		for(GameObject i : objects)
			i.update();
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		for(GameObject i : objects)
			i.render(container, game, g);
	}
}
