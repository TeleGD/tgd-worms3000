package games.worms3000.ground;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import app.AppLoader;

import games.worms3000.utils.PathUtils;

public class GroundPolygon {

    public static Image[] images = new Image[5];

    static {
        images[0] = AppLoader.loadPicture(PathUtils.Grass);
        images[1] = AppLoader.loadPicture(PathUtils.Dirt);
        images[2] = AppLoader.loadPicture(PathUtils.DirtMap_1);
        images[3] = AppLoader.loadPicture(PathUtils.DirtMap_2);
        images[4] = AppLoader.loadPicture(PathUtils.DirtTile);
    }

    private  Polygon polygon;
    private Image inner,outer;
    private int imageType = 0;

    private Color color = Color.white;

    public GroundPolygon(Polygon polygon,int type,Color color) {

        this.color = color;
        polygon.setX(0);
        polygon.setY(0);
        this.polygon = polygon;
        this.imageType = type;

    }



    public void setInner(Image inner) {
        this.inner = inner;
    }

    public void setOuter(Image outer) {
        this.outer = outer;
    }

    public boolean contains(float cx, float cy) {
        return polygon.contains(cx,cy);
    }

    public boolean intersects(Shape shape) {

        polygon.setX(0);
        polygon.setY(0);
        return polygon.intersects(shape);

    }

    public Image getOuter() {
        return outer;
    }

    public Image getInner() {
        return inner;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public int getImageType() {
        return imageType;
    }



    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;

        polygon.setX(0);
        polygon.setY(0);
    }


    public float distance(float x, float y, float x2, float y2){
        return (x2-x)*(x2-x) +(y2-y)*(y2-y);
    }
    public void destroyPartOfGround(float x, float y, int r) {
        int nbPoint = r/4;
        double xi,yi;

        Circle circle = new Circle(x,y,r);

        ArrayList<float[]> points = new ArrayList<>();
        ArrayList<float[]> pointsToAdd = new ArrayList<>();

        int index = -1;
        //pointsASupprimer
        for(int i=0;i<polygon.getPoints().length/2;i++){

            if(distance(x,y,polygon.getPoint(i)[0],polygon.getPoint(i)[1])>r*r)
            {
                points.add(polygon.getPoint(i));
            }else{
                if(index==-1) index = i;
            }
        }

        boolean test=false;
        //point a ajouter dans le polygon
        for(double deg=Math.PI/2; deg<5*Math.PI/2;deg += 2*Math.PI/nbPoint){

            xi = x+r*Math.cos(deg);
            yi = y-r*Math.sin(deg);

            if(polygon.contains((float)xi,(float)yi)){
                pointsToAdd.add(new float[]{(float) xi, (float) yi});

            }

        }

        polygon = new Polygon();
        for(int i=0;i<points.size();i++){
            if(index==i){
                for(int j=0;j<pointsToAdd.size();j++){
                    polygon.addPoint(pointsToAdd.get(j)[0],pointsToAdd.get(j)[1]);
                }
            }
            polygon.addPoint(points.get(i)[0],points.get(i)[1]);
        }
        polygon.setAllowDuplicatePoints(false);

    }

    public int getPoint(int x, int y) {
        for(int i=0;i<polygon.getPoints().length/2;i++){
            if(polygon.getPoint(i)[0]<x-5 || polygon.getPoint(i)[0]>x+5)continue;
            if(polygon.getPoint(i)[1]<y-5 || polygon.getPoint(i)[1]>y+5)continue;

            return i;

        }
        return -1;
    }

    public void changeImageType() {
        imageType ++;
        if(imageType>=images.length)imageType = 0;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
