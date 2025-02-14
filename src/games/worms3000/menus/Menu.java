package games.worms3000.menus;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import app.AppFont;
import app.AppLoader;

import games.worms3000.World;

/**
 *
 * Pour faire un menu c'est simple,
 * il suffit de faire une classe qui herite de celle la et de
 * reseiner via les setters, les params TitrePrincipal, titreSecondaire
 * et les items. Et C'est tout
 * Vous recevrez l'index de l'item selectionné dans la méthode
 * onOptionItemSelected.
 *
		super.setTitrePrincipal("TELE-ARCADE DESIGN");
		super.setTitreSecondaire("Menu Principal");
		super.setItems("Jouer","Editeur", "Quitter");

		super.setEnableClignote(true);
		super.setCouleurClignote(Color.red);
		super.setTempsClignote(400);
 *
 *on peut add/remove item maintenant
 *ajouter autant d'item qu'on veut
 *
 *@author Jérôme
 */
public abstract class Menu extends BasicGameState {

	public static final int HEIGHT_ITEM=30;

	private static final int START_MENU_Y=World.hauteur/2-130;
	private static final int END_MENU_Y=World.hauteur-200;

	protected static final int MAX_ITEMS_VISIBLE = (END_MENU_Y-START_MENU_Y)/HEIGHT_ITEM-3;


	private String titrePrincipal="";
	private String titreSecondaire="";
	protected ArrayList<String> items=new ArrayList<String>();

	private TrueTypeFont fontTitrePrincipal;
	private TrueTypeFont fontTitreSecondaire;

	protected TrueTypeFont fontItem;
	protected TrueTypeFont fontConfirmText;

	protected int selection;
	protected long tempsClignote=400;
	protected Color couleurClignote=Color.red;
	private boolean enableClignote=false;

	protected GameContainer container;
	public StateBasedGame game;
	protected long time;


	protected int indexItemPlusGrand;
	protected int decalage;
	private String bottomText="PRESS ENTER";


	public Menu(){

	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		time=System.currentTimeMillis();

		setFontTitrePrincipal("/fonts/press-start-2p.ttf",AppFont.BOLD,40);
		setFontTitreSecondaire("/fonts/worms3000/PT_Sans.ttf",AppFont.BOLD,24);
		setFontItem("/fonts/worms3000/PT_Sans.ttf",AppFont.BOLD,14);

		fontConfirmText=AppLoader.loadFont("/fonts/press-start-2p.ttf",AppFont.PLAIN,20);

		this.container = container;
		this.game = game;
		container.setShowFPS(false);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setBackground(Color.black);

		renderTitrePrincipal(arg0,arg1,g);
		renderTitreSecondaire(arg0,arg1,g);
		renderMenusItems(arg0,arg1,g);
		renderSelectionItem(arg0,arg1,g,selection);

		g.setColor(Color.white);
		g.drawRect(World.longueur/2-300, START_MENU_Y, 600,37);

		g.drawRect(World.longueur/2-300, END_MENU_Y, 600,37);

		g.setFont(fontConfirmText);
		g.drawString(bottomText, World.longueur/2-fontConfirmText.getWidth(bottomText)/2, 530);

	}

	public void renderSelectionItem(GameContainer arg0, StateBasedGame arg1, Graphics g,int position) {
		if(items==null)return;
		if(enableClignote){
			if((System.currentTimeMillis()-time)%(2*tempsClignote)<=tempsClignote)g.setColor(Color.white);
			else g.setColor(couleurClignote);
		}else{
			g.setColor(couleurClignote);
		}
		g.drawString(">>", World.longueur/2-fontItem.getWidth(items.get(indexItemPlusGrand))/2-35, getYMenu() + HEIGHT_ITEM * (position-decalage));
		g.drawString("<<", World.longueur/2-fontItem.getWidth(items.get(indexItemPlusGrand))/2+fontItem.getWidth(items.get((position)))+10, getYMenu() + HEIGHT_ITEM * (position-decalage));

	}

	public void renderTitrePrincipal(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setColor(Color.red);
		g.setFont(fontTitrePrincipal);

		g.drawString(titrePrincipal,(World.longueur-fontTitrePrincipal.getWidth(titrePrincipal))/2 , 120);
		g.setColor(Color.white);
		g.setFont(fontTitrePrincipal);
		g.drawString(titrePrincipal,(World.longueur-fontTitrePrincipal.getWidth(titrePrincipal))/2+4 , 122);

	}

	public void renderTitreSecondaire(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.setFont(fontTitreSecondaire);
		g.drawString(titreSecondaire, World.longueur/2-fontTitreSecondaire.getWidth(titreSecondaire)/2, 232);
	}

	public void renderMenusItems(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		if(items==null)return;

		g.setColor(Color.white);

		for (int i = decalage; i < Math.min(items.size(),decalage+MAX_ITEMS_VISIBLE); i++) {
			g.setFont(fontItem);
			g.drawString(this.items.get(i), World.longueur/2-fontItem.getWidth(items.get(indexItemPlusGrand))/2, getYMenu() + HEIGHT_ITEM * (i-decalage));
		}

	}

	protected int getYMenu() {
		return START_MENU_Y+37+(END_MENU_Y-START_MENU_Y-37)/2-HEIGHT_ITEM*Math.min(MAX_ITEMS_VISIBLE,items.size())/2;
	}
	protected float getYitem(int i) {
		return getYMenu() + HEIGHT_ITEM * (i-decalage);

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {


	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		//time=System.currentTimeMillis();
		switch (key) {
		//case Input.KEY_NUMPAD2:
		case Input.KEY_DOWN:
			if (selection < items.size() - 1)
				selection++;
			else
				selection = 0;
			if(selection>=MAX_ITEMS_VISIBLE){
				decalage=selection-MAX_ITEMS_VISIBLE+1;
			}else decalage=0;
			onOptionItemFocusedChanged(selection);
			break;
		//case Input.KEY_NUMPAD8:
		case Input.KEY_UP:
			if (selection > 0)
				selection--;
			else
				selection = items.size() - 1;
			if(selection>=MAX_ITEMS_VISIBLE){
				decalage=selection-MAX_ITEMS_VISIBLE+1;
			}else decalage=0;
			onOptionItemFocusedChanged(selection);
			break;
		case Input.KEY_ENTER:
			onOptionItemSelected(selection);
			break;

		case Input.KEY_ESCAPE:
			//exit();
			break;
		}
	}


	public void onOptionItemFocusedChanged(int position){
		time = System.currentTimeMillis();
	}
	public abstract void onOptionItemSelected(int position);

	public String getTitrePrincipal() {
		return titrePrincipal;
	}

	public void setTitrePrincipal(String titrePrincipal) {
		this.titrePrincipal = titrePrincipal;
	}

	public String getTitreSecondaire() {
		return titreSecondaire;
	}

	public void setTitreSecondaire(String titreSecondaire) {
		this.titreSecondaire = titreSecondaire;
	}

	public ArrayList<String> getItems() {
		return items;
	}

	public void setItems(String... itemsLoc) {
		this.items = new ArrayList<String>(Arrays.asList(itemsLoc));
		this.selection = 0;
		calculerPlusGrandItem();
	}

	private void calculerPlusGrandItem() {
		indexItemPlusGrand=0;
		for(int i=0;i<items.size();i++){
			if(items.get(indexItemPlusGrand).length()<items.get(i).length()){
				indexItemPlusGrand=i;
			}
		}
	}
	public void setFontTitrePrincipal(String name, int type, int size) {
		fontTitrePrincipal = AppLoader.loadFont(name,type,size);
	}

	public void setFontTitreSecondaire(String name, int type, int size) {
		fontTitreSecondaire=AppLoader.loadFont(name,type,size);
	}
	public void setFontItem(String name, int type, int size) {
		fontItem=AppLoader.loadFont(name,type,size);
	}


	public void setEnableClignote(boolean b) {
		enableClignote=b;
	}

	public void setTempsClignote(long timeEnMillisecond){
		this.tempsClignote=timeEnMillisecond;
	}

	public void setCouleurClignote(Color coul){
		this.couleurClignote=coul;
	}
	public void addItem(String titre) {
		items.add(titre);
		calculerPlusGrandItem();
	}
	public void removeItemAtIndex(int index) {
		items.remove(index);
		calculerPlusGrandItem();

	}
	public void removeAllItems() {
		items.removeAll(items);
	}




	public void setBottomText(String text){
		this.bottomText=text;
	}

	public String getBottomText(){
		return bottomText;
	}


}
