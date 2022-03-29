class guessing_game {
    public static void main(String[] args) {
        int guess = 0;
        int lastGuess = 0;
        int answer = 0;

        answer = (int)Math.random()*25+1;

        System.out.println("Hint: the answer is between "+(answer-3)+" and "+(answer+3));

        if (guess == answer){
            System.out.println("Congratulation!");
        }

        if (Math.abs(guess - answer) < Math.abs(lastGuess - answer)) {
            System.out.println("Warmer");
        } else if (Math.abs(guess - answer) > Math.abs(lastGuess - answer)) {
            System.out.println("Colder");
        } else {
            System.out.println("You guessed the same thing");
        }
    }
}   
