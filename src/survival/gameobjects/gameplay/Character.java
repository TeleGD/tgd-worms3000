package survival.gameobjects.gameplay;

import org.newdawn.slick.Image;

import survival.input.CustomInput;
import survival.utils.Vector2;

public class Character extends MoveableGameObject{

	protected float walkSpeed = 0.25f;
	
	public Character(Image spr, float wlkspeed) {
		super(spr);
		location = new Vector2(0, 0);
		walkSpeed = wlkspeed;
	}
	
	public void setWalkSpeed(float val)
	{
		walkSpeed = val;
	}
	
	public void walk(Vector2 direction)
	{
		if(direction.magnitude() > 0.1f)
			move(Vector2.mult(walkSpeed , Vector2.normalize(direction)));
	}

}
