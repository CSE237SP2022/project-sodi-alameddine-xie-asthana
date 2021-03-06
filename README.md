# Guessing Game
A math-based guessing game of varying difficulty levels


**How To Run Guessing Game**

To run the project, first download it using git or by clicking the green code button then clicking `Download ZIP` in the dropdown.

Navigate into the folder you just downloaded, `project-sodi-alameddine-xie-asthana`, using terminal.

Finally, enter the following command:
```
bash run.sh
```

**How The Game Works**

After running the program, you are asked to type in your name. You are then asked to choose your difficulty level. If difficulty is hard, you might get a problem involving division and exponents. Conversely, if the difficulty is easy, you might get a problem like 8+99

There are 6 difficulties to choose from:
* Trivial
* Easy
* Medium
* Hard
* Expert
* Nightmare

If you fancy yourself good at math, give Nightmare a shot! 

Otherwise, if math is the bane of your existence, you should be just fine with trivial.

**Scoring**

There is a points system with higher difficulties offering higher points multipliers. The maximum amount of points will be rewarded for guessing the answer in only one attempt and within ten seconds.

For a detailed explanation of the scoring system, see below

There is a leaderboard file that keeps a record of every game ever played along with the user who played it. This local leaderboard keeps track of scores and difficulties too.

There is primitive multiplayer functionality. You can compete against your friends using the leaderboard.


**How Scoring Works**

points based on attempts taken: 1 attempt taken will reward full 100% of the points. As attempts grows, points are subtracted more quickly at first then slowly. As attempts goes to ∞, 25% points are rewarded. Function a(x) will keep track of this for x ε [1, ∞) ∩ x ε Z

a(x) = 25 + 75/x

<img width="713" alt="Screen Shot 2022-04-10 at 6 26 15 PM" src="https://user-images.githubusercontent.com/7902589/162644417-56bb8c18-d529-4cd6-8f28-2306bcb7db6f.png">


points based on time taken: anything 10 seconds or under will reward full 100% points. After 2 minutes have passed, only 50% points will be rewarded. Function t(x) will keep track of this for x ε [0, ∞)

<img width="316" alt="Screen Shot 2022-04-10 at 6 23 25 PM" src="https://user-images.githubusercontent.com/7902589/162644309-6c0a6643-587b-48e1-9fb9-30873457482e.png">
<img width="727" alt="Screen Shot 2022-04-10 at 6 26 55 PM" src="https://user-images.githubusercontent.com/7902589/162644441-82cb48c3-a754-40e9-b5fe-d78f1fe0d195.png">



Points for 1 question = a(attempts taken) • t(time taken)
Total points = sum(points for each question) • diffMultiplier

diffMultiplier is a preset value based on difficulty
