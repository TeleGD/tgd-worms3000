package general.worms;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bazooka extends Weapon {


	public Bazooka() {
		super(50, 50, 25, 425, 1, -1);

		try {
			image = new Image(PathUtils.Bazooka);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		Projectile proj = new Projectile(x, y, alpha, this.firepower, this.weight, this);
		World.addProjectile(proj);
	}




}
