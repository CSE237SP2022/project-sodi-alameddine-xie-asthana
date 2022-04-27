package guessinggame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private Question test;

    @Test
    void testGenerateQuestionDiffOne() {
        //generateDifficulty will return and array with the text of the question at 0 and the int answer at 1
        //Difficulty 1 can only have addition-based questions, so that's all we check for

        test = new Question(1);
        Object[] result = test.generateQuestion(1);
        String text = (String)result[0];
        assertTrue(text.contains("+"));
        assertEquals(2,result.length);
    }

    @Test
    void testGenerateQuestionDiffTwo() {
        //generateDifficulty will return and array with the text of the question at 0 and the int answer at 1
        //Difficulty 2 can have addition- and subtraction-based questions, so that's what we check for

        test = new Question(2);
        Object[] result = test.generateQuestion(2);
        String text = (String)result[0];
        assertTrue(text.contains("+") || text.contains("-"));
        assertEquals(2,result.length);
    }

    @Test
    void testGenerateQuestionDiffThree() {
        //generateDifficulty will return and array with the text of the question at 0 and the int answer at 1
        //Difficulty 3 can have addition-, subtraction- and multiplication-based questions, so that's what we check for

        test = new Question(3);
        Object[] result = test.generateQuestion(3);
        String text = (String)result[0];
        assertTrue(text.contains("+") || text.contains("-") || text.contains("•"));
        assertEquals(2,result.length);
    }

    @Test
    void testGenerateQuestionDiffFour() {
        //generateDifficulty will return and array with the text of the question at 0 and the int answer at 1
        //Difficulty 4 can have division-, subtraction- and multiplication-based questions, so that's what we check for

        test = new Question(4);
        Object[] result = test.generateQuestion(4);
        String text = (String)result[0];
        assertTrue(text.contains("÷") || text.contains("-") || text.contains("•"));
        assertEquals(2,result.length);
    }

    @Test
    void testGenerateQuestionDiffFive() {
        //generateDifficulty will return and array with the text of the question at 0 and the int answer at 1
        //Difficulty 5 can have subtraction-, division-, exponent- and multiplication-based questions, so that's what we check for

        test = new Question(5);
        Object[] result = test.generateQuestion(5);
        String text = (String)result[0];
        assertTrue(text.contains("÷") || text.contains("-") || text.contains("•") || text.contains("^"));
        assertEquals(2,result.length);
    }

    @Test
    void testGenerateQuestionDiffSix() {
        //generateDifficulty will return and array with the text of the question at 0 and the int answer at 1
        //Difficulty 6 will have subtraction/addition, division/multiplication, and an exponent in the questions, so that's what we check for

        test = new Question(6);
        Object[] result = test.generateQuestion(6);
        String text = (String)result[0];
        assertTrue(((text.contains("÷") || text.contains("•")) && (text.contains("-") || text.contains("+")) && text.contains("^")));
        assertEquals(2,result.length);
    }

    @Test
    void testGenerateQuestionDiffInvalid() {
        //generateDifficulty will return and array with the text of the question at 0 and the int answer at 1
        //If an invalid difficulty is somehow input, the user is simply prompted by 1+1. The answer should be 2.

        test = new Question(8);
        Object[] result = test.generateQuestion(8);
        String text = (String)result[0];
        int ans = (int)result[1];
        assertTrue(text.contains("1+1"));
        assertEquals(2,result.length);
        assertEquals(2, ans);
    }

}