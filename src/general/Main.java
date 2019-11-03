package general;

import games.worms3000.LevelEditor;
import games.worms3000.menus.LoadLevelMenu;
import games.worms3000.menus.LoadPlayerMenu;
import games.worms3000.menus.WormMenu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.worms3000.World;
import menus.MainMenu;
import menus.WelcomeMenu;

public class Main extends StateBasedGame{

	public static int longueur=1280;
	public static int hauteur=720;

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}


	public Main() {
		super("INSERER LE NOM DU PROJET ICI MAIS EN PLUS COURT QUAND MEME PARCE-QUE LA C'EST UN PEU LONG");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
	    long time = System.currentTimeMillis();

	    addState(new WelcomeMenu());
		addState(new MainMenu());
		addState(new WormMenu());
		addState(new World());
		addState(new LevelEditor());
		addState(new LoadLevelMenu());
		addState(new LoadPlayerMenu());

        System.out.println("time to load game = "+(System.currentTimeMillis()-time));

        this.enterState(WelcomeMenu.ID);
	}
}
