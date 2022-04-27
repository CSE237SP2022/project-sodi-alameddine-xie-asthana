package guessinggame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionBuilderTest {

    Question q = new Question(1);

    @Test
    void testGenerateRandomInteger() {
        //generateRandomInteger should create an integer within the given range.

        int num = QuestionBuilder.generateRandomInteger(1, 10);
        assertTrue((num >= 1 && num <= 10));
    }

    @Test
    void testGenerateAddition() {
        //generateAddition should return an Object with a String question and an int answer. the question should have addition

        Object[] test = QuestionBuilder.generateAddition(q.DIFFICULTY_PARAMETERS.get(1));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("+"));
    }

    @Test
    void testGenerateSubtraction() {
        //generateSubtraction should return an Object with a String question and an int answer.  the question should have subtraction

        Object[] test = QuestionBuilder.generateSubtraction(q.DIFFICULTY_PARAMETERS.get(2));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("-"));
    }

    @Test
    void testGenerateMultiplication() {
        //generateMultiplication should return an Object with a String question and an int answer. the question should have multiplication

        Object[] test = QuestionBuilder.generateMultiplication(q.DIFFICULTY_PARAMETERS.get(3));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("•"));
    }

    @Test
    void testGenerateDivision() {
        //generateDivision should return an Object with a String question and an int answer.  the question should have division

        Object[] test = QuestionBuilder.generateDivision(q.DIFFICULTY_PARAMETERS.get(4));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("÷"));
    }

    @Test
    void testGenerateExponent() {
        //generateExponent should return an Object with a String question and an int answer.  the question should have an exponent

        Object[] test = QuestionBuilder.generateExponent(q.DIFFICULTY_PARAMETERS.get(5));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("^"));
    }

    @Test
    void testGenerateFirstPemdasTermMult() {
        //generateFirstPemdasTerm should return an Object with an array of subterms for the question and the question text generated so far
        //termZeroOperator is 1, so it should use multiplication.

        Object[] test = QuestionBuilder.generateFirstPemdasTerm(q.DIFFICULTY_PARAMETERS.get(6), 1);
        assertEquals(2, test.length);
        String text = (String)test[1];
        assertTrue(text.contains("•"));
    }

    @Test
    void testGenerateFirstPemdasTermDiv() {
        //generateFirstPemdasTerm should return an Object with an array of subterms for the question and the question text generated so far
        //termZeroOperator is 2, so it should use division.

        Object[] test = QuestionBuilder.generateFirstPemdasTerm(q.DIFFICULTY_PARAMETERS.get(6), 2);
        assertEquals(2, test.length);
        String text = (String)test[1];
        assertTrue(text.contains("÷"));
    }

    @Test
    void testGenerateSecondPemdasTerm() {
        //generateSecondPemdasTerm should return an int[] with the base for the exponent at 0 and the power of the exponent at 1
        //Pemdas is at difficulty 6, so that's what goes in the getter of DIFFICULTY_PARAMETERS
        //the exponent should have a minimum power of 2

        int[] test = QuestionBuilder.generateSecondPemdasTerm(q.DIFFICULTY_PARAMETERS.get(6));
        assertEquals(2, test.length);
        assertTrue(test[1] >= 2);
    }

    @Test
    void testGeneratePemdasCalculationResultAdd() {
        //generatePemdasCalculationResult should return an int[][] with the equation terms and the text of the question
        //We're looking at the addition path, so intermediate operator is 1

        int[][] terms = new int [3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                terms[i][j] = 1;
            }
        }
        Object[] test = QuestionBuilder.generatePemdasCalculationResult(1, terms);
        assertEquals(2, test.length);

        String text = (String)test[1];
        assertTrue(text.contains("+"));
    }

    @Test
    void testGeneratePemdasCalculationResultSub() {
        //generatePemdasCalculationResult should return an int[][] with the equation terms and the text of the question
        //We're looking at the subtraction path, so intermediate operator is 2

        int[][] terms = new int [3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                terms[i][j] = 1;
            }
        }
        Object[] test = QuestionBuilder.generatePemdasCalculationResult(2, terms);
        assertEquals(2, test.length);

        String text = (String)test[1];
        assertTrue(text.contains("-"));
    }

    @Test
    void testGeneratePemdasTextAndAnswer() {
        //generatePemdasTextAndAnswer should return the full text of the question at 0 and the answer at 1
        //I will use 2 and 2 for the base and power respectively. Answer is terms[1][2], and so should be 1.
        //I will check the text to make sure textTerms made it into the returned string.

        int[][] terms = new int [3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                terms[i][j] = 1;
            }
        }
        String[] textTerms = {"+", "-"};
        Object[] test = QuestionBuilder.generatePemdasTextAndAnswer(terms, textTerms, 2, 2);
        assertEquals(2, test.length);

        String text = (String)test[0];
        assertTrue(text.contains(textTerms[0]));
        assertTrue(text.contains(textTerms[1]));

        int answer = (int)test[1];
        assertEquals(1, answer);
    }

    @Test
    void testGenerateAndCombinePemdasTerms() {
        //generateAndCombinePemdasTerms should return the terms used, the full text question, base of the exponent, and power of the exponent
        //I will use 2 and 2 for the base and power respectively.

        int[][] terms = new int [3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                terms[i][j] = 1;
            }
        }
        String[] textTerms = {"+", "-"};
        Object[] test = QuestionBuilder.generateAndCombinePemdasTerms(q.DIFFICULTY_PARAMETERS.get(6), terms, textTerms);
        assertEquals(4, test.length);
        String[] returnText = (String[])test[1];
        String text = (String)returnText[0] + (String)returnText[1];
        System.out.println(text);
        assertTrue(  (text.contains("•") || text.contains("÷") ) );
    }

    @Test
    void testGeneratePemdas() {
        //generatePemdas will call all other Pemdas methods to construct the question and answer.
        //It will return question text at 0 and the answer at 1.

        Object[] test = QuestionBuilder.generatePemdas(q.DIFFICULTY_PARAMETERS.get(6));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue( ( (text.contains("•") || text.contains("÷")) && (text.contains("-") || text.contains("+")) && text.contains("^") ) );
    }

    @Test
    void testGenerateTrivial() {
        //Trivial difficulty (or 1) should simply call generateAddition and return the results, the text question and the answer.

        Object[] test = QuestionBuilder.generateTrivial(q.DIFFICULTY_PARAMETERS.get(1));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("+"));
    }

    @Test
    void testGenerateEasy() {
        //Easy difficulty randomly chooses to ask a question with addition or subtraction. It returns the text question and the answer.

        Object[] test = QuestionBuilder.generateEasy(q.DIFFICULTY_PARAMETERS.get(2));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue((text.contains("+") || text.contains("-") ));
    }

    @Test
    void testGenerateMedium() {
        //Medium difficulty asks for sum, different or product (add, subtract or multiply).
        //It returns the text of the question and the answer.
        //The text of the question will include either a +, -, or •

        Object[] test = QuestionBuilder.generateMedium(q.DIFFICULTY_PARAMETERS.get(3));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue((text.contains("•") || text.contains("+") || text.contains("-") ));
    }

    @Test
    void testGenerateHard() {
        //generateHard can make a question with subtraction, multiplication or division.
        //it returns the text and the answer.

        Object[] test = QuestionBuilder.generateHard(q.DIFFICULTY_PARAMETERS.get(4));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue((text.contains("•") || text.contains("÷") || text.contains("-") ));
    }

    @Test
    void testGenerateExpert() {
        //generateExpert can make a question with subtraction, multiplication, division, or powers.
        //it returns the text and the answer.

        Object[] test = QuestionBuilder.generateExpert(q.DIFFICULTY_PARAMETERS.get(5));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue((text.contains("•") || text.contains("÷") || text.contains("-") || text.contains("^") ));
    }

    @Test
    void testGenerateNightmare() {
        //generateNightmare creates a question that implements PEMDAS with four terms.
        //it returns the text and the answer.

        Object[] test = QuestionBuilder.generateNightmare(q.DIFFICULTY_PARAMETERS.get(6));
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue( ( (text.contains("•") || text.contains("÷")) && (text.contains("-") || text.contains("+")) && text.contains("^") ) );
    }

    //generateDifficulty is used for difficulties 2-5. It returns the text of the generated question and the answer.
    //Each difficulty has a few options for what sort of problem is chosen that is randomly selected.
    @Test
    void testGenerateDifficultyTwoAdd() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(2),1, 2);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("+"));
    }

    @Test
    void testGenerateDifficultyTwoSub() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(2),2, 2);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("-"));
    }

    @Test
    void testGenerateDifficultyThreeAdd() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(3),1, 3);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("+"));
    }

    @Test
    void testGenerateDifficultyThreeSub() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(3),2, 3);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("-"));
    }

    @Test
    void testGenerateDifficultyThreeMult() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(3),3, 3);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("•"));
    }

    @Test
    void testGenerateDifficultyFourSub() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(4),1, 4);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("-"));
    }

    @Test
    void testGenerateDifficultyFourMult() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(4),2, 4);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("•"));
    }

    @Test
    void testGenerateDifficultyFourDiv() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(4),3, 4);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("÷"));
    }

    @Test
    void testGenerateDifficultyFiveSub() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(5),1, 5);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("-"));
    }

    @Test
    void testGenerateDifficultyFiveMult() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(5),2, 5);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("•"));
    }

    @Test
    void testGenerateDifficultyFiveDiv() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(5),3, 5);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("÷"));
    }

    @Test
    void testGenerateDifficultyFiveExp() {
        Object[] test = QuestionBuilder.generateDifficulty(q.DIFFICULTY_PARAMETERS.get(5),4, 5);
        assertEquals(2, test.length);
        String text = (String)test[0];
        assertTrue(text.contains("^"));
    }
}