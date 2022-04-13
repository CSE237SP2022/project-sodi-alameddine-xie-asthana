import java.util.Map;
import java.util.Random;

import static java.util.Map.entry;

public class Question {
    public String text;
    public int answer;

    // difficultyParameters is a map that contains the relevant parameters for each difficulty.
    // This way there are no magic numbers and it's easier to modify values for balance
    // Each difficulty (integer) is mapped to another map which contains the names and values of the parameters for that difficulty
    private final Map<Integer, Map<String, Object>> DIFFICULTY_PARAMETERS = Map.ofEntries(
            entry(0, Map.of("Range", 100)),
            entry(1, Map.of("Range", 200)),
            entry(2, Map.of("AdditionRange", 500, "SubtractionRange", 500, "MultiplicationRange", 25))
    );


    public Question(int difficulty) {
        text = (String) generateQuestion(difficulty)[0];
        answer = (int) generateQuestion(difficulty)[1];
    }


    /**
     * Returns an array containing a random question in slot 0 and its answer in slot 1
     * The integer argument difficulty must be provided to generate a question
     * @param difficulty an integer that specifies the difficulty of the question
     * @return an array containing a random question in slot 0 and its answer in slot 1
     */
    private Object[] generateQuestion(int difficulty) {
        // Initialize the question text and answer, generateQuestion is what we will return
        text = "";
        answer = 0;

        // parameters is a map of the name and value of the parameters for each difficulty
        Map<String, Object> parameters = DIFFICULTY_PARAMETERS.get(difficulty);

        switch(difficulty) {
            //difficulty 0 generates two random positive integers - summand1 and summand2 - and asks for their sum
            case 0: {
                int range = (int) parameters.get("Range");
                int summand1 = generateRandomInteger(1, range), summand2 = generateRandomInteger(1, range);
                text = "What is " + summand1 + " + " + summand2 + "?";
                answer = summand1 + summand2;
                break;
            }

            //difficulty 1 introduces subtraction and negative integers
            case 1: {
                // We randomly choose to ask a question with addition or subtraction
                int operator = generateRandomInteger(1, 2);
                int range = (int) parameters.get("Range");

                //Addition
                if(operator == 1) {
                    int summand1 = generateRandomInteger(-range, range);
                    int summand2 = generateRandomInteger(-range, range);
                    text = "What is " + summand1 + " + " + summand2 + "?";
                    answer = summand1 + summand2;
                }

                //Subtraction
                else if (operator == 2) {
                    int minuend = generateRandomInteger(-range, range);
                    int subtrahend = generateRandomInteger(-range, range);
                    text = "What is " + minuend + " - " + subtrahend + "?";
                    answer = minuend - subtrahend;
                }

                break;
            }

            //difficulty 2 generates two random integers and asks for their sum, difference or product
            case 2: {
                // We randomly choose to ask a question with addition, subtraction or multiplication
                int operator = generateRandomInteger(1, 3);

                // Addition
                if (operator == 1) {
                    int range = (int) parameters.get("AdditionRange");
                    int summand1 = generateRandomInteger(-range, range);
                    int summand2 = generateRandomInteger(-range, range);
                    text = "What is " + summand1 + " + " + summand2 + "?";
                    answer = summand1 + summand2;
                }

                // Subtraction
                if (operator == 2) {
                    int range = (int) parameters.get("SubtractionRange");
                    int minuend = generateRandomInteger(-range, range);
                    int subtrahend = generateRandomInteger(-range, range);
                    text = "What is " + minuend + " - " + subtrahend + "?";
                    answer = minuend - subtrahend;
                }

                // Multiplication
                if (operator == 3) {
                    int range = (int) parameters.get("MultiplicationRange");
                    int factor1 = generateRandomInteger(-range, range);
                    int factor2 = generateRandomInteger(-range, range);
                    text = "What is " + factor1 + " • " + factor2 + "?";
                    answer = factor1 * factor2;
                }
            }

                // difficulty 3 generates two random integers and asks for their sum, difference, product or quotient
                // difficulty 3 has much larger ranges, too

                // difficulty 4 generates 3 random integers and asks for some combination of them involving sum, difference, product or quotient

        }

        return new Object[]{text, answer};
    }

    public int generateRandomInteger(int lowerBound, int upperBound) {
        Random randomIntGenerator = new Random();
        int returnedInteger = 0;

        while(true) {
            returnedInteger = randomIntGenerator.nextInt(upperBound - lowerBound + 1) + lowerBound;
            if (returnedInteger != 0) {
                break;
            }
        }

        return returnedInteger;
    }
}
