package general.worms;

public class MachineGun extends Weapon{

	public MachineGun() {
		super(5,5,0,700,15,-1);
	}

	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		float deltaAlpha = (float) ((Math.random() * 3 - 0.5) * (2*Math.PI) / 360.);
		Projectile proj = new Projectile(x, y, alpha + deltaAlpha, this.firepower, this.weight, this);
		World.addProjectile(proj);
	}

}
