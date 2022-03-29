class guessing_game {
    public static void main(String[] args) {
        int guess = 0;
        int lastGuess = 0;
        int answer = generateAnswer();

        System.out.println("Hint: the answer is between "+(answer-3)+" and "+(answer+3));

        if(checkGuess(guess, answer)){
            
        }
        else {
            checkDistance(guess, lastGuess, answer);
        }


    }

    static int generateAnswer() {
        return (int)Math.random()*25+1;
    }

    static boolean checkGuess(int guess, int answer) {
        if (guess == answer){
            System.out.println("Congratulations! That's correct!");
            return true;
        }
        System.out.println("Wrong!");
        return false;
    }

    static void checkDistance(int guess, int lastGuess, int answer) {
        if (Math.abs(guess - answer) < Math.abs(lastGuess - answer)) {
            System.out.println("Warmer");
        } else if (Math.abs(guess - answer) > Math.abs(lastGuess - answer)) {
            System.out.println("Colder");
        } else {
            System.out.println("You guessed the same thing twice!");
        }
    }

    static String generateHint() {
        int way = (int)Math.random()*2;
        String hint = "";
        if(way == 1){


        }
    }
}
