package guessinggame;

import java.util.Map;
import java.util.Random;

// Generates different types of questions
public class QuestionBuilder {
    private QuestionBuilder() {}

    // All methods of this class are static as a QuestionBuilder object need not be instantiated
    // QuestionBuilder serves as an organization class

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

    public static Object[] generateFirstPemdasTerm(Map<String, Object> parameters, int termZeroOperator) {
        int[] subterms = new int[3];
        String textTerm = "";

        int MultiplicationRange = (int) parameters.get("MultiplicationRange");
        int DivisionRange = (int) parameters.get("DivisionRange");

        // Term 0 Multiplication
        if (termZeroOperator == 1) {
            subterms[0] = generateRandomInteger(-MultiplicationRange, MultiplicationRange);  // Num1
            subterms[1] = generateRandomInteger(-MultiplicationRange, MultiplicationRange);  // Num2
            subterms[2] = subterms[0] * subterms[1];  // Answer

            textTerm = " • ";
        }

        // Term 0 Division
        if (termZeroOperator == 2) {
            subterms[1] = generateRandomInteger(-DivisionRange, DivisionRange);  // Num2
            subterms[2] = generateRandomInteger(-DivisionRange, DivisionRange);  // Answer
            subterms[0] = subterms[1] * subterms[2];  // Num1

            textTerm = " ÷ ";
        }

        return new Object[]{subterms, textTerm};
    }

    public static int[] generateSecondPemdasTerm(Map<String, Object> parameters) {
        int baseRange = (int) parameters.get("ExponentBaseRange");
        int powerRange = (int) parameters.get("ExponentPowerRange");

        int base = generateRandomInteger(-baseRange, baseRange);
        int power = generateRandomInteger(2, powerRange);

        return new int[]{base, power};
    }

    public static Object[] generatePemdasCalculationResult(int intermediateOperator, int[][] terms) {
        String textTerm = "";

        // Intermediate Operator Addition
        if(intermediateOperator == 1) {
            textTerm = " + ";
            terms[1][2] = terms[1][0] + terms[1][1];
        }

        // Intermediate Operator Subtraction
        if(intermediateOperator == 2) {
            textTerm = " - ";
            terms[1][2] = terms[1][0] - terms[1][1];
        }

        return new Object[]{terms, textTerm};
    }

    public static Object[] generatePemdasTextAndAnswer(int[][] terms, String[] textTerms, int base, int power) {
        String text = "What is ((" + terms[0][0] + ")" + textTerms[0] + "(" + terms[0][1] + "))"
                +textTerms[1] + "((" + base + ")^" +power + ")?";
        int answer = terms[1][2];

        return new Object[]{text, answer};
    }

    public static Object[] generateAndCombinePemdasTerms(Map<String, Object> parameters, int[][] terms, String[] textTerms) {
        int termZeroOperator = generateRandomInteger(1, 2);
        int intermediateOperator = generateRandomInteger(1, 2);

        // Term 0 (first term)
        Object[] term0 = generateFirstPemdasTerm(parameters, termZeroOperator);
        terms[0] = (int[]) term0[0];
        textTerms[0] = (String) term0[1];

        // Term 1 (second term)
        int[] term1 = generateSecondPemdasTerm(parameters);
        int base = term1[0];
        int power = term1[1];
        terms[1][0] = terms[0][2];
        terms[1][1] = (int) Math.pow(base, power);

        // Combine the two terms
        Object[] results = generatePemdasCalculationResult(intermediateOperator, terms);
        terms = (int[][]) results[0];
        textTerms[1] = (String) results[1];

        return new Object[]{terms, textTerms, base, power};
    }

    // Generates a question with the following format:
    // (term1 */ term2) +- (term3 ^ term4)
    // where */ means multiplication or division and +- means addition or subtraction
    public static Object[] generatePemdas(Map<String, Object> parameters) {

        //The first subarray stores values from the first parenthetical.
        // terms[0][0] and terms[0][1] are the two random numbers from the first paranthetical, and terms[0][2] is the result of evaluating num1*num2 or num1/num2
        // terms[1][0] is the answer to the first paranthetical (so the same value as terms[0][2]), terms[1][1] is the answer to the second paranthetical
        int[][] terms = new int[][] {
                new int[3], /*Num1, Num2, Answer*/
                new int[3], /*Num1, Num2, Answer*/
        };

        String[] textTerms = new String[2];

        Object[] pemdasQuestionComponents = generateAndCombinePemdasTerms(parameters, terms, textTerms);
        terms = (int[][]) pemdasQuestionComponents[0];
        textTerms = (String[]) pemdasQuestionComponents[1];
        int base = (int) pemdasQuestionComponents[2];
        int power = (int) pemdasQuestionComponents[3];


        return generatePemdasTextAndAnswer(terms, textTerms, base, power);
    }


    //difficulty 1 generates two random positive integers and asks for their sum
    public static Object[] generateTrivial(Map<String, Object> parameters) {
        return QuestionBuilder.generateAddition(parameters);
    }

    //difficulty 2 introduces subtraction and negative integers
    public static Object[] generateEasy(Map<String, Object> parameters) {
        // We randomly choose to ask a question with addition or subtraction
        int operator = QuestionBuilder.generateRandomInteger(1, 2);
        Object[] question = new Object[]{"", 0};

        //Addition
        if(operator == 1) {
            question = QuestionBuilder.generateAddition(parameters);
        }

        //Subtraction
        else if (operator == 2) {
            question = QuestionBuilder.generateSubtraction(parameters);
        }

        return question;
    }

    //difficulty 3 generates two random integers and asks for their sum, difference or product
    public static Object[] generateMedium(Map<String, Object> parameters) {
        int operator = QuestionBuilder.generateRandomInteger(1, 3);
        Object[] question = new Object[]{"", 0};

        // Addition
        if (operator == 1) {
            question = QuestionBuilder.generateAddition(parameters);
        }

        // Subtraction
        if (operator == 2) {
            question = QuestionBuilder.generateSubtraction(parameters);
        }

        // Multiplication
        if (operator == 3) {
            question = QuestionBuilder.generateMultiplication(parameters);
        }

        return question;
    }

    // difficulty 4 generates two random integers and asks for their difference, product or quotient
    public static Object[] generateHard(Map<String, Object> parameters) {
        int operator = QuestionBuilder.generateRandomInteger(1, 3);
        Object[] question = new Object[]{"", 0};

        // Subtraction
        if (operator == 1) {
            question = QuestionBuilder.generateSubtraction(parameters);
        }

        // Multiplication
        if (operator == 2) {
            question = QuestionBuilder.generateMultiplication(parameters);
        }

        // Division
        if (operator == 3) {
            question = QuestionBuilder.generateDivision(parameters);
        }


        return question;
    }

    // difficulty 5 generates two random integers and asks for their difference, product, quotient, or power

    public static Object[] generateExpert(Map<String, Object> parameters) {
        int operator = QuestionBuilder.generateRandomInteger(1, 4);
        Object[] question = new Object[]{"", 0};

        switch(operator) {
            // Subtraction
            case 1:
                question = QuestionBuilder.generateSubtraction(parameters);
                break;

            // Multiplication
            case 2:
                question = QuestionBuilder.generateMultiplication(parameters);
                break;

            // Division
            case 3:
                question = QuestionBuilder.generateDivision(parameters);
                break;

            // Exponentiation
            case 4:
                question = QuestionBuilder.generateExponent(parameters);
                break;
        }


        return question;
    }

    // Difficulty 6 (nightmare) generates a question with the following format:
    // (term1 */ term2) +- (term3 ^ term4)
    // where */ means multiplication or division and +- means addition or subtraction
    public static Object[] generateNightmare(Map<String, Object> parameters) {
        return QuestionBuilder.generatePemdas(parameters);
    }
}
