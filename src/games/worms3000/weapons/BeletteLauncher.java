package games.worms3000.weapons;

import app.AppLoader;

import games.worms3000.World;
import games.worms3000.utils.PathUtils;
import games.worms3000.weapons.projectiles.ProjectileSinusoidal;

public class BeletteLauncher extends Weapon {


    public BeletteLauncher(World world){
		super(world, 10,5,0,200,1,UNLIMITED);
		image = AppLoader.loadPicture(PathUtils.Bazooka_Belette);
		sound = AppLoader.loadAudio(PathUtils.Bellete_sound);

	}
	@Override
	protected void fireOneShot(int x, int y, float alpha) {
		ProjectileSinusoidal proj = new ProjectileSinusoidal(x, y, alpha, this.firepower, this.weight, this);
        proj.setImages(PathUtils.Belette_1,PathUtils.Belette_2);
        proj.setExplodeDamage(destruction);
		proj.setDamage(dmg);

        this.world.addProjectile(proj);


    }

}
