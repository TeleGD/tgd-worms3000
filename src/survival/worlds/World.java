package survival.worlds;

import java.util.LinkedList;

import survival.gameobjects.GameObject;

public abstract class World {
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
	
	public void render()
	{
		for(GameObject i : objects)
			i.render();
	}
}
