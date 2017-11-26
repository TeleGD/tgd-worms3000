package general.worms;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;

public class GroundPolygon {

    private  Polygon polygon;
    private Image inner,outer;
    private int imageType = 0;

    public GroundPolygon(Polygon polygon,int type) {
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
}
