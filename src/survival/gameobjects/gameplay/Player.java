package survival.gameobjects.gameplay;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.tests.xml.Item;

import survival.gameobjects.GameObject;
import survival.gameobjects.items.Items;

public class Player extends GameObject {
	public static ArrayList<Items> itemList = new ArrayList<Items>();
	public float location;
	public 	Compteur life;
	public 	Compteur hunger;
	public 	Compteur thirst;
	public Items activatedWeapons;


	public Player(ArrayList<Items> itemList,float location) {
		this.activatedWeapons = null;
		this.location = location;
		/*this.life = new Compteur("life",100);
		this.hunger = new Compteur("hunger",100);
		this.thirst = new Compteur("thirst",100);*/
		}

	public static void addItem(Items  Item) {
		itemList.add(Item);
	}

	public void removeItem(Items Item) {
		itemList.remove(Item);
	}
}
