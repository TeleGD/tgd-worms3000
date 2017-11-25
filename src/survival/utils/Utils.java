package survival.utils;

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
	
	public static void main (String argv[])
	{
		System.out.println(Utils.lerp(0.0f,1f,0.2f));
		System.out.println(Utils.lerp(0.0f,1f,100f));
	}

}
