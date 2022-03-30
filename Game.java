public class Game {
    private int answer; //answer is the correct answer. For now, is a positive number between 1 and 25. We don't want the user seeing this.
    int turn;   //turn is the turn counter. Will be used later to help generate points. Will be displayed to the user later.

    //Constructor for the game class.
    public Game () {
        System.out.println("The game has begun.");
        answer = (int)(Math.random()*25+1);
        turn = 0;
    }

    //checkGuess will check if the user's guess is correct or not, and will add one to the turn counter.
    boolean checkGuess(int guess) {
        ++turn;
        if (guess == answer){
            System.out.println("Congratulations! That's correct!");
            return true;
        }
        System.out.println("Wrong!");
        return false;
    }

    //checkDistance looks at the guess made by the user. If it is closer to the answer than the previous guess, "Warmer." is output. If it's further, "Colder." is output.
    //If the same thing is guessed twice, "You guessed the same thing twice!" is output.
    String checkDistance(int guess, int lastGuess) {
        if (Math.abs(guess - answer) < Math.abs(lastGuess - answer)) {
            return "Warmer.";
        } else if (Math.abs(guess - answer) > Math.abs(lastGuess - answer)) {
            return "Colder.";
        } else {
            return "You guessed the same thing twice!";
        }
    }

    //generateHint will create a hint to give the player. Very basic as of now for proof of concept. Difficulty functionality will be added later.
    String generateHint() {
        int way = (int)(Math.random()*2);
        String hint = "";
        if(way == 1){
            int num1 = (int)(Math.random()*answer + 1);
            int num2 = answer - num1;
            hint = "Hint: " + num1 + " + " + num2;
        }
        else{
            int num1 = (int)(Math.random()*100 + answer);
            int num2 = num1 - answer;
            hint = "Hint: " + num1 + " - " + num2;
        }
        return hint;
    }
}