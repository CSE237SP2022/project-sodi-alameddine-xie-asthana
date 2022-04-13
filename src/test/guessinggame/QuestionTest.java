package guessinggame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void testNumberGenerator() {

        Question q = new Question(1);
        int num = q.generateRandomInteger(1, 10);

        assertTrue((num >= 1 && num <= 10));
    }

}