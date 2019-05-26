package survival.input;

import org.newdawn.slick.Input;

public class CustomInput {

	public static boolean up;
	public static boolean right;
	public static boolean left;
	public static boolean down;
	public static boolean space;
	public static boolean one;
	public static boolean two;
	public static boolean three;

	public static void keyPressed(int key, char c)
	{
		switch (key) {
		case Input.KEY_Z:
			up=true;
			break;
		case Input.KEY_D:
			right=true;
			break;
		case Input.KEY_Q:
			left=true;
			break;
		case Input.KEY_S:
			down=true;
			break;
		case Input.KEY_SPACE:
			space=true;
			break;
		case Input.KEY_1:
			one=true;
			break;
		case Input.KEY_2:
			two=true;
			break;
		case Input.KEY_3:
			three = true;
			break;
		}
	}

	public static void keyReleased(int key, char c)
	{
		switch (key) {
		case Input.KEY_Z:
			up=false;
			break;
		case Input.KEY_D:
			right=false;
			break;
		case Input.KEY_Q:
			left=false;
			break;
		case Input.KEY_S:
			down=false;
			break;
		case Input.KEY_SPACE:
			space=false;
			break;
		case Input.KEY_1:
			one=false;
			break;
		case Input.KEY_2:
			two=false;
			break;
		case Input.KEY_3:
			three=false;
			break;
		}
	}
}
