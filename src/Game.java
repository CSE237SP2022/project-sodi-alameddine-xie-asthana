import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    //The difficulty parameter sets the difficulty of the game
    public int difficulty;

    // The integer parameter score is the user's rounded score for the game
    public int score = 0;

    //The number of questions constant sets the number of questions in the game
    public final int numberOfQuestions = Configuration.Hyperparameters.NUMBER_OF_QUESTIONS;

    //questions is the list of questions that the game will ask
    private List<Question> questions = new ArrayList<Question>(numberOfQuestions);

    //timeTaken and attemptsTaken are lists that contain the time taken and attempts taken for each question
    private List<Float> timeTaken = new ArrayList<>(numberOfQuestions);
    private List<Integer> attemptsTaken = new ArrayList<>(numberOfQuestions);

    //questionsCorrect is an array of booleans which keeps track of which questions the user answers correctly.
    //If a question is answered correctly, its corresponding index in this array is updated to true
    private boolean[] questionsCorrect = new boolean[numberOfQuestions];

    //currentAttempts is an integer which stores the attempts taken for the current question
    private int currentAttempt;

    //currentQuestion is an integer which stores the question the player is currently on
    private int currentQuestion;

    //Constructor for the game class.
    public Game (int gameDifficulty) {
        currentAttempt = 1;
        currentQuestion = 0;
        difficulty = gameDifficulty;
    }

    /**
     *
     * @param guess the player's guess (an integer)
     * @param answer the correct answer (an integer)
     * @return false if the guess was incorrect or true if the guess was correct
     */
    boolean checkGuess(int guess, int answer) {
        ++currentAttempt;
        if (guess == answer){
            System.out.println("Congratulations! That's correct!");
            currentAttempt = 1;
            questionsCorrect[currentQuestion] = true;
            ++currentQuestion;
            return true;
        }

        System.out.println("Incorrect guess. You are currently on attempt " + currentAttempt);
        return false;
    }

    //checkDistance looks at the guess made by the user. If it is closer to the answer than the previous guess, "Warmer." is output. If it's further, "Colder." is output.
    //If the same thing is guessed twice, "You guessed the same thing twice!" is output.
    String checkDistance(int guess, int lastGuess, int answer) {
        if (Math.abs(guess - answer) < Math.abs(lastGuess - answer)) {
            return "Warmer.";
        } else if (Math.abs(guess - answer) > Math.abs(lastGuess - answer)) {
            return "Colder.";
        } else {
            return "You guessed the same thing twice!";
        }
    }

    //generateHint will create a hint to give the player. Very basic as of now for proof of concept. Difficulty functionality will be added later.
    String generateHint(int answer) {
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

    void play() {
        System.out.println("Welcome to Guessing Game! Good Luck!");
        System.out.println("Enter \"skip\" to skip any question or \"quit\" to end the game");
        System.out.println("Please round all answers to the nearest integer. Only integer answers will be accepted.\n");
        generateQuestions();


        for (Question question : questions) {
            // nextQuestion stores whether we should proceed to the next question after the function call terminates
            // 0 = stay on current question, 1 = go to next question
            int nextQuestion = 0;

            while (nextQuestion == 0) {
                int[] playerAnswer = askQuestion(question);
                nextQuestion = playerAnswer[1];
                int guess = playerAnswer[0];
            }

        }

        generateScore();
        System.out.println("Congratulations, you finished the game! Score: " + score);
    }

    void generateQuestions() {
        for (int i = 0; i < numberOfQuestions; ++i) {
            Question question = new Question(difficulty);
            questions.add(question);
        }
    }

    /**
     *
     * @param question
     * @return
     */
    int[] askQuestion(Question question) {
        System.out.println("Question " + (currentQuestion+1) + ": " + question.text);
        Scanner answerScanner = new Scanner(System.in);

        int guess = 0;

        // nextQuestion stores whether we should proceed to the next question after the function call terminates
        // 0 = stay on current question, 1 = go to next question
        int nextQuestion = 0;
        while(answerScanner.hasNext()) {
            if(answerScanner.hasNextInt()) {
                guess = answerScanner.nextInt();
                if(checkGuess(guess, question.answer)) {
                    nextQuestion = 1;
                    break;
                }
            } else {
                String stringGuess = answerScanner.next();
                if (stringGuess.equals("skip")) {
                    System.out.println("Question " + (currentQuestion+1) + " skipped");
                    ++currentQuestion;
                    currentAttempt = 0;
                    nextQuestion = 1;
                    break;
                }
                else if (stringGuess.equals("quit")) {
                    generateScore();
                    System.out.println("Game Over. Final score: " + score);
                    System.exit(Configuration.ExitCodes.PLAYER_QUIT_GAME);

                }
                else {
                    System.out.println("Please enter an integer. Only integer answers are accepted.");
                }
            }

        }


        return new int[]{guess, nextQuestion};
    }

    void generateScore() {
    }
}