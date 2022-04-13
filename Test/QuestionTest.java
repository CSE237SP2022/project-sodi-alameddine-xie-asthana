import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QuestionTest {

    @Test
    void testNumberGenerator() {
        //Difficulty should be the same as when generated

        Question q = new Question(1);
        int num = q.generateRandomInteger(1, 10);

        assertTrue((num >= 1 && num <= 10));
    }

}