package general.worms;

import org.newdawn.slick.Image;

public abstract class Weapon {

	public static final int UNLIMITED = -1;

	protected int dmg;
	protected int destruction; // impact on terrain
	protected int weight; // weight of 0 is not affected by gravity
	protected int firepower; // initial velocity of projectile
	protected int nbProjectiles; // number of shots per use
	protected int ammo; // number of uses remaining
	protected  Image image;

	public Weapon(int dmg, int destruction, int weight, int firepower, int nbProjectiles, int ammo) {
		this.dmg = dmg;
		this.destruction = destruction;
		this.weight = weight;
		this.firepower = firepower;
		this.nbProjectiles = nbProjectiles;
		this.ammo = ammo;
	}

	public Image getImage() {
		return image;
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

		// StTocard was here
		this.fireOneShot(x, y, alpha);
		// dirty trick :'(
		if (this.nbProjectiles > 1) {
			// aled
		}

		this.ammo--;
	}

	protected boolean hasAmmo() {
		return this.ammo != 0;
	}


}