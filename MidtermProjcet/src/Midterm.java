public class Midterm{ 

    public static Player player1;

    public static Question q1;
    public static Question q2;
    public static Question q3;
    public static Question q4;

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
        q1=new Question("Do you want to walk up to them?");
        System.out.println(q1);
        if(input.getInput(0)==('y')){
            q1.result("The classmate named Jerry starts running at you and has blood covering their face.");
            q2=new Question("Do you:\n1. Run back to your room\n2.  Put up your fist to fight\n3. Grab your phone to call 911 and run");
            System.out.println(q2);
            input.setYNOnly(false);
            //player1.setBooleanQuestion(true, 1);
            switch(input.getInput(3)){
                case '1': q2.result("You run back to your dorm and decide to call your parents, but realize that there is not signal. You see your\n keys on the table.");
                q3=new Question("Do you grab them?");
                System.out.println(q3);
                input.setYNOnly(true);
                if(input.getInput(0)==('Y')){
                    q3.result("You have picked up your keys and sprint to door. You run past Jerry and right to your car. Still you see no one in sight.\n"
                        +"You get to the door of your car when you hear what sounds like 50 people running and walking toward you.");
                    q4=new Question("Do you want to go look what is happening?");
                    System.out.println(q4);
                    if(input.getInput(0)==('Y')){
                        q4.result("You are bombarded by a crowd of students who have blood all over them.",-100,player1);
                        gameSaver.writePlayer(player1);
                    }
                    else{
                        q4.result("You get in your car and begin driving around investigating what is going on.");
                        gameSaver.writePlayer(player1);
                    }
                }
                else{
                    q3.result("You decide that you should investigate more rather than running to your car. You have the options to:\n");
                    q4=new Question("1. Go to your friends dorm to tell them what happened.\n2. Grab the closest \"Weapon\" and run back to check on Jerry\n 3. Try to get to class another way");
                    System.out.println(q4);
                    input.setYNOnly(false);
                    switch(input.getInput(3)){
                        case '1':q4.result("Your friend does not answer the knock on the door and your messages are not sending. You start to panic.");
                        gameSaver.writePlayer(player1);
                        break;
                        case '2':q4.result("You grab an umbrella as a defense against Jerry whom you are running back to see.");
                        gameSaver.writePlayer(player1);
                        break;                        
                        case '3':q4.result("You take another way to class in which you see a swarm of people acting the way Jerry was.");
                        gameSaver.writePlayer(player1);
                        break;
                    }
                }
                break;

                case '2': q2.result("Jerry, the classmate, trys to bite you but instead gives your arm a gnarly gash.",-10,player1);
                System.out.println("You run to your dorm in hope of finding someone, but again there is no one in sight.\n You get to your room and begin fixing your wound.");
                q3=new Question("You realize something is very wrong and decide to turn on the TV to watch the news.");
                System.out.println(q3);
                System.out.println("News Anchor: \"There is a rabie outbrake. Stay as far from the rabied humans as possible! You need to find safety.\n"+
                    "Get as far from busy cities or any building that has a lot of people. Stay safe and please....\" The TV turns off.");
                gameSaver.writePlayer(player1);
                break;

                case '3': q2.result("The phone just says connecting, but ringing does not start. You see that there is no service.");
                q3=new Question("Do you want to: \n1: Go to your dorm \n2: Go find a friend.");
                System.out.println(q3);
                switch(input.getInput(2)){
                    case '1': q3.result("You run back to your room and turn on the TV to see that the News Channel has just a broken green screen and\nthe lights are flickering."+
                        "It looks as though they just got up and left. You know there is something seriously wrong.");
                    gameSaver.writePlayer(player1);
                    break;
                    case '2':q3.result("You run to your friend's dorm and find that they are not answering. You try to send a text, but the service is still out.");
                    gameSaver.writePlayer(player1);
                    break;
                }
                break;
            }
        }
        else{
            q1.result("No time for small talk. There's a test today , and you can't be bothered by wild stories of \"last night's party\".");
            gameSaver.writePlayer(player1);
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