package survival.gameobjects.gameplay;

import org.newdawn.slick.Image;

import survival.gameobjects.GameObject;
import survival.input.CustomInput;
import survival.utils.Vector2;

public class Character extends MoveableGameObject{

	protected Compteur health;
	protected float walkSpeed = 0.25f;
	
	/**
	 * 0 = up
	 * 1 = left
	 * 2 = down
	 * 3 = right
	 */
	protected int direction = 0;

	
	/**
	 * 
	 * @param spr
	 * @param wlkspeed
	 */
	public Character(Image spr, float wlkspeed) {
		super(spr);
		location = new Vector2(0, 0);
		walkSpeed = wlkspeed;
	}
	
	public void hurt(float damage)
	{
		if(this instanceof Infected){
			if(health.canAdd(-damage)==false) {
				destroy(this);
			}
		} else {
			health.canAdd(-damage);
		}
			
		
		//System.out.println("touchee");
	}
	
	public void updateDirection(Vector2 dir)
	{
		if(Math.abs(dir.x) > Math.abs(dir.y) )
		{
			if(dir.x > 0)
				direction = 3;
			else
				direction = 1;
		}else
		{
			if(dir.y > 0)
				direction = 2;
			else
				direction = 0;
		}
	}
	
	
	public void setWalkSpeed(float val)
	{
		walkSpeed = val;
	}
	
	public void walk(Vector2 direction)
	{
		if(direction.magnitude() > 0.1f)
		{
			move(Vector2.mult(walkSpeed , Vector2.normalize(direction)));
			updateDirection(direction);
		}
	}

}
