package menus;

import games.worms3000.menus.WormMenu;
import org.newdawn.slick.Color;


public class MainMenu extends Menu{

	public static int ID = -5;

	public MainMenu(){
		super.setTitrePrincipal("INSERER TITRE ICI");
		super.setTitreSecondaire("SOUS TITRE");

		super.setItems("Worms 3000","Quitter");
		//super.setItems(World1.GAME_NAME,World2.GAME_NAME,World3.GAME_NAME,"Scores", "Quitter");

		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}

	@Override
	public void onOptionItemSelected(int position) {
		switch (position) {
		case 0:
			//appeler le reset du world du jeu correspondant
			//puis faire un game.enterState(ID du world, transition de sortie comme new FadeOutTransition(),
			//											 transition d'entree comme new FadeInTransition())
			game.enterState(WormMenu.ID);
			break;
		case 1:
			System.out.println("exit");
			System.exit(0);
			break;
		}
	}

	@Override
	public int getID() {
		return ID;
	}


}
