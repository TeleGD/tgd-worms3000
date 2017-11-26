package survival.gameobjects.gameplay;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.Camera;
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
		boundingBox = new Vector2(50,80);
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

	
	public void render(GameContainer container, StateBasedGame game, Graphics g, boolean useCamera) throws SlickException
	{
		float xx = location.x - boundingBox.x;
		float yy = location.y- boundingBox.y;
		
		if(useCamera)
		{
			xx -= Camera.location.x;
			yy -= Camera.location.y;
		}
		
		if(sprite != null)
		{
			float w = sprite.getWidth()/3;
			
			float h = sprite.getHeight()/4;
			//System.out.println(h);
			float yyy = direction*h;
			float xxx = 0;
			
			g.drawImage(sprite, xx, yy,xx+boundingBox.x,yy+boundingBox.y,xxx,yyy,xxx+w,yyy+h);
		}
	}
}
