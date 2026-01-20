package org.example.game;

import java.util.*;

public class Game {
    private static final int MAX_WRONG_GUESSES = 6;
    private static final char MASK_SYMBOL = '*';

    private int countWrongGuesses;
    private final String secretWord;
    private final List<Character> secretLetters = new ArrayList<>();
    private final List<Character> revealedLetters = new ArrayList<>();
    private final Set<Character> enteredLetters = new LinkedHashSet<>();

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
        }
        countWrongGuesses++;
        return MoveResult.WRONG;
    }

    public boolean isGameOver() {
        return isLose() || isWon();
    }

    public boolean isLose() {
        return countWrongGuesses >= MAX_WRONG_GUESSES;
    }

    public boolean isWon() {
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