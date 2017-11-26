package survival.gameobjects.gameplay;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.Camera;
import survival.SurvivalMain;
import survival.gameobjects.GameObject;
import survival.input.CustomInput;
import survival.utils.Vector2;
import survival.worlds.World;

public class TestObject extends Character{
	
	//protected Compteur health;
	protected float attackRange = 50;
	protected float attackDamage = 1;
	
	public TestObject(Image spr) throws SlickException
	{
		super(spr, 5);
		health = new Compteur(100,100, new Vector2(200,650), new Vector2(10,11),new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/barre.png"), new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/HealthBar.png"));
		World.activeWorld.addUiGameObject(health);
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
		
		if(CustomInput.space)
		{
			System.out.println(direction);
			Vector2 attackPoint = new Vector2(location.x, location.y);
			switch(direction)
			{
			case 0:
				attackPoint.add(new Vector2(0,-attackRange));
				break;
			case 1:
				attackPoint.add(new Vector2(-attackRange,0));
				break;
			case 2:
				attackPoint.add(new Vector2(0,attackRange));
				break;
			case 3:
				attackPoint.add(new Vector2(attackRange,0));
				break;
			}
			
			for(GameObject i : overlapPoint(attackPoint))
			{
				if(i.equals(this) == false)
					i.hurt(attackDamage);
			}
		}
		
		if(isCollidingWithSomething())
		{
			undoLocation();
		}
		
		Camera.follow(location, 5, arg2);
		
		updateCollisionData();
	}
}
