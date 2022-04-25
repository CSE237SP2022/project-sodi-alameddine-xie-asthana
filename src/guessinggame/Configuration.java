package guessinggame;

public class Configuration {
    private Configuration() {}

    public static class Hyperparameters {
        private Hyperparameters() {}
        public static final int NUMBER_OF_QUESTIONS = 5;
        public static final int MAX_DIFFICULTY = 6;
    }

    public static class ExitCodes {
        private ExitCodes() {}

        public static final int PLAYER_QUIT_GAME = 1;
        public static final int GAME_COMPLETE = 2;

        public static final int GAME_COMPLETE_WITHOUT_LEADERBOARD = 3;
    }

}
