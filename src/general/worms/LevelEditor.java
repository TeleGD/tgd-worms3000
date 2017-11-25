package general.worms;
import general.Main;
import general.ui.Button;
import general.ui.TGDComponent;
import general.ui.TextField;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.*;
import java.util.ArrayList;

public class LevelEditor extends BasicGameState{

    public static int ID = 43;
    public static Image texture;
    private static ArrayList<int[]> list = new ArrayList<>();
    private static ArrayList<Polygon> listPolygon = new ArrayList<>();

    private Polygon polygon;
    private Button button;
    private Button newPolyGon;
    private TextField textField;
    private StateBasedGame game;

    public LevelEditor()
	{

	}

	@Override
	public int getID() {
		return ID;
	}

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        game = stateBasedGame;
    }

    @Override
	public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        texture = new Image("images/Worms/Terrain/Dirt.png");

        newPolyGon = new Button(gameContainer,Main.longueur-200,80, TGDComponent.AUTOMATIC,TGDComponent.AUTOMATIC);
        newPolyGon.setText("NOUVEAU POLYGONE");
        newPolyGon.setOnClickListener(new TGDComponent.OnClickListener() {
            @Override
            public void onClick(TGDComponent componenent) {

                listPolygon.add(polygon);
                polygon = new Polygon();
                list.clear();

            }
        });

        button = new Button(gameContainer,Main.longueur-200,50, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        button.setText("SAUVEGARDER");
        button.setOnClickListener(new TGDComponent.OnClickListener() {
            @Override
            public void onClick(TGDComponent componenent) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Terrain.FOLDER_LEVEL+"/"+textField.getText()+".txt")));
                    listPolygon.add(polygon);

                    for(int i=0;i<listPolygon.size();i++)
                    {
                        bw.write("new_polygone");
                        bw.newLine();

                        for(int j=0;j<listPolygon.get(i).getPoints().length/2;j++){

                            bw.write(""+listPolygon.get(i).getPoint(j)[0]);
                            bw.newLine();
                            bw.write(""+listPolygon.get(i).getPoint(j)[1]);
                            bw.newLine();
                        }
                    }
                    bw.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        textField = new TextField(gameContainer,Main.longueur-200,20, newPolyGon.getWidth(),TGDComponent.AUTOMATIC);
        textField.setPlaceHolder("Nom du level");
        textField.setMaxNumberOfLetter(15);
        textField.setOnlyFigures(false);


    }

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
        try {
            button.update(arg0,arg1,arg2);
            newPolyGon.update(arg0,arg1,arg2);
            textField.update(arg0,arg1,arg2);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        texture.draw(0,0,Main.longueur,Main.hauteur);
        newPolyGon.render(container, game, g);
        button.render(container,game,g);
        textField.render(container,game,g);
        g.setColor(Color.white);


        for(int i=0;i<list.size();i++){
            g.setColor(Color.white);

            g.drawOval(list.get(i)[0]-1,list.get(i)[1]-1,5,5);
            g.setColor(Color.black);
            g.drawOval(list.get(i)[0],list.get(i)[1],3,3);
        }

        for(int i=0;i<listPolygon.size();i++){
            g.draw(listPolygon.get(i));
        }

        if(polygon!=null && polygon.getPoints().length>0) g.draw(polygon);
	}

	public void mouseReleased(int button, int x,int y){

	}
	
	public void mousePressed(int button, int oldx,int oldy){
	    if(!this.button.contains(oldx,oldy) &&
                !newPolyGon.contains(oldx,oldy) &&
                !textField.contains(oldx,oldy)){
            list.add(new int[]{oldx,oldy});

            updatePolygon();
        }


	}

    private void updatePolygon() {
        polygon = new Polygon();
        for(int i=0;i<list.size();i++){
            polygon.addPoint(list.get(i)[0],list.get(i)[1]);
        }
    }


    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if(key==Input.KEY_BACK){
            if(list.size()>0)list.remove(list.size()-1);
            updatePolygon();
        }else if(key == Input.KEY_ESCAPE){
            game.enterState(WormMenu.ID);
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        super.keyReleased(key, c);

    }

    public static void reset() {
	    listPolygon.clear();
        list.clear();
    }
}
