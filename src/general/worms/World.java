package general.worms;

import java.util.ArrayList;

import org.lwjgl.Sys;
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
	private Projectile proj = new Projectile(100, 100, (float) 45, 425, 25,null);

//	private Bazooka baz = new Bazooka();
//	private Shotgun cykablyat = new Shotgun();

	public static void setLevel(String levelName) {
		terrain.setLevelName(levelName);
	}


	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		game = arg1;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		if(terrain!=null)terrain.enter(container,game);
        World.addProjectile(proj);

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
		for (int i=0;i<projectiles.size(); i++) {

            projectiles.get(i).updatePosition(arg2);

			Object o = collide(projectiles.get(i));

            if(o instanceof GroundPolygon){
                System.out.println("collide = "+o);

                ((GroundPolygon)o).destroyPartOfGround(projectiles.get(i).getX(),projectiles.get(i).getY(),100);
                World.removeProjectile(projectiles.get(i));
			    i--;
            }
		}
	}

    private static void removeProjectile(Projectile p) {
	    projectiles.remove(p);
    }


    private Object collide(Projectile p){

        for(int i=0;i<terrain.getGroundPolygonList().size();i++){
            System.out.println("x="+i);

            if(terrain.getGroundPolygonList().get(i).getPolygon().contains(p.getX(),p.getY())) return terrain.getGroundPolygonList().get(i);
            if(terrain.getGroundPolygonList().get(i).getPolygon().contains(p.getX()+p.getWidth(),p.getY())) return terrain.getGroundPolygonList().get(i);
            if(terrain.getGroundPolygonList().get(i).getPolygon().contains(p.getX()+p.getWidth(),p.getY()+p.getHeight())) return terrain.getGroundPolygonList().get(i);
            if(terrain.getGroundPolygonList().get(i).getPolygon().contains(p.getX(),p.getY()+p.getHeight())) return terrain.getGroundPolygonList().get(i);
        }

        if(player.contains(p.getX(),p.getY())) return player;
        if(player.contains(p.getX()+p.getWidth(),p.getY())) return player;
        if(player.contains(p.getX()+p.getWidth(),p.getY()+p.getHeight())) return player;
        if(player.contains(p.getX(),p.getY()+p.getHeight())) return player;

        return null;
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
