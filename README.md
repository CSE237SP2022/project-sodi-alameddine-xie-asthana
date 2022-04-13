# project-sodi-alameddine-xie-asthana
The goal of this project is to create a math-based guessing game of varying difficulty levels and with math-related hints.

To run the project, _______

Currently, the game works like this:

After running the program, you are asked to type in your name. You are then asked to choose your difficulty level. If difficulty is hard, you might get a problem like cos(π/67), and as you guess, you'll be told "warmer" or "colder" relative to the last guess. Conversely, if the difficulty is easy, you might get a problem like 8+99, with much more revealing hints, such as "the answer is equivalent to 50 + 57."
As of iteration 2, there are three implemented difficulties, with 0 being the easiest, 1 being in the middle, and 2 harder. In later iterations more difficulties will be added.

There is a points system with higher difficulties offering higher points multipliers. The maximum amount of points will be rewarded for guessing the answer in only one attempt and within ten seconds.

For a detailed explanation of the scoring system, including functions, graphs, and rationale, please view [this issue](https://github.com/CSE237SP2022/project-sodi-alameddine-xie-asthana/issues/4#issuecomment-1094389913)

There is a "leaderboard" file that keeps a record of every game ever played along with the user who played it. This local leaderboard keeps track of scores and difficulties too.

There is primitive multiplayer functionality. You can compete against your friends using the leaderboard.

For the next iteration, we intend to improve the leaderboard, add new difficulties and create more/better hints