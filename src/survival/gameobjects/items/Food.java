package survival.gameobjects.items;

public class Food extends ItemsInventory
{
	
	public float amount;
	
	public Food (String name,float amount)
	{
		this.name = name;
		this.amount = amount;
	}
	
	//the action that made the item
	public void action()
	{
		//player.food.addAmount(amount);
	}

}
