# Guessing Game
The goal of this project is to create a math-based guessing game of varying difficulty levels and with math-related hints.

To run the project, _______

Currently, the game works like this:

After running the program, you are asked to type in your name. You are then asked to choose your difficulty level. If difficulty is hard, you might get a problem like cos(π/67), and as you guess, you'll be told "warmer" or "colder" relative to the last guess. Conversely, if the difficulty is easy, you might get a problem like 8+99, with much more revealing hints, such as "the answer is equivalent to 50 + 57."
As of iteration 2, there are three implemented difficulties, with 0 being the easiest, 1 being in the middle, and 2 harder. In later iterations more difficulties will be added.

There is a points system with higher difficulties offering higher points multipliers. The maximum amount of points will be rewarded for guessing the answer in only one attempt and within ten seconds.

For a detailed explanation of the scoring system, see below

There is a "leaderboard" file that keeps a record of every game ever played along with the user who played it. This local leaderboard keeps track of scores and difficulties too.

There is primitive multiplayer functionality. You can compete against your friends using the leaderboard.

For the next iteration, we intend to improve the leaderboard, add new difficulties and create more/better hints


**Here's how scoring works in detail:**

points based on attempts taken: 1 attempt taken will reward full 100% of the points. As attempts grows, points are subtracted more quickly at first then slowly. As attempts goes to ∞, 25% points are rewarded. Function a(x) will keep track of this for x ε [1, ∞) ∩ x ε Z
a(x) = 25 + 75/x
<img width="713" alt="Screen Shot 2022-04-10 at 6 26 15 PM" src="https://user-images.githubusercontent.com/7902589/162644417-56bb8c18-d529-4cd6-8f28-2306bcb7db6f.png">


points based on time taken: anything 10 seconds or under will reward full 100% points. After 2 minutes have passed, only 50% points will be rewarded. Function t(x) will keep track of this for x ε [0, ∞)

<img width="316" alt="Screen Shot 2022-04-10 at 6 23 25 PM" src="https://user-images.githubusercontent.com/7902589/162644309-6c0a6643-587b-48e1-9fb9-30873457482e.png">
<img width="727" alt="Screen Shot 2022-04-10 at 6 26 55 PM" src="https://user-images.githubusercontent.com/7902589/162644441-82cb48c3-a754-40e9-b5fe-d78f1fe0d195.png">



Points for 1 question = a(attempts taken) • t(time taken)
Total points = sum(points for each question) • diffMultiplier

diffMultiplier is a preset value based on difficulty



In order to run this program, do the following inside of the guessinggame folder inside of src:

javac -d . *.java

java GuessingGame.java
