package org.example.game;

import java.util.Scanner;

public class Menu {
    private static final String CMD_NEW_GAME = "1";
    private static final String CMD_EXIT = "2";
    private final Scanner scanner;
    private final GameRunner gameRunner;

    public Menu(Scanner scanner, GameRunner gameRunner) {
        this.scanner = scanner;
        this.gameRunner = gameRunner;
    }

    public void start() {
        while (true) {
            printMainMenu();
            String line = scanner.nextLine().trim();

            if (line.equals(CMD_NEW_GAME)) {
                gameRunner.playRound();
                continue;
            }
            if (line.equals(CMD_EXIT)) {
                System.out.println("пока пока");
                break;
            }
            System.out.println("некорректное значение");
        }
    }

    private void printMainMenu() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Привет, хочешь сыграть в виселицу? введи:");
        System.out.println(CMD_NEW_GAME + " - если хочешь начать новую игру");
        System.out.println(CMD_EXIT + " - если хочешь выйти из приложения");
    }
}
