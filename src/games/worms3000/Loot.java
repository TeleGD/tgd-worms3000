package games.worms3000;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.worms3000.utils.PathUtils;

public class Loot extends Rectangle {

    public static final int NB_TYPE = 2;
    public static final int HEALTH_CRATE = 0;
    public static final int AMO_CRATE = 1;
    private static Image parachute;
    private static Image images[] = new Image[NB_TYPE];

    static {
        parachute = AppLoader.loadPicture(PathUtils.Parachute);

        images[0] = AppLoader.loadPicture(PathUtils.HealthCrate);
        images[1] = AppLoader.loadPicture(PathUtils.AmmoCrate);
    }

    private final int type;
    private float speedY = 0.2f;
    private int t;
    private float amplitude=2;
    private float frequence=2;
    private boolean parachuteRelease;
    private int repere;
    private boolean collide;

    public Loot(int x,int y,int type) {
        super(x,y,20,15);
        this.type = type;
    }

    public void setFrequence(float frequence) {
        this.frequence = frequence;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    public void update(GameContainer arg0, StateBasedGame arg1, int deltaTime) {

        t = t + deltaTime;

        if(!collide){
            if(!parachuteRelease)setX((float) (getX()+amplitude*Math.sin(frequence*t/100)));
            setY(getY()+deltaTime*speedY);
        }

        this.setWidth(images[type].getWidth());
        this.setHeight(images[type].getHeight());


        collide = false;
    }

    public void setCollide(boolean b){
        collide = b;
    }

    public void leaveLoot() {
        if(parachuteRelease)return;
        parachuteRelease = true;
        repere = t;
    }

    public void render(Graphics g) {
        if(!parachuteRelease)
            g.drawImage(parachute,getX(),getY()-19);
        else{
            if((t-repere)<255){
                g.drawImage(parachute,getX()+(t-repere)/14,getY()+(t-repere)/7-19,new Color(255,255,255,255-((t-repere))));

            }
        }
        g.drawImage(images[type],getX(),getY());
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getType() {
        return type;
    }

    public boolean isParachuteReleased() {
        return parachuteRelease;
    }
}
