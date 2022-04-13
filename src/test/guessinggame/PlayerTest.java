package guessinggame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testName() {
        //Difficulty should be the same as when generated

        Player player = new Player("Test");

        assertEquals("Test", player.name);
    }

}