package guessinggame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Map.entry;

public class Game {
    // The difficulty parameter sets the difficulty of the game
    public int difficulty;

    // A map containing the score multiplier for each difficulty
    // Static because each instance of game doesn't need its own difficulty map
    private static final Map<Integer, Number> DIFFICULTY_MULTIPLIERS = Map.ofEntries(
            entry(1, 0.5F),
            entry(2, 1.25F),
            entry(3, 3.25F),
            entry(4, 10F),
            entry(5, 25F),
            entry(6, 100F)
    );

    // A map containing a name for each difficulty
    // Static because each instance of game doesn't need its own name map
    static final Map<Integer, String> DIFFICULTY_NAMES = Map.ofEntries(
            entry(1, "TRIVIAL"),
            entry(2, "EASY"),
            entry(3, "MEDIUM"),
            entry(4, "HARD"),
            entry(5, "EXPERT"),
            entry(6, "NIGHTMARE")
    );


    // The integer parameter score is the user's rounded score for the game
    public int score;

    // The user playing this game
    private Player player;

    //The number of questions constant sets the number of questions in the game
    public final int numberOfQuestions = Configuration.Hyperparameters.NUMBER_OF_QUESTIONS;

    //questions is the list of questions that the game will ask
    public List<Question> questions = new ArrayList<Question>(numberOfQuestions);

    //timeTaken and attemptsTaken are lists that contain the time taken and attempts taken for each question
    private List<Float> timeTaken = new ArrayList<>(numberOfQuestions);
    private List<Integer> attemptsTaken = new ArrayList<>(numberOfQuestions);

    //questionsCorrect is an array of booleans which keeps track of which questions the user answers correctly.
    //If a question is answered correctly, its corresponding index in this array is updated to true
    private boolean[] questionsCorrect = new boolean[numberOfQuestions];

    //currentAttempts is an integer which stores the attempts taken for the current question
    private int currentAttempt;

    //currentQuestion is an integer which stores the question the player is currently on
    public int currentQuestion;

    private Scanner answerScanner;

    //Constructor for the game class.
    public Game (int gameDifficulty, Player gamePlayer) {
        score = 0;
        currentAttempt = 1;
        currentQuestion = 0;
        difficulty = gameDifficulty;
        player = gamePlayer;
    }

    // Function to pretty print the list of difficulties
    // Method is static as there is no reason for every game object to use this method; it's a method of the game class itself
    static void prettyPrintDifficulties() {
        Map<Integer, String> sortedDifficultyNames = new TreeMap<>(DIFFICULTY_NAMES);
        for (Map.Entry<Integer, String> difficultyLevel: sortedDifficultyNames.entrySet()) {
            System.out.println(difficultyLevel.getKey() + ". " + difficultyLevel.getValue());
        }
    }

    /**
     *
     * @param guess the player's guess (an integer)
     * @param answer the correct answer (an integer)
     * @return false if the guess was incorrect or true if the guess was correct
     */
     boolean checkGuess(int guess, int answer) {
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

    public void play() {
        answerScanner = new Scanner(System.in);
        System.out.println("You have chosen " + DIFFICULTY_NAMES.get(difficulty) + " difficulty. Good Luck!");
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
            }

        }

        generateScore();
        System.out.println("Congratulations, you finished the game!");
        System.out.println("Your score is: " + score);
        generateLeaderboard();
    }

    private String generateLeaderboardString(Path leaderboardPath) {
        try {
            return Files.readString(leaderboardPath);
        } catch (IOException ignored) {
            System.out.println("Couldn't read leaderboard file - please make sure this program has write" +
                    " and read permissions in order to use the leaderboard feature");
            System.exit(Configuration.ExitCodes.GAME_COMPLETE_WITHOUT_LEADERBOARD);
            return "";
        }
    }

    private void printLeaderboard(String[] currentEntries) {
        //Make a pretty, sorted table in the future
        System.out.format("%20s%20s%20s%n", "Name", "Score", "Difficulty");
        System.out.println();

        for (String entry : currentEntries) {
            String[] individualValues = entry.split(" ");
            if (individualValues.length == 3) {
                System.out.format("%20s%20s%20s%n", individualValues[0], individualValues[1], individualValues[2]);
            }
        }
    }

    public void generateLeaderboard(){
        // The reason for catching errors and ignoring them is that there are scenarios where permissions don't exist to create the file
        // If the leaderboard file cannot be created, the rest of the program will still work as intended, so we can just alert the user that leaderboard could not be created
        try {
            File leaderboardFile = new File("src/guessinggame/scores.txt");
            leaderboardFile.createNewFile();

            String leaderboardEntry = player.name + " " + score + " " + DIFFICULTY_NAMES.get(difficulty) + "\n";
            Path leaderboardPath = Path.of("src/guessinggame/scores.txt");
            Files.writeString(leaderboardPath, leaderboardEntry, CREATE, APPEND);

            String leaderboardString = generateLeaderboardString(leaderboardPath);
            System.out.println("\nLeaderboard:");

            String[] currentEntries = leaderboardString.split("\n");  // will be used for pretty table
            printLeaderboard(currentEntries);

        } catch(IOException ignored) {
            System.out.println("Couldn't generate leaderboard file. Please make sure this program has write and read" +
                    " permissions in order to use the leaderboard feature");
            System.exit(Configuration.ExitCodes.GAME_COMPLETE_WITHOUT_LEADERBOARD);
        }
    }

     public void generateQuestions() {
        for (int i = 0; i < numberOfQuestions; ++i) {
            Question question = new Question(difficulty);
            questions.add(question);
        }
    }

    /**
     *
     * @param question question that will be asked, which is an object array containing the String of the question (in position 0) and integer answer (in position 1)
     * @return an integer array containing the user's answer in position 0 and whether to move on to the next question in position 1 (0 = don't continue, 1 = continue)
     */
     private int[] askQuestion(Question question) {
        long timeBeforeQuestion = System.nanoTime();
        System.out.println("Question " + (currentQuestion+1) + ": " + question.text);
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
                    skipQuestion();
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

    private void skipQuestion() {
        System.out.println("Question " + (currentQuestion+1) + " skipped\n");
        ++currentQuestion;
        currentAttempt = 1;
        timeTaken.add(0F);
        attemptsTaken.add(0);
    }

    public void generateScore() {
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

    public float calculateTimeScore(float seconds) {
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

    public float calculateAttemptsScore(int attempts) {
        return 25 + 75F/attempts;
    }
}