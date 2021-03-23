package games.worms3000.weapons.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import app.AppLoader;

import games.worms3000.World;
import games.worms3000.weapons.Weapon;

public class Projectile extends Rectangle {

    private  int explodeDamage;
    protected int x0, y0; // initial position
	protected float alpha0; // initial orientation
	protected int v0; // initial speed
	protected int weight;
	protected Weapon firingWeapon;
	protected int t; // time since firing
    private Image image;
    protected double dx;
    protected double dy;
    private int damage;
    private Image[] images;
    int compteur = 0;

    // graphisms

	public Projectile(int x0, int y0, float alpha0, int v0, int weight, Weapon firingWeapon) {
        super(x0,y0,5,5);
		this.x0 = x0;
		this.y0 = y0;
		this.alpha0 = alpha0;
		this.v0 = v0;
		this.weight = weight;
		this.firingWeapon = firingWeapon;
		this.t = 0;
		this.damage = 1;
		this.explodeDamage = 5;
	}

	public boolean updatePosition(int deltaT) {

        this.t += deltaT;
		this.dx = Math.cos(alpha0) * (v0*t/1000) ;
        this.dy = Math.sin(alpha0) * (-v0*t/1000) + 0.5*weight*9.81*t*t/1000000;

        this.setX((float) (dx + x0));

		if (this.getX() < 0 || this.getX() > 1280) {
			return true;
		}
        this.setY((float) (dy + y0));

		if (this.getY() < -World.hauteur || this.getY() > World.hauteur) {
			return true;
		}


		return false;
	}

    private void updateImage() {
	    if(images == null)return;

	    compteur++;

	    if(compteur >= images.length * 7)compteur=0;

        image = images[compteur/7];

        this.setHeight(image.getHeight());
        this.setWidth(image.getWidth());
    }


	public void render(Graphics g) {
        updateImage();

		if(image == null){
            g.setColor(new Color(255,0,0));
            g.fillRect(getX(),getY(),getWidth(),getHeight());
        }else{

            g.setColor(new Color(255,0,0));
            if(dx!=0){
                float angle = (float) (180*dy/dx/Math.PI);
                if(dx<0){
                    angle+=180;
                }
                image.setRotation(angle);

                g.drawImage(image,getX(),getY());

            }
        }

	}


    public void setImages(String... path) {
        images = new Image[path.length];
        for(int i=0;i<path.length;i++){
            images[i] = AppLoader.loadPicture(path[i]).getScaledCopy(0.8f);
        }
        //image = images[0];
        this.setHeight(images[0].getHeight());
        this.setWidth(images[0].getWidth());
    }




    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getExplodeDamage() {
        return explodeDamage;
    }

    public void setExplodeDamage(int explodeDamage) {
        this.explodeDamage = explodeDamage;
    }
}
