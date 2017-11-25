package general.worms;

public class Projectiles {

	private int x, y;
	private int x0, y0; // initial position
	private float alpha0; // initial orientation
	private int v0; // initial speed
	private int weight;
	private Weapon firingWeapon;
	private int t; // time since firing
	// graphisms

	public Projectiles(int x0, int y0, int alpha0, int v0, int weight, Weapon firingWeapon) {
		this.x = x0;
		this.y = y0;
		this.x0 = x0;
		this.y0 = y0;
		this.alpha0 = alpha0;
		this.v0 = v0;
		this.weight = weight;
		this.firingWeapon = firingWeapon;
		this.t = 0;
	}

	public void updatePosition() {
		this.x = (int) (Math.cos(alpha0) * (v0*t + x0));
		this.y = (int) (Math.sin(alpha0) * (0.5*weight*9.81*t*t - v0*t + y0));
		this.t++;
	}
}
