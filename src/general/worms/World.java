package general.worms;

import general.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class World extends BasicGameState {


	public static int ID = 42;

	private StateBasedGame game;

    public static Terrain terrain = new Terrain();
    private static Player player = new Player(400,0);
    private static Player player2 = new Player(800,0);
    
    private static LootHandler looter = new LootHandler();

	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private Projectile proj = new Projectile(100, 100, (float) 45, 425, 25,null);
    private int weaponIndex,weaponIndex2;
    private Image imageVie2,imageVie1;
    private Weapon[] weapons = new Weapon[]{new Bazooka(),new BeletteLauncher(),new MachineGun(),new Shotgun()};

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

        imageVie1 = new Image(PathUtils.HPBar);
        imageVie2 = new Image(PathUtils.HPBar);



    }

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		if(terrain!=null)terrain.render(arg0,arg1,arg2);

		player.render(arg0,arg1,arg2);
		player2.render(arg0,arg1,arg2)
        ;
		// TODO Auto-generated method stub
		for (Projectile proj : this.projectiles) {
			proj.render(arg2);
		}

		arg2.setColor(Color.white);
        arg2.fillRoundRect(20,20,168,104,20,20);
        arg2.setColor(Color.black);
        arg2.fillRoundRect(25,25,158,94,20,20);


        arg2.setColor(Color.white);
        arg2.fillRoundRect(Main.longueur-178,20,168,104,20,20);
        arg2.setColor(Color.black);
        arg2.fillRoundRect(Main.longueur-173,25,158,94,20,20);

        arg2.drawImage(imageVie1,200,20);
        arg2.drawImage(imageVie2,Main.longueur-170,20);

        arg2.drawImage(player.getWeapon().getImage().getScaledCopy(128,64),40,40);
        arg2.drawImage(player2.getWeapon().getImage().getScaledCopy(128,64),Main.longueur-148,30);

        looter.render(arg0, arg1, arg2);
        
    }

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		terrain.update(arg0,arg1,arg2);
		player.update(arg0, arg1, arg2);
        player2.update(arg0,arg1,arg2);
		// TODO Auto-generated method stub

		for (int i=0;i<projectiles.size(); i++) {

            boolean needRemove= projectiles.get(i).updatePosition(arg2);

			Object o = collide(projectiles.get(i));

            if(o instanceof GroundPolygon){

                ((GroundPolygon)o).destroyPartOfGround(projectiles.get(i).getX(),projectiles.get(i).getY(),100);
			    needRemove = true;
            }else{

            }
            if(needRemove){
                World.removeProjectile(projectiles.get(i));
                i--;
            }

            player.getWeapon().update(arg2);
            player2.getWeapon().update(arg2);

		}

		looter.update(arg0, arg1, arg2);

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
		if(key == Input.KEY_A){

		    weaponIndex ++;
            if(weaponIndex >= weapons.length)weaponIndex = 0;
		    player.setWeapon(weapons[weaponIndex]);

        }else if(key == Input.KEY_Z){
            weaponIndex --;
            if(weaponIndex<0)weaponIndex = weapons.length-1;
            player.setWeapon(weapons[weaponIndex]);

        }
        if(key == Input.KEY_P){
            weaponIndex2 ++;
            if(weaponIndex2>=weapons.length)weaponIndex2 = 0;
            player2.setWeapon(weapons[weaponIndex2]);

        }else if(key == Input.KEY_M){

            weaponIndex2 --;
            if(weaponIndex2<0)weaponIndex2 = weapons.length-1;
            player2.setWeapon(weapons[weaponIndex2]);
        }
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
