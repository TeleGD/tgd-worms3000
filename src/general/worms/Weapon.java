package general.worms;

public abstract class Weapon {

	protected int dmg;
	protected int destruction; // impact on terrain
	protected int weight; // weight of 0 is not affected by gravity
	protected int firepower; // initial velocity of projectile
	protected int nbProjectiles; // number of shots per use
	protected int ammo; // number of uses remaining

	public Weapon(int dmg, int destruction, int weight, int firepower, int nbProjectiles, int ammo) {
		this.dmg = dmg;
		this.destruction = destruction;
		this.weight = weight;
		this.firepower = firepower;
		this.nbProjectiles = nbProjectiles;
		this.ammo = ammo;
	}

	protected abstract void fireOneShot(int x, int y, float alpha);

	public void fire(int x, int y, int x2, int y2) {

		float alpha = (float) 0.0;
		if (x2-x >= 0) {
			alpha = (float) Math.atan((float)(y-y2)/(float)(x2-x));
		} else {
			alpha = - (float) Math.atan((float)(y-y2)/(float)(x2-x));
		}
		System.out.println(alpha);

		for (int i = 0; i < nbProjectiles; i++) { // fire as many times as nbProjectiles
			// StTocard was here
			this.fireOneShot(x, y, alpha);
		}
		this.ammo--;
	}

	protected boolean hasAmmo() {
		return this.ammo != 0;
	}

}
