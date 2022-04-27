package guessinggame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game test;
    private Player player;
    //prettyPrintDifficulties just uses System.out.println and doesn't return anything, so it can't really be tested.
    //play() and askQuestion() require user input and can't be tested.

    @BeforeEach
    void setup() {
        player = new Player("Test");
        test = new Game(1, player);
    }

    @Test
    void testDifficulty() {
        //Difficulty should be the same as when generated

        int difficulty = this.test.difficulty;
        assertEquals(1, difficulty);
    }

    @Test
    void checkCorrectGuess() {
        // If the guess is correct, checkGuess should return true.

        assertEquals(true, test.checkGuess(10,10));
    }

    @Test
    void checkIncorrectGuess() {
        // If the guess is incorrect, checkGuess should return false.

        assertEquals(false, test.checkGuess(10,12));
    }

    @Test
    void calculateTimeScoreUnderTen() {
        // If it is guessed within ten seconds, the full time score should be awarded.

        assertEquals(100F, test.calculateTimeScore(9), 0.001);
    }

    @Test
    void calculateTimeScorePastTwoMinutes() {
        // If it is guessed past 120 seconds, half of the time score should be awarded.

        assertEquals(50F, test.calculateTimeScore(120), 0.001);
    }

    @Test
    void calculateTimeScorePastTen() {
        // If it is guessed past 10 seconds and before 120, a lesser time score should be awarded.

        assertEquals(97.261F, test.calculateTimeScore(15), 0.001);
    }

    @Test
    void calculateOneAttemptScore() {
        // If done in one attempt, the full attempt score should be awarded.

        assertEquals(100F, test.calculateAttemptsScore(1), 0.001);
    }

    @Test
    void calculateTwoAttemptsScore() {
        // If done in more than 1 attempt, a lesser attempt score should be awarded.

        assertEquals(62.5F, test.calculateAttemptsScore(2), 0.001);
    }

    @Test
    void generateZeroScore() {
        //If none of the questions have been answered yet, the score generated should be zero

        test.generateScore();
        assertEquals(0, test.score);
    }

    @Test
    void generateQuestionsDiffOne() {
        //test is a Game of difficulty 1, and 5 questions should be generated

        test.generateQuestions();
        assertEquals(5, test.questions.size());
    }

    @Test
    void generateQuestionsDiffTwo() {
        //test is a Game of difficulty 2, and 5 questions should be generated

        test = new Game(2, player);

        test.generateQuestions();
        assertEquals(5, test.questions.size());
    }

    @Test
    void generateQuestionsDiffThree() {
        //test is a Game of difficulty 3, and 5 questions should be generated

        test = new Game(3, player);

        test.generateQuestions();
        assertEquals(5, test.questions.size());
    }

    @Test
    void generateQuestionsDiffFour() {
        //test is a Game of difficulty 4, and 5 questions should be generated

        test = new Game(4, player);

        test.generateQuestions();
        assertEquals(5, test.questions.size());
    }

    @Test
    void generateQuestionsDiffFive() {
        //test is a Game of difficulty 5, and 5 questions should be generated

        test = new Game(5, player);

        test.generateQuestions();
        assertEquals(5, test.questions.size());
    }

    @Test
    void generateQuestionsDiffSix() {
        //test is a Game of difficulty 6, and 5 questions should be generated

        test = new Game(6, player);

        test.generateQuestions();
        assertEquals(5, test.questions.size());
    }

    
}