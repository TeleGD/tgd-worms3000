package general.worms;

import menus.MainMenu;
import menus.Menu;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.File;


public class LoadLevelMenu extends Menu{

	public static int ID = 45;

	public LoadLevelMenu(){
		super.setTitrePrincipal("Worms 3000");
		super.setTitreSecondaire("Jouer au worms !");


		String[] list = new File("levels").list();
		super.setItems(list);

		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
	}

	@Override
	public void onOptionItemSelected(int position) {
		World.reset();
        World.setLevel(getItems().get(position));
        game.enterState(World.ID,new FadeOutTransition(),new FadeInTransition());

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
