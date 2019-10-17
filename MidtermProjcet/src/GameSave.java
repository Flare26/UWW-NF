import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//GameSave objects will have ability to name them and set other parameters
public class GameSave implements Serializable {

	private String SAVE_TITLE;
	private int SAVE_SLOT;
	private int loadcode;
	private Object playerObject;
	
	public GameSave(String name, int LC, Object playerObject) {
		SAVE_TITLE = name;
		LC = loadcode;
		this.playerObject = playerObject;
	}
	
	public void writeSave(String filepath) {
		// FileOutputSteam throws FileNotFoundException
		// ObjectOutputStream throws IOException
		System.out.println("!!! Saving File " + SAVE_TITLE + "..." );
		try { 
			FileOutputStream fos = new FileOutputStream ( SAVE_TITLE , false );
			ObjectOutputStream oos = new ObjectOutputStream( fos );
			oos.writeObject(this);
			oos.close();
			System.out.println("\t...Save complete!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
