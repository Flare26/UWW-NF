import java.io.Serializable;
public class Player implements Serializable{
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
    
    public String getName(){
        return name;
    }
    
    
}