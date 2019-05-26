package survival.gameobjects.items;

import survival.gameobjects.GameObject;
import survival.gameobjects.gameplay.Player;
import survival.utils.Vector2;
import survival.worlds.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Items extends GameObject {

	protected String name;
	private boolean onGround;

	public Items(Vector2 location,Image sprite) {
		super(sprite);
		//this.sprite = sprite;
		this.onGround = true;
		this.location = location;
	}

	public abstract void action();

	public boolean getOnGround() {
		return this.onGround;
	}

	public void pick() {
		if(onGround){
		this.onGround=false;
		World.activePlayer.addItem(this);
		destroy(this);
		}
		// objet rammassé par joueur --> inventaire
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		if(onGround && isCollidingWithSomething())
		{
			if(this.collisionOn.equals(World.activePlayer))
			{
				//System.out.println("entre");
				pick();
				collisionOn = null;
			}
		}
	}


}
