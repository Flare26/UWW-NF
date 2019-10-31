import java.util.Random;
public class ItemGen {

	Weapon gift;
	
	private Weapon [] weapons = 
			{new Weapon("Knife" , 5) , new Weapon("Baseball Bat", 15), new Weapon("Ray Gun", 95), new Weapon("David's Pumpkin", 36), new Weapon("FN SCAR-H" , 150)};
		
	
	public String getRandomWeapon() {
		Random rand = new Random();
		gift = weapons[rand.nextInt(5)];
		return gift.toString();
	}
	
	public int getWeaponDamage() {
		
		if (gift instanceof Weapon) {
			return gift.getDMG();
		} else {
			System.out.println("You attempt to fight using your fists. It's not very effective....");
			return 1;
		}
	
	}
	
class Weapon{
	
	String name;
	int dmg;
	
	Weapon(String name, int damage) {
		this.name = name;
		dmg = damage;
	}
	
	int getDMG() {
		return dmg;
	}
	
	public String toString() {
		return String.format("{ %s [ DMG : %d ] }", name, dmg);
	}
	
}
	
}


