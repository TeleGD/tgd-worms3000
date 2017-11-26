package general.worms.weapons;

import general.worms.PathUtils;
import general.worms.Projectile;
import general.worms.World;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MachineGun extends Weapon {

	private boolean firing = false;
	private int dirtyX = 0;
	private int dirtyY = 0;
	private float dirtyAlpha = (float) 0;
	private int t = 0;

	public MachineGun() {
		super(5,10,0,700,15,-1);
		try {
			image = new Image(PathUtils.Uzy);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		this.firing = true;
		this.dirtyX = x;
		this.dirtyY = y;
		this.dirtyAlpha = alpha;
		float deltaAlpha = (float) ((Math.random() * 3 - 0.5) * (2*Math.PI) / 360.);
		Projectile proj = new Projectile(x, y, alpha + deltaAlpha, this.firepower, this.weight, this);;
		proj.setExplodeDamage(destruction);
		proj.setDamage(dmg);
		World.addProjectile(proj);
	}

	@Override
	public void update(int timeDelta){
		if (this.firing) {
			this.t += timeDelta;
			if (t % 200 < timeDelta) {
				this.fireOneShot(this.dirtyX, this.dirtyY, this.dirtyAlpha);
			}
			if (t / 200.0 >= this.nbProjectiles - 1) {
				this.firing = false;
				this.t = 0;
			}
		}
	}

}
