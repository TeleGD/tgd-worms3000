package survival.gameobjects.gameplay;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.Camera;
import survival.input.CustomInput;
import survival.utils.Vector2;

public class TestObject extends Character{
	public TestObject(Image spr)
	{
		super(spr, 10);
		location = new Vector2(0, 0);
		sprite = spr;
	}
	
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		Vector2 moveDirection = new Vector2(0,0);
		if(CustomInput.up)
			moveDirection.y -= 1;
		if(CustomInput.left)
			moveDirection.x -= 1;
		if(CustomInput.down)
			moveDirection.y += 1;
		if(CustomInput.right)
			moveDirection.x += 1;
		
		
		walk(moveDirection);
		
		if(isCollidingWithSomething())
		{
			undoLocation();
		}
		
		Camera.follow(location, 5, arg2);
		
		updateCollisionData();
	}
}
