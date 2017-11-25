package general.worms;

import menus.MainMenu;
import menus.Menu;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class WormMenu extends Menu{

	public static int ID = -3;

	public WormMenu(){
		super.setTitrePrincipal("Worms 3000");
		super.setTitreSecondaire("Jouer au worms !");

		super.setItems("Jouer","Editeur","Retour");

		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}

	@Override
	public void onOptionItemSelected(int position) {
		switch (position) {
		case 0:
			game.enterState(LoadLevelMenu.ID);
			break;
		case 1:
			LevelEditor.reset();
			game.enterState(LevelEditor.ID);
			//Pareil pour le deuxieme item, etc
			break;
		case 2:
			game.enterState(MainMenu.ID,new FadeOutTransition(),new FadeInTransition());

			break;
		}
	}

	@Override
	public int getID() {
		return ID;
	}


}
