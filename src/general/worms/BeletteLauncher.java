package general.worms;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BeletteLauncher extends Weapon {

	public BeletteLauncher(){
		super(99,500000,0,200,1,UNLIMITED);
		try {
			image = new Image(PathUtils.Bazooka);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		ProjectileSinusoidal proj = new ProjectileSinusoidal(x, y, alpha, this.firepower, this.weight, this);
		World.addProjectile(proj);

	}

}
