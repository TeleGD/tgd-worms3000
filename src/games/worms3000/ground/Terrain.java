package games.worms3000.ground;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.worms3000.Player;
import games.worms3000.World;
import games.worms3000.utils.PathUtils;

public class Terrain {

    private World world;
    private ArrayList<GroundPolygon> grounds = new ArrayList<GroundPolygon>();

    public Terrain(World world) {
      this.world = world;
    }

    public void loadMap(String levelName, boolean custom) {
        String level;
        if (custom) {
            level = AppLoader.restoreData("/worms3000/levels/" + levelName + ".txt");
        } else {
            level = AppLoader.loadData("/data/worms3000/levels/" + levelName + ".txt");
        }
        BufferedReader reader = new BufferedReader(new StringReader(level));
        String line;
        try {
            Polygon polygon = null;
            int type = 0;
            float a=0,r=0,g=0,b=0;
            this.world.imageBackground = AppLoader.loadPicture(PathUtils.UI + "Background/" + reader.readLine());
            while ((line = reader.readLine()) != null) {
                if (line.equals("new_polygone")) {
                    if(polygon!=null){
                        Color color = new Color((int)(r*255),(int)(g*255),(int)(b*255),(int)(a*255));
                        grounds.add(new GroundPolygon(polygon,type,color));
                    }
                    polygon = new Polygon();

                    type = Integer.valueOf(reader.readLine());
                    a = Float.valueOf(reader.readLine());
                    r = Float.valueOf(reader.readLine());
                    g = Float.valueOf(reader.readLine());
                    b = Float.valueOf(reader.readLine());
                    line = reader.readLine();
                }
                polygon.addPoint(Float.valueOf(line),Float.valueOf(reader.readLine()));
            }
            Color color = new Color((int)(r*255),(int)(g*255),(int)(b*255),(int)(a*255));
            grounds.add(new GroundPolygon(polygon,type,color));
            reader.close();
        } catch (Exception error) {}
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
