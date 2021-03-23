package games.worms3000.weapons;

import app.AppLoader;

import games.worms3000.World;
import games.worms3000.utils.PathUtils;
import games.worms3000.weapons.projectiles.Projectile;

public class MachineGun extends Weapon {

	private boolean firing = false;
	private int dirtyX = 0;
	private int dirtyY = 0;
	private float dirtyAlpha = 0;
	private int t = 0;

	public MachineGun(World world) {
		super(world, 5,10,0,700,15,-1);
		image = AppLoader.loadPicture(PathUtils.Uzy);
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
		this.world.addProjectile(proj);
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
