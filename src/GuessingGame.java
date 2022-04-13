import java.sql.SQLOutput;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        int guess = 0;
        int lastGuess = 0;
        boolean play = true;

        int difficulty = 0;

        Scanner nameScanner = new Scanner(System.in);
        System.out.println("What's your name? Enter one word please");
        String name = nameScanner.next();
        //nameScanner.close();

        Player player = new Player(name);


        Scanner difficultyScanner = new Scanner(System.in);
        System.out.println("Please choose a difficulty from 0, the easiest, to 6, the hardest (for now - higher difficulties coming soon!)");
        while(difficultyScanner.hasNext()) {
            if(difficultyScanner.hasNextInt()) {
                int chosenDifficulty = difficultyScanner.nextInt();
                if(chosenDifficulty < 0 || chosenDifficulty > 6) {
                    System.out.println("Please enter a difficulty between 0 and 6");
                } else {
                    difficulty = chosenDifficulty;
                    break;
                }
            } else {
                difficultyScanner.next();
                System.out.println("Enter an integer please");
            }
        }

        //difficultyScanner.close();

        Game game = new Game(difficulty, player); //create game object
        game.play();

//        String hint = myGame.generateHint();
//        System.out.println(hint); //create and print out the hint

//        Scanner scan = new Scanner(System.in);

//        while(play){
//            System.out.println("Make your guess: ");
//            lastGuess = guess;
//            guess = scan.nextInt(); //We will need to add something to catch thrown exceptions here if the user does not enter an integer.
//
//            if(myGame.checkGuess(guess)){ //checkGuess will return true if the player wins.
//                play = false;
//            }
//            else if(myGame.currentAttempts > 1) { //After the first turn, the player will begin getting "warmer" and "colder" messages upon guessing.
//                System.out.println(myGame.checkDistance(guess, lastGuess));
//            }
//        }

    }
}

