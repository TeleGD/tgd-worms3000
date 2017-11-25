package survival.gameobjects.gameplay;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.tests.xml.Item;

import survival.gameobjects.GameObject;

public class Player extends GameObject {
	public ArrayList<Item> itemList;
	public double horizontal;
	public double vertical;
	//public 		life;
	//public 		hunger;
	//public 		thirst;
	
	public Player(ArrayList<Item> itemList,double horizontal,double vertical/*, life, hunger, thirst*/) {
		this.itemList = itemList;
		//this.position   initialiser la position
		/*this.life = life;
		this.hunger = hunger;
		this.thirst = thirst;*/
		}
	
	/*public void addItem(   Item) {
		itemList = itemList.add(Item);
	}
	
	public void removeItem(   Item) {
		itemList = itemList.remove(   Item); 
	}
	*/
	
	





}
