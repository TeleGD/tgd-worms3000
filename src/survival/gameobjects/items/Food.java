package survival.gameobjects.items;

import survival.gameobjects.gameplay.Compteur;
import survival.gameobjects.gameplay.Player;
import survival.utils.Vector2;

public class Food extends Items {
	
	public float amount;
	
	public Food (Vector2 location, String name,float amount)
	{
		super(location);
		this.name = name;
		this.amount = amount;
	}
	
	//the action that made the item
	public void action()
	{
		Items.player.hunger.canAdd(amount);
		//this.destroy()
	}

	


}
