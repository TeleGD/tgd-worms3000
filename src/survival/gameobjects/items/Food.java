package survival.gameobjects.items;

import survival.gameobjects.gameplay.Compteur;
import survival.gameobjects.gameplay.Player;

public class Food extends Items {
	
	public float amount;
	
	public Food (String name,float amount)
	{
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
