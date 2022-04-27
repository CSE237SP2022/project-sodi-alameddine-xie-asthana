package guessinggame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testName() {
        //Username stored should be the same as what is input

        Player player = new Player("Test");
        assertEquals("Test", player.name);
    }

}