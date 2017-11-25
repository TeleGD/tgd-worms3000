package survival.gameobjects.gameplay;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.Camera;
import survival.input.CustomInput;
import survival.utils.Vector2;

public class TestObject extends MoveableGameObject{

	protected float walkSpeed = 0.5f;
	
	public TestObject(Image spr)
	{
		super(spr);
		location = new Vector2(0, 0);
		sprite = spr;
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		Vector2 moveDirection = new Vector2(0,0);
		if(CustomInput.up)
			moveDirection.y -= arg2*walkSpeed;
		if(CustomInput.left)
			moveDirection.x -= arg2*walkSpeed;
		if(CustomInput.down)
			moveDirection.y += arg2*walkSpeed;
		if(CustomInput.right)
			moveDirection.x += arg2*walkSpeed;
		
		move(moveDirection);
		
		if(isCollidingWithSomething())
		{
			undoLocation();
		}
		
		Camera.follow(location, 5, arg2);
		
		updateCollisionData();
	}
}
