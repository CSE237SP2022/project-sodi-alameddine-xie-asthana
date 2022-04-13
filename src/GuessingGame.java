import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        int guess = 0;
        int lastGuess = 0;
        boolean play = true;

        Game game = new Game(0); //create game object
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

