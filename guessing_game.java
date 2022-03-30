import java.util.Scanner;
//issue with importing Game.class for some reason

public class guessing_game {
    public static void main(String[] args) {
        int guess = 0;
        int lastGuess = 0;
        boolean play = true;
        
        Game myGame = new Game();

        String hint = myGame.generateHint();
        System.out.println(hint);

        Scanner scan = new Scanner(System.in);
        System.out.println("Make your guess: ");
        lastGuess = guess;
        guess = scan.nextInt();

        while(play){
            if(myGame.checkGuess(guess)){
                play = false;
            }
            else if(myGame.turn > 1) {
                System.out.println(myGame.checkDistance(guess, lastGuess));
            }
        }

    }
}

