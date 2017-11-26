package general.worms;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class LootHandler {

	private long lastTime;
	private int nextTime;
	
	private ArrayList<Loot> loots = new ArrayList<Loot>();
	
	public LootHandler() {		
		reload();		
	}

	private void reload() {
		// TODO Auto-generated method stub
		lastTime = System.currentTimeMillis();
		nextTime = (int) (Math.random() * 120 * 1000);
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		for(Loot l : loots){
			
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(System.currentTimeMillis() > lastTime + nextTime){
			reload();
			loots.add(new Loot((int) (Math.random()*Main.longueur)));
		}
	}
	
}
