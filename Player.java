
//The Player class will be implemented alongside the scoring system/personal highscore functionality in the next iteration.
//This may be folded into the Game class.
public class Player {
    public String name; //Username.
    public int score;   //Player score.
    
    public Player(String name_input) {
        name = name_input;
        score = 0;
        System.out.println("Welcome to Guessing Game, "+name+"!");
    }
    
  }
