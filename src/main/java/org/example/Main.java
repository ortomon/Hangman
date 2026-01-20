package org.example;

import org.example.game.GameRunner;
import org.example.game.HangmanRenderer;
import org.example.validator.LetterTextValidator;
import org.example.validator.WordTextValidator;
import org.example.word.WordRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character.UnicodeBlock allowedAlphabet = Character.UnicodeBlock.CYRILLIC;
        WordTextValidator wordValidator = new WordTextValidator(allowedAlphabet);
        LetterTextValidator letterValidator = new LetterTextValidator(allowedAlphabet);
        WordRepository wordRepository = new WordRepository("/words.txt", wordValidator);
        HangmanRenderer hangmanRenderer = new HangmanRenderer();
        GameRunner gameRunner = new GameRunner(scanner, wordRepository, letterValidator, hangmanRenderer);
        gameRunner.start();
    }
}
