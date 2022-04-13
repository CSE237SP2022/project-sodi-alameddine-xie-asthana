import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void testName() {
        //Difficulty should be the same as when generated

        Player player = new Player("Test");

        assertEquals("Test", player.name);
    }

}