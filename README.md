# project-sodi-alameddine-xie-asthana
project-sodi-alameddine-xie-asthana created by GitHub Classroom

This is a guessing game with math-related hints, of varying difficulty levels.

For Iteration 1, we're just getting the guessing game itself working, with very simple math hints.

The game will work like this:
You choose your difficulty level. If difficulty is hard, you might get a problem like cos(π/67), and as you guess, you'll be told "warmer" or "colder" relative to the last guess. Conversely, if the difficulty is easy, you might get a problem like 3sqrt(81), with much more revealing hints, such as distance away from the answer

There will be a points system, with higher difficulties offering higher points multipliers. The maximum amount of points will be rewarded for guessing the answer in only one attempt. As the amount of attempts goes to ∞, points rewarded will approach only 33% of the maximum (subject to change, may depend on difficulty too).

There will be an output file that keeps a record of every game ever played (unless the user chooses to turn this off), and high scores will be accessable from the program itself (by reading this file).

There will be an extra hints feature, with extra hints earned by completing the game on higher difficulties. As of now, we only plan for a single user, but if time permits, we may add additional functionality to allow users to compete by switching who's playing and keeping track of it.

For the next iteration, we intend to implement the ability to choose other difficulties, and if we have the time, the other difficulties themselves. (If we're unable to implement the other difficulties, the same kind of hints from the first difficulty will be used.)

To use:
In folder, use these commands from terminal

```
javac *.java
Java Guessing_Game.java
```

