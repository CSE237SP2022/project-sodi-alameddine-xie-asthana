import java.util.Scanner;

public class Game {
    private int answer; //answer is the correct answer. For now, is a positive number between 1 and 25. We don't want the user seeing this.
<<<<<<< Updated upstream
    int turn;   //turn is the turn counter. Will be used later to help generate points. Will be displayed to the user later.
=======
    private int turn;   //turn is the turn counter.
    public String username; //username. used for the leaderboard.
    public int score;       //points. used for the leaderboard.
    public int difficulty;  //difficulty of the game.
>>>>>>> Stashed changes

    //Constructor for the game class.
    public Game () {
        System.out.println("The game has begun.");
        answer = (int)(Math.random()*25+1);
        turn = 0;
    }

    //runs the game
    public void run(){
        int guess = 0;
        int lastGuess = 0;
        boolean play = true;

        Scanner scan = new Scanner(System.in);

        System.out.println("Choose your difficulty: ");
        difficulty = scan.nextInt();
        if(difficulty > 6){
            difficulty = 6;
        }
        if(difficulty < 0){
            difficulty = 0;
        }

        String hint = generateHint();
        System.out.println(hint); //create and print out the hint

        while(play){
            System.out.println("Make your guess: ");
            lastGuess = guess;
            guess = scan.nextInt(); //We will need to add something to catch thrown exceptions here if the user does not enter an integer.

            if(checkGuess(guess)){ //checkGuess will return true if the player wins. 
                play = false;
            }
            else if(turn > 1) { //After the first turn, the player will begin getting "warmer" and "colder" messages upon guessing.
                System.out.println(checkDistance(guess, lastGuess));
            }
        }
    }

    //checkGuess will check if the user's guess is correct or not, and will add one to the turn counter.
<<<<<<< Updated upstream
    boolean checkGuess(int guess) {
        ++turn;
        if (guess == answer){
            System.out.println("Congratulations! That's correct!");
=======
    private boolean checkGuess(int guess) {
        ++turn;
        if (guess == answer){
            System.out.println("Congratulations! That's correct!");
            if(turn > 10){
                System.out.println("You took too long! So bad, so sad. 0 points!");
            }
            else{
                int points = (100 - turn*9)*difficulty;
                System.out.println(points + " points!");
            }
>>>>>>> Stashed changes
            return true;
        }
        System.out.println("Wrong!");
        return false;
    }

    //checkDistance looks at the guess made by the user. If it is closer to the answer than the previous guess, "Warmer." is output. If it's further, "Colder." is output.
    //If the same thing is guessed twice, "You guessed the same thing twice!" is output.
<<<<<<< Updated upstream
    String checkDistance(int guess, int lastGuess) {
=======
    private String checkDistance(int guess, int lastGuess) {
>>>>>>> Stashed changes
        if (Math.abs(guess - answer) < Math.abs(lastGuess - answer)) {
            return "Warmer.";
        } else if (Math.abs(guess - answer) > Math.abs(lastGuess - answer)) {
            return "Colder.";
        } else {
            return "You guessed the same thing twice!";
        }
    }

    //generateHint will create a hint to give the player. Very basic as of now for proof of concept. Difficulty functionality will be added later.
<<<<<<< Updated upstream
    String generateHint() {
        int way = (int)(Math.random()*2);
        String hint = "";
        if(way == 1){
=======
    private String generateHint() {
        int divisor_range = 20;
        int add_sub_range = 100;

        if(difficulty == 0){//add
            String hint = "";
>>>>>>> Stashed changes
            int num1 = (int)(Math.random()*answer + 1);
            int num2 = answer - num1;
            hint = "Hint: " + num1 + " + " + num2;
            return hint;
        }
        else if(difficulty == 1){//mult, div or sub
            int way = (int)(Math.random()*3);
            String hint = "";
            if(way == 0){
                int num1 = (int)(Math.random()*divisor_range + 1);
                int num2 = answer * num1;
                hint = "Hint: " + num2 + " / " + num1;
            }
            else if(way == 1){
                int num1 = (int)(Math.random()*add_sub_range + answer);
                int num2 = num1 - answer;
                hint = "Hint: " + num1 + " - " + num2;
            }
            else {
                double num1 = (int)(Math.random()*divisor_range + 1);
                double num2 = answer / num1;

                if(num2 % 1 != 0)
                    hint = "Hint: " + String.format("%.2f",num2) + " * " + num1;
                else
                    hint = "Hint: " + num2 + " * " + num1;
            }
            return hint;
        }
        else if(difficulty == 2){//mdas of pemdas
            int way1 = (int)(Math.random()*2);
            int way2 = (int)(Math.random()*2);
            String hint = "";
            int num1 = 0;
            int num2 = 0;

            if(way1 == 0){
                num1 = (int)(Math.random()*answer + 1);
                num2 = answer - num1;
                hint = "Hint: " + num1 + " + ";
            }
            else {
                num1 = (int)(Math.random()*add_sub_range + answer);
                num2 = num1 - answer;
                hint = "Hint: " + num1 + " - ";
            }

            if(way2 == 0){
                int num3 = (int)(Math.random()*divisor_range + 1);
                int num4 = num2 * num3;
                hint = hint + num4 + " / " + num3;
            }
            else {
                int num3 = (int)(Math.random()*divisor_range + 1);
                double num4 = (double)num2 / num3;

                if(num3 % 1 != 0)
                    hint = hint + num3 + " * " + String.format("%.2f",num4);
                else
                    hint = hint + num3 + " * " + num4;
            }
            
            return hint;
        }
        else if(difficulty == 3){//square root/cube root/powers
            int way = (int)(Math.random()*2);
            String hint = "";
            if(Math.sqrt(answer) % 1 == 0){//checks if square root is a whole number
                hint = "Hint: " + Math.sqrt(answer) + " to the second power.";
            }
            else if(way == 1){
                int num1 = (int)(Math.pow(answer, 2));
                hint = "Hint: Square root of " + num1;
            }
            else{
                int num1 = (int)(Math.pow(answer, 3));
                hint = "Hint: Cube root of " + num1;
            }
            return hint;
        }
        else if(difficulty == 4){//pemdas
            int way1 = (int)(Math.random()*2);
            int way2 = (int)(Math.random()*4);
            String hint = "";
            int num1 = 0;

            if(Math.sqrt(answer) % 1 == 0){//checks if square root is a whole number
                num1 = (int)Math.sqrt(answer);
                hint = "Hint: ";
            }
            else if(way1 == 1){
                num1 = (int)(Math.pow(answer, 2));
                hint = "Hint: Square root of ";
            }
            else{
                num1 = (int)(Math.pow(answer, 3));
                hint = "Hint: Cube root of ";
            }

            if(way2 == 0){
                int num2 = (int)(Math.random()*answer + 1);
                int num3 = num1 - num2;
                hint = hint + "(" + num2 + " + " + num3 + ")";
            }
            else if(way2 == 1){
                int num2 = (int)(Math.random()*add_sub_range + num1);
                int num3 = num2 - num1;
                hint = hint + "(" + num2 + " - " + num3 + ")";
            }
            else if(way2 == 2){
                int num2 = (int)(Math.random()*divisor_range + 1);
                int num3 = num2 * num1;
                hint = hint + "(" + num3 + " / " + num2 + ")";
            }
            else {
                int num2 = (int)(Math.random()*divisor_range + 1);
                double num3 = (double)num1 / num2;

                if(num3 % 1 != 0)
                    hint = hint + "(" + num2 + " * " + String.format("%.2f",num3) + ")";
                else
                    hint = hint + "(" + num2 + " * " + num3 + ")";
            }

            if(Math.sqrt(answer) % 1 == 0){//checks if square root is a whole number
                hint = hint + " to the second power.";
            }


            return hint;
        }
        else if(difficulty == 5){//sin, cos, tan
            int way = (int)(Math.random()*3);
            String hint = "";
            if(way == 0){
                int num1 = (int)(Math.random()*divisor_range + 1);
                int num2 = answer * num1;
                hint = "Hint: Find the sine of a triangle with adjacent side " + num2 + " and hypotenuse " + num1 + ".";
            }
            else if(way == 1){
                int num1 = (int)(Math.random()*divisor_range + 1);
                int num2 = answer * num1;
                hint = "Hint: Find the cosine of a triangle with opposite side " + num2 + " and hypotenuse " + num1 + ".";
            }
            else {
                int num1 = (int)(Math.random()*divisor_range + 1);
                int num2 = answer * num1;
                hint = "Hint: Find the tangent of a triangle with adjacent side " + num2 + " and opposite side " + num1 + ".";
            }
            return hint;
        }
        else if(difficulty == 6){//log, ln.
            int log_base_range = 9;
            int way = (int)(Math.random()*2);
            String hint = "";
            if(way == 0){
                int num1 = (int)(Math.random()*log_base_range + 2);
                int num2 = (int)Math.pow(num1,answer);
                hint = "Hint: log base " + num1 + " of " + num2;
            }
            else{
                hint = "Hint: ln of e to the power of " + answer;
            }
            return hint;
        }

        return "";
    }
}