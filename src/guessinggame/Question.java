package guessinggame;

import java.util.Map;

import static java.util.Map.entry;

public class Question {
    public String text;
    public int answer;

    // difficultyParameters is a map that contains the relevant parameters for each difficulty.
    // This way there are no magic numbers and it's easier to modify values for balance
    // Each difficulty (integer) is mapped to a submap which contains the names and values of the parameters for that difficulty
    private final Map<Integer, Map<String, Object>> DIFFICULTY_PARAMETERS = Map.ofEntries(
            entry(1, Map.of("AdditionRange", 100, "LowerBound", 1)),
            entry(2, Map.of("AdditionRange", 250, "SubtractionRange", 200)),
            entry(3, Map.of("AdditionRange", 2500, "SubtractionRange", 2000, "MultiplicationRange", 25)),
            entry(4, Map.of("SubtractionRange", 25000, "MultiplicationRange", 50, "DivisionRange", 20)),
            entry(5, Map.of("SubtractionRange", 1000000, "MultiplicationRange", 100,
                    "DivisionRange", 40, "ExponentBaseRange", 25, "ExponentPowerRange", 3)),
            entry(6, Map.of("MultiplicationRange", 250, "DivisionRange", 100,
                    "ExponentBaseRange", 30, "ExponentPowerRange", 4))
    );


    public Question(int difficulty) {
        Object[] question = generateQuestion(difficulty);
        text = (String) question[0];
        answer = (int) question[1];
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
                return QuestionBuilder.generateTrivial(parameters);
            case 2:
                return QuestionBuilder.generateEasy(parameters);
            case 3:
                return QuestionBuilder.generateMedium(parameters);
            case 4:
                return QuestionBuilder.generateHard(parameters);
            case 5:
                return QuestionBuilder.generateExpert(parameters);
            case 6:
                return QuestionBuilder.generateNightmare(parameters);
            default:
                return new Object[]{"QUESTION GENERATED WITH INVALID DIFFICULTY - What is 1+1? :)", 2};
        }


    }


}
