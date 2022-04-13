import java.util.Map;

import static java.util.Map.entry;

public class Question {
    public String text;
    public int answer;

    // difficultyParameters is a map that contains the relevant parameters for each difficulty.
    // This way there are no magic numbers and it's easier to modify values for balance
    // Each difficulty (integer) is mapped to another map which contains the names and values of the parameters for that difficulty
    private final Map<Integer, Map<String, Object>> DIFFICULTY_PARAMETERS = Map.ofEntries(
            entry(0, Map.of("Range", 100))
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

        //difficulty 0 generates two random integers - summand1 and summand2 - and asks for their sum
        if (difficulty == 0) {
            int range = (int) parameters.get("Range");
            int summand1 = (int)(Math.random()*range+1), summand2 = (int)(Math.random()*range+1);
            text = "What is " + summand1 + " + " + summand2 + "?";
            answer = summand1 + summand2;
        }

        return new Object[]{text, answer};
    }
}
