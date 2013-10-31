project6
========

Evil space monster hangman is the most evil hangman possible, it features a spacemonster with 6 eyes. 
Each eye represents a letter. Because there are only 6 letters on the screen the possibility for a good guess
is higher. Don't forget to shake the phone to reset the letters.

features
--------

- The game of hangman
- A secretly cheating computer system
- Settings for changing gameplay modes, wordlength, amount of tries & the player name
- A big space monster with suggested letters for eyes
- Shake phone to reset letter eyes
- Shared Hi-scores

Frameworks Languages & libraries
--------------------------------

- java
- android sdk
- node.js for serverside

Models
------

- Hangman
    > hangman plays the game of hangman, the class is taken from the previous hangman project
    > the hangman class does the wordlist parse is now handled in a seperate class.

- EvilHangman (extends hangman)
    > evil hangman will extend the normal hangman class by adding a compare method that compares
    > the list to the currentWordState, currentWord will be replaced with the least likely thing
    > that the user can guess

- SpaceMonster
    > the space monster class will generate 6 random letters, the letters will allways include
    > one true letter. 
    > The spacemonster model might include features like: 
    > reducing the number of letters returned or returning the true answer. The features depend 
    > on the gameplay types I want to implement on the space monster with the time i have

   
- WordList
    > A seperate class that builds a List object from an sqlite database based off the wordlength

- HiScores
    > The HiScores class will connect to a seperate highscore server to get the hiscores and to
    > post them. it will also use the Accountmanager class to retrieve the username to use in
    > the hiscores.
    > the HiScores will return 10 values from the ladder with the current user allways in the
    > middle (so it will return 115 to 125 if the current user is in place 120)

wat daan zegt
-------------

- define fragment for spacemonster
- seperate the gameplay and the spacemonster fragment and communicate via the main activity

- the space monster keyboard will communicate with the spacemonster fragment


Screens & stuff
---------------

-   2 concept screens, the second mechanic will be implemented in the final game
    (shake to reset letters)  
![concepting.png](/doc/esmh-concepting.png)

- the first screen with a space monster and a concept i'm not going to implement
![concept.png](/doc/esmh-concept.png)

- screen of an expanded concept that i might implement
![expanded-concept.png](/doc/esmh-expanded.png)

Design doc
----------

https://moqups.com/svandenhout/kXOYuQ9v/
