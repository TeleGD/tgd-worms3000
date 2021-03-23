package games.worms3000.ground;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.worms3000.Player;
import games.worms3000.World;
import games.worms3000.utils.PathUtils;

public class Terrain {

    public static final String FOLDER_LEVEL = "levels";

    private World world;
    private ArrayList<GroundPolygon> grounds = new ArrayList<GroundPolygon>();

    public Terrain(World world) {
      this.world = world;
    }

    public void loadMap(String levelName){

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(FOLDER_LEVEL+"/"+levelName)));
            String ligne;
            int i=0;
            Polygon polygon = null;
            int type = 0;
            float a=0,r=0,g=0,b=0;

            this.world.imageBackground  = AppLoader.loadPicture(PathUtils.UI+"Background/"+br.readLine());

            while((ligne = br.readLine())!=null){
                i++;
                if(ligne.equals("new_polygone")){
                    if(polygon!=null){
                        Color color = new Color((int)(r*255),(int)(g*255),(int)(b*255),(int)(a*255));
                        grounds.add(new GroundPolygon(polygon,type,color));
                    }
                    polygon = new Polygon();

                    type = Integer.valueOf(br.readLine());
                    a = Float.valueOf(br.readLine());
                    r = Float.valueOf(br.readLine());
                    g = Float.valueOf(br.readLine());
                    b = Float.valueOf(br.readLine());
                    ligne = br.readLine();

                }

                polygon.addPoint(Float.valueOf(ligne),Float.valueOf(br.readLine()));

            }
            Color color = new Color((int)(r*255),(int)(g*255),(int)(b*255),(int)(a*255));

            grounds.add(new GroundPolygon(polygon,type,color));
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        int x,y;

        for(int i=0;i<grounds.size();i++){
            g.setColor(grounds.get(i).getColor());
            g.texture(grounds.get(i).getPolygon(),GroundPolygon.images[grounds.get(i).getImageType()],1,1,true);
        }

        g.setColor(new Color(255,255,255));


    }

    public void update(GameContainer arg0, StateBasedGame arg1, int arg2) {
        for(int i=0;i<grounds.size();i++){

            if(grounds.get(i).getPolygon().getPoints().length<3)
            {
                grounds.remove(i);
                i--;
            }
        }
    }

    public boolean intersects(Player player) {
		//System.out.println(player.getCenterX()+"   "+player.getY()+"   "+player.getWidth()+"   "+player.getHeight());
		for(int i=0;i<grounds.size();i++){
            if (grounds.get(i).getPolygon().intersects(player)) {
                return true;
            }
		}
		return false;
	}

    public ArrayList<GroundPolygon> getGroundPolygonList() {
        return grounds;
    }

    public boolean intersects(float x, float y) {
        for(int i=0;i<grounds.size();i++){
            if (grounds.get(i).getPolygon().contains(x,y)) {
                return true;
            }
        }
        return false;
    }
}
