package survival.gameobjects.gameplay;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import survival.Camera;
import survival.gameobjects.GameObject;
import survival.utils.Vector2;

public class Compteur extends GameObject{
	
	private float maxValue = 100;
	private float currentValue;
	private Vector2 backgroundDelta;
	protected Image fillSprite;
	
	public Compteur(float currentValue, float maxValue, Vector2 location, Vector2 bgdelta , Image background, Image fill) {
		super(background);
		this.backgroundDelta = bgdelta;
		this.fillSprite = fill;
		this.currentValue = currentValue;
		this.maxValue = maxValue;
		this.location = location;
		this.boundingBox = null;
	}
		
	public boolean canAdd(float amount) {
		currentValue = currentValue + amount;
		if (currentValue > maxValue) {
			currentValue = maxValue;
			return(true);
		}
		if (currentValue <= 0) {
			currentValue=0;
			return(false);
		}
		return(true);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g, boolean useCamera) throws SlickException
	{
		super.render(container, game, g, useCamera);
		
		if(fillSprite != null)
		{
			if(useCamera)
				for(float i=0; i<currentValue/maxValue; i+=0.1f)
					g.drawImage(fillSprite, location.x - Camera.location.x + i*160 + backgroundDelta.x - sprite.getWidth()/2, location.y  + backgroundDelta.y - Camera.location.y- sprite.getHeight()/2 -8);
			else
				for(float i=0; i<currentValue/maxValue; i+=0.1f)
					g.drawImage(fillSprite, location.x + i*160 + backgroundDelta.x - sprite.getWidth()/2, location.y  + backgroundDelta.y - sprite.getHeight()/2-8);
		}
	}
	
}
