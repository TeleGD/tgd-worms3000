package survival.worlds;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.SurvivalMain;
import survival.gameobjects.GameObject;
import survival.gameobjects.gameplay.TestObject;
import survival.gameobjects.mapping.MapObject;
import survival.utils.Vector2;

public class CityWorld extends World{
	
	public static int ID = 1000;
	
	public void mapGeneration(int seed) throws SlickException
	{
		for(int i=0; i<25; i++)
		{
			addGameObject(new MapObject(new Image(SurvivalMain.DIRECTORY_IMAGES + "william.png"), new Vector2(i*200,-200)));
		}
		
		for(int i=0; i<25; i++)
		{
			addGameObject(new MapObject(new Image(SurvivalMain.DIRECTORY_IMAGES + "william.png"), new Vector2(i*200,200)));
		}
	}


	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		objects = new LinkedList<GameObject>();
		addGameObject(new TestObject(new Image(SurvivalMain.DIRECTORY_IMAGES + "william.png")));
		World.activeWorld = this;
		mapGeneration(0);
	}



	@Override
	public int getID() {
		return ID;
	}
}
