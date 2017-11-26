package general.worms;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GroundPolygon {

    private  Polygon polygon;
    private Image inner,outer;
    private int imageType = 0;

    public static int NB_IMAGE = 4;
    public static BufferedImage[] images = new BufferedImage[NB_IMAGE];
    public GroundPolygon(Polygon polygon,int type) {

        if(images[0] == null){
            try {

                images[0] = ImageIO.read(new File(PathUtils.Grass));
                images[1] = ImageIO.read(new File(PathUtils.Dirt));
                images[2] = ImageIO.read(new File(PathUtils.DirtMap_1));
                images[3] = ImageIO.read(new File(PathUtils.DirtMap_2));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        this.polygon = polygon;
        this.imageType = type;

    }



    public void setInner(Image inner) {
        this.inner = inner;
    }

    public void setOuter(Image outer) {
        this.outer = outer;
    }

    public boolean contains(int cx, int cy) {
        return polygon.contains(cx,cy);
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


    public String getImagePath() {
        switch (imageType) {
            case 0:
                return PathUtils.Grass;
            case 1:
                return PathUtils.Dirt;
            case 2:
                return PathUtils.DirtMap_1;
            case 3:
                return PathUtils.DirtMap_2;
        }

        return PathUtils.Dirt;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }


     public void loadImagePolygon() {
        BufferedImage image2 = null;
        try {
            long time = System.currentTimeMillis();

            image2 = images[0];
            setAlpha(image2, (byte) 125);

            BufferedImage image = images[imageType];
            setAlpha(image, (byte) 125);


            Texture text = BufferedImageUtil.getTexture("", image);
            //Texture text2 = BufferedImageUtil.getTexture("", image2);

            Image texture = new Image(text.getImageWidth(), text.getImageHeight());
            texture.setTexture(text);

            this.setInner(texture);

            Image textureContour = new Image(1280,720);

            this.setOuter(textureContour);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public  void setAlpha(BufferedImage image,byte alpha) {
        for (int cx=0;cx<image.getWidth();cx++) {
            for (int cy=0;cy<image.getHeight();cy++) {
                int rgba = new java.awt.Color(0 , 0, 0, 0).getRGB();
                if(!this.contains(cx,cy)){
                    image.setRGB(cx, cy, rgba);
                }

            }

        }
    }
    public void destroyPartOfGround(int x, int y, int r) {
        int nbPoint = r/5;
        double xi,yi;

        Circle circle = new Circle(x,y,r);

        ArrayList<float[]> points = new ArrayList<>();
        ArrayList<float[]> pointsToAdd = new ArrayList<>();

        int index = 0;
        //pointsASupprimer
        for(int i=0;i<polygon.getPoints().length/2;i++){

            if(!circle.contains(polygon.getPoint(i)[0],polygon.getPoint(i)[1]))
            {
                points.add(polygon.getPoint(i));
            }else{
                index = i;
            }
        }


        //point a ajouter dans le polygon
        for(double deg=Math.PI;deg>-Math.PI;deg-=2*Math.PI/nbPoint){
            xi = x+r*Math.cos(deg);
            yi = y+r*Math.sin(deg);

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
        loadImagePolygon();

    }

    public BufferedImage getImage() {
        return images[imageType];
    }
}
