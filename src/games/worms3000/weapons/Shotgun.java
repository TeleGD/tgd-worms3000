package games.worms3000.weapons;

import app.AppLoader;

import games.worms3000.World;
import games.worms3000.utils.PathUtils;
import games.worms3000.weapons.projectiles.Projectile;

public class Shotgun extends Weapon {

	public Shotgun(World world){
		super(world, 8,5,0,750,1,-1);
		image = AppLoader.loadPicture(PathUtils.Shotgun);
		sound = AppLoader.loadAudio(PathUtils.Shotgun_sound);
	}
	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		for (int i=0; i < 7; i++) {
			float deltaAlpha = (float) ((Math.random() * 7 - 3.5) * (2*Math.PI) / 360.);
			Projectile proj = new Projectile(x, y, alpha + deltaAlpha, this.firepower, this.weight, this);
			proj.setExplodeDamage(destruction);
			proj.setDamage(dmg);
			this.world.addProjectile(proj);
		}
	}

}
