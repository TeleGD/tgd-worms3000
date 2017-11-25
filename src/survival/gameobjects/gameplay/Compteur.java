package survival.gameobjects.gameplay;

public class Compteur {
	
	private static int maxValue = 100;
	private int currentValue;
	private String type;
	
	
	
	public Compteur(String type,int currentValue) {
		this.currentValue = currentValue;
		this.type = type;
	}
		
	public boolean canAdd(int amount) {
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
