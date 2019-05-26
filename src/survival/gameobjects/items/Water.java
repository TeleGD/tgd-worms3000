package survival.gameobjects.items;

import survival.gameobjects.gameplay.TestObject;
import survival.utils.Vector2;
import survival.worlds.World;

import org.newdawn.slick.Image;

public class Water extends Items {

	public float amount;

	public Water(Vector2 location, String name,float amount,Image sprite)
	{
		super(location,sprite);
		this.name = name;
		this.amount = amount;
	}

	//the action that made the item
	public void action()
	{
		World.activePlayer.thirst.canAdd(amount);
		World.activePlayer.removeItem(this);
	}

}
