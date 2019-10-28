
public class Question{
    private static String question;
    public Question(String question){
        this.question=question;
    }

    public String result(String answer, int health, Player player){
        if(health<0){
            if(player.getHealth()+health<=0){
                return "You are dead try again";
            }
            else{
                System.out.println(answer);
                health=health*-1;
                player.takeDamage(health);
            }
        }
        else{
            if(player.getHealth()+health>100){
                System.out.println(answer);
                player.setHealth(100);
            }
        }
        return (answer + ". Your health is now " + player.getHealth());
    }
    
    public String result(String answer){
        return answer;
    }
    
    public String toString(){
        return question;
    }
}