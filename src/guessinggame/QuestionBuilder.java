package guessinggame;

import java.util.Map;
import java.util.Random;

// Generates different types of questions
public class QuestionBuilder {
    private QuestionBuilder() {}

    public static int generateRandomInteger(int lowerBound, int upperBound) {
        Random randomIntGenerator = new Random();
        int returnedInteger = 0;

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
        int answer = quotient;

        return new Object[]{text, answer};
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
}
