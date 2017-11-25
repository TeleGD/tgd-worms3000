package survival.gameobjects.mapping;

import org.newdawn.slick.Image;
import survival.gameobjects.GameObject;
import survival.utils.Vector2;

public class MapObject extends GameObject{
	
	public MapObject(Image spr, Vector2 loc)
	{
		super(spr);
		location = loc;
		sprite = spr;
	}	
}
