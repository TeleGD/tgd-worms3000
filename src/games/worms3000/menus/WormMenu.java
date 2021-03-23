package games.worms3000.menus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import games.worms3000.LevelEditor;

public class WormMenu extends Menu{

	private int ID;

	public WormMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		super.init(container, game);
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
			game.enterState(5 /* LoadLevelMenu */,new FadeOutTransition(),new FadeInTransition());
			break;
		case 1:
			LevelEditor.reset();
			game.enterState(7 /* LevelEditor */,new FadeOutTransition(),new FadeInTransition());
			//Pareil pour le deuxieme item, etc
			break;
		case 2:
			game.enterState(1 /* Choice */,new FadeOutTransition(),new FadeInTransition());
			break;
		}
	}

}
