package general.worms;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;

public class GroundPolygon {

    private  Polygon polygon;
    private Image inner,outer;

    public GroundPolygon(Polygon polygon) {
        this.polygon = polygon;

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
}
