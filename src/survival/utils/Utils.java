package survival.utils;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import survival.worlds.CityWorld;

public class Utils
{

	//Linearly interpolates between a and b by t.
	public static float lerp (float start,float end,float step)
	{
		if (step<0)
		{
			step = 0;
		}else if (step>1)
		{
			step = 1;
		}
		return (start - (start - end) * step);
	}

	public static Vector2 lerp (Vector2 start,Vector2 end,float step)
	{
		return (new Vector2(Utils.lerp(start.x,end.x,step),Utils.lerp(start.y,end.y,step)));
	}

}
