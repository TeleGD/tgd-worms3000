package general.worms;

import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import general.Main;

public class Player extends Rectangle {

	private double newX,newY,speedY,speedX,speed=0.1,accY=0.015,maxSpeedY=0.6;
	private double beforeJump, jumpHeight=40, jumpSpeed=0.28;
	private boolean faceLeft,upPress,leftPress,rightPress,rightLeft,jump;
	private Image image, imageJump, imageBasic;
	private int hp;
	private Weapon weapon = new Bazooka();

	public Player(double spawnX, double spawnY) {
		super((float)spawnX,(float)spawnY,24,43);

		try {
			imageBasic = new Image(PathUtils.PersoRightRed);
			imageJump = new Image(PathUtils.PersoRightRedJump);
			//image = image.getScaledCopy((float) 1);
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}

		image = imageBasic;
		hp = 100;
		faceLeft = false;
		this.height = image.getHeight();
		this.width = image.getWidth();
		//System.out.println(width+"   "+height);
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		g.drawImage(image, (float)x, (float)y);
		//g.setColor(Color.blue);
		//g.fillRect((float)x, (float)y, width, height);
		//g.fillOval((float)(x),(float)(y),(float)(width),(float)(height));
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		move();

		x+=speedX*delta;
		y+=speedY*delta;

		newX=x+speedX;
		newY=y+speedY;

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
		}

		if (newY<(beforeJump-jumpHeight) && jump) {
			jump = false;
		}

	}

	public void move() {

		speedX = 0;
		if (leftPress && !rightPress|| leftPress && rightPress && !rightLeft) {
			if (!faceLeft) {
				image = image.getFlippedCopy(true, false);
				faceLeft = true;
			}
			if (newX>0 && !World.terrain.intersects(this,(float)(newX),(float)(newY+height/2))) {
				speedX = -speed;
			}
		} else if (rightPress && !leftPress|| leftPress && rightPress && rightLeft) {
			if (faceLeft) {
				image = image.getFlippedCopy(true, false);
				faceLeft = false;
			}
			if (newX<(Main.longueur-width) && !World.terrain.intersects(this,(float)(newX+width),(float)(newY+height/2))) {
				speedX = speed;
			}
		} else {
			speedX = 0;
		}
		//System.out.println(World.terrain.intersect(this));
		if (newY<(Main.hauteur-height) && !World.terrain.intersects(this,(float)(newX+width/2),(float)(newY+height)) && !jump) {
			if(speedY<maxSpeedY) {
				speedY += accY;
			}
		} else if (upPress || jump) {
			if (World.terrain.intersects(this,(float)(newX+width/2),(float)(newY))) {
				speedY = 0;
				jump = false;
				upPress = false;
			} else if(!jump) {
				beforeJump = y;
				jump = true;
				speedY = -jumpSpeed;
			}
			speedY += accY;
		} else {
			speedY = 0;
		}

	}

	public void loseHP() {
		hp -= 1;
	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			upPress = false;
			break;

		case Input.KEY_LEFT:
			leftPress = false;
			break;

		case Input.KEY_RIGHT:
			rightPress = false;
			break;
		}
	}

	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			upPress = true;
			break;

		case Input.KEY_LEFT:
			leftPress = true;
			rightLeft = false;
			break;

		case Input.KEY_RIGHT:
			rightPress = true;
			rightLeft = true;
			break;
		case Input.KEY_SPACE:

			break;
		}
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
}
