package survival.gameobjects.gameplay;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.Camera;
import survival.SurvivalMain;
import survival.gameobjects.GameObject;
import survival.gameobjects.items.Items;
import survival.input.CustomInput;
import survival.utils.Vector2;
import survival.worlds.World;

public class TestObject extends Character{
	
	protected float attackRange = 50;
	protected float attackDamage = 1;
	public static ArrayList<Items> itemList;	
	protected Compteur hunger;
	protected Compteur thirst;
	protected Items activatedWeapons;
	
	public TestObject(Image spr) throws SlickException
	{
		super(spr, 5);
		health = new Compteur(100,100, new Vector2(200,650), new Vector2(10,11),new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/barre.png"), new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/HealthBar.png"));
		World.activeWorld.addUiGameObject(health);
		hunger = new Compteur(30,100, new Vector2(500,650), new Vector2(10,11),new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/barre.png"), new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/HungryBar.png"));
		World.activeWorld.addUiGameObject(hunger);
		thirst = new Compteur(80,100, new Vector2(800,650), new Vector2(10,11),new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/barre.png"), new Image(SurvivalMain.DIRECTORY_IMAGES + "ui/WaterBar.png"));
		World.activeWorld.addUiGameObject(thirst);
		
		location = new Vector2(0, 0);
		sprite = spr;
	}

	public void addItem(Items  Item) {
		itemList.add(Item);
	}
	
	public static void removeItem(Items Item) {
		itemList.remove(Item); 
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
		if(CustomInput.one)
			if (itemList.size()>0){
			itemList.get(0).action();
			}
		if(CustomInput.two)
			if (itemList.size()>1){
				itemList.get(1).action();
				}
		if(CustomInput.three)
			if (itemList.size()>2){
				itemList.get(2).action();
				}
		
		
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
