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

	private double x,y,newX,newY,speedY,speedX,speed=0.3,accY=0.025,jumpSpeed=0.5,maxSpeedY=1;
	private boolean faceLeft,upPress,leftPress,rightPress,rightLeft,jump=false;
	private int height,width;
	private Image image;

	public Player(double spawnX, double spawnY) {
		super((float)spawnX,(float)spawnY,48,65);
		this.x = spawnX;
		this.y = spawnY;
		
		try {
			image = new Image("images/Worms/Terrain/Red.png");
			image = image.getScaledCopy((float) 0.08);
		} catch (SlickException e) {
			// nous donne la trace de l'erreur si on ne peut charger l'image correctement
			e.printStackTrace();
		}
		
		this.faceLeft = false;
		this.height = image.getHeight();
		this.width = image.getWidth();
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
		
		if (speedY<0.01 && jump) {
			jump = false;
		}
	}

	public void move() {
		
		speedX = 0;		
		if (leftPress && !rightPress|| leftPress && rightPress && !rightLeft) {
			if (newX>0) {
				if (!faceLeft) {
					image = image.getFlippedCopy(true, false);
					faceLeft = true;
				}
				speedX = -speed;
			}
		} else if (rightPress && !leftPress|| leftPress && rightPress && rightLeft) {
			if (newX<(Main.longueur-width)) {
				if (faceLeft) {
					image = image.getFlippedCopy(true, false);
					faceLeft = false;
				}
				speedX = speed;
			}
		} else {
			speedX = 0;
		}
		
		if (newY<(Main.hauteur-height) && !jump) {
			if(speedY<maxSpeedY) {
				speedY += accY;
			}
		} else if (upPress && newY>=(Main.hauteur-height) || jump) {
			if(!jump) {
				jump = true;
				speedY = -jumpSpeed;
			}
			speedY += accY;
		} else {
			speedY = 0;
		}
		
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
		}
	}

}
