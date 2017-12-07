package worms.weapons;

import org.newdawn.slick.Sound;
import worms.utils.PathUtils;
import worms.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import worms.weapons.projectiles.Projectile;

public class Shotgun extends Weapon {

	public Shotgun(){
		super(8,5,0,750,1,-1);
		try {
			image = new Image(PathUtils.Shotgun);
			sound = new Sound(PathUtils.Shotgun_sound);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		for (int i=0; i < 7; i++) {
			float deltaAlpha = (float) ((Math.random() * 7 - 3.5) * (2*Math.PI) / 360.);
			Projectile proj = new Projectile(x, y, alpha + deltaAlpha, this.firepower, this.weight, this);
			proj.setExplodeDamage(destruction);
			proj.setDamage(dmg);
			World.addProjectile(proj);
		}
	}

}