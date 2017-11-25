package survival.gameobjects.gameplay;

public class Compteur {
	
	private static int maxValue = 100;
	private float currentValue;
	private String type;
	
	public Compteur(String type,float currentValue) {
		this.currentValue = currentValue;
		this.type = type;
	}
		
	public boolean canAdd(float amount) {
		currentValue = currentValue + amount;
		if (currentValue > maxValue) {
			currentValue = 100;
			return(true);
		}
		if (currentValue <= 0) {
			currentValue=0;
			return(false);
		}
		return(true);
	}
	
}
