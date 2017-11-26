package general.worms;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Projectile {

	protected int x, y;
	protected int x0, y0; // initial position
	protected float alpha0; // initial orientation
	protected int v0; // initial speed
	protected int weight;
	protected Weapon firingWeapon;
	protected int t; // time since firing
	// graphisms

	public Projectile(int x0, int y0, float alpha0, int v0, int weight, Weapon firingWeapon) {
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

	public boolean updatePosition(int deltaT) {
		this.t += deltaT;
		this.x = (int) (Math.cos(alpha0) * (v0*t/1000) + x0);
		if (this.x < 0 || this.x > 1280) {
			return true;
		}
		this.y = (int) (Math.sin(alpha0) * (-v0*t/1000) + 0.5*weight*9.81*t*t/1000000 + y0);
		if (this.y < 0 || this.y > 720) {
			return true;
		}
		//System.out.println(this.x);
		//System.out.println(this.y);
		//System.out.println(this.t);
		//System.out.println("___________________________");
		return false;
	}

	public void render(Graphics g) {
		g.setColor(new Color(255,0,0));
		g.fillRect(x,y,3,3);
	}
}
