package general.worms;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Shotgun extends Weapon{

	public Shotgun(){
		super(14,10,0,750,1,-1);
		try {
			image = new Image(PathUtils.Shotgun);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		for (int i=0; i < 7; i++) {
			float deltaAlpha = (float) ((Math.random() * 7 - 3.5) * (2*Math.PI) / 360.);
			Projectile proj = new Projectile(x, y, alpha + deltaAlpha, this.firepower, this.weight, this);
			World.addProjectile(proj);
		}
	}

}