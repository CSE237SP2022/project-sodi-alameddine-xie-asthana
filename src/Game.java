import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.*;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Map.entry;

public class Game {
    //The difficulty parameter sets the difficulty of the game
    public int difficulty;

    //A map containing the score multiplier for each difficulty
    private final Map<Integer, Number> DIFFICULTY_MULTIPLIERS = Map.ofEntries(
            entry(0, 0.5F),
            entry(1, 1.25F),
            entry(2, 3.25F),
            entry(3, 10F),
            entry(4, 25F)
    );

    // The integer parameter score is the user's rounded score for the game
    public int score;

    // The user playing this game
    Player player;

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
    public Game (int gameDifficulty, Player gamePlayer) {
        score = 0;
        currentAttempt = 1;
        currentQuestion = 0;
        difficulty = gameDifficulty;
        player = gamePlayer;
    }

    /**
     *
     * @param guess the player's guess (an integer)
     * @param answer the correct answer (an integer)
     * @return false if the guess was incorrect or true if the guess was correct
     */
    private boolean checkGuess(int guess, int answer) {
        if (guess == answer){
            System.out.println("That's correct!");
            questionsCorrect[currentQuestion] = true;
            attemptsTaken.add(currentAttempt);
            currentAttempt = 1;
            ++currentQuestion;
            return true;
        }
        ++currentAttempt;

        System.out.println("Incorrect guess. You are currently on attempt " + currentAttempt);
        return false;
    }

    //checkDistance looks at the guess made by the user. If it is closer to the answer than the previous guess, "Warmer." is output. If it's further, "Colder." is output.
    //If the same thing is guessed twice, "You guessed the same thing twice!" is output.
    private String checkDistance(int guess, int lastGuess, int answer) {
        if (Math.abs(guess - answer) < Math.abs(lastGuess - answer)) {
            return "Warmer.";
        } else if (Math.abs(guess - answer) > Math.abs(lastGuess - answer)) {
            return "Colder.";
        } else {
            return "You guessed the same thing twice!";
        }
    }

    //generateHint will create a hint to give the player. Very basic as of now for proof of concept. Difficulty functionality will be added later.
    private String generateHint(int answer) {
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

    public void play() {
        System.out.println("You have chosen difficulty " + difficulty + ". Good Luck!");
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
        System.out.println("Congratulations, you finished the game!");
        System.out.println("Your score is: " + score);

        String leaderboardEntry = player.name + " " + score + " " + difficulty + "\n";
        Path leaderboardFile = Path.of("src/scores.txt");
        try {
            Files.writeString(leaderboardFile, leaderboardEntry, CREATE, APPEND);
        } catch(IOException error) {
            System.out.println(error);
        }

        String leaderboardString = "";

        try {
            leaderboardString = Files.readString(leaderboardFile);
        } catch(IOException error) {
            System.out.println(error);
        }
        System.out.println("\nLeaderboard:");

        String[] currentEntries = leaderboardString.split("\n");  // will be used for pretty table

        //Make a pretty, sorted table in the future
        System.out.format("%20s%20s%20s%n", "Name", "Score", "Difficulty");
        System.out.println();

        for (String entry: currentEntries) {
            String[] individualValues = entry.split(" ");
            System.out.format("%20s%20s%15s%n", individualValues[0], individualValues[1], individualValues[2]);

        }


        System.exit(Configuration.ExitCodes.GAME_COMPLETE);
    }

    private void generateQuestions() {
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
    private int[] askQuestion(Question question) {
        long timeBeforeQuestion = System.nanoTime();
        System.out.println("Question " + (currentQuestion+1) + ": " + question.text);
        Scanner answerScanner = new Scanner(System.in);

        int guess = 0;

        // nextQuestion stores whether we should proceed to the next question after the function call terminates
        // 0 = stay on current question, 1 = go to next question
        int nextQuestion = 0;
        while(answerScanner.hasNext()) {
            if(answerScanner.hasNextInt()) {
                guess = answerScanner.nextInt();
                long timeAfterQuestion = System.nanoTime();
                if(checkGuess(guess, question.answer)) {
                    nextQuestion = 1;
                    float secondsTimeDifference = (float) ((timeAfterQuestion-timeBeforeQuestion)/Math.pow(10, 9));
                    timeTaken.add(secondsTimeDifference);
                    break;
                }
            } else {
                String stringGuess = answerScanner.next();
                if (stringGuess.equals("skip")) {
                    System.out.println("Question " + (currentQuestion+1) + " skipped\n");
                    ++currentQuestion;
                    currentAttempt = 1;
                    timeTaken.add(0F);
                    attemptsTaken.add(0);
                    nextQuestion = 1;
                    break;
                }
                else if (stringGuess.equals("quit")) {
                    generateScore();
                    System.out.println("You quit! Game Over");
                    System.exit(Configuration.ExitCodes.PLAYER_QUIT_GAME);

                }
                else {
                    System.out.println("Please enter an integer. Only integer answers are accepted.");
                }
            }

        }


        return new int[]{guess, nextQuestion};
    }

    private void generateScore() {
        float totalScore = 0;
        float difficultyMultiplier = (float) DIFFICULTY_MULTIPLIERS.get(difficulty);
        for(int i = 0; i < numberOfQuestions; ++i) {
            if(questionsCorrect[i]) {
                float questionScore = calculateAttemptsScore(attemptsTaken.get(i))
                        * calculateTimeScore(timeTaken.get(i));
                totalScore += questionScore;
            }
        }

        score = (int) (totalScore * difficultyMultiplier);
    }

    private float calculateTimeScore(float seconds) {
        //UNIT TEST SECONDS POSITIVE
        if(seconds <= 10) {
            return 100F;
        }
        else if (seconds > 10 && seconds < 120) {
            return (float) (100 + Math.pow(10, 5F/6) - Math.pow(seconds, 5F/6));
        }
        else {
            return 50F;
        }
    }

    private float calculateAttemptsScore(int attempts) {
        return 25 + 75F/attempts;
    }
}