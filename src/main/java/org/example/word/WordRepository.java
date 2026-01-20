package org.example.word;

import org.example.validator.WordTextValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordRepository {
    private List<String> words = new ArrayList<>();
    private final Random random = new Random();

    public WordRepository(String fileName, WordTextValidator wordValidator) {
        this.words = filterWords(readLinesFromFile(fileName), wordValidator);
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    private List<String> filterWords(List<String> lines, WordTextValidator wordValidator) {
        words = lines.stream()
                .map(word -> word.trim().toLowerCase())
                .filter(word -> wordValidator.isValid(word))
                .toList();
        if (words.isEmpty()) {
            throw new RuntimeException("No words available for the game");
        }
        return words;
    }

    private List<String> readLinesFromFile(String fileName) {
        InputStream text = WordRepository.class.getResourceAsStream(fileName);
        if (text == null) {
            throw new RuntimeException("Word file not found: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(text))) {
            return reader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException("Error reading words from file: ", e);
        }
    }
}
