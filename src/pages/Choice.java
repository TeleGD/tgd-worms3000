package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.elements.MenuItem;

import games.worms3000.LevelEditor;
import games.worms3000.menus.LoadLevelMenu;

public class Choice extends AppMenu {

	public Choice(int ID) {
		super(ID);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		super.initSize(container, game, 600, 400);
		super.init(container, game);
		this.setTitle("Worms3000");
		this.setSubtitle("Voici venir les worms !");
		this.setMenu(Arrays.asList(new MenuItem[] {
			new MenuItem("Niveaux intégrés") {
				public void itemSelected() {
					((LoadLevelMenu) game.getState(4 /* LoadLevelMenu */)).custom = false;
					game.enterState(4 /* LoadLevelMenu */,new FadeOutTransition(),new FadeInTransition());
				}
			},
			new MenuItem("Niveaux personnalisés") {
				public void itemSelected() {
					((LoadLevelMenu) game.getState(4 /* LoadLevelMenu */)).custom = true;
					game.enterState(4 /* LoadLevelMenu */,new FadeOutTransition(),new FadeInTransition());
				}
			},
			new MenuItem("Editeur") {
				public void itemSelected() {
					LevelEditor.reset();
					game.enterState(6 /* LevelEditor */,new FadeOutTransition(),new FadeInTransition());
				}
			},
			new MenuItem("Retour") {
				public void itemSelected() {
					game.enterState(0 /* Welcome */, new FadeOutTransition(), new FadeInTransition());
				}
			}
		}));
		this.setHint("BY TELEGAME DESIGN");
	}

}
