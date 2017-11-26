package general.worms.weapons;

import general.worms.PathUtils;
import general.worms.Projectile;
import general.worms.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bazooka extends Weapon {


	public Bazooka() {
		super(10, 50, 25, 425, 1, -1);

		try {
			image = new Image(PathUtils.Bazooka);
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
