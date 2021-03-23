package games.worms3000;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppFont;
import app.AppLoader;

import games.worms3000.ground.GroundPolygon;
import games.worms3000.ground.Terrain;
import games.worms3000.utils.PathUtils;
import games.worms3000.weapons.*;
import games.worms3000.weapons.projectiles.Projectile;

public class World extends BasicGameState {

	public static int longueur = 1280;
	public static int hauteur = 720;

	private int ID;
	private int state;

    public Image imageBackground;

    private StateBasedGame game;

    public Terrain terrain;
    private Player player;
    private Player player2;
    private ArrayList<Loot> loots;

	public ArrayList<Projectile> projectiles;

	private Projectile proj;
    private int weaponIndex,weaponIndex2;
    private Image imageVie2,imageVie1;
    private Weapon[] weapons;
    private boolean finish;

    private int playerWhoWin;
    private TrueTypeFont font;

	private String levelName;
	private int playerType;
	private int playerType2;

	public World(int ID) {
		this.ID = ID;
		this.state = 0;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) {
		/* Méthode exécutée une unique fois au chargement du programme */
		game = arg1;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à l'apparition de la page */
		if (this.state == 0) {
			this.play(container, game);
		} else if (this.state == 2) {
			this.resume(container, game);
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée à la disparition de la page */
		if (this.state == 1) {
			this.pause(container, game);
		} else if (this.state == 3) {
			this.stop(container, game);
			this.state = 0; // TODO: remove
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) {
		/* Méthode exécutée environ 60 fois par seconde */
        imageBackground.draw(0,0,World.longueur,World.hauteur);
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
        arg2.fillRoundRect(World.longueur-220,34,player2.getHP(),14,20);

		arg2.setColor(Color.white);
        arg2.fillRoundRect(20,20,84,52,5,5);
        arg2.setColor(Color.black);
        arg2.fillRoundRect(25,25,74,42,5,5);


        arg2.setColor(Color.white);
        arg2.fillRoundRect(World.longueur-108,20,84,52,5,5);
        arg2.setColor(Color.black);
        arg2.fillRoundRect(World.longueur-103,25,74,42,5,5);

        arg2.drawImage(imageVie1.getScaledCopy(100,22),140,30);
        arg2.drawImage(imageVie2.getScaledCopy(100,22),World.longueur-220,30);

        arg2.drawImage(player.getWeapon().getImage().getScaledCopy(64,32),30,30);
        arg2.drawImage(player2.getWeapon().getImage().getScaledCopy(64,32),World.longueur-98,30);

        if(finish){
            arg2.setColor(Color.black);
            arg2.setFont(font);
            arg2.drawString("Player "+playerWhoWin+" Win !!!!",World.longueur/2-200,200);
        }
    }

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
		/* Méthode exécutée environ 60 fois par seconde */
		Input input = arg0.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.setState(1);
			arg1.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
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
                this.removeProjectile(projectiles.get(i));
                i--;
            }
		}


		if(new Random().nextInt(1000)==0){
            Loot l = new Loot(new Random().nextInt(World.longueur),-new Random().nextInt(World.hauteur),new Random().nextInt(Loot.NB_TYPE));
            l.setAmplitude(new Random().nextInt(10));
            l.setSpeedY((new Random().nextInt(200))/1000.0f);
            l.setFrequence(new Random().nextInt(400)/1000.0f);
            this.addLoot(l);
        }


	}

	public void play(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois au début du jeu */
		terrain = new Terrain(this);
		player = new Player(this, 400,0);
		player2 = new Player(this, 800,0);
		loots = new ArrayList<>();
		projectiles = new ArrayList<Projectile>();
		proj = new Projectile(100, 100, 45, 425, 25,null);
		weaponIndex = 0;
		weaponIndex2 = 0;
		weapons = new Weapon[]{new Bazooka(this),new BeletteLauncher(this),new MachineGun(this),new Shotgun(this)};
		finish = false;

		player.setTypePerso(playerType);
		player2.setTypePerso(playerType2);
		terrain.loadMap(levelName);

		imageVie1 = AppLoader.loadPicture(PathUtils.HPBar);
		imageVie2 = AppLoader.loadPicture(PathUtils.HPBar);

		imageBackground = AppLoader.loadPicture(PathUtils.Background);

		font = AppLoader.loadFont("/fonts/worms3000/PT_Sans.ttf",AppFont.BOLD,45);

		player2.setInputs(Input.KEY_Q,Input.KEY_D,Input.KEY_Z,Input.KEY_E,Input.KEY_R,Input.KEY_T,Input.KEY_F);
	}

	public void pause(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la mise en pause du jeu */
	}

	public void resume(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée lors de la reprise du jeu */
	}

	public void stop(GameContainer container, StateBasedGame game) {
		/* Méthode exécutée une unique fois à la fin du jeu */
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

    private void removeProjectile(Projectile p) {
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
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
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

	public void addProjectile(Projectile proj) {
		this.projectiles.add(proj);
	}
    public void addLoot(Loot loot) {
        this.loots.add(loot);
    }


    public void setPersoType(int i, int typePerso) {
        if(i==0){
            playerType = typePerso;
        }else{
            playerType2 = typePerso;
        }
    }

	public void setLevel(String levelName) {
		this.levelName = levelName;
	}

}
