package org.example.game;

import org.example.word.TextValidator;
import org.example.word.WordRepository;

import java.util.Scanner;

public class GameRunner {
    private static final String CMD_NEW_GAME = "1";
    private static final String CMD_EXIT = "2";

    private final Scanner scanner;
    private final WordRepository wordRepository;
    private final TextValidator textValidator;

    public GameRunner(Scanner scanner, WordRepository wordRepository, TextValidator textValidator) {
        this.scanner = scanner;
        this.wordRepository = wordRepository;
        this.textValidator = textValidator;
    }

    public void start() {
        while (true) {
            printMainMenu();
            String line = scanner.nextLine().trim();

            if (line.equals(CMD_NEW_GAME)) {
                playRound();
                continue;
            }
            if (line.equals(CMD_EXIT)) {
                System.out.println("пока пока");
                break;
            }
            System.out.println("некорректное значение");
        }
    }

    private void playRound() {
        System.out.println("играем");
        System.out.println("-------------------------------------------------------------");
        Game game = new Game(wordRepository.getRandomWord());
        do {
            MoveResult result = game.playerMove(getPlayerMove());
            printMoveResult(game, result);
        } while (game.canContinue());
        printRoundResult(game);
    }

    private char getPlayerMove() {
        while (true) {
            System.out.println("введите букву:");
            String playerInput = scanner.nextLine().trim().toLowerCase();
            if (textValidator.isSingleLetterValid(playerInput)) {
                return playerInput.charAt(0);
            }
            System.out.println("некорректное значение, введите ровно одну русскую букву");
        }
    }

    private void printRoundResult(Game game) {
        switch (game.gameOver()) {
            case WON -> System.out.println("победа");
            case LOST -> System.out.println("поражение, загаданое слово было: " + game.getSecretWord());
        }
    }

    private void printMainMenu() {
        System.out.println("-------------------------------------------------------------" +
                "\nПривет, хочешь сыграть в виселицу? введи:\n" +
                CMD_NEW_GAME + " - если хочешь начать новую игру\n" +
                CMD_EXIT + " - если хочешь выйти из приложения");
    }

    private void printMoveResult(Game game, MoveResult moveResult) {
        switch (moveResult) {
            case CORRECT -> System.out.println("правильно");
            case WRONG -> System.out.println("неправильно");
            case ALREADY_USED -> System.out.println("эта буква уже была");
        }
        System.out.println("загаданное слово: " + game.getRevealedLetters()
                + "\nуже использованные буквы: " + game.getEnteredLetters()
                + "\nколичество ошибок: " + game.getCountWrongGuesses() + " из " + game.getMaxWrongGuesses()
                + HangmanState.fromMistakes(game.getCountWrongGuesses())
                + "\n-------------------------------------------------------------");
    }
}