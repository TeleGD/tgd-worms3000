package general.worms;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class Loot extends Rectangle {

	private Image parachuteIMG;
	private Image crateIMG;
	
	private int spawnX;
	
	private boolean isFalling = true;
	private boolean isAmmo;
	
	public Loot(int spawnX) {
		super(spawnX, -150, 0, 0);
		
		this.spawnX = spawnX;
		x = spawnX;
		// TODO Auto-generated constructor stub
		isAmmo = Math.random()>0.5?true:false;
		
		try {
			parachuteIMG = new Image(PathUtils.Parachute).getScaledCopy(69, 57);
			crateIMG = isAmmo?new Image(PathUtils.AmmoCrate):new Image(PathUtils.HealthCrate);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.height = crateIMG.getHeight() + parachuteIMG.getHeight();
		this.width = Math.max(crateIMG.getWidth(), parachuteIMG.getHeight());
	}
	
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		if(isFalling){
			g.drawImage(crateIMG, (float) (x + crateIMG.getWidth()*1.5), (float)y);
			g.drawImage(parachuteIMG, (float)x, (float)y - crateIMG.getHeight());
		}else{
			g.drawImage(crateIMG, (float)x, (float)y);
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if(isFalling){
			
			x = (float) (spawnX + Math.sin(0.1*y) * 15);
			y++;
			y++;
			
		}else{
			
			
			
		}
	}
	
}
