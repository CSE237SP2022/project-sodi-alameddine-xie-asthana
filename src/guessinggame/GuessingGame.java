package guessinggame;

import java.util.Scanner;


public class GuessingGame {
    public static void main(String[] args) {

        int difficulty = 0;

        Scanner nameScanner = new Scanner(System.in);
        System.out.println("What's your name? Only the first word will be used");
        String name = nameScanner.next();

        Player player = new Player(name);


        Scanner difficultyScanner = new Scanner(System.in);
        System.out.println("Please choose your difficulty by entering one of the following numbers:");
        Game.prettyPrintDifficulties();

        while(difficultyScanner.hasNext()) {
            if(difficultyScanner.hasNextInt()) {
                int chosenDifficulty = difficultyScanner.nextInt();
                if(chosenDifficulty < 1 || chosenDifficulty > Configuration.Hyperparameters.MAX_DIFFICULTY) {
                    System.out.println("Please enter a difficulty between 1 and "
                            + Configuration.Hyperparameters.MAX_DIFFICULTY);
                } else {
                    difficulty = chosenDifficulty;
                    break;
                }
            } else {
                difficultyScanner.next();
                System.out.println("Enter an integer please");
            }
        }


        Game game = new Game(difficulty, player); //create game object
        game.play();

        System.exit(Configuration.ExitCodes.GAME_COMPLETE);

    }
}

