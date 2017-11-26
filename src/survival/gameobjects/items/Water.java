package survival.gameobjects.items;

import survival.gameobjects.gameplay.TestObject;
import survival.utils.Vector2;

public class Water extends Items {
	
	public float amount;
	
	public Water(Vector2 location, String name,float amount)
	{
		super(location);
		this.name = name;
		this.amount = amount;
	}
	
	//the action that made the item
	public void action()
	{
		Items.player.thirst.canAdd(amount);
		TestObject.removeItem(this);
	}

}
