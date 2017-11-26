package worms;

import menus.Menu;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.File;
import java.util.ArrayList;


public class LoadLevelMenu extends Menu{

	public static int ID = 45;

    public LoadLevelMenu(){
		super.setTitrePrincipal("Worms 3000");
		super.setTitreSecondaire("Jouer au worms !");

		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        String[] list = new File("levels").list();

        ArrayList<String> listfile = new ArrayList<>();

        for(int i=0;i<list.length;i++)
        {
            if(list[i].endsWith(".txt"))
                listfile.add(list[i]);
        }

        super.setItems(listfile.toArray(new String[listfile.size()]));
        super.addItem("Retour");

    }

    @Override
	public void onOptionItemSelected(int position) {

	    if(position==items.size()-1){
	        game.enterState(WormMenu.ID,new FadeOutTransition(),new FadeInTransition());
        }else{
	        World.reset();
	        World.setLevel(getItems().get(position));
            game.enterState(LoadPlayerMenu.ID,new FadeOutTransition(),new FadeInTransition());
        }


	}

	@Override
	public int getID() {
		return ID;
	}

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);
        if(key == Input.KEY_ESCAPE){
            game.enterState(WormMenu.ID);
        }
    }
}
