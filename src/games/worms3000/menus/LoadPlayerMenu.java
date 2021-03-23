package games.worms3000.menus;

import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;

import games.worms3000.World;
import games.worms3000.utils.PathUtils;

public class LoadPlayerMenu extends Menu{

	public static Image[] images = new Image[4];

	static {
		images[0] = AppLoader.loadPicture(PathUtils.Blu);
		images[1] = AppLoader.loadPicture(PathUtils.Red);
		images[2] = AppLoader.loadPicture(PathUtils.PersoRightBlu);
		images[3] = AppLoader.loadPicture(PathUtils.PersoRightRed);
	}

	private int ID;
    private  int playerIndex =  1;
    private int typePerso,typePerso2;

    private Audio music;

	public LoadPlayerMenu(int ID) {
		this.ID = ID;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		super.init(container, game);
		super.setTitrePrincipal("SELECT YOUR PLAYER");
		super.setTitreSecondaire("PLAYER "+playerIndex);
        super.setItems("Pataoide Blue","Pataoide Red","Bonhomme Blue","Bonhomme Red","ReBack/Backtour");

		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);

    music = AppLoader.loadAudio(PathUtils.Menu_music);
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        music.playAsMusic(1f, .3f, true);
    }


    @Override
    public void renderMenusItems(GameContainer arg0, StateBasedGame arg1, Graphics g) {
        super.renderMenusItems(arg0, arg1, g);g.setColor(Color.white);

        for (int i = decalage; i < Math.min(items.size(),decalage+Menu.MAX_ITEMS_VISIBLE); i++) {
            g.setFont(fontItem);
            g.drawString(this.items.get(i), World.longueur/2-fontItem.getWidth(items.get(indexItemPlusGrand))/2, getYitem(i));

            if(i<items.size()-1){
                g.drawImage(images[i].getScaledCopy(0.6f),World.longueur/2-fontItem.getWidth(items.get(indexItemPlusGrand))-60,getYitem(i));
                g.drawImage(images[i].getScaledCopy(0.6f),World.longueur/2+fontItem.getWidth(items.get(indexItemPlusGrand))+30,getYitem(i));

            }
        }

    }



    @Override
	public void onOptionItemSelected(int position) {
        if(position==items.size()-1){
            playerIndex = 1;
            super.setTitreSecondaire("PLAYER "+playerIndex);
            game.enterState(4 /* WormMenu */,new FadeOutTransition(),new FadeInTransition());
        }else{
            if(playerIndex==2){
                typePerso2 = position;
                playerIndex = 1;
                super.setTitreSecondaire("PLAYER "+playerIndex);
                ((World) game.getState(3 /* World */)).setPersoType(0,typePerso);
                ((World) game.getState(3 /* World */)).setPersoType(1,typePerso2);
                game.enterState(3 /* World */,new FadeOutTransition(),new FadeInTransition());
            }else{
                typePerso = position;
                playerIndex = 2;
                super.setTitreSecondaire("PLAYER "+playerIndex);

            }



        }



	}

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);
        if(key == Input.KEY_ESCAPE){
            playerIndex = 1;
            super.setTitreSecondaire("PLAYER "+playerIndex);
            game.enterState(4 /* WormMenu */,new FadeOutTransition(),new FadeInTransition());
        }
    }

	@Override
	public void onOptionItemFocusedChanged(int position) {
		// TODO Auto-generated method stub

	}
}
