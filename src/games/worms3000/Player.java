package games.worms3000;

import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

import games.worms3000.utils.PathUtils;
import games.worms3000.weapons.*;

public class Player extends Rectangle {

	private World world;
    private  float HP = 100;
	private double newX,newY,speedY,speedX,speed=0.2,accY=0.015,maxSpeedY=0.6;
	private double beforeJump, jumpHeight=40, jumpSpeed=0.28;
	private boolean faceLeft,upPress,leftPress,rightPress,jump;
    private boolean rightWeaponPress,leftWeaponPress;

	private Weapon weapon ;
	private float score;

	private int buttonRight = Input.KEY_RIGHT;
    private int buttonLeft = Input.KEY_LEFT;
    private int buttonUp = Input.KEY_UP;

    private int buttonWeaponLeft = Input.KEY_K;
    private int buttonWeaponRight = Input.KEY_L;
    private int buttonSwitchWeapon = Input.KEY_P;
    private int buttonShoot = Input.KEY_SPACE;
    private double angle = 0;
    private float radius = 30;

    private Image image, imageJump, imageBasic;
    private Image arrowImage;
    private Image weaponImageRight;
    private Image weaponImageLeft;
    private  Image imageRightWalk;
    private int compteur=0;
    private int typePerso;
    private boolean shootPressed;

    public Player(World world, double spawnX, double spawnY) {
		super((float)spawnX,(float)spawnY,24,43);
    this.world = world;

		updateImages();

		image = imageBasic;
		HP = 100;
		faceLeft = false;
		this.setHeight(image.getHeight());
		this.setWidth(image.getWidth());

		setWeapon( new Bazooka(this.world));
		//System.out.println(width+"   "+height);
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
		g.drawImage(image, getX(), getY());

		placeWeaponImage(g);

		g.setColor(Color.blue);
        arrowImage.setRotation(90+(float) (angle*180/Math.PI));
        if(weapon instanceof BeletteLauncher || weapon instanceof  Bazooka)
            g.drawImage(arrowImage,(float)((getX()+getWidth()/2-arrowImage.getWidth()/2)+(radius+80)*Math.cos(angle)), (float)(getY()+15+(radius+80)*Math.sin(angle)));

		//g.fillOval((float)(x),(float)(y),(float)(width),(float)(height));
	}

    private void placeWeaponImage(Graphics g) {

        weaponImageLeft.setRotation(0+((float) (angle*180/Math.PI)));
        weaponImageRight.setRotation(0+((float) (angle*180/Math.PI)));

        if(faceLeft){
            g.drawImage(weaponImageLeft,(float)((getX()+getWidth()/2-weaponImageLeft.getWidth()/2)+radius*Math.cos(angle)), (float)(getY()+radius*Math.sin(angle))+5);
        }else{
            g.drawImage(weaponImageRight,(float)((getX()+getWidth()/2-weaponImageRight.getWidth()/2)+radius*Math.cos(angle)), (float)(getY()+radius*Math.sin(angle))+5);
        }


    }

    public void update(GameContainer container, StateBasedGame game, int delta) {
		move();


		compteur++;

		this.setX((float) (this.getX()+speedX*delta));
        this.setY((float) (this.getY()+speedY*delta));

		newX=getX()+speedX;
		newY=getY()+speedY;

		if (jump) {
			if (faceLeft) {
				image = imageJump.getFlippedCopy(true, false);
			} else {
				image = imageJump;
			}
		} else {
			if (faceLeft) {
				image = imageBasic.getFlippedCopy(true, false);
			} else {
				image = imageBasic;
			}

            if(rightPress){
                if(compteur > 7)
                    image = imageRightWalk;//:imageBasic;
            }

            if(leftPress) {
                if (compteur > 7)
                    image = imageRightWalk.getFlippedCopy(true, false);
            }

        }



        if(shootPressed && (((weapon instanceof MachineGun) && compteur<2) || !(weapon instanceof  MachineGun))){
            int posX = (int) (getX()+(radius+20)*Math.cos(angle));
            int posY = (int) (getY()+(radius+20)*Math.sin(angle))+10;

            if(faceLeft){
                weapon.setFirepower(- Math.abs(weapon.getFirepower()));
                weapon.fire(posX,posY, (float) angle);
            }else{
                weapon.setFirepower( Math.abs(weapon.getFirepower()));
                weapon.fire(posX,posY, (float) angle);
            }

            if(! (weapon instanceof MachineGun)) shootPressed = false;
        }

        if(compteur >14)compteur =0;

	}

	boolean collidingTerrain,needGravity=true,upLeftCollision = false,upCollision,downCollision,leftCollision,rightCollision,middleLeftCollision,middleRightCollision,downRightCollision,downMiddleCollision,downLeftCollision;
	public void move() {
         collidingTerrain = this.world.terrain.intersects(this);



        downMiddleCollision = this.world.terrain.intersects(getCenterX(),getMaxY());

        if(collidingTerrain){
            upCollision = this.world.terrain.intersects(getCenterX(),getY());
            leftCollision = this.world.terrain.intersects(getX(),getY());
            middleLeftCollision = this.world.terrain.intersects(getX(),getCenterY());
            rightCollision = this.world.terrain.intersects(getMaxX(),getY());
            middleRightCollision = this.world.terrain.intersects(getMaxX(),getCenterY());
        }

        speedX = 0;
		if (leftPress) {
			if (!faceLeft) {
				image = image.getFlippedCopy(true, false);
				faceLeft = true;
			}
			if (newX>0) {

                if(!middleLeftCollision && !leftCollision)
				    speedX = -speed;
			}

		} else if (rightPress) {
			if (faceLeft) {
				image = image.getFlippedCopy(true, false);
				faceLeft = false;
			}

			if (newX<(World.longueur-width) && !rightCollision && !middleRightCollision) {
				speedX = speed;
			}
		} else {
			speedX = 0;
		}


        if((jump || upPress) && upCollision) {
		    jump = false;
		    upPress = false;
		    speedY = 0;
        }

        if(upPress && !jump && (this.world.terrain.intersects(getCenterX(),getMaxY()+1) || newY+1>=World.hauteur-height)) {
		    //si onveut sauter et qu'on est pas entrain de sauter
            beforeJump = y;
            jump = true;
            speedY = -jumpSpeed;
        }

        if(downMiddleCollision || newY>=World.hauteur-height){
            this.setY(this.getY()-1);
            needGravity = false;
            jump = false;
            speedY=0;
        }

        if(!this.world.terrain.intersects(getCenterX(),getMaxY()+1) && newY+1<World.hauteur-height){
            needGravity = true;
        }
        //gravité. dans la map ou que pas de collision on decend
		if (newY<(World.hauteur-getHeight())) {

		    if(speedY<maxSpeedY) {
				if(needGravity)speedY += accY;
			}
		}

		if(rightWeaponPress){

		    angle += 0.05;
            angle = angle%(2*Math.PI);
            faceLeft = angle>Math.PI/2 && angle<Math.PI*3/2;

        } else if (leftWeaponPress) {

		    if(angle<0)angle += 40*2*Math.PI;
            angle = angle%(2*Math.PI);
            angle -=0.05;
            faceLeft = angle>Math.PI/2 && angle<Math.PI*3/2;
        }





    }

    //return true if death)
	public boolean loseHP(int damage) {
		HP -= damage;
		if(HP<=0){

		    HP=0;
		    imageBasic.setRotation(90);
		    return true;
        }
		return false;
	}

	public void keyReleased(int key, char c) {

        if(key == buttonRight){
            rightPress = false;
        }else if(key == buttonLeft){
            leftPress = false;
        }else if(key == buttonUp){
            upPress = false;
        }else if(key == buttonWeaponRight)
        {
            rightWeaponPress=false;
        }else if(key == buttonWeaponLeft)
        {
            leftWeaponPress=false;
        }else if(key == buttonShoot)
        {
            shootPressed = false;
            if(weapon.getSound()!=null){
                if( (weapon instanceof MachineGun)) weapon.getSound().stop();
            }
        }

	}

	public void keyPressed(int key, char c) {
        if(key == buttonRight){
            rightPress = true;
            if(faceLeft)angle=Math.PI-angle;
        }else if(key == buttonLeft){
            leftPress = true;
            if(!faceLeft) angle=Math.PI-angle;

        }else if(key == buttonUp){
            upPress = true;
        }else if(key == buttonWeaponRight)
        {
            rightWeaponPress=true;
        }else if(key == buttonWeaponLeft)
        {
            leftWeaponPress=true;
        }
        else if(key == buttonShoot){

            shootPressed = true;
            if(weapon.getSound()!=null)weapon.getSound().playAsSoundEffect(1f, .3f, false);

        }

	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;

		if(weapon instanceof Bazooka || weapon instanceof BeletteLauncher) {
            weaponImageLeft = weapon.getImage().getScaledCopy(0.5f).getFlippedCopy(false,true);
            weaponImageRight = weaponImageLeft.getFlippedCopy(false,true);
        }else{
            weaponImageLeft = weapon.getImage().getFlippedCopy(false,true);
            weaponImageRight = weaponImageLeft.getFlippedCopy(false,true);
        }
	}
	public float getHP() {
		return HP;
	}

    public void setInputs(int left, int right, int up, int switchWeapon, int weaponLeft, int weaponRight, int shoot) {
        buttonLeft = left;
        buttonRight = right;
        buttonUp = up;
        buttonWeaponLeft = weaponLeft;
        buttonWeaponRight = weaponRight;
        buttonSwitchWeapon = switchWeapon;
        buttonShoot = shoot;

    }

    public int getButtonChangeWeapon() {
        return buttonSwitchWeapon;
    }

    public void setTypePerso(int typePerso) {
        this.typePerso = typePerso;
        updateImages();
    }

    private void updateImages() {
        if(typePerso==0){
            imageBasic = AppLoader.loadPicture(PathUtils.Blu);
            imageJump = imageBasic;
            imageRightWalk = imageBasic;
        }else if(typePerso==1){
            imageBasic = AppLoader.loadPicture(PathUtils.Red);
            imageJump = imageBasic;
            imageRightWalk = imageBasic;
        }else if(typePerso==2){
            imageBasic = AppLoader.loadPicture(PathUtils.PersoRightBlu);
            imageJump = AppLoader.loadPicture(PathUtils.PersoRightBluJump);
            imageRightWalk = AppLoader.loadPicture(PathUtils.PersoRightBluWalk);
        }else if(typePerso==3){
            imageBasic = AppLoader.loadPicture(PathUtils.PersoRightRed);
            imageJump = AppLoader.loadPicture(PathUtils.PersoRightRedJump);
            imageRightWalk = AppLoader.loadPicture(PathUtils.PersoRightRedWalk);
        }

        arrowImage = AppLoader.loadPicture(PathUtils.arrow).getScaledCopy(18,18);
        //image = image.getScaledCopy((float) 1);
    }

    public void applyBonus(int type) {
        if(type == Loot.AMO_CRATE){

        }else if(type == Loot.HEALTH_CRATE){
            HP += 30;
            if(HP>100)HP=100;

        }
    }
}
