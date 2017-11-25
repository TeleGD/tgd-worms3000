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

	protected abstract void fireOneShot();

	public void fire() {
		for (int i = 0; i < nbProjectiles; i++) { // fire as many times as nbProjectiles
			// StTocard was here
			this.fireOneShot();
		}
		this.ammo--;
	}

	protected boolean hasAmmo() {
		return this.ammo != 0;
	}

}
