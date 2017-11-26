package general.worms;

public class BeletteLauncher extends Weapon {

	public BeletteLauncher(){
		super(99,500000,0,200,1,-1);
	}
	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		ProjectileSinusoidal proj = new ProjectileSinusoidal(x, y, alpha, this.firepower, this.weight, this);
		World.addProjectile(proj);

	}

}
