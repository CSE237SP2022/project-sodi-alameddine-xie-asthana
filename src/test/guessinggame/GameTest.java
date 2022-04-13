package guessinggame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game test;

    @BeforeEach
    void setup() {
        Player player = new Player("Test");
        test = new Game(0, player);
    }

    @Test
    void testDifficulty0() {
        //Difficulty should be the same as when generated

        int difficulty = this.test.difficulty;

        assertEquals(0, difficulty);
    }

    @Test
    void testCheckGuess1() {
        // If the guess is correct, checkGuess should return true.

        assertEquals(true, test.checkGuess(10,10));
    }

    @Test
    void testCheckGuess2() {
        // If the guess is incorrect, checkGuess should return false.

        assertEquals(false, test.checkGuess(10,12));
    }

    @Test
    void testCalculateTimeScore1() {
        // If it is guessed within ten seconds, the full time score should be awarded.

        assertEquals(100F, test.calculateTimeScore(9), 0.001);
    }

    @Test
    void testCalculateTimeScore2() {
        // If it is guessed past 120 seconds, half of the time score should be awarded.

        assertEquals(50F, test.calculateTimeScore(120), 0.001);
    }

    @Test
    void testCalculateTimeScore3() {
        // If it is guessed past 10 seconds and before 120, a lesser time score should be awarded.

        assertEquals(97.261F, test.calculateTimeScore(15), 0.001);
    }

    @Test
    void testCalculateAttemptsScore1() {
        // If done in one attempt, the full attempt score should be awarded.

        assertEquals(100F, test.calculateAttemptsScore(1), 0.001);
    }

    @Test
    void testCalculateAttemptsScore2() {
        // If done in more than 1 attempt, a lesser attempt score should be awarded.

        assertEquals(62.5F, test.calculateAttemptsScore(2), 0.001);
    }
}