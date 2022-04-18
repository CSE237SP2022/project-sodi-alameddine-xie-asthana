package guessinggame;

import java.util.ArrayList;
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
            entry(0, Map.of("AdditionRange", 100, "LowerBound", 1)),
            entry(1, Map.of("AdditionRange", 250, "SubtractionRange", 200)),
            entry(2, Map.of("AdditionRange", 1000, "SubtractionRange", 800, "MultiplicationRange", 25)),
            entry(3, Map.of("SubtractionRange", 2750, "MultiplicationRange", 50, "DivisionRange", 20))
    );


    public Question(int difficulty) {
        Object[] question = generateQuestion(difficulty);
        text = (String) question[0];
        answer = (int) question[1];
    }

    public Object[] generateAddition(Map<String, Object> parameters) {
        int summand1 = 0, summand2 = 0;

        int range = (int) parameters.get("AdditionRange");
        if(parameters.containsKey("LowerBound")) {
            int lowerBound = (int) parameters.get("LowerBound");
            summand1 = generateRandomInteger(lowerBound, range);
            summand2 = generateRandomInteger(lowerBound, range);
        }
        else {
            summand1 = generateRandomInteger(-range, range);
            summand2 = generateRandomInteger(-range, range);
        }

        text = "What is " + summand1 + " + " + summand2 + "?";
        answer = summand1 + summand2;

        return new Object[]{text, answer};
    }

    public Object[] generateSubtraction(Map<String, Object> parameters) {
        int range = (int) parameters.get("SubtractionRange");
        int minuend = generateRandomInteger(-range, range);
        int subtrahend = generateRandomInteger(-range, range);
        text = "What is " + minuend + " - " + subtrahend + "?";
        answer = minuend - subtrahend;

        return new Object[]{text, answer};
    }

    public Object[] generateMultiplication(Map<String, Object> parameters) {
        int range = (int) parameters.get("MultiplicationRange");
        int factor1 = generateRandomInteger(-range, range);
        int factor2 = generateRandomInteger(-range, range);
        text = "What is " + factor1 + " • " + factor2 + "?";
        answer = factor1 * factor2;

        return new Object[]{text, answer};
    }

    public Object[] generateDivision(Map<String, Object> parameters) {
        int range = (int) parameters.get("DivisionRange");
        int quotient = generateRandomInteger(-range, range);
        int divisor = generateRandomInteger(-range, range);
        int dividend = divisor*quotient;
        text = "What is " + dividend + " ÷ " + divisor + "?";
        answer = quotient;

        return new Object[]{text, answer};
    }

    //difficulty 0 generates two random positive integers and asks for their sum
    public Object[] difficulty0(Map<String, Object> parameters) {
        return generateAddition(parameters);
    }

    //difficulty 1 introduces subtraction and negative integers
    public Object[] difficulty1(Map<String, Object> parameters) {
        // We randomly choose to ask a question with addition or subtraction
        int operator = generateRandomInteger(1, 2);
        Object[] question = new Object[]{"", 0};

        //Addition
        if(operator == 1) {
            question = generateAddition(parameters);
        }

        //Subtraction
        else if (operator == 2) {
            question = generateSubtraction(parameters);
        }

        return question;
    }

    //difficulty 2 generates two random integers and asks for their sum, difference or product
    public Object[] difficulty2(Map<String, Object> parameters) {
        int operator = generateRandomInteger(1, 3);
        Object[] question = new Object[]{"", 0};

        // Addition
        if (operator == 1) {
            question = generateAddition(parameters);
        }

        // Subtraction
        if (operator == 2) {
            question = generateSubtraction(parameters);
        }

        // Multiplication
        if (operator == 3) {
            question = generateMultiplication(parameters);
        }

        return question;
    }

    // difficulty 3 generates two random integers and asks for their difference, product or quotient
    public Object[] difficulty3(Map<String, Object> parameters) {
        int operator = generateRandomInteger(1, 3);
        Object[] question = new Object[]{"", 0};

        // Addition
        if (operator == 1) {
            question = generateSubtraction(parameters);
        }

        // Subtraction
        if (operator == 2) {
            question = generateMultiplication(parameters);
        }

        // Multiplication
        if (operator == 3) {
            question = generateDivision(parameters);
        }


        return question;
    }

    // difficulty 4 generates two random integers and asks for their sum, difference, product or quotient



    /**
     * Returns an array containing a random question in slot 0 and its answer in slot 1
     * The integer argument difficulty must be provided to generate a question
     * @param difficulty an integer that specifies the difficulty of the question
     * @return an array containing a random question in slot 0 and its answer in slot 1
     */
     public Object[] generateQuestion(int difficulty) {
        // parameters is a map of the name and value of the parameters for each difficulty
        Map<String, Object> parameters = DIFFICULTY_PARAMETERS.get(difficulty);

        switch(difficulty) {
            case 0:
                return difficulty0(parameters);
            case 1:
                return difficulty1(parameters);
            case 2:
                return difficulty2(parameters);
            case 3:
                return difficulty3(parameters);


                // difficulty 4 generates 3 random integers and asks for some combination of them involving sum, difference, product or quotient

        }

        return new Object[]{text, answer};
    }

     int generateRandomInteger(int lowerBound, int upperBound) {
        Random randomIntGenerator = new Random();
        int returnedInteger = 0;

         do {
             returnedInteger = randomIntGenerator.nextInt(upperBound - lowerBound + 1) + lowerBound;
         } while (returnedInteger == 0);

        return returnedInteger;
    }
}
