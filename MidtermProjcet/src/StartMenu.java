
public class StartMenu {

	private String TITLE;
	private String SAVE_PATH;
	private boolean AT_MENU;
	private boolean isvalid;
	
	public StartMenu(String title) {
		TITLE = title;
		AT_MENU = true;
	}
	
	private String getHeader() {
		String header = String.format("/// Main Menu - %s ///", TITLE);
		return header;
	}
	
	private String getOptions() {
		//if possible make scalable menu with an array
		//String [] options = new String [3];
		String option1 = String.format("[%d] - %s", 1, "New Game");
		String option2 = String.format("[%d] - %s", 2, "Load Game");
		String option3 = String.format("[%d] - %s", 3, "Credits");
		String structure = String.format(
				  "%s"
				+ "\n%s"
				+ "\n%s"
				+ "\n-----------", option1, option2, option3);
		return structure;
	}
	
	
	
	public String toString() {
		String stringout = String.format("%s\n-----------\n%s\n\tTest input isvalid = " + isvalid, getHeader(), getOptions());
		return stringout;
	}
	
	
	public int selectMenuOption(int userinput) {

		if (userinput >= 'a' && userinput <= 'Z') 
			isvalid = false;
		else if (userinput > 0 && userinput < 4 )
			isvalid = true;
		return userinput;
	}
	
}
