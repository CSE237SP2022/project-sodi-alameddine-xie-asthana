package guessinggame;

import java.util.Map;

import static java.util.Map.entry;

public class Question {
    public String text;
    public int answer;

    // difficultyParameters is a map that contains the relevant parameters for each difficulty.
    // This way there are no magic numbers and it's easier to modify values for balance
    // Each difficulty (integer) is mapped to another map which contains the names and values of the parameters for that difficulty
    private final Map<Integer, Map<String, Object>> DIFFICULTY_PARAMETERS = Map.ofEntries(
            entry(1, Map.of("AdditionRange", 100, "LowerBound", 1)),
            entry(2, Map.of("AdditionRange", 250, "SubtractionRange", 200)),
            entry(3, Map.of("AdditionRange", 1000, "SubtractionRange", 800, "MultiplicationRange", 25)),
            entry(4, Map.of("SubtractionRange", 2750, "MultiplicationRange", 50, "DivisionRange", 20)),
            entry(5, Map.of("SubtractionRange", 100000, "MultiplicationRange", 100,
                    "DivisionRange", 40, "ExponentBaseRange", 25, "ExponentPowerRange", 3)),
            entry(6, Map.of("MultiplicationRange", 250, "DivisionRange", 100,
                    "ExponentBaseRange", 30, "ExponentPowerRange", 4))
    );


    public Question(int difficulty) {
        Object[] question = generateQuestion(difficulty);
        text = (String) question[0];
        answer = (int) question[1];
    }



    //difficulty 1 generates two random positive integers and asks for their sum
    public Object[] difficulty1(Map<String, Object> parameters) {
        return QuestionBuilder.generateAddition(parameters);
    }

    //difficulty 2 introduces subtraction and negative integers
    public Object[] difficulty2(Map<String, Object> parameters) {
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
    public Object[] difficulty3(Map<String, Object> parameters) {
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
    public Object[] difficulty4(Map<String, Object> parameters) {
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

    public Object[] difficulty5(Map<String, Object> parameters) {
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

    public Object[] difficulty6(Map<String, Object> parameters) {
        return QuestionBuilder.generatePemdas(parameters);
    }

    /**
     * Returns an array containing a random question in slot 0 and its answer in slot 1
     * The integer argument difficulty must be provided to generate a question
     * @param difficulty an integer that specifies the difficulty of the question
     * @return an array containing a random question (String) in slot 0 and its answer (int) in slot 1
     */
     public Object[] generateQuestion(int difficulty) {
        // parameters is a map of the name and value of the parameters for each difficulty
        Map<String, Object> parameters = DIFFICULTY_PARAMETERS.get(difficulty);

        switch(difficulty) {
            case 1:
                return difficulty1(parameters);
            case 2:
                return difficulty2(parameters);
            case 3:
                return difficulty3(parameters);
            case 4:
                return difficulty4(parameters);
            case 5:
                return difficulty5(parameters);
            case 6:
                return difficulty6(parameters);
            default:
                return new Object[]{"INVALID DIFFICULTY - What is 1+1? :)", 2};
        }


    }


}
