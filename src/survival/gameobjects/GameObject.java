package survival.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.utils.Vector2;

public abstract class GameObject {

	protected Image sprite;
	protected Vector2 location;
	
	public GameObject()
	{
		
	}
	
	public void update()
	{
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		g.drawImage(sprite, location.x, location.y);
	}
}
