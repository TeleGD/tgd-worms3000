package menus;

import general.Main;
import general.ui.TGDComponent;
import general.ui.TGDComponent.OnClickListener;
import general.utils.FontUtils;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.Calendar;
import java.util.Date;


public class WelcomeMenu extends Menu implements OnClickListener{

	public static int ID = -2;	
	
	private static final String CONFIRM_TEXT="PRESS ENTER";

	private Image background;
	private int blinkPeriod=10;
    private TrueTypeFont fontCopyright;
    private String textCopyright;

    @Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);

		background=new Image("Images/LogoTGD_transparent.png");
        fontCopyright = FontUtils.loadCustomFont("PT_Sans.ttf",java.awt.Font.BOLD,20);
        textCopyright = "A TGD Production - © " + Calendar.getInstance().get(Calendar.YEAR)+ " TGD CORP.";
	}

	
	@Override
	public void onOptionItemSelected(int position) {
		game.enterState(MainMenu.ID, new FadeOutTransition(),new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return ID;
	}
	
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//g.drawImage(new Image("img/accueil.png"), 0, 0);
		g.setColor(Color.white);

		g.drawRect(Main.longueur/2-300,25, 600,37);
		
		;
		g.setFont(fontConfirmText);
		int alpha=(int) ((System.currentTimeMillis()/blinkPeriod)%1000);
		if(alpha>255)alpha=500-alpha;
		if(alpha>500)alpha=0;
		g.setColor(new Color(255-alpha,255-alpha,255-alpha));
		g.drawString(CONFIRM_TEXT, Main.longueur/2-fontConfirmText.getWidth(CONFIRM_TEXT)/2,35);
		g.drawImage(background,Main.longueur/2-background.getWidth()/2,Main.hauteur/2-background.getHeight()/2);
        g.setColor(new Color(255,255,255));

        g.setFont(fontCopyright);
        g.drawString(textCopyright,Main.longueur/2-fontCopyright.getWidth(textCopyright)/2,Main.hauteur-80);
		g.setColor(Color.white);
	}
	
	@Override
	public void keyPressed(int key, char c) {

		if(key == Input.KEY_ENTER)
			onOptionItemSelected(0);
		if(key== Input.KEY_ESCAPE)
			System.exit(0);
	}

	@Override
	public void onClick(TGDComponent componenent) {
		
	}

}

