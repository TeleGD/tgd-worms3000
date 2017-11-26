package survival.gameobjects.gameplay;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.SurvivalMain;
import survival.utils.Vector2;
import survival.worlds.World;

public class Infected extends Character{

	protected float range = 150;
	protected boolean playerSpotted = false;
	
	protected float counter = 2000;
	protected Vector2 randomWalkDirection = new Vector2(0,-1);
	protected float runspeed;
	protected float wlkspeed;
	
	public Infected(Image spr,Vector2 location ,float wlkspeed, float rnspeed) throws SlickException {
		super(spr, wlkspeed);
		health = new Compteur(100,100, location, new Vector2(10,11),null, new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/HealthBar.png"));
		health.globalDelta = new Vector2(0,-100);
		World.activeWorld.addGameObject(health);
		this.wlkspeed = wlkspeed;
		this.location = location;
		this.old_location = new Vector2(location.x, location.y);
		runspeed = rnspeed;
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		Vector2 moveDirection = new Vector2(0,0);
		
		
		
		if(playerSpotted)
		{
			walkSpeed = runspeed;
			moveDirection = new Vector2(World.activePlayer.location.x - location.x,  World.activePlayer.location.y - location.y);
		}else
		{
			walkSpeed = wlkspeed;
			if(playerInSight())
				playerSpotted = true;
			
			if(counter < 0)
			{
				counter = (float) (Math.random()*5000 + 1000);
				randomWalkDirection.x = (float) (1-Math.random()*2);
				randomWalkDirection.y = (float) (1-Math.random()*2);
			}else
			{
				counter -= arg2;
				moveDirection = randomWalkDirection;
			}
		}
		
		walk(moveDirection);
		
		
		if(isCollidingWithSomething())
		{
			if(collisionOn.equals(World.activePlayer))
			{
				playerSpotted = true;
				World.activePlayer.hurt((float)arg2/100f);
			}
			
			if((collisionOn instanceof Infected)== false )
				undoLocation();
		}
		
		updateCollisionData();
	}
	
	public boolean playerInSight()
	{
		float deltax = World.activePlayer.location.x - location.x;
		float deltay = World.activePlayer.location.y - location.y;
		
		switch(direction)
		{
		case 0:
			if(deltay < 0)
				if(Math.abs(deltax) < Math.abs(deltay))
					if(Math.abs(deltay) < range)
						return true;
			break;
		case 1:
			if(deltax < 0)
				if(Math.abs(deltay) < Math.abs(deltax))
					if(Math.abs(deltax) < range)
						return true;
			break;
		case 2:
			if(deltay > 0)
				if(Math.abs(deltax) > Math.abs(deltay))
					if(Math.abs(deltay) < range)
						return true;
			break;
		case 3:
			if(deltax > 0)
				if(Math.abs(deltay) > Math.abs(deltax))
					if(Math.abs(deltax) < range)
						return true;
			break;
		}
		
		return false;
	}
}
