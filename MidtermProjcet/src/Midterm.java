public class Midterm{ 

    public static Player player1;

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

        System.out.println("Backstory: You have woken up one morning and did your regular routine. You showered, brushed your teeth,\n"+
            "and left to go to class. But something was different. There was no one out and about. However, you keep walking. There is a\n"+
            "smell in the air that is so bad you have to cover your mouth and nose so you do not throw up. While doing this you hear a noise\n"+
            "to your right. You turn and see a person limping and acting very weird. You then notice it is a kid from your class.");
        System.out.println("");
        Question q1=new Question("Do you want to walk up to them?");
        System.out.println(q1);
        if(input.getInput(0)==('y')){
            q1.result("The classmate named Jerry starts running at you and has blood covering their face.");
            System.out.println("Do you:\n1. Run back to your room\n2.  Put up your fist to fight\n3. Grab your phone to call 911 and run");
            input.setYNOnly(false);
            input.getInput(3);
        }
        else{
        	q1.result("No time for small talk. There's a test today , and you can't be bothered by wild stories of \"last night's party\".");
        
        }
        //InputHandler q1=new InputHandler();
        //q1.setYNOnly(true);

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