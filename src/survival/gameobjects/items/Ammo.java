package survival.gameobjects.items;

public class Ammo extends Items {
	
	public int quantity;
	
	public Ammo(String name, int quantity)
	{
		this.name = name;
		this.quantity = quantity;
	}
	
	//the action that made the item
	public void action()
	{
		//player.ammo.addQuantity(quantity);
	}

}
