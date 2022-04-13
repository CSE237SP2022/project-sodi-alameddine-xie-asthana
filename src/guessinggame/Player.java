package guessinggame;

//The Player class will be implemented alongside the scoring system/personal highscore functionality in the next iteration.
//This may be folded into the Game class.
public class Player {

    //Username
    public String name;

    public Player(String playerName) {
        name = playerName;
        System.out.println("Welcome to Guessing Game, "+name+"!");
    }
    
  }
