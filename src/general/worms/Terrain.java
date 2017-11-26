package general.worms;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.BufferedImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;

public class Terrain {

    public static final String FOLDER_LEVEL = "levels";
    private ArrayList<GroundPolygon> grounds = new ArrayList<GroundPolygon>();
    private String levelName;

    public void loadMap(String levelName){

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(FOLDER_LEVEL+"/"+levelName)));
            String ligne;
            int i=0;
            Polygon polygon = null;
            int type = 0;
            while((ligne = br.readLine())!=null){
                i++;
                if(ligne.equals("new_polygone")){
                    if(polygon!=null)grounds.add(new GroundPolygon(polygon,type));
                    polygon = new Polygon();

                    type = Integer.valueOf(br.readLine());
                    ligne = br.readLine();
                }

                polygon.addPoint(Float.valueOf(ligne),Float.valueOf(br.readLine()));

            }
            grounds.add(new GroundPolygon(polygon,type));
            br.close();

            for(i=0;i<grounds.size();i++){
               grounds.get(i).loadImagePolygon();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void enter(GameContainer container, StateBasedGame game) {
        loadMap(levelName);
    }


    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        int x,y;

        for(int i=0;i<grounds.size();i++){
            g.drawImage(grounds.get(i).getOuter(),0,0);
            g.drawImage(grounds.get(i).getInner(),0,5);
        }

        for(int i=0;i<grounds.size();i++){
            g.setColor(Color.white);

            g.draw(grounds.get(i).getPolygon());
        }

        g.setColor(new Color(255,255,255));


    }

    public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
    }


    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

	public boolean intersect(Player player) {
		//System.out.println(player.getX()+"   "+player.getY()+"   "+player.getWidth()+"   "+player.getHeight());
		for(int i=0;i<grounds.size();i++){
			if (grounds.get(i).getPolygon().contains(player.getCenterX(),player.getY()+player.getHeight())) {
				return true;
			}
		}
		return false;
	}

    public ArrayList<GroundPolygon> getGroundPolygonList() {
        return grounds;
    }
}
