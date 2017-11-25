package survival.worlds;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import survival.gameobjects.GameObject;
import survival.input.CustomInput;

public abstract class World extends BasicGameState {
	public static World activeWorld;
	
	protected LinkedList<GameObject> objects;
	
	public World()
	{
		
	}
	
	public void addGameObject(GameObject obj)
	{
		objects.add(obj);
	}
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		for(GameObject i : objects)
			i.update(arg0, arg1, arg2);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		for(GameObject i : objects)
			i.render(container, game, g);
	}
	
	public void keyPressed(int key, char c)
	{
		CustomInput.keyPressed(key, c);
	}
	
	public void keyReleased(int key, char c)
	{
		CustomInput.keyReleased(key, c);
	}
	
	public LinkedList<GameObject> getObjectList()
	{
		return objects;
	}
}
