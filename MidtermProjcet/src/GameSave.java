//Nathan Frazier GameSave saver
//GameSave objects should be used as an instance
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.ClassNotFoundException;
import java.io.File;

public class GameSave implements Serializable {
 
    private static File saveFile;
    private static String filepath = "OOPS.gamesave";
    private Player playerObject = new Player("OOPS.gamesave");
    
    public GameSave() {
    	
    }
    
    public GameSave(String filename, Player target) {
    	//Store file information ( path will just be playername.gamesave )
    	filepath = filename + ".gamesave";
    	saveFile = new File(filepath);
    	if ( target instanceof Player ) {
    		//We only want a Player instance
    		playerObject = target;
    	}
    	
    }

    public void writePlayer( Player target ) {
        // FileOutputSteam throws FileNotFoundException
        // ObjectOutputStream throws IOException
    	
    	//Update GameSave instance var for the Player
    	if ( target instanceof Player )
    		playerObject = target;
    	//Re-create filepath with new File(), for good measure
    	saveFile = new File(target.getName() + ".gamesave");
        System.out.println("!!! Saving File " + saveFile + "..." );
        //Write the copied 'playerObject' to file
        try { 
            FileOutputStream fos = new FileOutputStream ( saveFile , false );
            ObjectOutputStream oos = new ObjectOutputStream( fos );
            oos.writeObject(playerObject);
            oos.close();
            System.out.println("\t...Save complete!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player loadPlayer(String playername){
    	Player load = new Player();
    	//will search for file playername.gamesave
    	System.out.println("Attempting to load save file for " + playername + "...");
    	while ( ! new File(playername + ".gamesave").exists() ) {
    		//so... while the given player name does not have an existing save...
    	   	InputHandler temp = new InputHandler();
    	   	System.out.println("Save DOES NOT exist! Enter another player name : ");
    	   	String newname = temp.scannerName();
    	   	playername = newname;
    	}
    	System.out.printf( "Save file exists for %s!\n" , playername );
    	//Only once we know the file exists and we're out of the while loop--set the instance var
    	saveFile = new File( playername + ".gamesave" );
        
        try{
            FileInputStream fis = new FileInputStream( saveFile );
            
            ObjectInputStream ois = new ObjectInputStream(fis);
            load = (Player) ois.readObject();
            ois.close();
        }
        catch( FileNotFoundException e ) {
        	System.out.println("Somehow, we couldn't find the file " + saveFile );
        	e.printStackTrace();
        }
        catch(IOException e){
            System.out.println("GameSave --> Error Loading Player!");
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            System.out.println("GameSave --> Error finding class!");
            e.printStackTrace();
        }
        System.out.println("\t...finished!");
        return load;
    }
}
