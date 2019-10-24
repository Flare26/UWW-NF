import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.ClassNotFoundException;
import java.io.File;

//GameSave objects will have ability to name them and set other parameters
public class GameSave implements Serializable {
    
    private static File gamesave;
    private static String SAVE_TITLE;
    private static String SAVE_PATH;
    private int loadcode;
    private Object playerObject;

    public GameSave(String name, int LC, Object playerObject) {
        SAVE_TITLE = name;
        LC = loadcode;
        this.playerObject = playerObject;
    }

    public static void writePlayer(String filepath , Player target) {

        // FileOutputSteam throws FileNotFoundException
        // ObjectOutputStream throws IOException
        System.out.println("!!! Saving File " + SAVE_TITLE + "..." );
        try { 
            
            gamesave = new File(filepath);
            SAVE_PATH=filepath;
            FileOutputStream fos = new FileOutputStream ( gamesave , false );
            ObjectOutputStream oos = new ObjectOutputStream( fos );
            oos.writeObject(target);
            oos.close();
            System.out.println("\t...Save complete!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player loadPlayer(){
        Player load=new Player();
        try{
            FileInputStream fis=new FileInputStream(gamesave);
            ObjectInputStream ois=new ObjectInputStream(fis);
            load=(Player)ois.readObject();

            
        }
        catch(IOException e){
            System.out.println("Error Loading Player");
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            System.out.println("Error finding class");
            e.printStackTrace();
        }
        System.out.println(load.toString());
        return load;
    }
}
