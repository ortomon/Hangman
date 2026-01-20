package org.example.game;

import org.example.validator.LetterTextValidator;
import org.example.view.HangmanRenderer;
import org.example.word.WordRepository;

import java.util.Scanner;

public class GameRunner {
    private final Scanner scanner;
    private final WordRepository wordRepository;
    private final LetterTextValidator letterValidator;
    private final HangmanRenderer hangmanRenderer;

    public GameRunner(Scanner scanner, WordRepository wordRepository,
                      LetterTextValidator letterValidator, HangmanRenderer hangmanRenderer) {
        this.scanner = scanner;
        this.wordRepository = wordRepository;
        this.letterValidator = letterValidator;
        this.hangmanRenderer = hangmanRenderer;
    }

    public void playRound() {
        System.out.println("играем");
        System.out.println("-------------------------------------------------------------");
        Game game = new Game(wordRepository.getRandomWord());
        do {
            MoveResult result = game.playerMove(getPlayerMove());
            printMoveResult(game, result);
        } while (!game.isGameOver());
        printRoundResult(game);
    }

    private char getPlayerMove() {
        while (true) {
            System.out.println("введите букву:");
            String playerInput = scanner.nextLine().trim().toLowerCase();
            if (playerInput.length() == 1 && letterValidator.isValid(playerInput.charAt(0))) {
                return playerInput.charAt(0);
            }
            System.out.println("некорректное значение, введите ровно одну букву");
        }
    }

    private void printRoundResult(Game game) {
        if (game.isLose()) {
            System.out.println("победа");
        }
        if (game.isWon()) {
            System.out.println("поражение, загаданое слово было: " + game.getSecretWord());
        }
    }

    private void printMoveResult(Game game, MoveResult moveResult) {
        switch (moveResult) {
            case CORRECT -> System.out.println("правильно");
            case WRONG -> System.out.println("неправильно");
            case ALREADY_USED -> System.out.println("эта буква уже была");
        }
        System.out.println("загаданное слово: " + game.getRevealedLetters());
        System.out.println("уже использованные буквы: " + game.getEnteredLetters());
        System.out.printf("количество ошибок: %d из %d  %n", game.getCountWrongGuesses(), game.getMaxWrongGuesses());
        hangmanRenderer.render(game.getCountWrongGuesses());
        System.out.println("-------------------------------------------------------------");
    }
}