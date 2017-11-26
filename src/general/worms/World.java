package general.worms;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class World extends BasicGameState {

	public static int ID = 42;
	public static int NB_CASE_HORIZONTAL = 53;
	public static int NB_CASE_VERTICAL = 30;
	public static int LENGTH_CASE_HORIZONTAL = Main.longueur/NB_CASE_HORIZONTAL; //=24
	public static int LENGTH_CASE_VERTICAL = Main.longueur/NB_CASE_VERTICAL;//=24

	public static Terrain terrain = new Terrain();
	private StateBasedGame game;
	private static Player player = new Player(500,0);

	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	// kill me pls
	public static Weapon dirtyMachineGun = null;
	public static int dirtyX = 0;
	public static int dirtyY = 0;
	public static float dirtyAlpha = (float) 0;
	private int t; // used with dirtyMachineGun
//	private Projectile proj = new Projectile(500, 500, (float) 45, 425, 25, (Weapon) null);
	private Bazooka baz = new Bazooka();
//	private Shotgun cykablyat = new Shotgun();
//	private MachineGun johnson = new MachineGun();
//	private BeletteLauncher machette = new BeletteLauncher();

	public static void setLevel(String levelName) {
		terrain.setLevelName(levelName);
	}


	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		game = arg1;
		baz.fire(150, 400, 300, 300);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		if(terrain!=null)terrain.enter(container,game);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		if(terrain!=null)terrain.render(arg0,arg1,arg2);
		player.render(arg0,arg1,arg2);
		// TODO Auto-generated method stub
		for (Projectile proj : this.projectiles) {
			proj.render(arg2);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		terrain.update(arg0,arg1,arg2);
		player.update(arg0, arg1, arg2);
		// TODO Auto-generated method stub

		// handle all projectiles ingame
		List<Projectile> toRemove = new ArrayList<>();
		for (Projectile proj : projectiles) {
			if (proj.updatePosition(arg2)) {
				toRemove.add(proj);
			}
		}
		projectiles.removeAll(toRemove);

		// dirty handling of machinegun delayed firing
		this.t += arg2;
		if (World.dirtyMachineGun != null) {
			if (t % 100 < arg2) {
				dirtyMachineGun.fireOneShot(World.dirtyX, World.dirtyY, World.dirtyAlpha);
			}
			if (t / 100.0 >= dirtyMachineGun.nbProjectiles - 1) {
				this.dirtyMachineGun = null;
				this.t = 0;
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		if(key == Input.KEY_ESCAPE)game.enterState(WormMenu.ID);
		player.keyReleased(key, c);
	}

	@Override
	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
	}

	public static void reset() {
		terrain = new Terrain();
	}

	public static void addProjectile(Projectile proj) {
		World.projectiles.add(proj);
	}

}
