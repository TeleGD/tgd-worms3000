package worms;

import worms.weapons.Weapon;

public class ProjectileSinusoidal extends Projectile {

	public ProjectileSinusoidal(int x0, int y0, float alpha0, int v0, int weight, Weapon firingWeapon) {
		super(x0, y0, alpha0, v0, weight, firingWeapon);
	}

	@Override
	public boolean updatePosition(int deltaT) {
		this.t += deltaT;

		this.dx = Math.cos(alpha0) * (v0*t/1000) + Math.sin(alpha0) * 20*Math.sin(0.01*t);
		this.dy = Math.sin(alpha0) * (-v0*t/1000) + Math.cos(alpha0) * 20*Math.sin(0.01*t) + 0.5*weight*9.81*t*t/1000000;

		this.setX ((int) (dx + x0));
		if (this.getX() < 0 || this.getX() > 1280) {
			return true;
		}
		this.setY ((int) (dy + y0));
		if (this.getY() < 0 || this.getY() > 720) {
			return true;
		}
		return false;
	}
}
