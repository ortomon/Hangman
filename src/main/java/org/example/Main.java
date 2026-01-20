package org.example;

import org.example.game.GameRunner;
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
        GameRunner gameRunner = new GameRunner(scanner, wordRepository, letterValidator);
        gameRunner.start();
    }
}
