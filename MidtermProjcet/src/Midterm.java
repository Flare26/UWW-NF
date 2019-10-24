import java.io.BufferedReader;
import java.io.FileReader;
public class Midterm{
    private static InputHandler input;
    private static Player player;
    public static void main(String[] args){
        System.out.println("Welcome to Live Another Day.\n\nDo you want to start a new game?");
        input=new InputHandler();
        input.setYNOnly(true);
        if(input.getInput(0)==('y')){
            System.out.println("You are starting a new game. Please insert your name.");
            player=new Player(input.scannerName());
            GameSave.writePlayer("testsave.thing",player);
        }
        else{
            player=GameSave.loadPlayer();
        }
        
        
        
    }
}