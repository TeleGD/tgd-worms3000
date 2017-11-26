package general.worms;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MachineGun extends Weapon{

	public MachineGun() {
		super(5,5,0,700,15,-1);
		try {
			image = new Image(PathUtils.Uzy);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		float deltaAlpha = (float) ((Math.random() * 3 - 0.5) * (2*Math.PI) / 360.);
		Projectile proj = new Projectile(x, y, alpha + deltaAlpha, this.firepower, this.weight, this);
		World.addProjectile(proj);
	}


}
