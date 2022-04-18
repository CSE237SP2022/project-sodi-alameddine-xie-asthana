package guessinggame;

public class Configuration {
    private Configuration() {}

    public static class Hyperparameters {
        private Hyperparameters() {}
        public static final int NUMBER_OF_QUESTIONS = 5;
        public static final int TIME_LIMIT = 300; //300 seconds = 5 mins
        public static final int MAX_DIFFICULTY = 3;
    }

    public static class ExitCodes {
        private ExitCodes() {}

        public static final int PLAYER_QUIT_GAME = 1;
        public static final int GAME_COMPLETE = 2;
    }

}
