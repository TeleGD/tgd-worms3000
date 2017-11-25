package survival.gameobjects.items;

public class Water extends Items {
	
	public float amount;
	
	public Water(String name,float amount)
	{
		this.name = name;
		this.amount = amount;
	}
	
	//the action that made the item
	public void action()
	{
		//player.water.addAmount(amount);
	}

}
