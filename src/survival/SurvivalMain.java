package survival;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.worlds.CityWorld;


public class SurvivalMain extends StateBasedGame{

	public static int longueur=1280;
	public static int hauteur=720;

	public final static String GAME_FOLDER_NAME="tgd_survival";

	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;

	public static void main(String[] args) throws SlickException {
		//Normalement c'est plus necessaire, c'est fait dans le setup du projet en theorie
		//System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new SurvivalMain(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}


	public SurvivalMain() {
		super("TGD SURVIVAL PRE-ALPHA");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new CityWorld());
		this.enterState(CityWorld.ID);
	}
}
