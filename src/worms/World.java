package worms;

import general.Main;
import general.utils.FontUtils;
import worms.ground.GroundPolygon;
import worms.ground.Terrain;
import worms.menus.WormMenu;
import worms.utils.PathUtils;
import worms.weapons.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import worms.weapons.projectiles.Projectile;

import java.util.ArrayList;
import java.util.Random;

public class World extends BasicGameState {


	public static int ID = 42;
    public static Image imageBackground;

    private StateBasedGame game;

    public static Terrain terrain = new Terrain();
    private static Player player = new Player(400,0);
    private static Player player2 = new Player(800,0);
    private static ArrayList<Loot> loots = new ArrayList<>();

	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private Projectile proj = new Projectile(100, 100, (float) 45, 425, 25,null);
    private int weaponIndex,weaponIndex2;
    private Image imageVie2,imageVie1;
    private Weapon[] weapons = new Weapon[]{new Bazooka(),new BeletteLauncher(),new MachineGun(),new Shotgun()};
    private boolean finish;

    private int playerWhoWin;
    private TrueTypeFont font;

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

		terrain.enter(container,game);

        imageVie1 = new Image(PathUtils.HPBar);
        imageVie2 = new Image(PathUtils.HPBar);

        if(imageBackground== null)imageBackground = new Image(PathUtils.Background);

        font = FontUtils.loadCustomFont("PT_Sans.ttf",java.awt.Font.BOLD,45);

        player2.setInputs(Input.KEY_Q,Input.KEY_D,Input.KEY_Z,Input.KEY_E,Input.KEY_R,Input.KEY_T,Input.KEY_F);
    }

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
        imageBackground.draw(0,0,Main.longueur,Main.hauteur);
        terrain.render(arg0,arg1,arg2);

		player.render(arg0,arg1,arg2);
		player2.render(arg0,arg1,arg2)
        ;
		// TODO Auto-generated method stub
		for (Projectile proj : this.projectiles) {
			proj.render(arg2);
		}

        for(int i=0;i<loots.size();i++){

            loots.get(i).render(arg2);

        }

		arg2.setColor(new Color(255,0,0));
        arg2.fillRoundRect(140,34,player.getHP(),14,20);
        arg2.fillRoundRect(Main.longueur-220,34,player2.getHP(),14,20);

		arg2.setColor(Color.white);
        arg2.fillRoundRect(20,20,84,52,5,5);
        arg2.setColor(Color.black);
        arg2.fillRoundRect(25,25,74,42,5,5);


        arg2.setColor(Color.white);
        arg2.fillRoundRect(Main.longueur-108,20,84,52,5,5);
        arg2.setColor(Color.black);
        arg2.fillRoundRect(Main.longueur-103,25,74,42,5,5);

        arg2.drawImage(imageVie1.getScaledCopy(100,22),140,30);
        arg2.drawImage(imageVie2.getScaledCopy(100,22),Main.longueur-220,30);

        arg2.drawImage(player.getWeapon().getImage().getScaledCopy(64,32),30,30);
        arg2.drawImage(player2.getWeapon().getImage().getScaledCopy(64,32),Main.longueur-98,30);

        if(finish){
            arg2.setColor(Color.black);
            arg2.setFont(font);
            arg2.drawString("Player "+playerWhoWin+" Win !!!!",Main.longueur/2-200,200);
        }
    }

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		terrain.update(arg0,arg1,arg2);
		player.update(arg0, arg1, arg2);
        player2.update(arg0,arg1,arg2);
		// TODO Auto-generated method stub


        for(int i=0;i<loots.size();i++){

            loots.get(i).update(arg0,arg1,arg2);

            Object o = collide(loots.get(i));

            if(o instanceof GroundPolygon){
                loots.get(i).setCollide(true);
                loots.get(i).leaveLoot();
            }else if(o instanceof  Loot ){
                if(((Loot)o).isParachuteReleased())
                {
                    loots.get(i).leaveLoot();
                }else if(loots.get(i).isParachuteReleased())
                {
                    ((Loot)o).leaveLoot();
                }
            }else if(o instanceof  Player){
                if(loots.get(i).isParachuteReleased()){
                    ((Player)o).applyBonus(loots.get(i).getType());
                    loots.remove(i);
                    i--;
                }

            }
        }


		for (int i=0;i<projectiles.size(); i++) {

            boolean needRemove= projectiles.get(i).updatePosition(arg2);

			Object o = collide(projectiles.get(i));

            if(o instanceof GroundPolygon){

                ((GroundPolygon)o).destroyPartOfGround(projectiles.get(i).getX(),projectiles.get(i).getY(),100);
			    needRemove = true;
            }if(o instanceof Loot){
                ((Loot)o).leaveLoot();
                needRemove = true;

            }else if (o instanceof Player){
                needRemove = true;
                boolean isDead = ((Player)o).loseHP(proj.getDamage());

                if(isDead){
                    if(o == player) playerWhoWin = 2;
                    else playerWhoWin=1;

                    finish = true;

                }

            }
            if(needRemove){
                World.removeProjectile(projectiles.get(i));
                i--;
            }
		}


		if(new Random().nextInt(1000)==0){
            Loot l = new Loot(new Random().nextInt(Main.longueur),-new Random().nextInt(Main.hauteur),new Random().nextInt(Loot.NB_TYPE));
            l.setAmplitude(new Random().nextInt(10));
            l.setSpeedY((new Random().nextInt(200))/1000.0f);
            l.setFrequence(new Random().nextInt(400)/1000.0f);
            World.addLoot(l);
        }


	}

    private static void removeProjectile(Projectile p) {
	    projectiles.remove(p);
    }


    private Object collide(Rectangle p){


        if(player.intersects(p) || player.contains(p))return player;
        if(player2.intersects(p) || player2.contains(p))return player2;

        for(int i=0;i<terrain.getGroundPolygonList().size();i++){
            if(terrain.getGroundPolygonList().get(i).intersects(p) || terrain.getGroundPolygonList().get(i).getPolygon().contains(p)) return terrain.getGroundPolygonList().get(i);
        }


        for(int i=0;i<loots.size();i++){
            if(loots.get(i).intersects(p) || loots.get(i).contains(p))return loots.get(i);
        }

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
		if(key == player.getButtonChangeWeapon()) {

            weaponIndex++;
            if (weaponIndex >= weapons.length) weaponIndex = 0;
            player.setWeapon(weapons[weaponIndex]);

		}else if(key == player2.getButtonChangeWeapon()){
            weaponIndex2 ++;
            if(weaponIndex2>=weapons.length)weaponIndex2 = 0;
            player2.setWeapon(weapons[weaponIndex2]);
        }

		player.keyReleased(key, c);
        player2.keyReleased(key, c);

    }

	@Override
	public void keyPressed(int key, char c) {
		player.keyPressed(key, c);
        player2.keyPressed(key, c);

        //World.addProjectile(new Projectile((int) player.getX(), 100, (float) Math.PI/2 , 425, 25,null));
	}

	public static void reset() {
        terrain = new Terrain();
        player = new Player(400,0);
        player2 = new Player(800,0);
	}

	public static void addProjectile(Projectile proj) {
		World.projectiles.add(proj);
	}
    public static void addLoot(Loot loot) {
        World.loots.add(loot);
    }


    public static void setPersoType(int i, int typePerso) {
        if(i==0){
            player.setTypePerso(typePerso);
        }else{
            player2.setTypePerso(typePerso);
        }
    }
}
