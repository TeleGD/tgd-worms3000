package survival.utils;

public class Vector2 
{
	
	public float x;
	public float y;
	
	public Vector2(float x,float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vector2(float x)
	{
		this.x = x;
		this.y = x;
	}
	
	/*
	static 
	pas static
	
	normaliser vecteur2
	*/
	
	public void add(Vector2 other)
	{
		this.x += other.x;
		this.y += other.y;
	}
	
	public static Vector2 add(Vector2 vect1,Vector2 vect2)
	{
		return new Vector2(vect1.x+vect2.x,vect1.y+vect2.y);
	}
	
	public void mult(Vector2 other)
	{
		this.x *= other.x;
		this.y *= other.y;
	}
	
	public static Vector2 mult(Vector2 vect1,Vector2 vect2)
	{
		return new Vector2(vect1.x*vect2.x,vect1.y*vect2.y);
	}
	
	public void normalise()
	{
		this.x = (float) Math.sqrt(this.x*this.x + this.y*this.y);
		this.y = (float) Math.sqrt(this.x*this.x + this.y*this.y);
	};
	
	public static Vector2 normalize(Vector2 vector2)
	{
		return new Vector2((float) Math.sqrt(vector2.x*vector2.x + vector2.y*vector2.y));
	}
}
