package general.worms;

public class Bazooka extends Weapon {

	public Bazooka() {
		super(50, 50, 25, 425, 1, -1);
	}

	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		Projectile proj = new Projectile(x, y, alpha, this.firepower, this.weight, this);
		World.addProjectile(proj);

	}


}
