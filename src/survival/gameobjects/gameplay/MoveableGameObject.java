package survival.gameobjects.gameplay;

import org.newdawn.slick.Image;

import survival.gameobjects.GameObject;
import survival.utils.Vector2;
import survival.worlds.World;

public class MoveableGameObject extends GameObject {

	protected Vector2 old_location;
	
	public MoveableGameObject(Image spr) {
		super(spr);
		old_location = new Vector2(0,0);
	}
	
	protected void updateCollisionData()
	{
		old_location.x = location.x;
		old_location.y = location.y;
	}
	
	protected void undoLocation()
	{
		location.x = old_location.x;
		location.y = old_location.y;
	}

	protected boolean isCollidingWithSomething()
	{
		for(GameObject i : World.activeWorld.getObjectList())
		{
			if(isCollidingWith(i) != null)
			{
				return true;
			}
		}
		return false;
	}
}
