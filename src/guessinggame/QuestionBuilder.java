package guessinggame;

import java.util.Map;
import java.util.Random;

// Generates different types of questions
public class QuestionBuilder {
    private QuestionBuilder() {}

    public static int generateRandomInteger(int lowerBound, int upperBound) {
        Random randomIntGenerator = new Random();
        int returnedInteger = 1;

        do {
            returnedInteger = randomIntGenerator.nextInt(upperBound - lowerBound + 1) + lowerBound;
        } while (returnedInteger == 0);


        return returnedInteger;
    }

    public static Object[] generateAddition(Map<String, Object> parameters) {
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

        String text = "What is " + summand1 + " + " + summand2 + "?";
        int answer = summand1 + summand2;

        return new Object[]{text, answer};
    }

    public static Object[] generateSubtraction(Map<String, Object> parameters) {
        int range = (int) parameters.get("SubtractionRange");
        int minuend = generateRandomInteger(-range, range);
        int subtrahend = generateRandomInteger(-range, range);
        String text = "What is " + minuend + " - " + subtrahend + "?";
        int answer = minuend - subtrahend;

        return new Object[]{text, answer};
    }

    public static Object[] generateMultiplication(Map<String, Object> parameters) {
        int range = (int) parameters.get("MultiplicationRange");
        int factor1 = generateRandomInteger(-range, range);
        int factor2 = generateRandomInteger(-range, range);
        String text = "What is " + factor1 + " • " + factor2 + "?";
        int answer = factor1 * factor2;

        return new Object[]{text, answer};
    }

    public static Object[] generateDivision(Map<String, Object> parameters) {
        int range = (int) parameters.get("DivisionRange");
        int quotient = generateRandomInteger(-range, range);
        int divisor = generateRandomInteger(-range, range);
        int dividend = divisor*quotient;
        String text = "What is " + dividend + " ÷ " + divisor + "?";

        return new Object[]{text, quotient};
    }

    public static Object[] generateExponent(Map<String, Object> parameters) {
        int baseRange = (int) parameters.get("ExponentBaseRange");
        int powerRange = (int) parameters.get("ExponentPowerRange");
        int base = generateRandomInteger(-baseRange, baseRange);
        int power = generateRandomInteger(2, powerRange);
        String text = "What is (" + base + ")^" + power + "?";
        int answer = (int) Math.pow(base, power);

        return new Object[]{text, answer};
    }

    // Generates a question with the following format:
    // (term1 */ term2) +- (term3 ^ term4)
    // where */ means multiplication or division and +- means addition or subtraction
    public static Object[] generatePemdas(Map<String, Object> parameters) {

        int MultiplicationRange = (int) parameters.get("MultiplicationRange");
        int DivisionRange = (int) parameters.get("DivisionRange");
        int baseRange = (int) parameters.get("ExponentBaseRange");
        int powerRange = (int) parameters.get("ExponentPowerRange");

        int termZeroOperator = generateRandomInteger(1, 2);
        int intermediateOperator = generateRandomInteger(1, 2);

        //The first subarray stores values from the first parenthetical.
        // terms[0][0] and terms[0][1] are the two random numbers from the first paranthetical, and terms[0][2] is the result of evaluating num1*num2 or num1/num2
        // terms[1][0] is the answer to the first paranthetical (so the same value as terms[0][2]), terms[1][1] is the answer to the second paranthetical
        int[][] terms = new int[][] {
                new int[3], /*Num1, Num2, Answer*/
                new int[3], /*Num1, Num2, Answer*/
        };

        String[] textTerms = new String[2];

        // Term 0 Multiplication
        if (termZeroOperator == 1) {
            terms[0][0] = generateRandomInteger(-MultiplicationRange, MultiplicationRange);  // Num1
            terms[0][1] = generateRandomInteger(-MultiplicationRange, MultiplicationRange);  // Num2
            terms[0][2] = terms[0][0] * terms[0][1];  // Answer

            textTerms[0] = " • ";
        }

        // Term 0 Division
        if (termZeroOperator == 2) {
            terms[0][1] = generateRandomInteger(-DivisionRange, DivisionRange);  // Num2
            terms[0][2] = generateRandomInteger(-DivisionRange, DivisionRange);  // Answer
            terms[0][0] = terms[0][1] * terms[0][2];  // Num1

            textTerms[0] = " ÷ ";
        }

        // Term 1 (second term)
        int base = generateRandomInteger(-baseRange, baseRange);
        int power = generateRandomInteger(2, powerRange);

        terms[1][0] = terms[0][2];
        terms[1][1] = (int) Math.pow(base, power);

        // Intermediate Operator Addition
        if(intermediateOperator == 1) {
            textTerms[1] = " + ";
            terms[1][2] = terms[1][0] + terms[1][1];
        }

        // Intermediate Operator Subtraction
        if(intermediateOperator == 2) {
            textTerms[1] = " - ";
            terms[1][2] = terms[1][0] - terms[1][1];
        }


        String text = "What is ((" + terms[0][0] + ")" + textTerms[0] + "(" + terms[0][1] + "))"
                +textTerms[1] + "((" + base + ")^" +power + ")?";
        int answer = terms[1][2];
        return new Object[]{text, answer};
    }
}
