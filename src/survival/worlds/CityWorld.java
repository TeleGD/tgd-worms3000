package survival.worlds;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.SurvivalMain;
import survival.gameobjects.GameObject;
import survival.gameobjects.gameplay.Compteur;
import survival.gameobjects.gameplay.Infected;
import survival.gameobjects.gameplay.TestObject;
import survival.gameobjects.mapping.MapObject;
import survival.gameobjects.mapping.RepeatBackground;
import survival.utils.Vector2;

public class CityWorld extends World{
	
	public static int ID = 1000;
	
	public void mapGeneration(int seed) throws SlickException
	{
		addBackground(new RepeatBackground(new Image(SurvivalMain.DIRECTORY_IMAGES + "backgrounds/concrete0.jpg"), new Vector2(0,0)));
		
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
		World.activeWorld = this;
		
		objects = new LinkedList<GameObject>();
		deleteObjects = new LinkedList<GameObject>();
		uiobjects = new LinkedList<GameObject>();
		backgrounds = new LinkedList<GameObject>();
		
		World.activePlayer = new TestObject(new Image(SurvivalMain.DIRECTORY_IMAGES + "william.png"));
		addGameObject(World.activePlayer);
		addGameObject(new Infected(new Image(SurvivalMain.DIRECTORY_IMAGES + "william.png"), new Vector2(-400,-400),1f,5f));
		addUiGameObject(new Compteur(30,100, new Vector2(500,650), new Vector2(10,11),new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/barre.png"), new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/HungryBar.png")));
		addUiGameObject(new Compteur(80,100, new Vector2(800,650), new Vector2(10,11),new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/barre.png"), new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/WaterBar.png")));
		
		
		mapGeneration(0);
	}



	@Override
	public int getID() {
		return ID;
	}
}
