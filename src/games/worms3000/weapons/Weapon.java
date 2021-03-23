package games.worms3000.weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;

import games.worms3000.World;

public abstract class Weapon {

	public static final int UNLIMITED = -1;

	protected World world;
	protected int dmg;
	protected int destruction; // impact on terrain
	protected int weight; // weight of 0 is not affected by gravity
	protected int firepower; // initial velocity of projectile
	protected int nbProjectiles; // number of shots per use
	protected int ammo; // number of uses remaining
	protected  Image image;
    protected Audio sound;

	public Weapon(World world, int dmg, int destruction, int weight, int firepower, int nbProjectiles, int ammo) {
		this.world = world;
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

	public void fire(int x, int y, float angle) {

		if(angle>Math.PI/2 && angle<Math.PI*3/2)angle = (float) (Math.PI-angle);
		else angle = -angle;

		this.fireOneShot(x, y, angle);


		this.ammo--;
	}

	protected boolean hasAmmo() {
		return this.ammo != 0;
	}

	public void update(int timeDelta) {	}

    public void setFirepower(int firepower) {
        this.firepower = firepower;
    }

    public int getFirepower() {
        return firepower;
    }

    public Audio getSound() {
        return sound;
    }
}
