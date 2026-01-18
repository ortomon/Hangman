package org.example;

import org.example.game.GameRunner;
import org.example.word.TextValidator;
import org.example.word.WordRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextValidator textValidator = new TextValidator(Character.UnicodeBlock.CYRILLIC);
        WordRepository wordRepository = new WordRepository("/words.txt", textValidator);
        GameRunner gameRunner = new GameRunner(scanner, wordRepository, textValidator);
        gameRunner.start();
    }
}
