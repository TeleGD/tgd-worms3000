package survival.gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.Camera;
import survival.utils.Vector2;

public abstract class GameObject {

	protected Image sprite;
	public Vector2 location;
	public Vector2 boundingBox;
	
	public GameObject()
	{
		sprite = null;
		boundingBox = null;
		location = new Vector2(0,0);
	}
	
	public GameObject(Image spr)
	{
		sprite = spr;
		boundingBox = new Vector2(spr.getWidth(), spr.getHeight());
		location = new Vector2(0,0);
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		if(sprite != null)
			g.drawImage(sprite, location.x - Camera.location.x - sprite.getWidth()/2, location.y - Camera.location.y- sprite.getHeight()/2);
	}
	
	public void move(Vector2 direction)
	{
		location.x += direction.x;
		location.y += direction.y;
	}
	
	public Vector2 isCollidingWith(GameObject other)
	{
		if(boundingBox != null && other.boundingBox != null)
		{
			if(other.equals(this))
				return null;
			if(other.location.x + other.boundingBox.x/2f + boundingBox.x/2f > location.x)
				if(other.location.x - other.boundingBox.x/2f - boundingBox.x/2f < location.x)
					if(other.location.y + other.boundingBox.y/2f + boundingBox.y/2f > location.y)
						if(other.location.y - other.boundingBox.y/2f - boundingBox.y/2f < location.y)
							return new Vector2(other.location.x - location.x, other.location.y - location.y);
		//A NORMALISER
		}
		return null;
	}
}
