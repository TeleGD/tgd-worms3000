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
	
	private float sec = 60f;
	
	private ArrayList<Loot> loots = new ArrayList<Loot>();
	
	public LootHandler() {		
		reload();		
		loots.add(new Loot((int) (Math.random()*Main.longueur)));
	}

	private void reload() {
		// TODO Auto-generated method stub
		lastTime = System.nanoTime();
		nextTime = (int) (Math.random() * sec * 1000000000);
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		for(Loot l : loots){
			l.render(arg0, arg1, g);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		System.out.println(lastTime+"}{"+ (lastTime + nextTime));
		if(System.nanoTime() > lastTime + nextTime){
			reload();
			loots.add(new Loot((int) (Math.random()*Main.longueur)));
			System.out.println("poney");
		}
		
		for(Loot l : loots){
			l.update(container, game, delta);
		}
		
	}
	
}
