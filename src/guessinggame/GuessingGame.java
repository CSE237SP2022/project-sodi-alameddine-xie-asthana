package guessinggame;

import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        int guess = 0;
        int lastGuess = 0;
        boolean play = true;

        int difficulty = 0;

        Scanner nameScanner = new Scanner(System.in);
        System.out.println("What's your name? Only the first word will be used");
        String name = nameScanner.next();

        Player player = new Player(name);


        Scanner difficultyScanner = new Scanner(System.in);
        System.out.println("Please choose a difficulty from 0, the easiest, to "
                + Configuration.Hyperparameters.MAX_DIFFICULTY
                + ", the hardest (for now - higher difficulties coming soon!)");

        while(difficultyScanner.hasNext()) {
            if(difficultyScanner.hasNextInt()) {
                int chosenDifficulty = difficultyScanner.nextInt();
                if(chosenDifficulty < 0 || chosenDifficulty > Configuration.Hyperparameters.MAX_DIFFICULTY) {
                    System.out.println("Please enter a difficulty between 0 and "
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

    }
}

