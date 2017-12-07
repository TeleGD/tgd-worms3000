package worms.weapons;

import org.newdawn.slick.Sound;
import worms.utils.PathUtils;
import worms.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import worms.weapons.projectiles.Projectile;

public class Bazooka extends Weapon {


	public Bazooka() {
		super(10, 50, 25, 425, 1, -1);

		try {
			image = new Image(PathUtils.Bazooka);
            sound = new Sound(PathUtils.Bazooka_sound);

        } catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		Projectile proj = new Projectile(x, y, alpha, this.firepower, this.weight, this);
		proj.setImages(PathUtils.Bazooka_Bullet);
        proj.setExplodeDamage(destruction);
        proj.setDamage(dmg);

		World.addProjectile(proj);
	}




}
