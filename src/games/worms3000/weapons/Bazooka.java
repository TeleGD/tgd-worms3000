package games.worms3000.weapons;

import app.AppLoader;

import games.worms3000.World;
import games.worms3000.utils.PathUtils;
import games.worms3000.weapons.projectiles.Projectile;

public class Bazooka extends Weapon {


	public Bazooka(World world) {
		super(world, 10, 50, 25, 425, 1, -1);

		image = AppLoader.loadPicture(PathUtils.Bazooka);
		sound = AppLoader.loadAudio(PathUtils.Bazooka_sound);
	}

	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		Projectile proj = new Projectile(x, y, alpha, this.firepower, this.weight, this);
		proj.setImages(PathUtils.Bazooka_Bullet);
        proj.setExplodeDamage(destruction);
        proj.setDamage(dmg);

		this.world.addProjectile(proj);
	}




}
