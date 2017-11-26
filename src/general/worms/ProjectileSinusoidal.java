package general.worms;

public class ProjectileSinusoidal extends Projectile {

	public ProjectileSinusoidal(int x0, int y0, float alpha0, int v0, int weight, Weapon firingWeapon) {
		super(x0, y0, alpha0, v0, weight, firingWeapon);
	}

	@Override
	public boolean updatePosition(int deltaT) {
		this.t += deltaT;
		this.x = (int) (Math.cos(alpha0) * (v0*t/1000) + Math.sin(alpha0) * 20*Math.sin(0.01*t) + x0);
		System.out.println(x);
		if (this.x < 0 || this.x > 1280) {
			System.out.println("x");
			return true;
		}
		this.y = (int) (Math.sin(alpha0) * (-v0*t/1000) + Math.cos(alpha0) * 20*Math.sin(0.01*t) + 0.5*weight*9.81*t*t/1000000 + y0);
		if (this.y < 0 || this.y > 720) {
			System.out.println("y");
			return true;
		}
		System.out.println(this.x);
		System.out.println(this.y);
		System.out.println(this.t);
		System.out.println("___________________________");
		return false;
	}
}
