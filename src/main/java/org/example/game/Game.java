package org.example.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private static final int MAX_WRONG_GUESSES = 6;
    private static final char MASK_SYMBOL = '*';

    private int countWrongGuesses;
    private final String secretWord;
    private final List<Character> secretLetters = new ArrayList<Character>();
    private final List<Character> revealedLetters = new ArrayList<Character>();
    private final Set<Character> enteredLetters = new HashSet<Character>();

    public Game(String secretWord) {
        this.secretWord = secretWord;
        initializeRevealedLetters();
    }

    public MoveResult playerMove(char guessLetter) {
        if (enteredLetters.contains(guessLetter)) {
            return MoveResult.ALREADY_USED;
        }

        enteredLetters.add(guessLetter);
        if (secretLetters.contains(guessLetter)) {
            openLetter(guessLetter);
            return MoveResult.CORRECT;
        } else {
            countWrongGuesses++;
            return MoveResult.WRONG;
        }
    }

    public GameState gameOver() {
        if (isWon()) {
            return GameState.WON;
        } else {
            return GameState.LOST;
        }
    }

    public boolean canContinue() {
        return countWrongGuesses < MAX_WRONG_GUESSES && !isWon();
    }

    private boolean isWon() {
        return !revealedLetters.contains(MASK_SYMBOL);
    }

    private void openLetter(char guessLetter) {
        for (int i = 0; i < secretLetters.size(); i++) {
            if (guessLetter == secretLetters.get(i)) {
                revealedLetters.set(i, guessLetter);
            }
        }
    }

    private void initializeRevealedLetters() {
        for (int i = 0; i < secretWord.length(); i++) {
            secretLetters.add(secretWord.charAt(i));
            revealedLetters.add(MASK_SYMBOL);
        }
    }

    public int getCountWrongGuesses() {
        return countWrongGuesses;
    }

    public Set<Character> getEnteredLetters() {
        return enteredLetters;
    }

    public List<Character> getRevealedLetters() {
        return revealedLetters;
    }

    public int getMaxWrongGuesses() {
        return MAX_WRONG_GUESSES;
    }

    public String getSecretWord() {
        return secretWord;
    }
}