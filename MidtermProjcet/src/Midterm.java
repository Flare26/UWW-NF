public class Midterm{ 
	
    private static Player player1;
    
    public static void main(String[] args){
    	GameSave gameSaver = new GameSave();
    	InputHandler input = new InputHandler();
    	input.setYNOnly(true);
        System.out.println("Welcome to Live Another Day.\n\nDo you want to start a new game?\n\t( To exit, type 'quit' )");
        
        
        if( input.getInput(0) == ( 'y' ) ) {
        	
            System.out.println("You are starting a new game. Please insert your name : ");
            String name = input.scannerName();
            player1 = new Player(name);
            //...write new player to file instantly!!
            gameSaver.writePlayer( player1 );
            
        } else {
        	System.out.println("Enter the name of an existing save : ");
            player1 = (Player) gameSaver.loadPlayer( input.scannerName() );
            //Test to see if data loaded correctly
            System.out.println(player1.toString());
        } //END if / else
        
        example();
        
    }
    
    public static void example() { 
    	//example() just deals 34 damage and tries to save the player.
    	//If you keep loading the same save file it will keep dealing 34 damage!!! 
    	GameSave gameSaver = new GameSave();
        System.out.printf("EXAMPLE : %s\'s HP is %d.\n" , player1.getName(),  player1.getHealth());
        player1.takeDamage(34);
        System.out.printf("%s\'s HP is %d\n", player1.getName() , player1.getHealth());
        //saving the current state of the player after takeDamage()
        gameSaver.writePlayer(player1);
    }
}