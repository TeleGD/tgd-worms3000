package games.worms3000.menus;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

import games.worms3000.World;

public class LoadLevelMenu extends Menu{

	private int ID;
	public boolean custom;

	public LoadLevelMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		super.init(container, game);
		super.setTitrePrincipal("Worms3000");
		super.setTitreSecondaire("Jouer au worms !");

		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
		String levels;
		if (this.custom) {
			levels = AppLoader.restoreData("/worms3000/levels.txt");
		} else {
			levels = AppLoader.loadData("/data/worms3000/levels.txt");
		}
		BufferedReader reader = new BufferedReader(new StringReader(levels));
		List<String> items = new ArrayList<String>();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				items.add(line);
			}
			reader.close();
		} catch (Exception error) {}
		this.setItems(items.toArray(new String[0]));
		super.addItem("Retour");
    }

    @Override
	public void onOptionItemSelected(int position) {

	    if(position==items.size()-1){
	        game.enterState(4 /* WormMenu */,new FadeOutTransition(),new FadeInTransition());
        }else{
	        ((World) game.getState(3 /* World */)).setLevel(getItems().get(position), this.custom);
            game.enterState(6 /* LoadPlayerMenu */,new FadeOutTransition(),new FadeInTransition());
        }


	}

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);
        if(key == Input.KEY_ESCAPE){
            game.enterState(4 /* WormMenu */,new FadeOutTransition(),new FadeInTransition());
        }
    }

	@Override
	public void onOptionItemFocusedChanged(int position) {
		// TODO Auto-generated method stub

	}
}
