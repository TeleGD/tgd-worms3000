package worms.menus;

import general.Main;
import menus.Menu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import worms.utils.PathUtils;
import worms.World;


public class LoadPlayerMenu extends Menu{

	public static int ID = 48;
    private  int playerIndex =  1;
    public static Image[] images;
    private int typePerso,typePerso2;

    private Sound sound ;

    public LoadPlayerMenu(){
		super.setTitrePrincipal("SELECT YOUR PLAYER");
		super.setTitreSecondaire("PLAYER "+playerIndex);
        super.setItems("Pataoide Blue","Pataoide Red","Bonhomme Blue","Bonhomme Red","ReBack/Backtour");

		super.setEnableClignote(false);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);


        try {
            sound = new Sound(PathUtils.Menu_sound);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        if(images == null){
            images=new Image[4];
            images[0] = new Image(PathUtils.Blu);
            images[1] = new Image(PathUtils.Red);
            images[2] = new Image(PathUtils.PersoRightBlu);
            images[3] = new Image(PathUtils.PersoRightRed);

        }
        sound.play();
    }


    @Override
    public void renderMenusItems(GameContainer arg0, StateBasedGame arg1, Graphics g) {
        super.renderMenusItems(arg0, arg1, g);g.setColor(Color.white);

        for (int i = decalage; i < Math.min(items.size(),decalage+Menu.MAX_ITEMS_VISIBLE); i++) {
            g.setFont(fontItem);
            g.drawString(this.items.get(i), Main.longueur/2-fontItem.getWidth(items.get(indexItemPlusGrand))/2, getYitem(i));

            if(i<items.size()-1){
                g.drawImage(images[i].getScaledCopy(0.6f),Main.longueur/2-fontItem.getWidth(items.get(indexItemPlusGrand))-60,getYitem(i));
                g.drawImage(images[i].getScaledCopy(0.6f),Main.longueur/2+fontItem.getWidth(items.get(indexItemPlusGrand))+30,getYitem(i));

            }
        }

    }



    @Override
	public void onOptionItemSelected(int position) {
        if(position==items.size()-1){
            game.enterState(WormMenu.ID,new FadeOutTransition(),new FadeInTransition());
        }else{
            if(playerIndex==2){
                typePerso2 = position;
                World.setPersoType(0,typePerso);
                World.setPersoType(1,typePerso2);
                game.enterState(World.ID,new FadeOutTransition(),new FadeInTransition());
            }else{
                typePerso = position;
                playerIndex = 2;
                super.setTitreSecondaire("PLAYER "+playerIndex);

            }



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
