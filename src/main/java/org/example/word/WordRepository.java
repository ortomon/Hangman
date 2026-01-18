package org.example.word;

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

    public WordRepository(String fileName, TextValidator textValidator) {
        this.words = filterWords(readLinesFromFile(fileName), textValidator);
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    private List<String> filterWords(List<String> lines, TextValidator textValidator) {
        words = lines.stream()
                .map(word -> textValidator.toCanonicalForm(word))
                .filter(word -> textValidator.isWordValid(word))
                .toList();
        if (words.isEmpty()) {
            throw new RuntimeException("нет слов для игры");
        }
        return words;
    }

    private List<String> readLinesFromFile(String fileName) {
        InputStream text = WordRepository.class.getResourceAsStream(fileName);
        if (text == null) {
            throw new RuntimeException("файл со словами не найден: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(text))) {
            return reader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException("ошибка чтения слов из файла ", e);
        }
    }
}
