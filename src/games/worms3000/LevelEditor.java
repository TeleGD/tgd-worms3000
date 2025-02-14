package games.worms3000;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;
import app.ui.Button;
import app.ui.ColorPicker;
import app.ui.TGDComponent;
import app.ui.TextField;

import games.worms3000.ground.GroundPolygon;
import games.worms3000.utils.PathUtils;

public class LevelEditor extends BasicGameState implements TGDComponent.OnClickListener {

    private int ID;

    private GroundPolygon ground = new GroundPolygon(new Polygon(),0,Color.white);
    private Button button,cacherMenu;
    private Button newPolyGon;
    private TextField textField;
    private StateBasedGame game;

    public static ArrayList<GroundPolygon> grounds = new ArrayList<>();
    private Button changeTexture;
    private int selected = -1,pointSelected = -1;
    private GroundPolygon groundSelected;
    private Button back,changeColor;
    private Button quit,changerBack;
    private boolean saved = false;
    private int scale = 1;

    private ColorPicker picker;
    private List<String> imagesFiles;
    private int indexImageBack;
    private Image currentBackgroundImage ;

	public LevelEditor(int ID) {
		this.ID = ID;
	}



	@Override
	public int getID() {
		return ID;
	}

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {
        game = stateBasedGame;
    }

    @Override
	public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) {


        loadImageBackground();
        picker = new ColorPicker(gameContainer,World.longueur-450,300,200,200);
        picker.setColorSelected(Color.white);
        picker.setOnClickListener(new TGDComponent.OnClickListener() {
            @Override
            public void onClick(TGDComponent componenent) {
                picker.setVisible(false);
            }
        });
        picker.setVisible(false);

        ground.setColor(Color.red);
        newPolyGon = new Button(gameContainer,World.longueur-200,100, TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
        newPolyGon.setText("NOUVEAU POLYGONE");
        newPolyGon.setOnClickListener(this);

        button = new Button(gameContainer,World.longueur-200,60, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        button.setText("SAUVEGARDER");
        button.setOnClickListener(this);
        button.setBackgroundColor(new Color(220,220,220));
        button.setTextColor(Color.black);

        changeTexture = new Button(gameContainer,World.longueur-200,140, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        changeTexture.setText("CHANGER TEXTURE");
        changeTexture.setOnClickListener(this);

        changeColor = new Button(gameContainer,World.longueur-200,180, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        changeColor.setText("CHANGER COLOR");
        changeColor.setOnClickListener(this);

        changerBack = new Button(gameContainer,World.longueur-200,220, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        changerBack.setText("CHANGER BACKGROUND");
        changerBack.setOnClickListener(this);

        back = new Button(gameContainer,World.longueur-200,260, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        back.setText("ANNULER");
        back.setOnClickListener(this);

        quit = new Button(gameContainer,World.longueur-200,300, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        quit.setText("QUITTER");
        quit.setOnClickListener(this);

        cacherMenu = new Button(gameContainer,World.longueur-200,340, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        cacherMenu.setText("CACHER MENU");
        cacherMenu.setOnClickListener(this);


        textField = new TextField(gameContainer,World.longueur-200,20, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        textField.setPlaceHolder("Nom du level");
        textField.setOnlyFigures(false);

        picker.setY(changeColor.getY());

    }

    private void loadImageBackground() {
        String backgrounds = AppLoader.loadData("/data/worms3000/backgrounds.txt");
        BufferedReader reader = new BufferedReader(new StringReader(backgrounds));
        List<String> items = new ArrayList<String>();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                items.add(line);
            }
            reader.close();
        } catch (Exception error) {}
        imagesFiles = items;
        currentBackgroundImage = AppLoader.loadPicture(PathUtils.UI+"Background/"+imagesFiles.get(0));
        indexImageBack = 0;
    }


    @Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
            button.update(arg0,arg1,arg2);
            newPolyGon.update(arg0,arg1,arg2);
            textField.update(arg0,arg1,arg2);
            changeTexture.update(arg0,arg1,arg2);
            cacherMenu.update(arg0,arg1,arg2);
            picker.update(arg0,arg1,arg2);
            back.update(arg0,arg1,arg2);
            quit.update(arg0,arg1,arg2);
            changeColor.update(arg0,arg1,arg2);
            changerBack.update(arg0, arg1, arg2);

        if(selected >0){
            grounds.get(selected).setColor(picker.getColorSelected());
        }else{
            ground.setColor(picker.getColorSelected());
        }
    }

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {

        currentBackgroundImage.draw(0,0,World.longueur,World.hauteur);

        g.setColor(Color.white);


        for(int i=0;i<grounds.size();i++){
            g.setColor(grounds.get(i).getColor());
            g.texture(grounds.get(i).getPolygon(),GroundPolygon.images[grounds.get(i).getImageType()],1,1,true);

            if(selected == i){
                g.setColor(Color.red);
            }else{
                g.setColor(Color.white);
            }

            g.draw(grounds.get(i).getPolygon());
        }

        if(ground.getPolygon()!=null && ground.getPolygon().getPoints().length>0) {
            g.setColor(Color.white);
            g.setColor(ground.getColor());
            g.texture(ground.getPolygon(), GroundPolygon.images[ground.getImageType()], scale, scale, true);

            if(selected == -2)g.setColor(Color.red);
            else g.setColor(Color.white);

            g.draw(ground.getPolygon());
        }

        if(ground.getPolygon().getPoints().length>0)
        {
            for(int i=0;i<ground.getPolygon().getPoints().length/2;i++){
                g.setColor(Color.white);

                float[] f = ground.getPolygon().getPoint(i);
                g.drawOval(f[0]-5,f[1]-5,10,10);
                g.setColor(Color.black);
                g.drawOval(f[0]-4,f[1]-4,8,8);
            }
        }


        newPolyGon.render(container, game, g);
        button.render(container,game,g);
        textField.render(container,game,g);
        changeTexture.render(container,game,g);
        cacherMenu.render(container,game,g);
        back.render(container, game, g);
        quit.render(container, game, g);
        picker.render(container,game,g);
        changeColor.render(container,game,g);
        changerBack.render(container, game, g);

        if(saved){
            g.setColor(Color.red);
            g.drawString("Sauve!",cacherMenu.getX(),cacherMenu.getY()+40);
        }
	}

	@Override
	public void mouseReleased(int button, int x,int y){
        if(button==1){
            pointSelected =-1;
            groundSelected = null;
        }

	}

	@Override
	public void mousePressed(int buttonType, int oldx,int oldy){
	    if(!button.contains(oldx,oldy) &&
            !newPolyGon.contains(oldx,oldy) &&
            !textField.contains(oldx,oldy) &&
            !changeTexture.contains(oldx,oldy)&&
            !cacherMenu.contains(oldx,oldy) &&
            !back.contains(oldx,oldy) &&
            !quit.contains(oldx,oldy)&&
                !changeColor.contains(oldx,oldy)&&
                !picker.contains(oldx,oldy) &&
                !changerBack.contains(oldx,oldy)){

            saved = false;

            selected = -1;
            if(buttonType==1){
                for(int i = 0;i<grounds.size();i++)
                {
                    int point = grounds.get(i).getPoint(oldx,oldy);
                    if(point != -1){
                        groundSelected = grounds.get(i);
                        pointSelected = point;
                    }else if(grounds.get(i).contains(oldx,oldy)){
                        pointSelected = -1;
                        groundSelected = null;
                        selected = i;
                    }
                }

                int point = ground.getPoint(oldx,oldy);
                if(point != -1){
                    groundSelected = ground;
                    pointSelected = point;
                }else if(ground.contains(oldx,oldy)){
                    pointSelected = -1;
                    groundSelected = null;
                    selected = -2;
                }


            }else{
                ground.getPolygon().addPoint(oldx,oldy);
            }



        }


	}


    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        super.mouseDragged(oldx, oldy, newx, newy);

        if(pointSelected!=-1){

            float[] f = groundSelected.getPolygon().getPoints();

            Polygon p =new Polygon();

            for(int i=0;i<f.length/2;i++){
                if(i!=pointSelected){
                    p.addPoint(f[2*i],f[2*i+1]);
                }else{
                    p.addPoint(newx,newy);
                }
            }

            groundSelected.setPolygon(p);
        }
    }


    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if(textField.hasFocus())return;
        if(key==Input.KEY_BACK){

            if(selected > 0){
                grounds.remove(selected);
            }else{
                if(selected == -2) {
                    ground.setPolygon(new Polygon());

                }else
                if(ground.getPolygon().getPoints().length>0){
                    removeLastPoint();
                }
            }

        }else if(key == Input.KEY_ESCAPE){
            game.enterState(1 /* Choice */,new FadeOutTransition(),new FadeInTransition());
        }else if(key == Input.KEY_P){
            scale++;
        }else if(key == Input.KEY_M){
            scale--;
        }else if(key == Input.KEY_B){
           changeBackgroundImage();
        }
    }

    private void changeBackgroundImage() {
        indexImageBack++ ;
        if(indexImageBack>=imagesFiles.size())indexImageBack = 0;
        currentBackgroundImage = AppLoader.loadPicture(PathUtils.UI+"Background/"+imagesFiles.get(indexImageBack));
	}

    private void removeLastPoint() {
        float[] f = ground.getPolygon().getPoints();

        Polygon p =new Polygon();

        for(int i=0;i<f.length/2-1;i++){
            p.addPoint(f[2*i],f[2*i+1]);
        }

        ground.setPolygon(p);

    }

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);


    }


    public static void reset() {
	    grounds.clear();
    }

    @Override
    public void onClick(TGDComponent componenent) {
        if (componenent == button) {

            saveLevel();
        } else if (componenent == newPolyGon) {

            addNewPolygon();
        } else if (componenent == changeTexture) {
            changeTexture();
        } else if (componenent == cacherMenu) {
            toggleMenu();
        } else if (componenent == quit) {
            game.enterState(1 /* Choice */,new FadeOutTransition(),new FadeInTransition());
        } else if (componenent == changerBack) {
            changeBackgroundImage();
        }else if (componenent == changeColor)
        {
            picker.setVisible(true);
        }else if(componenent == back){

            if(ground.getPolygon().getPoints().length>0){
                removeLastPoint();
            }
        }
    }

    private void toggleMenu() {
	    if(newPolyGon.getVisible()){
            cacherMenu.setText("AFFICHER MENU");
            cacherMenu.setY(20);
            picker.setVisible(false);

        }else{
            cacherMenu.setText("CACHER MENU");
            cacherMenu.setY(340);
        }

        newPolyGon.setVisible(!newPolyGon.getVisible());
        button.setVisible(!button.getVisible());
        changeTexture.setVisible(!changeTexture.getVisible());
        textField.setVisible(!textField.getVisible());
        back.setVisible(!back.getVisible());
        quit.setVisible(!quit.getVisible());
        changeColor.setVisible(!changeColor.getVisible());

    }

    private void saveLevel() {
        String nomFichier = textField.getText();
        String levels = AppLoader.restoreData("/worms3000/levels.txt");
        BufferedReader reader = new BufferedReader(new StringReader(levels));
        List<String> items = new ArrayList<String>();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                items.add(line);
            }
            reader.close();
        } catch (Exception error) {}
        int i = 0;
        int li = items.size();
        while (i < li && items.get(i).compareTo(nomFichier) < 0) {
            ++i;
        }
        if (i == li || !items.get(i).equals(nomFichier)) {
            items.add(i, nomFichier);
        }
        AppLoader.saveData("/worms3000/levels.txt", String.join("\n", items));
        List<String> textLines = new ArrayList<String>();
        grounds.add(ground);
        textLines.add(imagesFiles.get(indexImageBack));
        for (GroundPolygon ground: grounds) {
            textLines.add("new_polygone");
            textLines.add(ground.getImageType() + "");
            textLines.add(ground.getColor().a + "");
            textLines.add(ground.getColor().r + "");
            textLines.add(ground.getColor().g + "");
            textLines.add(ground.getColor().b + "");
            Polygon polygon = ground.getPolygon();
            for (int j = 0, lj = polygon.getPoints().length / 2; j < lj; j++) {
                textLines.add(polygon.getPoint(j)[0] + "");
                textLines.add(polygon.getPoint(j)[1] + "");
            }
        }
        AppLoader.saveData("/worms3000/levels/" + nomFichier + ".txt", String.join("\n", textLines));
        saved = true;
    }


    private void changeTexture() {
        if(selected >=0){
            grounds.get(selected).changeImageType();
        }else{
            ground.changeImageType();
        }
    }

    private void addNewPolygon() {
        if(ground.getPolygon().getPoints().length>3){
            grounds.add(ground);
            ground = new GroundPolygon(new Polygon(),ground.getImageType(),ground.getColor());
        }
	}
}
