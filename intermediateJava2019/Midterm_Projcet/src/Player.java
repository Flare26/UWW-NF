import java.io.Serializable;
public class Player implements Serializable{
	//NOTE if you add code to this and try to load from an old save IT WILL BE CORRUPTED
	
    private String name;
    private int health;
    
    public Player(){
        
    }
    
    public Player(String name){
        this.name=name;
        health=100;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int health){
        this.health=health;
    }
    
    public String getName(){
        return name;
    }
    
    public void takeDamage(int dmg) {
    	health -= dmg;
    	System.out.printf("%s took %d DMG!\n", getName() , dmg);
    }
    
    public String toString() {
    	return String.format("-- PLAYER OBJECT DATA --\n"
    			+ "Name : %s\n"
    			+ "Health : %d\n", getName(), getHealth());
    }
    
}